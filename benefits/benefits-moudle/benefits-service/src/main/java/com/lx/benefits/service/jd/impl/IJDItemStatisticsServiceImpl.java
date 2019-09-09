package com.lx.benefits.service.jd.impl;

import com.alibaba.fastjson.JSON;
import com.lx.benefits.bean.dto.jd.*;
import com.lx.benefits.bean.entity.basedistrictinfo.DistrictInfo;
import com.lx.benefits.bean.entity.jd.PrdJdBaseItem;
import com.lx.benefits.bean.entity.product.BrandEntity;
import com.lx.benefits.bean.entity.product.CategoryEntity;
import com.lx.benefits.bean.entity.product.ProductEntity;
import com.lx.benefits.bean.entity.product.SkuEntity;
import com.lx.benefits.bean.util.CalRateUtil;
import com.lx.benefits.bean.util.DateUtil;
import com.lx.benefits.mapper.basedistrictinfo.DistrictInfoMapper;
import com.lx.benefits.mapper.jd.PrdJdBaseItemMapper;
import com.lx.benefits.mapper.product.CategoryMapper;
import com.lx.benefits.mapper.supplierInfo.SupplierInfoMapper;
import com.lx.benefits.service.jd.IJDItemService;
import com.lx.benefits.service.jd.IJDItemStatisticsService;
import com.lx.benefits.service.jd.IJDPriceService;
import com.lx.benefits.service.jd.IJDStockService;
import com.lx.benefits.service.product.BrandService;
import com.lx.benefits.service.product.ProductService;
import com.lx.benefits.service.product.SkuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * User: fan
 * Date: 2019/02/25
 * Time: 21:59
 */
@Service
public class IJDItemStatisticsServiceImpl extends JDBaseService implements IJDItemStatisticsService {

    private static final Logger logger = LoggerFactory.getLogger(IJDItemStatisticsServiceImpl.class);

    @Value("${jd.image.path}")
    private String imagePath;

    @Autowired
    IJDItemService jdItemService;

    @Autowired
    PrdJdBaseItemMapper jdBaseItemMapper;

    @Autowired
    IJDPriceService jdPriceService;

    @Autowired
    IJDStockService jdStockService;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    BrandService brandService;

    @Autowired
    SkuService skuService;

    @Autowired
    ProductService productService;

    @Autowired
    SupplierInfoMapper supplierInfoMapper;

    @Autowired
    DistrictInfoMapper districtInfoMapper;

    @Override
    public void statistics() throws Exception {
        // 删除 jd 暂时表数据
        jdBaseItemMapper.delete();
        // 拉取商品池
        List<JDItemPool> jdItemPools = jdItemService.poolList();
        // 保存价格、库存
        synData(jdItemPools);
        // 拉取所有数据
        List<PrdJdBaseItem> jdBaseItems = jdBaseItemMapper.queryByStatusAndRate(new HashMap<>());
        // 更新基本信息
        updateItemDetailByRate(jdBaseItems);
    }

