package com.lx.benefits.service.yx.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lx.benefits.bean.dto.yx.*;
import com.lx.benefits.bean.dto.yx.result.YXResult;
import com.lx.benefits.bean.dto.yx.utils.ListUtil;
import com.lx.benefits.bean.entity.basedistrictinfo.DistrictInfo;
import com.lx.benefits.bean.entity.product.CategoryEntity;
import com.lx.benefits.bean.entity.yx.PrdYxBaseItem;
import com.lx.benefits.bean.entity.yx.PrdYxBaseItemSku;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.RetryUtil;
import com.lx.benefits.mapper.basedistrictinfo.DistrictInfoMapper;
import com.lx.benefits.mapper.product.CategoryMapper;
import com.lx.benefits.mapper.supplierInfo.SupplierInfoMapper;
import com.lx.benefits.mapper.yx.PrdYxBaseItemMapper;
import com.lx.benefits.mapper.yx.PrdYxBaseItemSkuMapper;
import com.lx.benefits.service.yx.IYXItemService;
import com.lx.benefits.service.yx.YXBaseService;
import com.lx.benefits.service.yx.YXItemInventory;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * User: fan
 * Date: 2019/02/27
 * Time: 16:54
 */
@Service
public class YXItemServiceImpl extends YXBaseService implements IYXItemService {

    private static final Logger logger = LoggerFactory.getLogger(YXItemServiceImpl.class);

    @Autowired
    YXItemInventory yxItemInventory;

    @Autowired
    PrdYxBaseItemMapper itemMapper;

    @Autowired
    PrdYxBaseItemSkuMapper skuMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    SupplierInfoMapper supplierInfoMapper;

    @Autowired
    DistrictInfoMapper districtInfoMapper;

    @Override
    public YXResult<List<Long>> getItemIds() {
        Map<String, String> param = new HashMap<>();
        String res = handle(param, getItemGetIds());
        logger.info("YX_GET_ITEM_IDS_RESULT:" + res);
        if (StringUtils.isBlank(res)) {
            logger.error("YX_GET_ITEM_IDS_ERROR:" + res);
            RetryUtil.setDelayRetryTimes(3, 1200).retry();
        }
        YXResult<List<Long>> result = JSON.parseObject(res, new TypeReference<YXResult<List<Long>>>() {
        });
        if (result.getCode() == null || result.getCode() != 200) {
            logger.error("YX_GET_ITEM_IDS_ERROR:" + res);
            RetryUtil.setDelayRetryTimes(3, 1200).retry();
        }
        return result;
    }

    @Override
    public JSONObject getYxItem(List<Long> subIds) {
        JSONObject jsonObject = new JSONObject();
        YXResult<List<YXItem>> itemResult = getItemByIds(subIds);
        jsonObject.put("itemResult", itemResult);
        if (itemResult != null && itemResult.success() && itemResult.getResult() != null) {
            List<YXItem> items = itemResult.getResult();
            List<YXItemInventoryDto> itemInventories = getYxItemInventories(items);
            jsonObject.put("Inventories", itemInventories);
        }
        return Response.succ(jsonObject);
    }

