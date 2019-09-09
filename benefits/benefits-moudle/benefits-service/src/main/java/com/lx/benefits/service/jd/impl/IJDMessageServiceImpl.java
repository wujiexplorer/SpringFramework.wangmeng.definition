package com.lx.benefits.service.jd.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lx.benefits.bean.dto.jd.*;
import com.lx.benefits.bean.dto.jd.res.JDResult;
import com.lx.benefits.bean.entity.jd.PrdJdBaseItem;
import com.lx.benefits.bean.entity.product.ProductEntity;
import com.lx.benefits.bean.entity.product.SkuEntity;
import com.lx.benefits.bean.enums.jdapi.JDMessageLineStatus;
import com.lx.benefits.bean.enums.jdapi.JDMessageType;
import com.lx.benefits.bean.enums.yianapi.util.JsonUtil;
import com.lx.benefits.bean.util.CalRateUtil;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.mapper.jd.JdMessageLineMapper;
import com.lx.benefits.mapper.jd.PrdJdBaseItemMapper;
import com.lx.benefits.service.jd.*;
import com.lx.benefits.service.product.ProductService;
import com.lx.benefits.service.product.SkuService;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * User: fan
 * Date: 2019/03/17
 * Time: 21:37
 */
@Service
public class IJDMessageServiceImpl extends JDBaseService implements IJDMessageService {

    private static final Logger logger = LoggerFactory.getLogger(IJDMessageServiceImpl.class);

    @Autowired
    JdMessageLineMapper jdMessageLineMapper;

    @Autowired
    ProductService productService;

    @Autowired
    IJDItemService jdItemService;

    @Autowired
    IJDPriceService jdPriceService;

    @Autowired
    SkuService skuService;

    @Autowired
    IJDStockService jdStockService;

    @Autowired
    PrdJdBaseItemMapper jdBaseItemMapper;

    @Autowired
    IJDItemStatisticsService ijdItemStatisticsService;

    List<Long> messageIds;

    @Override
    public JSONObject getMessageList(JdMessageLineReq req) {
        req.setPage(req.getPage() > 0 ? (req.getPage() - 1) * req.getPageSize() : 0);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("list", jdMessageLineMapper.getMessageList(req));
        jsonObject.put("count", jdMessageLineMapper.getMessageListCount(req));
        return jsonObject;
    }

    @Override
    public JSONObject process(Long id) {
        JdMessageLine messageLine = jdMessageLineMapper.getMessage(id);
        if (messageLine == null) {
            return Response.fail("信息不存在");
        }
        if (messageLine.getStatus() != JDMessageLineStatus.LINED.getCode()) {
            return Response.fail("已处理过的信息");
        }
        try {
            process(messageLine);
            return Response.succ();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Response.fail("操作失败");
        }
    }


    @Override
    public Integer doMessageProcess() {
        try {
            List<JdMessageLine> jdMessageLines = get();
            logger.info("JD_DO_MESSAGE_PROCESS_START_MESSAGE_COUNT_" + jdMessageLines.size());
            process(jdMessageLines);
            batchDelete(messageIds);
            return jdMessageLines.size();
        } catch (Exception e) {
            logger.error("JD_GET_MESSAGE_ERROR", e);
            return -1;
        }
    }

    @Override
    public List<JdMessageLine> get() throws Exception {
        Map<String, String> param = getParam();
        String res = postData(getMessage_get_url(), param, CHARSET);
        logger.info("JD_MESSAGE_RESULT_" + res);
        JDResult<List<JDMessage>> jdResult = JSON.parseObject(res, new TypeReference<JDResult<List<JDMessage>>>() {
        });
        messageIds = new ArrayList<>();
        List<JdMessageLine> lines = new ArrayList<>();
        if (!jdResult.isSuccess()) {
            logger.error("JD_MESSAGE_ERROR_RESULT_" + res);
        } else {
            Date cur = new Date();
            List<JDMessage> jdMessages = jdResult.getResult();
            if (CollectionUtils.isEmpty(jdMessages)) {
                return Collections.emptyList();
            }
            for (JDMessage message : jdMessages) {
                JdMessageLine line = new JdMessageLine();
                line.setStatus(0);
                line.setMessageId(message.getId());
                line.setMessageType(message.getType());
                line.setMessageContent(JsonUtil.convertObjToStr(message.getResult()));
                line.setMessageTime(message.getTime());
                line.setCreateTime(cur);
                line.setUpdateTime(cur);
                lines.add(line);
                messageIds.add(message.getId());
            }
            jdMessageLineMapper.batchInsert(lines);
        }
        return lines;
    }

    /**
     * 删除京东消息 -- 单个 mid
     *
     * @param messageId
     */
    public void delete(Long messageId) throws Exception {
        if (messageId == null) {
            return;
        }
        Map<String, String> param = getParam();
        param.put("id", messageId.toString());
        String res = postData(getMessage_del_url(), param, CHARSET);
        logger.info("JD_MESSAGE_DEL_RESULT_" + res);
    }

