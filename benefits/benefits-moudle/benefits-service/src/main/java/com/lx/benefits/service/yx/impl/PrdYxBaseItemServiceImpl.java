package com.lx.benefits.service.yx.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lx.benefits.bean.dto.yx.PrdYxBaseItemDto;
import com.lx.benefits.bean.dto.yx.PrdYxBaseItemReq;
import com.lx.benefits.bean.dto.yx.YXItemSkuSpec;
import com.lx.benefits.bean.dto.yx.YxSkuImportReq;
import com.lx.benefits.bean.entity.product.ProductEntity;
import com.lx.benefits.bean.entity.product.SkuEntity;
import com.lx.benefits.bean.entity.yx.PrdYxBaseItem;
import com.lx.benefits.bean.entity.yx.PrdYxBaseItemSku;
import com.lx.benefits.bean.util.*;
import com.lx.benefits.mapper.yx.PrdYxBaseItemMapper;
import com.lx.benefits.mapper.yx.PrdYxBaseItemSkuMapper;
import com.lx.benefits.sdk.supplier.BaseSupplierInfo;
import com.lx.benefits.service.product.ProductService;
import com.lx.benefits.service.product.SkuService;
import com.lx.benefits.service.yx.PrdYxBaseItemService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: fan
 * Date: 2019/02/28
 * Time: 00:36
 */
@Service
public class PrdYxBaseItemServiceImpl extends BaseSupplierInfo implements PrdYxBaseItemService {

    Logger logger = LoggerFactory.getLogger(PrdYxBaseItemServiceImpl.class);

    @Autowired
    PrdYxBaseItemMapper itemMapper;

    @Autowired
    PrdYxBaseItemSkuMapper skuMapper;

    @Autowired
    ProductService productService;

    @Autowired
    SkuService skuService;

    @Override
    public JSONObject getYxItemList(PrdYxBaseItemReq record) {
        record.setPage(record.getPage() > 0 ? (record.getPage() - 1) * record.getPageSize() : 0);
        JSONObject jsonObject = new JSONObject();
        List<PrdYxBaseItem> list = itemMapper.getYxItemList(record);
        List<PrdYxBaseItemDto> plist = new ArrayList<>();
        for (PrdYxBaseItem item : list) {
            PrdYxBaseItemDto dto = BeansUtils.copyProperties(item, PrdYxBaseItemDto.class);
            dto.setSkuList(skuMapper.getSku(item.getId().toString()));
            plist.add(dto);
        }
        jsonObject.put("list", plist);
        jsonObject.put("count", itemMapper.getYxItemListCount(record));
        return jsonObject;
    }

    @Override
    public JSONObject goodsImport(YxSkuImportReq record) {
        List<PrdYxBaseItem> list = itemMapper.getInfoBySku(record.getSku());
        for (PrdYxBaseItem item : list) {
            try {
                ProductEntity productEntity = BeansUtils.copyProperties(item, ProductEntity.class);
                productEntity.setBrandId(item.getBrandId() == null ? 0L : item.getBrandId().longValue());
                productEntity.setCategoryId(item.getCategoryId() == null ? 0L : item.getCategoryId().longValue());
                productEntity.setCategoryId2(item.getCategoryId2() == null ? 0L : item.getCategoryId2().longValue());
                productEntity.setCategoryId3(item.getCategoryId3() == null ? 0L : item.getCategoryId3().longValue());
                productEntity.setSupplierId(item.getSupplierId() == null ? 0L : item.getSupplierId().longValue());
                productEntity.setPlaceOriginId(item.getPlaceOriginid() == null ? 0L : item.getPlaceOriginid().longValue());
                productEntity.setSpuCode(null);
                productEntity.setSkuCode(item.getSkuCode());
                productEntity.setCreatedTime(new Date());
                productEntity.setIntroduction(item.getDetailHtml());
                saveGoods(productEntity, item.getId().toString());
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        return Response.succ();
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveGoods(ProductEntity productEntity, String itemId) {
        ProductEntity entity = productService.getProductBySkuCode(productEntity.getSkuCode(), Integer.valueOf(YX_ID.toString()));
        if (entity != null) {
            if (entity.getIsEffect() == 1 || (entity.getGjValidStarttime() != null
                    && entity.getGjValidEndtime() != null && DateUtil.isInRange(entity.getGjValidStarttime(), entity.getGjValidEndtime()))) {
                return;
            }
            productEntity.setSpuCode(entity.getSpuCode());
            productEntity.setUpdatedTime(new Date());
            productService.update(productEntity);
        } else {
            productEntity.setCreatedTime(new Date());
            productService.insert(productEntity);
        }
        List<PrdYxBaseItemSku> skuList = skuMapper.getSku(itemId);
        for (PrdYxBaseItemSku itemSku : skuList) {
            SkuEntity sku = skuService.selectBySku(itemSku.getSkuCode(), productEntity.getSupplierName());
            SkuEntity skuEntity = BeansUtils.copyProperties(itemSku, SkuEntity.class);
            skuEntity.setSpuCode(productEntity.getSpuCode());
            skuEntity.setGoodsRate(CalRateUtil.calRate(skuEntity.getGoodsPrice(), null, skuEntity.getGoodsCostprice()));
            // 折扣
            BigDecimal discount = skuEntity.getGoodsPrice().divide(skuEntity.getGoodsMarkprice(), 2, RoundingMode.HALF_UP);
            skuEntity.setGoodsDiscount(discount);

            if (null != sku) {
                skuEntity.setId(sku.getId());
                skuEntity.setUpdatedTime(new Date());
                skuService.update(skuEntity);
            } else {
                skuEntity.setId(null);
                skuEntity.setCreatedTime(new Date());
                skuService.insert(skuEntity);
            }
        }
    }

}