    @Override
    public void synItem(List<Long> ids) throws InterruptedException {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        itemMapper.delete();
        skuMapper.delete();
        logger.info("YX_SYN_ITEM_TOTAL={}", ids.size());
        List<List<Long>> separateIds = ListUtil.separateList(ids, GET_ITEM_DETAIL_LIMIT);
        for (List<Long> subIds : separateIds) {
            try {
                YXResult<List<YXItem>> itemResult = getItemByIds(subIds);
                if (!itemResult.success()) {
                    logger.error("YX_GET_ITEM_BY_IDS_ERROR");
                    continue;
                }
                List<YXItem> items = itemResult.getResult();
                if (CollectionUtils.isEmpty(items)) {
                    continue;
                }
                List<YXItemInventoryDto> itemInventories = getYxItemInventories(items);
                if (itemInventories == null || itemInventories.size() == 0) {
                    logger.error("YX_GET_ITEM_BY_IDS_ERROR:库存数据为空");
                    continue;
                }
                Map<String, Long> map = inventoriesToMap(itemInventories);
                for (YXItem yxItem : items) {
                    PrdYxBaseItem baseItem = new PrdYxBaseItem();
                    baseItem.setSkuCode(yxItem.getPrimarySkuId().toString());
                    baseItem.setBrandId(0);
                    baseItem.setBrandName(YX_NAME);
                    setCategory(baseItem, yxItem.getCategoryPathList());
                    baseItem.setSupplierId(Integer.valueOf(YX_ID.toString()));
                    baseItem.setSupplierName(YX_NAME);
                    baseItem.setGoodsName(yxItem.getName());
                    baseItem.setGoodsNameEn("");
                    baseItem.setGoodsBody(yxItem.getSimpleDesc());
                    baseItem.setGoodsImage(yxItem.getPrimaryPicUrl());
                    DistrictInfo districtInfo = districtInfoMapper.getInfoByName(yxItem.getItemDetail().getOriginCountryName());
                    if (districtInfo != null) {
                        baseItem.setPlaceOriginid(Integer.valueOf(districtInfo.getId().toString()));
                    } else {
                        baseItem.setPlaceOriginid(0);
                    }
                    baseItem.setPlaceOrigin(yxItem.getItemDetail().getOriginCountryName());
                    baseItem.setCreatedTime(new Date());
                    baseItem.setCountrysize(yxItem.getItemDetail().getOriginCountryName());
                    baseItem.setGoodsImages(JSON.toJSONString(getImage(yxItem.getItemDetail())));
                    baseItem.setDetailHtml(yxItem.getItemDetail().getDetailHtml());
                    Integer row = itemMapper.insertSelective(baseItem);
                    if (row < 1) {
                        continue;
                    }
                    for (YXItemSKU sku : yxItem.getSkuList()) {
                        PrdYxBaseItemSku itemSku = new PrdYxBaseItemSku();
                        itemSku.setSpuCode(baseItem.getId());
                        itemSku.setSkuCode(sku.getId().toString());
                        itemSku.setGoodsSpec(sku.getDisplayString() == null ? "" : sku.getDisplayString());
                        Long count = map.get(sku.getId().toString());
                        itemSku.setGoodsStorge(count != null ? Integer.valueOf(count.toString()) : 0);
                        itemSku.setGoodsImage(sku.getPicUrl());
                        itemSku.setCreatedTime(new Date());
                        // 成本价 = 渠道售价
                        itemSku.setGoodsCostprice(new BigDecimal("0"));
                        // 市场价 = 基准价
                        itemSku.setGoodsMarkprice(new BigDecimal(sku.getYanxuanPrice().toString()));
                        // 售价 = 渠道售价
                        itemSku.setGoodsPrice(new BigDecimal(sku.getChannelPrice().toString()));
                        itemSku.setLength(sku.getSkuDetailTO().getLength() != null ? new BigDecimal(sku.getSkuDetailTO().getLength()) : null);
                        itemSku.setWidth(sku.getSkuDetailTO().getWidth() != null ? new BigDecimal(sku.getSkuDetailTO().getWidth()) : null);
                        itemSku.setHeight(sku.getSkuDetailTO().getHeight() != null ? new BigDecimal(sku.getSkuDetailTO().getHeight()) : null);
                        itemSku.setItemSkuSpecValueList(JSON.toJSONString(sku.getItemSkuSpecValueList()));
                        skuMapper.insertSelective(itemSku);
                    }
                }
                //严选限制：一分钟的时间段内（开始时间任意）请求不能超过70次,所以sleep 1s
                Thread.sleep(1200);
            } catch (Exception e) {
                logger.error("error={}", e.getMessage(), e);
            }
        }
    }