    /**
     * 删除京东消息 -- 批量 mids
     *
     * @param mids
     */
    public void batchDelete(String mids) throws Exception {
        if (mids == null) {
            return;
        }
        Map<String, String> param = getParam();
        param.put("id", mids);
        String res = postData(getMessage_del_url(), param, CHARSET);
        logger.info("JD_MESSAGE_DEL_RESULT_" + res);
    }

    /**
     * 删除京东消息 -- 批量Message
     *
     * @param messageLines
     */
    public void delete(List<JdMessageLine> messageLines) {
        if (CollectionUtils.isEmpty(messageLines)) {
            return;
        }
        try {
            for (JdMessageLine line : messageLines) {
                delete(line.getMessageId());
            }
        } catch (Exception e) {
            logger.error("JD_MESSAGE_DEL_ERROR", e);
        }
    }

    /**
     * 删除京东消息 -- 批量ID
     *
     * @param mids
     */
    public void batchDelete(List<Long> mids) {
        if (CollectionUtils.isEmpty(mids)) {
            return;
        }
        try {
            String ids = "";
            for (Long id : mids) {
                ids += id + ",";
            }
            if (ids.length() > 0) {
                ids = ids.substring(0, ids.length() - 1);
            }
            batchDelete(ids);
        } catch (Exception e) {
            logger.error("JD_MESSAGE_DEL_ERROR", e);
        }
    }


    public void process(List<JdMessageLine> lines) {
        if (CollectionUtils.isEmpty(lines)) {
            return;
        }
        for (JdMessageLine line : lines) {
            try {
                process(line);
            } catch (Exception e) {
                logger.error("JD_PROCESS_MESSAGE_ERROR_", e);
                logger.error("JD_PROCESS_MESSAGE_ERROR_LINE_ID" + line.getId());
            }
        }
    }

    public void process(JdMessageLine line) throws Exception {
        if (line == null) {
            return;
        }
        if (line.getMessageType() == null) {
            logger.error("JD_MESSAGE_PROCESS_ERROR_UNKNOWN_MESSAGE_TYPE_" + JsonUtil.convertObjToStr(line));
            return;
        }
        Integer type = line.getMessageType();
        JSONObject jsonObject = JsonUtil.parseJsonObject(line.getMessageContent());
        String sku = jsonObject.getString("skuId");
        if (type.equals(JDMessageType.ITEM_PRICE_CHANGE.getCode())) {
            // 变价
            updateGoodsPrices(line, sku);
        } else if (type.equals(JDMessageType.ITEM_STATUS_CHANGE.getCode())) {
            // 上下架
            updateGoodsState(line, sku);
        } else if (type.equals(JDMessageType.ITEM_DETAIL_CHANGE.getCode())) {
            // 规格参数变更
            updateGoodsSpec(line, sku);
        } else if (type.equals(JDMessageType.ITEM_POOL_CHANGE.getCode())) {
            // 商品 增加/删除
            updateGoodsStatus(line, sku);
        }
    }

    /**
     * 商品状态变化
     *
     * @param line
     * @param sku
     */
    public void updateGoodsStatus(JdMessageLine line, String sku) {
        // state:1/添加 2/删除
        try {
            JSONObject jsonObject = JsonUtil.parseJsonObject(line.getMessageContent());
            Integer state = jsonObject.getInteger("state");
            ProductEntity product = productService.getProductBySkuCode(sku, Integer.valueOf(JD_ID.toString()));
            if (state == 1) {
                if (addGoods(sku) < 1) {
                    updateMessageLine(line, JDMessageLineStatus.LINED.getCode());
                    return;
                }
                PrdJdBaseItemReq req = new PrdJdBaseItemReq();
                req.setJdSku(sku);
                List<PrdJdBaseItem> jdBaseItems = jdBaseItemMapper.getJdItemList(req);
                ijdItemStatisticsService.updateItemDetailByRate(jdBaseItems);
                List<String> list = new ArrayList<>();
                list.add(sku);
                ijdItemStatisticsService.goodsImport(new JDSkuImportReq(list));
            } else {
                if (product != null) {
                    // 商品下架
                    product.setGoodsState(0);
                    productService.update(product);
                }
            }
            updateMessageLine(line, JDMessageLineStatus.PROCESSED.getCode());
        } catch (Exception e) {
            updateMessageLine(line, JDMessageLineStatus.LINED.getCode());
            logger.error(e.getMessage(), e);
        }
    }