    @Override
    public void goodsImport(JDSkuImportReq record) throws Exception {
        List<PrdJdBaseItem> list = jdBaseItemMapper.getInfoBySku(record.getSku());
        for (PrdJdBaseItem item : list) {
            try {
                JDItemDetail detail = jdItemService.itemDetail(item.getJdSku());
                ProductEntity product = new ProductEntity();
                BrandEntity brand = brandService.selectBrandByName(item.getJdBrandName());
                if (brand != null) {
                    product.setBrandId(brand.getId());
                }
                product.setSkuCode(item.getJdSku());
                product.setBrandName(item.getJdBrandName());
                product.setCategoryId(item.getBigCatId());
                product.setCategoryName(item.getBigCatName() == null ? "" : item.getBigCatName());
                product.setCategoryId2(item.getMiddleCatId());
                product.setCategoryName2(item.getMiddleCatName() == null ? "" : item.getMiddleCatName());
                product.setCategoryId3(item.getSmallCatId());
                product.setCategoryName3(item.getSmallCatName() == null ? "" : item.getSmallCatName());
                product.setItemNumber(item.getBarcode());
                product.setSupplierId(JD_ID);
                product.setSupplierName(JD_NAME);
                product.setGoodsName(item.getJdName());
                product.setGoodsNameEn("");
                product.setGoodsBody("");
                product.setIntroduction(detail.getIntroduction());
                product.setGoodsImage(imagePath + detail.getImagePath());
                DistrictInfo districtInfo = districtInfoMapper.getInfoByName(detail.getProductArea());
                if (districtInfo != null) {
                    product.setPlaceOriginId(districtInfo.getId());
                }
                product.setPlaceOrigin(detail.getProductArea());

                product.setCreatedTime(new Date());
                List<JDItemImage> jdItemImages = jdItemService.itemImages(item.getJdSku());
                List<String> images = new ArrayList<>();
                for (JDItemImage image : jdItemImages) {
                    images.add(imagePath + image.getPath());
                }
                product.setGoodsImages(JSON.toJSONString(images));
                SkuEntity sku = new SkuEntity();
                sku.setSkuCode(item.getJdSku());
                sku.setGoodsSpec("无规格");
                sku.setGoodsStorge(item.getInventory());
                sku.setGoodsImage(imagePath + detail.getImagePath());
                sku.setCreatedTime(new Date());
                sku.setSaleUnit(detail.getSaleUnit() == null ? "" : detail.getSaleUnit());

                // 市场价 = 市场价
                sku.setGoodsMarkprice(new BigDecimal(item.getMarketPrice().toString()));
                // 协议价 = 成本价
                sku.setGoodsCostprice(new BigDecimal(item.getJdProtocolPrice().toString()));
                // 京东价 = 销售价
                sku.setGoodsPrice(new BigDecimal(item.getJdPrice().toString()));
                // 毛利率
                sku.setGoodsRate(new BigDecimal(item.getJdRate().toString()));
                // 折扣
                BigDecimal discount = sku.getGoodsPrice().divide(sku.getGoodsMarkprice(), 2, RoundingMode.HALF_UP);
                sku.setGoodsDiscount(discount);
                sku.setParam(detail.getParam());
                saveGoods(product, sku, item.getJdSku(), detail.getParam());
            } catch (Exception e) {
                logger.error("error data {}", item.toString());
                logger.error(e.getMessage(), e);
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveGoods(ProductEntity product, SkuEntity sku, String jdSku, String spec) {
        ProductEntity productEntity = productService.getProductBySkuCode(product.getSkuCode(),Integer.valueOf(JD_ID.toString()));
        if (productEntity != null) {
            if (productEntity.getIsEffect() == 1 || (productEntity.getGjValidStarttime() != null
                    && productEntity.getGjValidEndtime() != null
                    && DateUtil.isInRange(productEntity.getGjValidStarttime(), productEntity.getGjValidEndtime()))) {
                return;
            }
            product.setUpdatedTime(new Date());
            product.setSpuCode(productEntity.getSpuCode());
            productService.update(product);
        } else {
            product.setCreatedTime(new Date());
            productService.insert(product);
        }
        SkuEntity skuEntity = skuService.selectBySku(jdSku, product.getSupplierName());
        sku.setSpuCode(product.getSpuCode());
        if (null != skuEntity) {
            sku.setId(skuEntity.getId());
            sku.setUpdatedTime(new Date());
            skuService.update(sku);
        } else {
            sku.setCreatedTime(new Date());
            skuService.insert(sku);
        }
    }

    public void synData(List<JDItemPool> jdItemPools) throws Exception {
        int total = 0;
        for (JDItemPool pool : jdItemPools) {
            try {
                List<String> skus = jdItemService.pollSkuList(pool.getPage_num().toString());
                total += skus.size();
                int page = skus.size() % 100 > 0 ? skus.size() / 100 + 1 : skus.size() / 100;
                if (skus.isEmpty()) {
                    continue;
                }
                for (int i = 0; i < page; i++) {
                    try {
                        int start = i * 100;
                        int end = (i + 1) * 100;
                        end = end > skus.size() ? skus.size() : end;
                        List<JDPrice> jdPrices = jdPriceService.getPriceBySkus(getString(skus.subList(start, end)));
                        List<JDStock> jdStocks = jdStockService.stock(skus.subList(start, end), "15_1213_3409");
                        List<JDItemState> jdItemStates = jdItemService.itemState(skus.subList(start, end));
                        List<PrdJdBaseItem> items = new ArrayList<>(100);
                        for (JDPrice jdPrice : jdPrices) {
                            PrdJdBaseItem item = new PrdJdBaseItem();
                            item.setJdPrice(jdPrice.getJdPrice()); //京东价格
                            item.setMarketPrice(jdPrice.getMarketPrice());//市场价格
                            item.setJdProtocolPrice(jdPrice.getPrice());//协议价格
                            item.setJdSku(jdPrice.getSkuId());
                            item.setJdRate(CalRateUtil.calRate(jdPrice.getJdPrice(), jdPrice.getPrice()));
                            for (JDItemState state : jdItemStates) {
                                if (state != null && state.getSku() != null && state.getSku().equals(jdPrice.getSkuId())) {
                                    item.setStatus(state.getState());
                                    break;
                                }
                            }
                            for (JDStock jdStock : jdStocks) {
                                if (jdStock != null && jdStock.getSku().equals(jdPrice.getSkuId())) {
                                    item.setInventory(jdStock.getState());
                                    break;
                                }
                            }
                            items.add(item);
                        }
                        jdBaseItemMapper.batchInsert(items);
                    } catch (Exception e) {
                        System.out.println(e);
                        logger.error("JD_SYN_ITEM_BASE_ERROR", e);
                        continue;
                    }
                }
                logger.info("JD_SYN_ITEM_TOTAL_" + total);
            } catch (Exception e) {
                logger.error("JD_SYN_ITEM_BASE_ERROR", e);
                System.out.println(e);
                continue;
            }
        }
    }

    @Override
    public void updateItemDetailByRate(List<PrdJdBaseItem> jdBaseItems) {
        logger.info("AVAILABLE_COUNT" + jdBaseItems.size());
        List<CategoryEntity> queryByParam = categoryMapper.queryByParam(new HashMap<>());
        Map<Long, String> catM = getCategoryMap(queryByParam);
        int count = 0;
        for (PrdJdBaseItem item : jdBaseItems) {
            try {
                JDItemDetail detail = getJDItemDetail(item.getJdSku());
                if (detail == null) {
                    jdBaseItemMapper.deleteBySku(item.getJdSku());
                    continue;
                }
                item.setJdName(detail.getName());
                item.setJdBrandName(detail.getBrandName());
                item.setBarcode(detail.getUpc());
                setCategory(catM, item, detail);
                jdBaseItemMapper.updateByPrimaryKeySelective(item);
                count++;
            } catch (Exception e) {
                logger.error("JD_UPDATE_ITEM_DETAIL_BY_RATE_ERROR", e);
                continue;
            }
            System.out.println("LEFT_" + (jdBaseItems.size() - count));
        }
    }

    private JDItemDetail getJDItemDetail(String jdSku) {
        JDItemDetail detail = null;
        for (int i = 0; i < 3; i++) {
            try {
                detail = jdItemService.itemDetail(jdSku);
                if (detail != null) {
                    return detail;
                }
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
        return detail;
    }


    private Map<Long, String> getCategoryMap(List<CategoryEntity> categories) {
        Map<Long, String> catM = new HashMap<>();
        for (CategoryEntity category : categories) {
            catM.put(category.getId(), category.getName());
        }
        return catM;
    }

    private void setCategory(Map<Long, String> catM, PrdJdBaseItem item, JDItemDetail detail) {
        String categoryStr = detail.getCategory();
        String[] catArray = categoryStr.split(";");
        String larCat = "";
        String midCat = "";
        String smaCat = "";
        if (catArray != null && catArray.length > 0) {
            larCat = catM.get(Long.valueOf(catArray[0]));
            item.setBigCatId(Long.valueOf(catArray[0]));
            item.setBigCatName(larCat);
        }
        if (catArray != null && catArray.length > 1) {
            midCat = catM.get(Long.valueOf(catArray[1]));
            item.setMiddleCatId(Long.valueOf(catArray[1]));
            item.setMiddleCatName(midCat);
        }
        if (catArray != null && catArray.length > 2) {
            smaCat = catM.get(Long.valueOf(catArray[2]));
            item.setSmallCatId(Long.valueOf(catArray[2]));
            item.setSmallCatName(smaCat);
        }
        item.setCatName(larCat + "-" + midCat + "-" + smaCat);
    }
}