    public List<String> getImage(YXItemDetail detail) {
        List<String> images = new ArrayList<>();
        if (StringUtils.isNotBlank(detail.getPicUrl1())) {
            images.add(detail.getPicUrl1());
        }
        if (StringUtils.isNotBlank(detail.getPicUrl2())) {
            images.add(detail.getPicUrl2());
        }
        if (StringUtils.isNotBlank(detail.getPicUrl3())) {
            images.add(detail.getPicUrl3());
        }
        if (StringUtils.isNotBlank(detail.getPicUrl4())) {
            images.add(detail.getPicUrl4());
        }
        return images;
    }


    public Map<String, Long> inventoriesToMap(List<YXItemInventoryDto> itemInventories) {
        Map<String, Long> map = new HashMap<>();
        if (itemInventories == null) {
            return map;
        }
        for (YXItemInventoryDto item : itemInventories) {
            map.put(item.getSkuId(), item.getInventory());
        }
        return map;
    }

    public void setCategory(PrdYxBaseItem baseItem, List<List<YXCategory>> categoryList) {
        if (categoryList == null || categoryList.size() == 0) {
            return;
        }
        // 一级默认严选
        baseItem.setCategoryId(YX_ID.intValue());
        baseItem.setCategoryName(YX_NAME);
        List<YXCategory> category = categoryList.get(0);
        if (category.size() > 0) {
            if (StringUtils.isBlank(category.get(0).getName())) {
                return;
            }
            CategoryEntity entity = categoryMapper.queryByNameAndPid(category.get(0).getName(), baseItem.getCategoryId().longValue());
            if (entity == null) {
                entity = addCategory(category.get(0).getName(), baseItem.getCategoryId().longValue());
            }
            baseItem.setCategoryId2(entity.getId().intValue());
            baseItem.setCategoryName2(category.get(0).getName());
        }
        if (category.size() > 1) {
            if (StringUtils.isBlank(category.get(1).getName())) {
                return;
            }
            CategoryEntity entity = categoryMapper.queryByNameAndPid(category.get(1).getName(), baseItem.getCategoryId2().longValue());
            if (entity == null) {
                entity = addCategory(category.get(1).getName(), baseItem.getCategoryId2().longValue());
            }
            baseItem.setCategoryId3(entity.getId().intValue());
            baseItem.setCategoryName3(category.get(1).getName());
        }
    }

    public CategoryEntity addCategory(String name, Long pid) {
        CategoryEntity entity = new CategoryEntity(name, pid);
        categoryMapper.insert(entity);
        return entity;
    }

    public YXResult<List<YXItem>> getItemByIds(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return new YXResult<>(Collections.EMPTY_LIST);
        }
        Map<String, String> param = new HashMap<>();
        param.put("itemIds", getString(ids));
        String res = handle(param, getItemGetDetails());
        if (StringUtils.isBlank(res)) {
            logger.error("YX_GET_ITEM_BY_IDS_ERROR:  res={},ids={}", res, ArrayUtils.toString(ids));
            RetryUtil.setDelayRetryTimes(3, 1200).retry(ids);
        }
        YXResult<List<YXItem>> result = JSON.parseObject(res, new TypeReference<YXResult<List<YXItem>>>() {
        });
        if (!result.success()) {
            logger.error("YX_GET_ITEM_BY_IDS_ERROR: res={},ids={}", res, ArrayUtils.toString(ids));
            RetryUtil.setDelayRetryTimes(3, 1200).retry(ids);
        }
        return result;
    }

    private List<YXItemInventoryDto> getYxItemInventories(List<YXItem> items) {
        List<Long> skus = new ArrayList<>();
        for (YXItem yxItem : items) {
            for (YXItemSKU yxItemSKU : yxItem.getSkuList()) {
                skus.add(yxItemSKU.getId());
            }
        }
        List<List<Long>> sepSKUDIS = ListUtil.separateList(skus, GET_ITEM_DETAIL_LIMIT);
        List<YXItemInventoryDto> itemInventories = new ArrayList<>();
        for (List<Long> longs : sepSKUDIS) {
            YXResult<List<YXItemInventoryDto>> inRes = yxItemInventory.inventory(longs);
            if (inRes.success()) {
                itemInventories.addAll(inRes.getResult());
            }
        }
        return itemInventories;
    }

}