    public Integer addGoods(String skuId) {
        try {
            List<String> skus = new ArrayList<>();
            skus.add(skuId);
            List<JDPrice> jdPrices = jdPriceService.getPriceBySkus(ArrayUtils.toString(skus));
            List<JDStock> jdStocks = jdStockService.stock(skus, "15_1213_3409");
            List<JDItemState> jdItemStates = jdItemService.itemState(skus);
            List<PrdJdBaseItem> items = new ArrayList<>(100);
            for (JDPrice jdPrice : jdPrices) {
                PrdJdBaseItem item = new PrdJdBaseItem();
                //协议价格
                item.setJdProtocolPrice(jdPrice.getPrice());
                //市场价格
                item.setMarketPrice(jdPrice.getMarketPrice());
                //京东价格
                item.setJdPrice(jdPrice.getJdPrice());
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
            return jdBaseItemMapper.batchInsert(items);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return 0;
    }

    /**
     * 变价
     *
     * @param line
     * @param sku
     * @throws Exception
     */
    private void updateGoodsPrices(JdMessageLine line, String sku) throws Exception {
        ProductEntity product = productService.getProductBySkuCode(sku, Integer.valueOf(JD_ID.toString()));
        if (product == null) {
            updateMessageLine(line, JDMessageLineStatus.ABORTED.getCode());
            return;
        }
        SkuEntity entity = skuService.selectBySku(sku, JD_NAME);
        List<String> list = new ArrayList<>();
        list.add(sku);
        List<JDPrice> jdPrices = jdPriceService.getPriceBySkus(getString(list));
        if (jdPrices == null) {
            updateMessageLine(line, JDMessageLineStatus.LINED.getCode());
            return;
        }
        for (JDPrice jdPrice : jdPrices) {
            if (jdPrice.getSkuId().equals(entity.getSkuCode())) {
                // 市场价 = 市场价
                entity.setGoodsMarkprice(new BigDecimal(jdPrice.getMarketPrice().toString()));
                // 协议价 = 成本价
                entity.setGoodsCostprice(new BigDecimal(jdPrice.getPrice().toString()));
                // 京东价 = 销售价
                entity.setGoodsPrice(new BigDecimal(jdPrice.getJdPrice().toString()));
                // 毛利率
				entity.setGoodsRate(new BigDecimal(CalRateUtil.calRate(jdPrice.getJdPrice(), jdPrice.getPrice()).toString()));
				if (entity.getGoodsRate().compareTo(BigDecimal.ZERO) < 0) {// 毛利小于0
					entity.setStatus(0);// 直接下架
				}
                // 折扣
                BigDecimal discount = entity.getGoodsPrice().divide(entity.getGoodsMarkprice(), 2, RoundingMode.HALF_UP);
                entity.setGoodsDiscount(discount);
                entity.setUpdatedTime(new Date());
                entity.setUpdatedUser("SYSTEM");
                // sku
                skuService.update(entity);
                updateMessageLine(line, JDMessageLineStatus.PROCESSED.getCode());
                return;
            }
        }
        updateMessageLine(line, JDMessageLineStatus.LINED.getCode());
    }


    /**
     * 商品规格变化
     *
     * @param line
     * @param sku
     */
    private void updateGoodsSpec(JdMessageLine line, String sku) {
        try {
            SkuEntity entity = skuService.selectBySku(sku, JD_NAME);
            if (entity == null) {
                updateMessageLine(line, JDMessageLineStatus.ABORTED.getCode());
                return;
            }
            JDItemDetail detail = jdItemService.itemDetail(sku);
            logger.info("JD_MESSAGE_PROCESS_ITEM_PRICE_CHANGE_SKU_ID_" + String.valueOf(sku));
            if (detail != null) {
                updateMessageLine(line, JDMessageLineStatus.PROCESSED.getCode());
                return;
            }
            updateMessageLine(line, JDMessageLineStatus.ABORTED.getCode());
        } catch (Exception e) {
            logger.error("JD_MESSAGE_UPDATE_ITEM_ERROR", e);
            logger.error("JD_MESSAGE_UPDATE_ITEM_ERROR_MESSAGE_ID" + line.getId());
        }
    }

    /**
     * 商品上下架
     *
     * @param line
     * @param sku
     */
    private void updateGoodsState(JdMessageLine line, String sku) {
        try {
            ProductEntity product = productService.getProductBySkuCode(sku, Integer.valueOf(JD_ID.toString()));
            if (product == null) {
                updateMessageLine(line, JDMessageLineStatus.ABORTED.getCode());
                return;
            }
            JSONObject jsonObject = JsonUtil.parseJsonObject(line.getMessageContent());
            String state = jsonObject.getString("state");
            logger.info("JD_MESSAGE_PROCESS_ITEM_PRICE_CHANGE_SKU_ID_" + String.valueOf(sku));
            if (state != null && state.equals("0")) {
                product.setGoodsState(0);
                productService.update(product);
                updateMessageLine(line, JDMessageLineStatus.PROCESSED.getCode());
                return;
            }
            updateMessageLine(line, JDMessageLineStatus.ABORTED.getCode());
        } catch (Exception e) {
            logger.error("JD_MESSAGE_UPDATE_ITEM_ERROR", e);
            logger.error("JD_MESSAGE_UPDATE_ITEM_ERROR_MESSAGE_ID" + line.getId());
        }
    }

    private void updateMessageLine(JdMessageLine line, Integer code) {
        line.setStatus(code);
        jdMessageLineMapper.updateByJdMessageId(line);

    }

}