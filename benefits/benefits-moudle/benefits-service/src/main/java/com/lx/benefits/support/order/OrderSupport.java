package com.lx.benefits.support.order;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.alibaba.fastjson.TypeReference;
import com.lx.benefits.bean.entity.seckill.Seckill;
import com.lx.benefits.bean.entity.voucher.Voucher;
import com.lx.benefits.bean.util.JsonUtil;
import com.lx.benefits.bean.util.StringUtil;
import com.lx.benefits.constant.MemberInfoConstant;
import com.lx.benefits.service.seckill.SeckillService;
import com.lx.benefits.service.voucher.VoucherService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.apollo.common.annotation.RedisLock;
import com.apollo.common.exception.BusinessException;
import com.apollo.common.utils.bean.BeanUtil;
import com.apollo.common.utils.bean.CollectionExtUtil;
import com.apollo.common.utils.money.MoneyUtil;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lx.benefits.bean.bo.order.OrderRefundBO;
import com.lx.benefits.bean.bo.order.OrderSaveBO;
import com.lx.benefits.bean.bo.pay.OrderCreditBO;
import com.lx.benefits.bean.bo.product.SkuBO;
import com.lx.benefits.bean.dto.enterpriseadmin.employee.EmployeeInfoDto;
import com.lx.benefits.bean.dto.jdOrder.api.JDOrderSubOrder;
import com.lx.benefits.bean.dto.yxOrder.api.YXChannelOrderOut;
import com.lx.benefits.bean.entity.cardkey.CardKeyStorage;
import com.lx.benefits.bean.entity.cardkey.CardKeyStorageExample;
import com.lx.benefits.bean.entity.cart.CartProduct;
import com.lx.benefits.bean.entity.employeecreditinfo.EmployeeCreditInfo;
import com.lx.benefits.bean.entity.enterprfestivalpacket.EnterprFestivalPacket;
import com.lx.benefits.bean.entity.memconsigneeaddress.ConsigneeAdress;
import com.lx.benefits.bean.entity.order.Order;
import com.lx.benefits.bean.entity.order.OrderPay;
import com.lx.benefits.bean.entity.order.OrderShip;
import com.lx.benefits.bean.entity.order.OrderShipLogistics;
import com.lx.benefits.bean.entity.order.ProductOrderEx;
import com.lx.benefits.bean.entity.product.ProductEntity;
import com.lx.benefits.bean.entity.product.SkuEntity;
import com.lx.benefits.bean.entity.supplierInfo.SupplierInfo;
import com.lx.benefits.bean.enums.CardKeyStatusEnum;
import com.lx.benefits.bean.enums.CreditTypeEnum;
import com.lx.benefits.bean.enums.OptTypeEnum;
import com.lx.benefits.bean.enums.OrderEnum;
import com.lx.benefits.bean.enums.OrderLevel;
import com.lx.benefits.bean.enums.PayChannelEnum;
import com.lx.benefits.bean.param.order.OrderBuyNowParam;
import com.lx.benefits.bean.param.order.OrderBuyNowParam.SkuParam;
import com.lx.benefits.bean.param.order.OrderBuyParam;
import com.lx.benefits.bean.param.order.OrderLogisticsParam;
import com.lx.benefits.bean.param.order.OrderSubmitParam;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.util.SessionShopInfo;
import com.lx.benefits.bean.vo.order.OrderConfirmVO;
import com.lx.benefits.bean.vo.order.ProductValidateResult;
import com.lx.benefits.bean.vo.product.SkuPriceBean;
import com.lx.benefits.config.properties.ThirdPlaformSeller;
import com.lx.benefits.config.properties.YibaoProperties;
import com.lx.benefits.constant.CommonConstant;
import com.lx.benefits.dao.memconsigneeaddress.ConsignessAdressDao;
import com.lx.benefits.mapper.cardkey.CardKeyStorageMapper;
import com.lx.benefits.service.agent.AgentCashService;
import com.lx.benefits.service.cart.CartProductService;
import com.lx.benefits.service.client.YibaoApiService;
import com.lx.benefits.service.creditoperateinfo.CreditOperateInfoService;
import com.lx.benefits.service.employeecreditinfo.EmployeeCreditInfoService;
import com.lx.benefits.service.employeeuserinfo.EmployeeUserInfoService;
import com.lx.benefits.service.enterprcustomgoods.EnterprCustomGoodsService;
import com.lx.benefits.service.enterprfestival.EnterprFestivalService;
import com.lx.benefits.service.jdOrder.IJDOrderService;
import com.lx.benefits.service.order.OrderInfoValidateService;
import com.lx.benefits.service.order.OrderPayService;
import com.lx.benefits.service.order.OrderService;
import com.lx.benefits.service.order.OrderShipLogisticsService;
import com.lx.benefits.service.order.OrderShipService;
import com.lx.benefits.service.order.ProductOrderExService;
import com.lx.benefits.service.product.ProductService;
import com.lx.benefits.service.product.SkuService;
import com.lx.benefits.service.supplierInfo.SupplierInfoService;
import com.lx.benefits.service.yxOrder.IYXOrderService;
import com.lx.benefits.support.common.IdGenerator;
import com.lx.benefits.utils.DateTimeUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OrderSupport {

    @Resource
    private OrderService orderService;
    @Resource
    private ProductOrderExService productOrderExService;
    @Resource
    private SkuService skuService;
    @Resource
    private ProductService productService;
    @Resource
    private CartProductService cartProductService;
    @Resource
    private SupplierInfoService supplierInfoService;
    @Resource
    private OrderShipService orderShipService;
    @Resource
    private EmployeeCreditInfoService employeeCreditInfoService;
    @Autowired
    private ConsignessAdressDao adressDao;
    @Resource
    private OrderShipLogisticsService orderShipLogisticsService;
    @Resource
    private OrderAliPaySupport orderAliPaySupport;
    @Resource
    private OrderPayService orderPayService;
    @Resource
    private EnterprFestivalService enterprFestivalService;
    @Resource
    private IJDOrderService ijdOrderService;
    @Resource
    private IYXOrderService iyxOrderService;
    @Resource
    private ThirdPlaformSeller thirdPlaformSeller;
    @Resource
    private EmployeeUserInfoService employeeUserInfoService;
    @Resource
    private OrderWxPaySupport orderWxPaySupport;
    @Resource
    private AgentCashService agentCashService;
    @Resource
    private IdGenerator idGenerator;
    @Resource
    private CreditOperateInfoService creditOperateInfoService;
    @Autowired
    private CardKeyStorageMapper cardKeyStorageMapper;

    @Resource
    private OrderInfoValidateService orderInfoValidateService;
    @Resource
    private EnterprCustomGoodsService enterprCustomGoodsService;
    @Autowired
    private YibaoApiService yibaoApiService;
    @Autowired
    private YibaoProperties yibaoProperties;
    @Autowired
    private VoucherService voucherService;
    @Autowired
    private SeckillService seckillService;

    private Set<Integer> reverseSet = ImmutableSet.of(OrderEnum.REVERSE_STATUS.END.getCode(),
            OrderEnum.REVERSE_STATUS.END_SUCC.getCode(), OrderEnum.REVERSE_STATUS.APPLY.getCode());

    /**
     * 正常确认订单页
     *
     * @return
     */
    public OrderConfirmVO confirmOrder(Long userReceiveAddrId, Long accountId, String voucherIds) {

        List<CartProduct> cartProductList = cartProductService.listCheckedByAccountId(accountId);
        if (CollectionUtils.isEmpty(cartProductList)) {
            OrderConfirmVO confirmVO = new OrderConfirmVO();
            confirmVO.setSource(0);
            confirmVO.setPointAmount(BigDecimal.valueOf(0.00));
            confirmVO.setCanUsePointAmount(BigDecimal.valueOf(0.00));
            confirmVO.setTotalFreightPrice(BigDecimal.valueOf(0.00));
            confirmVO.setTotalGoodsPrice(BigDecimal.valueOf(0.00));
            confirmVO.setTotalRealPrice(BigDecimal.valueOf(0.00));
            confirmVO.setSellerOrderList(Collections.emptyList());
            return confirmVO;
        }
        List<Integer> skuIdList = Lists.newArrayList();
        List<Long> productIdList = Lists.newArrayList();
        Long campaginId = cartProductList.get(0).getActivityId();
        Map<Long, Integer> skuParamMap = Maps.newHashMap();
        cartProductList.forEach(cartProduct -> {
            if (!Objects.equals(campaginId, cartProduct.getActivityId())) {
                throw new BusinessException("节日礼金活动只能立即购买");
            }
            skuIdList.add(cartProduct.getSkuId().intValue());
            productIdList.add(cartProduct.getId());
            skuParamMap.put(cartProduct.getSkuId(), cartProduct.getCount());
        });
        List<Long> vouchers = new ArrayList<>();
        for (int i = 0, len = skuIdList.size(); i < len; i++) {
            SkuEntity skuEntity = skuService.getBySkuId(skuIdList.get(i).longValue());
            ProductEntity productEntity = productService.selectById(skuEntity.getSpuCode());
            if (StringUtil.isNotEmpty(productEntity.getVoucherIds())) {
                List<Long> list = JsonUtil.parseObject(productEntity.getVoucherIds(), new TypeReference<List<Long>>() {
                });
                vouchers.addAll(list);
            }
        }
        List<Long> vouchersTemp = voucherService.fingFullRangeVoucherIds();
        vouchers.addAll(vouchersTemp);
        Set<Long> sets = new HashSet<>(vouchers);
        vouchers = new ArrayList<>(sets);
        String voucherIdsTemp = "";
        if (!vouchers.isEmpty()) {
            voucherIdsTemp = vouchers.get(0) + ",";
            for (int i = 1, len = vouchers.size(); i < len; i++) {
                voucherIdsTemp = voucherIdsTemp + vouchers.get(i) + ",";
            }
        }
        OrderConfirmVO confirmVO = getOrderConfirmVO(accountId, userReceiveAddrId, skuIdList, campaginId, null,
                skuParamMap, null);
        confirmVO.setSource(0);
        if (StringUtil.isNotEmpty(voucherIdsTemp)) {
            confirmVO.setFlag(voucherService.validateCartVouchers(voucherIdsTemp));
        } else {
            confirmVO.setFlag(false);
        }
        return confirmVO;
    }

    /**
     * 立即购买订单确认页
     *
     * @param orderBuyNowParam
     * @return
     */
    public OrderConfirmVO buyNowConfirmOrder(OrderBuyNowParam orderBuyNowParam) {
        SessionShopInfo sessionEmployeeInfo = SessionContextHolder.getSessionEmployeeInfo();
        Long enterprId = sessionEmployeeInfo.getEnterprId();
        SkuParam sku = orderBuyNowParam.getSkuList().get(0);
        SkuEntity skuEntity = skuService.getBySkuId(sku.getSkuId());
        Seckill seckill = null;
        //秒杀只能购买一件商品
        if (orderBuyNowParam.getSeckillId() != null) {
            Integer count = 0;
            List<SkuParam> skuList = orderBuyNowParam.getSkuList();
            for (SkuParam item : orderBuyNowParam.getSkuList()) {
                count += item.getQuantity();
            }
            if (count > 1 || skuList.size() > 1) {
                throw new BusinessException("抱歉,该活动商品只能选购一件!");
            }
            seckill = seckillService.getSeckill(orderBuyNowParam.getSeckillId());
        }
        if (Objects.isNull(skuEntity)) {
            throw new BusinessException("该商品不存在");
        } else if ((seckill == null && skuEntity.getGoodsStorge() < 1) || (seckill != null && seckill.getLeftStorage() < 1)) {
            throw new BusinessException("库存不足");
        }
        ProductEntity productEntity = productService.selectById(skuEntity.getSpuCode());
        if (Objects.isNull(productEntity)) {
            throw new BusinessException("该商品不存在");
        }
        if (enterprId.equals(yibaoProperties.getEnterprId())) {
            int count = 0;
            List<SkuParam> skuList = orderBuyNowParam.getSkuList();
            for (SkuParam item : orderBuyNowParam.getSkuList()) {
                count += item.getQuantity();
            }
            if (count > 1 || skuList.size() > 1) {
                throw new BusinessException("抱歉,该活动商品只能选购一件!");
            }
        }
        Map<Long, SkuPriceBean> priceList = skuService.singleCustomPrice(Arrays.asList(skuEntity.getId()), enterprId);
        SkuPriceBean skuPriceBean = priceList.get(skuEntity.getId());
        boolean isCustomer = skuPriceBean == null ? false : skuPriceBean.isCustomer();
        if (!isCustomer) {
            if (skuEntity.getStatus() != 1 || productEntity.getGoodsState() != 1) {
                throw new BusinessException("该商品已下架");
            }
            boolean available = enterprCustomGoodsService.checkAvailable(orderBuyNowParam.getCampaginId(), enterprId,
                    productEntity, skuEntity);
            if (!available) {
                throw new BusinessException("抱歉,您没有权限购买此商品!");
            }
        }
        Long accountId = sessionEmployeeInfo.getEmployeeId();
        List<Integer> skuIdList = Lists.newArrayList();
        Long campaginId = orderBuyNowParam.getCampaginId();
        Long topicId = orderBuyNowParam.getTopicId();
        Map<Long, Integer> skuParamMap = Maps.newHashMap();
        orderBuyNowParam.getSkuList().forEach(skuParam -> {
            skuIdList.add(skuParam.getSkuId().intValue());
            skuParamMap.put(skuParam.getSkuId(), skuParam.getQuantity());
        });
        OrderConfirmVO confirmVO = getOrderConfirmVO(accountId, orderBuyNowParam.getUserReceiveAddrId(), skuIdList,
                campaginId, topicId, skuParamMap, orderBuyNowParam.getSeckillId());
        confirmVO.setSource(1);
        return confirmVO;
    }

    /**
     * 订单确认页
     *
     * @param accountId
     * @param skuIdList
     * @param activityId
     * @param skuParamMap
     * @return
     */
    private OrderConfirmVO getOrderConfirmVO(Long accountId, Long userReceiveAddrId, List<Integer> skuIdList,
                                             Long activityId, Long topicId, Map<Long, Integer> skuParamMap,
                                             Long seckillId) {
        List<SkuEntity> skuList = skuService.listByIdList(skuIdList);
        if (seckillId != null) {
            for (int i = 0, len = skuList.size(); i < len; i++) {
                skuList.get(i).setSeckillId(seckillId);
            }
        }
        List<Long> spuCodeList = skuList.stream().map(SkuEntity::getSpuCode).distinct().collect(Collectors.toList());
        List<ProductEntity> productList = productService.listBySpuCodeList(spuCodeList);
        Map<Long, ProductEntity> productMap = Maps.newHashMapWithExpectedSize(productList.size());

        EmployeeInfoDto employeeInfoDto = employeeUserInfoService.findByEmployeeId(accountId, false);
        List<Long> sellerIdList = Lists.newArrayList();


        productList.forEach(productEntity -> {
            sellerIdList.add(productEntity.getSupplierId());
            productMap.put(productEntity.getSpuCode(), productEntity);
        });

        /**节日礼金查询验证商品是否都在节日礼金活动中 校验*/
        if (Objects.nonNull(activityId)
                && activityId != 0L
                && !productService.checkProductMix(activityId, topicId, spuCodeList)) {
            throw new BusinessException("非法请求！");
        }
        List<SupplierInfo> supplierInfoList = supplierInfoService.listByIds(sellerIdList);
        Map<Long, String> supplierMap = CollectionExtUtil.toMap(supplierInfoList, SupplierInfo::getId,
                SupplierInfo::getName);

        List<SkuBO> skuBOList = assembleSkuBOS(skuParamMap, skuList, productMap, supplierMap,
                employeeInfoDto.getEnterprId());


        OrderConfirmVO confirmVO = assembleOrderConfirmVO(supplierMap, skuBOList, accountId, activityId,
                userReceiveAddrId, seckillId);

        return confirmVO;
    }

    @RedisLock(name = "order", keys = {"#orderSaveBO.accountId"})
    @Transactional(rollbackFor = Exception.class)
    public void saveOrder(OrderSaveBO orderSaveBO, Long campaginId) {
        SessionShopInfo sessionEmployeeInfo = SessionContextHolder.getSessionEmployeeInfo();
        if (campaginId != null && campaginId > 0 && sessionEmployeeInfo != null && sessionEmployeeInfo.getEnterprId().equals(yibaoProperties.getEnterprId())) {
            int updateCount = creditOperateInfoService.updateParticipate(campaginId,
                    sessionEmployeeInfo.getEmployeeId());
            if (updateCount == 0) {
                if (creditOperateInfoService.isAuthority(campaginId, sessionEmployeeInfo.getEmployeeId())) {
                    throw new BusinessException("抱歉,您已经参加过该活动!");
                } else {
                    throw new BusinessException("抱歉,您没有权限参加该活动!");
                }
            }
        }
        //删除购物车商品
        if (CollectionUtils.isNotEmpty(orderSaveBO.getCartProductIdList())) {
            cartProductService.delByIdList(orderSaveBO.getAccountId(), orderSaveBO.getCartProductIdList());
        }
        if (orderSaveBO.getDeductionPointAmount() > 0) {

            //扣除积分
            EnterprFestivalPacket festivalPacket = enterprFestivalService.getById(orderSaveBO.getCampaignId());

            OrderCreditBO orderCreditBO = new OrderCreditBO();
            orderCreditBO.setCampaignId(festivalPacket.getCampaignId());
            orderCreditBO.setEmployeeId(orderSaveBO.getAccountId());
            orderCreditBO.setPayOrderNumber(orderSaveBO.getPayOrderNumber().toString());
            orderCreditBO.setCreditType(festivalPacket.getCreditType());
            orderCreditBO.setOptType(OptTypeEnum.USER_ORDER_REDUCE.getValue());
            orderCreditBO.setRemark(OptTypeEnum.USER_ORDER_REDUCE.getDesc());
            orderCreditBO.setReduceCredit(MoneyUtil.changeF2Y(orderSaveBO.getDeductionPointAmount().intValue()));
            //使用优惠卷，员工积分扣减
            if (orderSaveBO.getVoucherSkus() != null) {
                Long voucherId = Long.parseLong(orderSaveBO.getVoucherSkus().split(",")[2]);
                Voucher voucher = voucherService.getVoucherByVoucherId(voucherId);
                orderCreditBO.setReduceCredit((MoneyUtil.changeF2Y(orderSaveBO.getDeductionPointAmount().intValue() - voucher.getBenefitContent().multiply(new BigDecimal(100)).intValue()).compareTo(BigDecimal.ZERO)<1)?(MoneyUtil.changeF2Y(orderSaveBO.getDeductionPointAmount().intValue())):(MoneyUtil.changeF2Y(orderSaveBO.getDeductionPointAmount().intValue() - voucher.getBenefitContent().multiply(new BigDecimal(100)).intValue())));
            }
            employeeCreditInfoService.modifyEmployeeCredit4Order(orderCreditBO);
            BigDecimal cardAmount = orderCreditBO.getCardAmount();
            if (cardAmount != null && cardAmount.compareTo(BigDecimal.ZERO) > 0) {
                this.distributeCardAmountToOrder(orderSaveBO.getOrderList(), orderCreditBO.getReduceCredit(),
                        cardAmount);
            }
        }

        List<Long> orderNumbers = Lists.newArrayList();
        List<Order> thirdOrderList = Lists.newArrayList();
        List<Order> productOrders = new ArrayList<>();
        //更新库存操作
        orderSaveBO.getOrderList().forEach(order -> {
            orderNumbers.add(order.getNumber());
            if (order.getLevel() == 3) {
                // 非京东/严选产品做库存更新操作
                if (!order.getSellerId().equals(thirdPlaformSeller.getJd().getId()) && !order.getSellerId().equals(thirdPlaformSeller.getYx().getId())) {
                    Long seckillId = order.getSeckillId();
                    Seckill seckill = null;
                    if (seckillId != null) {
                        seckill = seckillService.getSeckill(seckillId);
                    }
                    if ((seckill == null && skuService.reduceStock(order.getSkuId(), order.getQuantity()) == 0) || (seckill != null && seckill.getLeftStorage() < 1)) {
                        ProductEntity productEntity = productService.selectById(order.getSpuCode());
                        throw new BusinessException(String.format("【%s】 库存不足", productEntity.getGoodsName()));
                    }
                    // 全部积分抵扣时更新待支付占用库存
                    if (seckill == null && orderSaveBO.getOrderStatus() == OrderEnum.STATUS.PAID.getCode()) {
                        skuService.reducePayStock(order.getSkuId(), order.getQuantity());
                    }
                }
                productOrders.add(order);
            } else if (order.getLevel() == 2) {
                if (order.getSellerId().equals(thirdPlaformSeller.getJd().getId())
                        || order.getSellerId().equals(thirdPlaformSeller.getYx().getId())) {
                    thirdOrderList.add(order);
                }
            }
        });

        orderService.doAddBatchRecord(orderSaveBO.getOrderList());
        productOrderExService.doAddBatchRecord(orderSaveBO.getItemOrderExList());
        orderShipService.doAddBatchRecord(orderSaveBO.getOrderShipList());

        //商品级订单号
        List<Long> itemOrderNumbers = CollectionExtUtil.getPropertyList(orderSaveBO.getItemOrderExList(),
                ProductOrderEx::getOrderNumber);
        //查询虚拟商品订单
        List<Order> virtualOrders = orderService.listVirtualOrders(itemOrderNumbers);
        Long employeeId = null;
        if (sessionEmployeeInfo != null) {
            employeeId = sessionEmployeeInfo.getEmployeeId();
        } else {
            EmployeeInfoDto employeeInfoDto = employeeUserInfoService.findByEmployeeId(orderSaveBO.getAccountId(),
                    false);
            employeeId = employeeInfoDto.getEmployeeId();
        }
        Boolean flag = false;
        //插入原来没有的秒杀信息
        if (StringUtil.isNotEmpty(orderSaveBO.getSeckillNum())) {
            Integer num = 0;
            String[] seckills = orderSaveBO.getSeckillNum().split(":");
            Integer count = Integer.parseInt(seckills[1]);
            Long seckillId = Long.parseLong(seckills[0]);
            EmployeeCreditInfo employeeCreditInfo = employeeCreditInfoService.getEmployeeCreditInfo(employeeId, 0L);
            String receivedSeckillIds = employeeCreditInfo.getReceivedSeckillIds();
            StringBuilder stringBuilder = new StringBuilder();
            if (StringUtil.isNotEmpty(receivedSeckillIds)) {
                String[] receivedSingleSeckillIds = receivedSeckillIds.split(",");
                for (int i = 0, len = receivedSingleSeckillIds.length; i < len; i++) {
                    String[] temp = receivedSingleSeckillIds[i].split(":");
                    if (seckillId.equals(Long.parseLong(temp[0]))) {
                        num = 1;
                    }
                    stringBuilder.append(receivedSingleSeckillIds[i] + ",");
                }
                if (num == 0) {
                    stringBuilder.append(seckillId + ":" + count + ",");
                    flag = true;
                }
                EmployeeCreditInfo employeeCreditInfoTemp = new EmployeeCreditInfo();
                employeeCreditInfoTemp.setEmployeeId(SessionContextHolder.getSessionEmployeeInfo().getEmployeeId());
                employeeCreditInfoTemp.setReceivedSeckillIds(stringBuilder.toString());
                employeeCreditInfoService.updateGrainIdByEmployeeIdSelective(employeeCreditInfoTemp);
            }
        }
        //处理员工秒杀商品数量叠加与判断
        if (StringUtil.isNotEmpty(orderSaveBO.getSeckillNum())) {
            String[] seckills = orderSaveBO.getSeckillNum().split(":");
            Integer count = Integer.parseInt(seckills[1]);
            Long seckillId = Long.parseLong(seckills[0]);
            EmployeeCreditInfo employeeCreditInfo = employeeCreditInfoService.getEmployeeCreditInfo(employeeId, 0L);
            Seckill seckill = seckillService.getSeckill(seckillId);
            String receivedSeckillIds = employeeCreditInfo.getReceivedSeckillIds();
            StringBuilder stringBuilder = new StringBuilder();
            if (StringUtil.isNotEmpty(receivedSeckillIds)) {
                String[] receivedSingleSeckillIds = receivedSeckillIds.split(",");
                for (int i = 0, len = receivedSingleSeckillIds.length; i < len; i++) {
                    String[] temp = receivedSingleSeckillIds[i].split(":");
                    if (seckillId.equals(Long.parseLong(temp[0]))) {
                        if (flag == true) {
                            // no handle
                        } else {
                            count = count + Integer.parseInt(temp[1]);
                        }
                        if (seckill.getLimitPerUser() != 0 && count > seckill.getLimitPerUser()) {
                            throw new BusinessException("每人限购单个秒杀商品次数不超过" + seckill.getLimitPerUser());
                        }
                        String res = temp[0] + ":" + count + ",";
                        stringBuilder.append(res);
                        continue;
                    }
                    stringBuilder.append(receivedSingleSeckillIds[i] + ",");
                }
                EmployeeCreditInfo employeeCreditInfoTemp = new EmployeeCreditInfo();
                employeeCreditInfoTemp.setEmployeeId(employeeId);
                employeeCreditInfoTemp.setReceivedSeckillIds(stringBuilder.toString());
                employeeCreditInfoService.updateGrainIdByEmployeeIdSelective(employeeCreditInfoTemp);
            } else {
                String res = seckillId + ":" + count + ",";
                EmployeeCreditInfo employeeCreditInfoTemp = new EmployeeCreditInfo();
                employeeCreditInfoTemp.setEmployeeId(employeeId);
                employeeCreditInfoTemp.setReceivedSeckillIds(res);
                employeeCreditInfoService.updateGrainIdByEmployeeIdSelective(employeeCreditInfoTemp);
            }
        }
        //支付状态更新订单支付时间
        if (orderSaveBO.getOrderStatus() == OrderEnum.STATUS.PAID.getCode()) {
            String employeeNo = null;
            Long enterprId;
            if (sessionEmployeeInfo != null) {
                employeeNo = sessionEmployeeInfo.getEmployeeNo();
                enterprId = sessionEmployeeInfo.getEnterprId();
            } else {
                EmployeeInfoDto employeeInfoDto = employeeUserInfoService.findByEmployeeId(orderSaveBO.getAccountId()
                        , false);
                employeeNo = employeeInfoDto.getEmployeeNo();
                enterprId = employeeInfoDto.getEnterprId();
            }
            orderService.modifyBatchOrderPayStatus(orderNumbers, 0L);
            //虚拟商品更改订单状态为已发货
            if (CollectionUtils.isNotEmpty(virtualOrders)) {
                List<Long> itemOrderNumberLists = CollectionExtUtil.getPropertyList(virtualOrders, Order::getNumber);
                List<Long> itemParentOrderNumbers = CollectionExtUtil.getPropertyList(virtualOrders,
                        Order::getParentNumber);
                itemOrderNumberLists.addAll(itemParentOrderNumbers);
                orderService.modifyBatchOrderShipStatus(itemOrderNumberLists, 0L);
                //更新卡密状态
                CardKeyStorage record = new CardKeyStorage();
                record.setStatus(CardKeyStatusEnum.DELIVERED.status);
                record.setDeadTime(DateTimeUtils.getDate());
                cardKeyStorageMapper.updateStatusBySkuAndNum(record, virtualOrders);
            }
            // 处理代理商收益
            agentCashService.addOrderIncomeRecorder(orderSaveBO.getItemOrderExList(), productOrders,
                    orderSaveBO.getPayOrderNumber());
            // 第三方订单处理
            if (CollectionUtils.isNotEmpty(thirdOrderList)) {
                thirdOrderHandle(thirdOrderList);
            }
            // 处理易豹逻辑
            if (enterprId.equals(yibaoProperties.getEnterprId())) {
                yibaoApiService.sendOrderMessage(employeeNo, orderSaveBO.getPayOrderNumber(), true);
            }

            //更新优惠卷已使用的叠加
            if (StringUtil.isNotEmpty(orderSaveBO.getVoucherSkus())) {
                voucherService.updateVoucherUsed(orderSaveBO.getVoucherSkus());
            }
            //处理秒杀已购买量的叠加
            if (StringUtil.isNotEmpty(orderSaveBO.getSeckillNum())) {
                String[] seckills = orderSaveBO.getSeckillNum().split(":");
                Integer count = Integer.parseInt(seckills[1]);
                Long seckillId = Long.parseLong(seckills[0]);
                Seckill temp = seckillService.getSeckill(seckillId);
                Seckill seckill = new Seckill();
                seckill.setSeckillId(seckillId);
                seckill.setAlreadyBuy(count + temp.getAlreadyBuy());
                seckill.setLeftStorage(temp.getLeftStorage() - count);
                seckillService.updateSeckill(seckill);
            }

        } else {
            //处理秒杀库存的扣除
            if (StringUtil.isNotEmpty(orderSaveBO.getSeckillNum())) {
                String[] seckills = orderSaveBO.getSeckillNum().split(":");
                Integer count = Integer.parseInt(seckills[1]);
                Long seckillId = Long.parseLong(seckills[0]);
                Seckill temp = seckillService.getSeckill(seckillId);
                Seckill seckill = new Seckill();
                seckill.setSeckillId(seckillId);
                //seckill.setAlreadyBuy(count+temp.getAlreadyBuy());
                seckill.setLeftStorage(temp.getLeftStorage() - count);
                seckillService.updateSeckill(seckill);
            }
        }
    }

    @RedisLock(name = "order", keys = {"#sellerOrderNumber"})
    @Transactional(rollbackFor = Exception.class)
    public void cancelOrder(Long sellerOrderNumber, Long accountId, boolean isSystemCancel) {

        Order sellerOrder = getSellerOrder(sellerOrderNumber, accountId, isSystemCancel);
        List<Order> itemOrderList = orderService.listByParentNumberAndLevel(sellerOrderNumber, 3);
        if (itemOrderList.stream().anyMatch(o -> reverseSet.contains(o.getReverseStatus()))) {
            throw new BusinessException("该订单存在售后行为,不允许取消");
        }

        //处理秒杀库存的回收
        if (sellerOrder.getSeckillId() != null && sellerOrder.getNum() != null) {
            Integer count = sellerOrder.getNum();
            Long seckillId = sellerOrder.getSeckillId();
            Seckill temp = seckillService.getSeckill(seckillId);
            if (temp.getValidateEndTime().getTime() > System.currentTimeMillis() && System.currentTimeMillis() > temp.getValidateStartTime().getTime()) {
                Seckill seckill = new Seckill();
                seckill.setSeckillId(seckillId);
                //seckill.setAlreadyBuy(temp.getAlreadyBuy()-count);
                seckill.setLeftStorage(temp.getLeftStorage() + count);
                seckillService.updateSeckill(seckill);
            }
        }
        //订单取消，验证秒杀结束后回库存
        if (sellerOrder.getSeckillId() != null && sellerOrder.getNum() != null) {
            Long seckillId = sellerOrder.getSeckillId();
            Seckill seckill = seckillService.getSeckill(seckillId);
            Integer num = sellerOrder.getNum();
            seckillService.validateSeckillEffective(seckill, false);
            seckillService.returnSkuStorageBySeckillEnd(seckill, num);
        }
        //订单取消，用户还可以重复秒杀
        if (sellerOrder.getSeckillId() != null && sellerOrder.getNum() != null) {
            Integer count = sellerOrder.getNum();
            Long seckillId = sellerOrder.getSeckillId();
            EmployeeCreditInfo employeeCreditInfo =
                    employeeCreditInfoService.getEmployeeCreditInfo(sellerOrder.getBuyerUserId(), 0L);
            Seckill seckill = seckillService.getSeckill(seckillId);
            String receivedSeckillIds = employeeCreditInfo.getReceivedSeckillIds();
            StringBuilder stringBuilder = new StringBuilder();
            if (StringUtil.isNotEmpty(receivedSeckillIds)) {
                String[] receivedSingleSeckillIds = receivedSeckillIds.split(",");
                for (int i = 0, len = receivedSingleSeckillIds.length; i < len; i++) {
                    String[] temp = receivedSingleSeckillIds[i].split(":");
                    if (seckillId.equals(Long.parseLong(temp[0]))) {
                        count = Integer.parseInt(temp[1]) - count;
                        String res = temp[0] + ":" + count + ",";
                        stringBuilder.append(res);
                        continue;
                    }
                    stringBuilder.append(receivedSingleSeckillIds[i] + ",");
                }
                EmployeeCreditInfo employeeCreditInfoTemp = new EmployeeCreditInfo();
                employeeCreditInfoTemp.setEmployeeId(sellerOrder.getBuyerUserId());
                employeeCreditInfoTemp.setReceivedSeckillIds(stringBuilder.toString());
                employeeCreditInfoService.updateGrainIdByEmployeeIdSelective(employeeCreditInfoTemp);
            }
        }

        if (!isSystemCancel) {
            if (sellerOrder.getSellerId().equals(thirdPlaformSeller.getJd().getId())
                    || sellerOrder.getSellerId().equals(thirdPlaformSeller.getYx().getId())) {
                throw new BusinessException("该订单需联系客服取消");
            }
        }
        if (!isSystemCancel && (accountId == null || accountId == 0L)) {
            throw new BusinessException("非法操作");
        }
        orderService.cancelBySellerOrderNumber(accountId, sellerOrderNumber, sellerOrder.getParentNumber());

        /**非jd严选商品需返还库存逻辑*/
        if (!sellerOrder.getSellerId().equals(thirdPlaformSeller.getJd().getId())
                && !sellerOrder.getSellerId().equals(thirdPlaformSeller.getYx().getId())) {
            itemOrderList.forEach(item -> {
                //库存返还场景目前仅支持 待支付状态，已支付状态订单返还
                if (Objects.equals(sellerOrder.getStatus(), OrderEnum.STATUS.INIT.getCode())) {
                    //不是秒杀，归还Sku库存
                    if (sellerOrder.getSeckillId() == null && sellerOrder.getNum() == null) {
                        skuService.unPayReturnStock(item.getSkuId(), item.getQuantity());
                    }
                    //扣减已使用的优惠卷数量
//                    if(item.getVoucherId() != null && item.getVoucherId() != 0){
//                        Voucher voucher = voucherService.getVoucherByVoucherId(item.getVoucherId());
//                        Voucher voucherTemp = new Voucher();
//                        voucherTemp.setVoucherId(item.getVoucherId());
//                        voucherTemp.setUsedAmount(voucher.getUsedAmount() - 1);
//                        voucherService.updateVoucher(voucherTemp);
//                    }
                    //回收员工已领取的优惠卷
                    if (item.getVoucherId() != null && item.getVoucherId() != 0) {
                        List<Long> voucherIds = new ArrayList<>();
                        voucherIds.add(item.getVoucherId());
                        voucherService.updateEmployeeCreditInfoByRefundOrderVouchers(voucherIds,
                                sellerOrder.getBuyerUserId());
                    }
                } else if (Objects.equals(sellerOrder.getStatus(), OrderEnum.STATUS.PAID.getCode())) {
                    skuService.payReturnStock(item.getSkuId(), item.getQuantity());
                }
            });

        }
        try {
            //返还积分
            Long deductionPointAmount = sellerOrder.getPointAmount() + sellerOrder.getShipExpensePointAmount();
            if (deductionPointAmount > 0) {
                EnterprFestivalPacket festivalPacket = enterprFestivalService.getById(sellerOrder.getCampaignId());

                OrderCreditBO orderCreditBO = new OrderCreditBO();
                orderCreditBO.setCampaignId(festivalPacket.getCampaignId());
                orderCreditBO.setEmployeeId(sellerOrder.getBuyerUserId());
                orderCreditBO.setPayOrderNumber(sellerOrder.getParentNumber().toString());
                orderCreditBO.setCreditType(festivalPacket.getCreditType());
                orderCreditBO.setOptType(OptTypeEnum.USER_REFUND_ADD.getValue());
                orderCreditBO.setRemark(OptTypeEnum.USER_REFUND_ADD.getDesc());
                orderCreditBO.setReduceCredit(MoneyUtil.changeF2Y(deductionPointAmount.intValue()));
                if (sellerOrder.getCardAmount() != null) {
                    orderCreditBO.setCardAmount(MoneyUtil.changeF2Y(sellerOrder.getCardAmount().intValue()));
                }
                employeeCreditInfoService.modifyEmployeeCredit4Order(orderCreditBO);
            }

        } catch (Exception e) {
            throw new BusinessException("取消订单失败");
        }

        if (Objects.equals(sellerOrder.getStatus(), OrderEnum.STATUS.PAID.getCode())) {
            // 代理商扣除相应的订单收益
            agentCashService.addAbnormalOrderIncomeRecorde(itemOrderList.stream().map(item -> item.getNumber().toString()).collect(Collectors.toList()));
            //TODO 返还钱
            OrderPay orderPay = orderPayService.getByPayOrderNumber(sellerOrder.getParentNumber());
            if (Objects.nonNull(orderPay)) {

                Order payOrder = orderService.getOrderByNumber(sellerOrder.getParentNumber());
                Long totalFee = payOrder.getPrice() + payOrder.getShipExpense();
                Long refundNumber = idGenerator.generateRefundNumber();
                Long refundAmout = sellerOrder.getPrice() + sellerOrder.getShipExpense();
                OrderRefundBO refundBO = new OrderRefundBO();
                refundBO.setOperatorId(accountId.toString());
                refundBO.setOutRequestNo(sellerOrder.getNumber() + "-order-" + refundNumber);
                refundBO.setPayMark(orderPay.getPayMark());
                refundBO.setRefundApplyNumber(refundNumber);
                refundBO.setRefundAmount(MoneyUtil.changeF2Y(refundAmout.intValue()));
                refundBO.setTotalFee(MoneyUtil.changeF2Y(totalFee.intValue()));
                refundBO.setPayAccount(orderPay.getBuyerId());
                /**支付宝退款*/
                if (orderPay.getPayChannel().equals(PayChannelEnum.ALIPAY.getCode())) {

                    try {
                        orderAliPaySupport.alipayRefund(refundBO);
                    } catch (AlipayApiException e) {
                        log.error("退款打款失败", e);
                        throw new BusinessException("取消订单失败");
                    }
                } else if (orderPay.getPayChannel().equals(PayChannelEnum.WEIXINPAY.getCode())) {
                    /**微信退款*/
                    try {
                        orderWxPaySupport.wxRefund(refundBO);
                    } catch (Exception e) {
                        log.error("退款打款失败", e);
                        throw new BusinessException("取消订单失败");
                    }
                } else {
                    throw new BusinessException("支付渠道异常");
                }
            }
        } else if (Objects.equals(sellerOrder.getStatus(), OrderEnum.STATUS.INIT.getCode())) {
            /**未支付状态取消jd严选订单*/
            if (sellerOrder.getSellerId().equals(thirdPlaformSeller.getJd().getId())) {
                ijdOrderService.cancel(sellerOrder.getThirdOrderNumber());
            }
            /**严选订单无需清除*/
//            else if(sellerOrder.getSellerId().equals(thirdPlaformSeller.getYx().getId())){
//                iyxOrderService.cancel(sellerOrder.getThirdOrderNumber());
//            }
        }
        //返回优惠卷金额
//        List<Order> orders = orderService.listByNumberAndLevel(sellerOrderNumber,2);
//        List<Long> vouchers = new ArrayList<>();
//        if(orders.get(0).getVoucherId() != null){
//            vouchers.add(orders.get(0).getVoucherId());
//            voucherService.updateEmployeeCreditInfoByRefundOrderVouchers(vouchers);
//        }
    }


    @RedisLock(name = "confirmOverOrder", keys = {"#sellerOrderNumber"})
    @Transactional(rollbackFor = Exception.class)
    public void confirmOverOrder(Long sellerOrderNumber) {

        Order sellerOrder = getSellerOrder(sellerOrderNumber, null, true);
        List<Order> itemOrderList = orderService.listByParentNumberAndLevel(sellerOrderNumber, 3);
        if (itemOrderList.stream().anyMatch(o -> OrderEnum.REVERSE_STATUS.APPLY.getCode() == o.getReverseStatus())) {
            throw new BusinessException("该订单存在未完结售后行为,不允许完结");
        }
        orderService.confirmReceiptBySellerOrderNumber(sellerOrder.getBuyerUserId(), sellerOrderNumber,
                sellerOrder.getParentNumber());
    }

    /**
     * 第三方订单更新
     *
     * @param thirdOrderList
     */
    public void thirdOrderHandle(List<Order> thirdOrderList) {

        thirdOrderList.forEach(sellerOrder -> {
            if (sellerOrder.getSellerId().equals(thirdPlaformSeller.getJd().getId())) {
                //jd订单确认订单
                try {
                    ijdOrderService.confirmOrder(sellerOrder.getThirdOrderNumber());
                } catch (BusinessException e) {
                    log.error(String.format("京东订单确认失败，订单信息:%s", JSON.toJSONString(sellerOrder)));
                    log.error(String.format("京东订单确认失败，开始取消订单，订单信息:%s", JSON.toJSONString(sellerOrder)));
                    cancelOrder(sellerOrder.getNumber(), 0L, true);
                }
            } else if (sellerOrder.getSellerId().equals(thirdPlaformSeller.getYx().getId())) {
                //严选订单提交订单
                try {
                    YXChannelOrderOut orderOut = iyxOrderService.submit(sellerOrder.getNumber());
                    /**更新商家级&商品级订单第三方订单编号*/
                    orderService.modifyOrderThirdNumber(sellerOrder.getNumber(), orderOut.getOrderId());

                } catch (BusinessException e) {
                    log.error(String.format("严选提交订单失败，订单信息:%s", JSON.toJSONString(sellerOrder)));
                    log.error(String.format("严选提交订单失败，开始取消订单，订单信息:%s", JSON.toJSONString(sellerOrder)));
                    cancelOrder(sellerOrder.getNumber(), 0L, true);
                }
            }
        });

    }

    @RedisLock(name = "order", keys = {"#accountId"})
    @Transactional(rollbackFor = Exception.class)
    public void confirmReceipt(Long sellerOrderNumber, Long accountId) {
        Order sellerOrder = getSellerOrder(sellerOrderNumber, accountId, false);
        orderService.confirmReceiptBySellerOrderNumber(accountId, sellerOrderNumber, sellerOrder.getParentNumber());
    }

    @Transactional(rollbackFor = Exception.class)
    public void shipOrder(Long itemOrderNumber, List<OrderLogisticsParam> logistics, Long supplierId) {

        List<Order> orderList = orderService.listByNumberAndLevel(itemOrderNumber, 3);
        if (CollectionUtils.isEmpty(orderList)) {
            throw new BusinessException("订单不存在");
        }
        Order order = orderList.get(0);
        if (!order.getSellerId().equals(supplierId)) {
            throw new BusinessException("非法操作");
        }
        if (reverseSet.contains(order.getReverseStatus())) {
            throw new BusinessException("该订单存在售后申请：发货失败");
        }
        Order sellerOrder = orderService.getOrderByNumber(order.getParentNumber());
        if (sellerOrder.getStatus() != OrderEnum.STATUS.PAID.getCode()
                && sellerOrder.getStatus() != OrderEnum.STATUS.SHIPPED.getCode()) {
            throw new BusinessException("该订单当前状态不允许发货");
        }
        Date date = Calendar.getInstance().getTime();
        List<OrderShipLogistics> orderShipLogistics = CollectionExtUtil.copyListWithCheck(logistics, logistic -> {
            OrderShipLogistics orderShip = new OrderShipLogistics();
            orderShip.setSellerOrderNumber(order.getParentNumber());
            orderShip.setItemOrderNumber(order.getNumber());
            orderShip.setShipTime(date);
            orderShip.setShipExpress(logistic.getLogisticsChannel());
            orderShip.setShipExpressNumber(logistic.getLogisticsNumber());
            String logisticRemark = logistic.getRemark();
            if (!StringUtils.isEmpty(logisticRemark)) {
                orderShip.setRemark(logisticRemark.trim());
            }
            return orderShip;
        });
        orderShipLogisticsService.doAddBatchRecord(orderShipLogistics);
        orderService.modifyOrderShipStatus(order.getNumber(), order.getParentNumber());
    }


    /**
     * 获取支付级订单，不存在则创建一个支付级订单
     *
     * @param payOrderNumber 店铺级订单
     * @param accountId
     * @return
     */
    @RedisLock(name = "orderPay", keys = {"#accountId"}, keepMills = 2000L)
    @Transactional(rollbackFor = Exception.class)
    public Order getOrCreatePayOrder(Long payOrderNumber, Long accountId) {
        Order payOrder;
        List<Order> orderList = orderService.listByNumberAndLevel(payOrderNumber, 1);
        if (CollectionUtils.isEmpty(orderList)) {
            orderList = orderService.listByNumberAndLevel(payOrderNumber, 2);
            if (CollectionUtils.isEmpty(orderList)) {
                throw new BusinessException("订单不存在");
            }
            Order sellerOrder = orderList.get(0);
//            List<Order> payOrders = orderService.listByNumberAndLevel(sellerOrder.getParentNumber(),1);
//            if(null != payOrders && !payOrders.isEmpty() && payOrders.get(0).getNum() == 1 ){
//                throw new BusinessException("每个拆分级订单只能生成一个支付级订单号！");
//            }
            //改变等级为1，使其成为支付级订单号，不是等级0
            Long payNumber = idGenerator.generateOrderNumber(1);
            payOrder = BeanUtil.copySpring(sellerOrder, Order.class);
            payOrder.setId(null);
            payOrder.setLevel(1);
            payOrder.setNumber(payNumber);
            payOrder.setParentNumber(0L);
            payOrder.setNum(1);
            orderService.doAddRecord(payOrder);
            if (orderService.doModSellerOrderParentNumber(sellerOrder.getNumber(), payNumber) != 1) {
                throw new BusinessException("支付失败");
            }
        } else {
            payOrder = orderList.get(0);
        }
        if (orderList.size() != 1) {
            throw new BusinessException("支付失败");
        }
        /** 用户不匹配或者订单状态不是未支付 */
        if (!payOrder.getBuyerUserId().equals(accountId)
                || !payOrder.getStatus().equals(OrderEnum.STATUS.INIT.getCode())) {
            throw new BusinessException("支付失败");
        }
        return payOrder;
    }


    /**
     * 下单信息组装
     *
     * @param orderSubmitParam
     * @return
     */
    @RedisLock(name = "order", keys = {"#orderSubmitParam.accountId"})
    public OrderSaveBO assembleOrder(OrderSubmitParam orderSubmitParam) {
        //用户ID获取
        Long accountId = orderSubmitParam.getAccountId();
        Integer platform = orderSubmitParam.getPlatform();
        List<Integer> skuIdList = Lists.newArrayList();
        Map<Long, Integer> skuParamMap = Maps.newHashMap();
        List<Long> sellerIdList = Lists.newArrayList();
        Map<Long, String> buyerCommentMap = Maps.newHashMap();
        List<Long> cartProductIdList = Lists.newArrayList();

        if (orderSubmitParam.getSource() == 0) {
            List<CartProduct> cartProductList = cartProductService.listCheckedByAccountId(accountId);
            if (CollectionUtils.isEmpty(cartProductList)) {
                throw new BusinessException("请选择商品");
            }
            cartProductList.forEach(cartProduct -> {
                cartProductIdList.add(cartProduct.getId());
                sellerIdList.add(cartProduct.getSellerId());
                skuIdList.add(cartProduct.getSkuId().intValue());
                skuParamMap.put(cartProduct.getSkuId(), cartProduct.getCount());
            });
            orderSubmitParam.getSellerOrderList().forEach(sellerOrderParam -> buyerCommentMap.put(sellerOrderParam.getSellerId(), BeanUtil.getOrDefault(sellerOrderParam.getBuyerComment(), "")));
        } else {
            orderSubmitParam.getSellerOrderList().forEach(sellerOrderParam -> {
                if (CollectionUtils.isEmpty(sellerOrderParam.getItemList())) {
                    throw new BusinessException("请选择商品");
                }
                sellerIdList.add(sellerOrderParam.getSellerId());
                buyerCommentMap.put(sellerOrderParam.getSellerId(),
                        BeanUtil.getOrDefault(sellerOrderParam.getBuyerComment(), ""));
                sellerOrderParam.getItemList().forEach(skuParam -> {
                    skuIdList.add(skuParam.getSkuId());
                    skuParamMap.put(skuParam.getSkuId().longValue(), skuParam.getQuantity());
                });
            });
        }

        List<SkuEntity> skus = skuService.listByIdList(skuIdList);
        List<Long> spuCodeList = CollectionExtUtil.getPropertyList(skus, SkuEntity::getSpuCode);
        /**节日礼金查询验证商品是否都在节日礼金活动中 校验*/
        Long topicId = orderSubmitParam.getTopicId();
        if ((orderSubmitParam.getCampaginId() != 0L || topicId != null && topicId != 0L)
                && !productService.checkProductMix(orderSubmitParam.getCampaginId(), topicId, spuCodeList)) {
            throw new BusinessException("活动无效或商品不在活动里！");
        }
        ConsigneeAdress userReceiveAddress = adressDao.getAddressById(orderSubmitParam.getUserReceiveAddrId());
        if (Objects.isNull(userReceiveAddress)) {
            throw new BusinessException("请选择收货地址");
        }
        if (!userReceiveAddress.getUserid().equals(accountId)) {
            throw new BusinessException("非法请求");
        }
        List<ProductEntity> productList = productService.listBySpuCodeList(spuCodeList);
        Map<Long, ProductEntity> productMap = CollectionExtUtil.toMap(productList, ProductEntity::getSpuCode);
        /**筛选商家状态商品*/
        List<SkuEntity> skuList = Lists.newArrayListWithExpectedSize(skus.size());
        Long enterprId;
        SessionShopInfo sessionEmployeeInfo = SessionContextHolder.getSessionEmployeeInfo();
        if (sessionEmployeeInfo != null) {
            enterprId = sessionEmployeeInfo.getEnterprId();
        } else {
            EmployeeInfoDto employeeInfoDto = employeeUserInfoService.findByEmployeeId(accountId, false);
            enterprId = employeeInfoDto.getEnterprId();
        }
        skus.forEach(skuEntity -> {
            ProductEntity productEntity = productMap.get(skuEntity.getSpuCode());
            Boolean isCustomer = null;
            if (productEntity.getGoodsState() != 1) {
                Map<Long, SkuPriceBean> customPrice = skuService.singleCustomPrice(Arrays.asList(skuEntity.getId()),
                        enterprId);
                SkuPriceBean skuPriceBean = customPrice.get(skuEntity.getId());
                if (skuPriceBean != null && skuPriceBean.isCustomer()) {
                    isCustomer = true;
                } else {
                    throw new BusinessException(String.format("【%s】已下架", productEntity.getGoodsName()));
                }
            }
            if (!Integer.valueOf(1).equals(skuEntity.getStatus())) {
                if (isCustomer == null) {
                    Map<Long, SkuPriceBean> customPrice =
                            skuService.singleCustomPrice(Arrays.asList(skuEntity.getId()), enterprId);
                    SkuPriceBean skuPriceBean = customPrice.get(skuEntity.getId());
                    isCustomer = skuPriceBean == null ? false : skuPriceBean.isCustomer();
                }
                if (!isCustomer) {
                    SkuEntity skuDetailInfo = skuService.getSkuDetailInfo(skuEntity.getId());
                    String goodsSpec = skuDetailInfo.getGoodsSpec();
                    if (!StringUtils.isEmpty(goodsSpec)) {
                        throw new BusinessException("抱歉,【" + skuDetailInfo.getGoodsName() + "(" + skuDetailInfo.getGoodsSpec() + ")】已下架");
                    } else {
                        throw new BusinessException("抱歉,【" + skuDetailInfo.getGoodsName() + "】已下架");
                    }
                }
            }
            Seckill seckill = null;
            if (StringUtil.isNotEmpty(orderSubmitParam.getSeckillNum())) {
                Long seckillId = Long.parseLong(orderSubmitParam.getSeckillNum().split(":")[0]);
                seckill = seckillService.getSeckill(seckillId);
                skuEntity.setSeckillId(seckillId);
            }
            if (seckill == null && skuEntity.getGoodsStorge() < skuParamMap.get(skuEntity.getId()) || seckill != null && seckill.getLeftStorage() < 1) {
                throw new BusinessException(String.format("【%s】库存不足", productEntity.getGoodsName()));
            }
            skuList.add(skuEntity);
        });
        if (CollectionUtils.isEmpty(skuList)) {
            throw new BusinessException("请选择有效商品");
        }

        List<SupplierInfo> supplierInfoList = supplierInfoService.listByIds(sellerIdList);
        EnterprFestivalPacket festivalPacket = enterprFestivalService.getById(orderSubmitParam.getCampaginId());
        Long campaginId = (festivalPacket.getCreditType().equals(CreditTypeEnum.PUTONG.getValue())
                || festivalPacket.getCreditType().equals(CreditTypeEnum.ADVANCESALE.getValue())) ? 0 :
                festivalPacket.getCampaignId();
        EmployeeCreditInfo employeeCreditInfo = employeeCreditInfoService.getEmployeeCreditInfo(accountId, campaginId);
        /**是否必须积分支付判断*/
        if (yibaoApiService.isBelongTo(enterprId)// 易豹企业必须全积分支付
                || festivalPacket.getEnterprId() != null && !orderSubmitParam.isOnlyPointPay() && festivalPacket.getThirdPay() == 0) {
            orderSubmitParam.setOnlyPointPay(true);
        }

        Map<Long, String> supplierMap = CollectionExtUtil.toMap(supplierInfoList, SupplierInfo::getId,
                SupplierInfo::getName);
        List<SkuBO> skuBOList = assembleSkuBOS(skuParamMap, skuList, productMap, supplierMap,
                orderSubmitParam.getEnterprId());
        //修改秒杀商品的秒杀价
        for (int i = 0, len = skuBOList.size(); i < len; i++) {
            if (StringUtil.isNotEmpty(orderSubmitParam.getSeckillNum())) {
                Long seckillId = Long.parseLong(orderSubmitParam.getSeckillNum().split(":")[0]);
                Seckill seckill = seckillService.getSeckill(seckillId);
                if (seckill.getSkuId().equals(skuBOList.get(i).getId())) {
                    skuBOList.get(i).setGoodsPrice(seckill.getSeckillPrice().multiply(new BigDecimal(100)).longValue());
                }
            }
        }
        Map<Long, List<SkuBO>> listMap = CollectionExtUtil.groupAndMapping(skuBOList, SkuBO::getSellerId, s -> s);


        /**第三方订单初始化*/
        Map<Long, Order> sellerOrderMap = Maps.newHashMap();
        /**第三方订单运费初始化*/
        Map<Long, Integer> otherPlatformSellerFreightMap = Maps.newHashMap();
        if (listMap.get(thirdPlaformSeller.getJd().getId()) != null) {
            /**jd订单提交订单*/
            Long sellerOrderNumber = idGenerator.generateOrderNumber(2);
            JDOrderSubOrder jdOrderSubOrder = ijdOrderService.submitOrder(sellerOrderNumber,
                    listMap.get(thirdPlaformSeller.getJd().getId()), userReceiveAddress);
            Order sellerOrder = new Order();
            sellerOrder.setNumber(sellerOrderNumber);
            sellerOrder.setThirdOrderNumber(jdOrderSubOrder.getJdOrderId().toString());

            sellerOrderMap.put(thirdPlaformSeller.getJd().getId(), sellerOrder);
            otherPlatformSellerFreightMap.put(thirdPlaformSeller.getJd().getId(),
                    MoneyUtil.changeY2F(jdOrderSubOrder.getFreight()));
        }
        if (listMap.get(thirdPlaformSeller.getYx().getId()) != null) {
            /**严选订单运费*/
            List<SkuBO> skuBOS = listMap.get(thirdPlaformSeller.getYx().getId());
            long totalGoodsPrice =
                    skuBOS.stream().mapToLong(skuBO -> skuBO.getGoodsPrice() * skuBO.getQuantity()).sum();
            /**严选订单88包邮，不满88每单收取10元运费*/
            Integer yxFreight = iyxOrderService.freight(totalGoodsPrice);
            otherPlatformSellerFreightMap.put(thirdPlaformSeller.getYx().getId(), yxFreight);
            Long sellerOrderNumber = idGenerator.generateOrderNumber(2);
            Order sellerOrder = new Order();
            sellerOrder.setNumber(sellerOrderNumber);
            sellerOrder.setThirdOrderNumber("");
            sellerOrderMap.put(thirdPlaformSeller.getYx().getId(), sellerOrder);
        }


        /**订单总金额*/
        Long totalAmount = skuBOList.stream().mapToLong(skuBO -> sellerOrderMap.get(skuBO.getSellerId()) != null ?
                skuBO.getGoodsPrice() * skuBO.getQuantity() :
                (skuBO.getGoodsPrice() + skuBO.getGoodsFreight()) * skuBO.getQuantity()).sum();
        /**累加jd严选运费*/
        for (Map.Entry<Long, Integer> e : otherPlatformSellerFreightMap.entrySet()) {
            totalAmount += e.getValue();
        }
        Long userAmount = Objects.isNull(employeeCreditInfo) ? 0L :
                MoneyUtil.changeY2F(employeeCreditInfo.getCredit()).longValue();
        if (orderSubmitParam.isOnlyPointPay() && totalAmount > userAmount) {
            throw new BusinessException("该订单只允许积分支付,积分不足");
        }
        //订单总金额扣除优惠卷的金额
        //totalAmount = totalAmount -findTotalVouchersForPayOrder(orderSubmitParam.getVoucherSkus());
        Long canUsePointAmount = totalAmount < userAmount ? totalAmount : userAmount;
        /**需扣除积分*/
        Long deductionPointAmount = canUsePointAmount;
        /**全部积分支付时，订单状态改为已支付*/
        int orderStatus = totalAmount.equals(canUsePointAmount) ? OrderEnum.STATUS.PAID.getCode() :
                OrderEnum.STATUS.INIT.getCode();

        List<Order> orderList = Lists.newArrayList();
        List<ProductOrderEx> itemOrderExList = Lists.newArrayList();
        List<OrderShip> orderShipList = Lists.newArrayList();
        /**支付级订单*/
        Order payOrder = new Order();
        payOrder.setNumber(idGenerator.generateOrderNumber(1));
        payOrder.setParentNumber(0L);
        payOrder.setStatus(0);
        payOrder.setLevel(1);
        payOrder.setSellerId(0L);
        payOrder.setPlatform(platform);
        payOrder.setBuyerUserId(accountId);
        payOrder.setBuyerComment("");
        payOrder.setSpuCode(0L);
        payOrder.setSkuId(0L);
        payOrder.setCampaignId(orderSubmitParam.getCampaginId());
        payOrder.setQuantity(0);
        payOrder.setMarketplace(0);
        payOrder.setPrice(0L);
        payOrder.setPointAmount(0L);
        payOrder.setShipExpense(0);
        payOrder.setShipExpensePointAmount(0);
        payOrder.setSource(orderSubmitParam.getSource());
        payOrder.setThirdOrderNumber("");


        for (Map.Entry<Long, List<SkuBO>> entry : listMap.entrySet()) {
            Long sellerId = entry.getKey();
            List<SkuBO> skuBOS = entry.getValue();

            Order sellerOrder;
            if (sellerOrderMap.get(sellerId) != null) {
                sellerOrder = sellerOrderMap.get(sellerId);
            } else {
                sellerOrder = new Order();
                Long sellerOrderNumber = idGenerator.generateOrderNumber(2);
                sellerOrder.setNumber(sellerOrderNumber);
                sellerOrder.setThirdOrderNumber("");
            }

            sellerOrder.setParentNumber(payOrder.getNumber());
            sellerOrder.setLevel(2);
            sellerOrder.setStatus(0);
            sellerOrder.setSellerId(sellerId);
            sellerOrder.setPlatform(platform);
            sellerOrder.setBuyerUserId(accountId);
            sellerOrder.setBuyerComment(buyerCommentMap.get(sellerId));
            sellerOrder.setSpuCode(0L);
            sellerOrder.setSkuId(0L);
            sellerOrder.setCampaignId(orderSubmitParam.getCampaginId());
            sellerOrder.setQuantity(0);
            sellerOrder.setMarketplace(0);
            sellerOrder.setPrice(0L);
            sellerOrder.setPointAmount(0L);
            sellerOrder.setShipExpense(0);
            sellerOrder.setShipExpensePointAmount(0);
            sellerOrder.setSource(orderSubmitParam.getSource());


            Long sellerGoodsAmount = null;
            Long freight = null;
            if (otherPlatformSellerFreightMap.get(sellerId) != null) {
                freight = otherPlatformSellerFreightMap.get(sellerId).longValue();
                sellerGoodsAmount = skuBOS.stream().mapToLong(item -> item.getGoodsPrice() * item.getQuantity()).sum();
            }
            Long remainingFreight = freight;
            int skuSize = skuBOS.size();
            int index = 0;
            for (SkuBO skuBO : skuBOS) {

                long itemPrice = skuBO.getGoodsPrice() * skuBO.getQuantity();
                long itemFreight;
                /**判断是否有第三方运费*/
                if (freight == null) {
                    itemFreight = skuBO.getGoodsFreight() * skuBO.getQuantity();
                } else {
                    if (index != (skuSize - 1)) {
                        itemFreight = (long) Math.ceil(freight * itemPrice / (sellerGoodsAmount * 1.0));
                        remainingFreight -= itemFreight;
                    } else {
                        itemFreight = remainingFreight;
                    }
                }
                /**积分分摊*/
                long apportionPointAmount = (long) Math.ceil(deductionPointAmount * itemPrice / (totalAmount * 1.0));
                if (canUsePointAmount > 0L) {
                    if (canUsePointAmount < apportionPointAmount) {
                        apportionPointAmount = canUsePointAmount;
                    }
                    canUsePointAmount -= apportionPointAmount;
                } else {
                    apportionPointAmount = 0L;
                }
                long apportionFreightPointAmount =
                        (long) Math.ceil(deductionPointAmount * itemFreight / (totalAmount * 1.0));
                if (canUsePointAmount > 0L) {
                    if (canUsePointAmount < apportionFreightPointAmount) {
                        apportionFreightPointAmount = canUsePointAmount;
                    }
                    canUsePointAmount -= apportionFreightPointAmount;
                } else {
                    apportionFreightPointAmount = 0L;
                }

                long apportionItemPrice = itemPrice - apportionPointAmount;
                long apportionItemFreight = itemFreight - apportionFreightPointAmount;

                Order itemOrder = new Order();

                itemOrder.setNumber(idGenerator.generateOrderNumber(3));
                itemOrder.setParentNumber(sellerOrder.getNumber());
                itemOrder.setLevel(3);
                itemOrder.setStatus(0);
                itemOrder.setSellerId(sellerId);
                itemOrder.setPlatform(platform);
                itemOrder.setBuyerUserId(accountId);
                itemOrder.setBuyerComment(buyerCommentMap.get(sellerId));
                itemOrder.setSpuCode(skuBO.getSpuCode());
                itemOrder.setSkuId(skuBO.getId());
                //设置商品级订单下优惠卷优惠的金额（含拆分）
                //itemOrder.setVoucherBenefit(findVoucherBySkuId(skuBO.getId(),orderSubmitParam.getVoucherSkus()));
                if (StringUtil.isNotEmpty(orderSubmitParam.getVoucherSkus())) {
                    String[] skuInfos = orderSubmitParam.getVoucherSkus().split(",");
                    String[] skuIds = skuInfos[0].split(":");
                    String[] goodsProportionPrices = skuInfos[1].split(":");
                    for (int j = 0, len = skuIds.length; j < len; j++) {
                        if (skuBO.getId().equals(Long.parseLong(skuIds[j]))) {
                            Long goodsProportion =
                                    new BigDecimal(goodsProportionPrices[j]).multiply(new BigDecimal(100)).longValue();
                            itemOrder.setVoucherBenefit(goodsProportion);
                        }
                    }
                }
                itemOrder.setCampaignId(orderSubmitParam.getCampaginId());
                itemOrder.setQuantity(skuBO.getQuantity());
                itemOrder.setMarketplace(0);
                //设置商品级订单的秒杀ID及秒杀商品数量
                if (StringUtil.isNotEmpty(orderSubmitParam.getSeckillNum())) {
                    Long seckillId = Long.parseLong(orderSubmitParam.getSeckillNum().split(":")[0]);
                    Integer count = Integer.parseInt(orderSubmitParam.getSeckillNum().split(":")[1]);
                    Seckill seckill = seckillService.getSeckill(seckillId);
                    if (seckill.getSkuId().equals(skuBO.getId())) {
                        itemOrder.setSeckillId(seckillId);
                    }
                    itemOrder.setNum(count);
                }
                itemOrder.setPrice(apportionItemPrice);
                itemOrder.setPointAmount(apportionPointAmount);
                Long priceTemp = itemOrder.getPrice();
                //设置商品优惠卷优惠后的价格
                if (StringUtil.isNotEmpty(orderSubmitParam.getVoucherSkus())) {
                    String[] sellerIds = orderSubmitParam.getVoucherSkus().split(";");
                    for (int i = 0, len = sellerIds.length; i < len; i++) {
                        String[] skuInfos = sellerIds[i].split(",");
                        String[] skuIds = skuInfos[0].split(":");
                        String[] goodsProportionPrices = skuInfos[1].split(":");
                        for (int j = 0, len1 = skuIds.length; j < len1; j++) {
                            if (skuBO.getId().equals(Long.parseLong(skuIds[j]))) {
                                Long goodsProportion =
                                        new BigDecimal(goodsProportionPrices[j]).multiply(new BigDecimal(100)).longValue();
                                itemOrder.setPrice(priceTemp - (long) Math.ceil(priceTemp / (priceTemp + itemOrder.getPointAmount()) * (goodsProportion * 1.0)));
                                itemOrder.setPointAmount(itemOrder.getPointAmount() - (goodsProportion - (long) Math.ceil(priceTemp / (priceTemp + itemOrder.getPointAmount()) * (goodsProportion * 1.0))));
                            }
                        }
                    }
                }
                //设置商品级订单的优惠卷ID
                if (StringUtil.isNotEmpty(orderSubmitParam.getVoucherSkus())) {
                    String[] skuInfos = orderSubmitParam.getVoucherSkus().split(",");
                    String[] skuIds = skuInfos[0].split(":");
                    Long voucherId = Long.parseLong(skuInfos[2]);
                    for (int j = 0, len1 = skuIds.length; j < len1; j++) {
                        if (skuBO.getId().equals(Long.parseLong(skuIds[j]))) {
                            itemOrder.setVoucherId(voucherId);
                        }
                    }
                }
                itemOrder.setShipExpense((int) apportionItemFreight);
                itemOrder.setShipExpensePointAmount((int) apportionFreightPointAmount);
                itemOrder.setSource(orderSubmitParam.getSource());
                itemOrder.setThirdOrderNumber(sellerOrder.getThirdOrderNumber());

                sellerOrder.setQuantity(sellerOrder.getQuantity() + itemOrder.getQuantity());
                sellerOrder.setPrice(sellerOrder.getPrice() + itemOrder.getPrice());
                //设置商家级优惠卷优惠的金额
                sellerOrder.setVoucherBenefit((sellerOrder.getVoucherBenefit() != null ?
                        sellerOrder.getVoucherBenefit() : 0) + (itemOrder.getVoucherBenefit() != null ?
                        itemOrder.getVoucherBenefit() : 0));
                sellerOrder.setPointAmount(sellerOrder.getPointAmount() + itemOrder.getPointAmount());
                //给商品订单设置优惠卷ID
                //sellerOrder.setVoucherId(findVoucherBySellerId(sellerId,orderSubmitParam.getVoucherSkus()));
                sellerOrder.setShipExpense(sellerOrder.getShipExpense() + itemOrder.getShipExpense());
                sellerOrder.setShipExpensePointAmount(sellerOrder.getShipExpensePointAmount() + itemOrder.getShipExpensePointAmount());
                //设置商家级订单的秒杀ID及秒杀商品数量
                if (StringUtil.isNotEmpty(orderSubmitParam.getSeckillNum())) {
                    Long seckillId = Long.parseLong(orderSubmitParam.getSeckillNum().split(":")[0]);
                    Integer count = Integer.parseInt(orderSubmitParam.getSeckillNum().split(":")[1]);
                    sellerOrder.setSeckillId(seckillId);
                    sellerOrder.setNum(count);
                }

                orderList.add(itemOrder);

                ProductOrderEx itemOrderEx = new ProductOrderEx();
                itemOrderEx.setOrderNumber(itemOrder.getNumber());
                itemOrderEx.setBuyerUserId(accountId);
                itemOrderEx.setSkuId(skuBO.getId());
                itemOrderEx.setCostPrice(skuBO.getCostPrice());
                //TODO 用途为确认暂定为商家ID
                itemOrderEx.setDeliverAreaId(sellerId);
                //TODO 用途为确认暂定为skuCode
                itemOrderEx.setDeliverCode(skuBO.getSkuCode());
                itemOrderEx.setImage(skuBO.getGoodsImage());
                itemOrderEx.setPrice(skuBO.getGoodsMarketprice());
                itemOrderEx.setNowPrice(skuBO.getGoodsPrice());
                itemOrderEx.setTitle(skuBO.getGoodsName());
                itemOrderEx.setTitleEn(skuBO.getGoodsNameEn());
                itemOrderEx.setSpec(skuBO.getGoodsSpec());

                itemOrderExList.add(itemOrderEx);
                index++;
            }

            orderList.add(sellerOrder);

            OrderShip orderShip = new OrderShip();
            orderShip.setOrderNumber(sellerOrder.getNumber());
            orderShip.setBuyerUserId(accountId);
            orderShip.setShipToName(userReceiveAddress.getName());
            orderShip.setShipToProvince(userReceiveAddress.getProvince());
            orderShip.setShipToCity(userReceiveAddress.getCity());
            orderShip.setShipToDistrict(userReceiveAddress.getDistrict());
            orderShip.setShipToTown(userReceiveAddress.getStreet());
            orderShip.setShipToAddress(userReceiveAddress.getInfo());
            orderShip.setShipToZip(BeanUtil.getOrDefault(userReceiveAddress.getZipCode(), ""));
            orderShip.setShipToMobile(userReceiveAddress.getTel());
            //TODO 用户真实姓名待确认
            orderShip.setUserRealName(userReceiveAddress.getName());
            orderShip.setIdCardNo(userReceiveAddress.getIdentityCard());

            orderShipList.add(orderShip);

            payOrder.setQuantity(payOrder.getQuantity() + sellerOrder.getQuantity());
            payOrder.setPrice(payOrder.getPrice() + sellerOrder.getPrice());
            //设置支付级订单秒杀ID及秒杀商品数量
            if (StringUtil.isNotEmpty(orderSubmitParam.getSeckillNum())) {
                Long seckillId = Long.parseLong(orderSubmitParam.getSeckillNum().split(":")[0]);
                Integer count = Integer.parseInt(orderSubmitParam.getSeckillNum().split(":")[1]);
                payOrder.setNum(count);
                payOrder.setSeckillId(seckillId);
            }

            payOrder.setPointAmount(payOrder.getPointAmount() + sellerOrder.getPointAmount());
            payOrder.setShipExpense(payOrder.getShipExpense() + sellerOrder.getShipExpense());
            payOrder.setShipExpensePointAmount(payOrder.getShipExpensePointAmount() + sellerOrder.getShipExpensePointAmount());
            //设置支付级订单总优惠卷的优惠金额
            //payOrder.setVoucherBenefit(findTotalVouchersForPayOrder(orderSubmitParam.getVoucherIds()));
        }
        orderList.add(payOrder);

        if (CollectionUtils.isEmpty(orderList)
                || CollectionUtils.isEmpty(itemOrderExList)
                || CollectionUtils.isEmpty(orderShipList)) {
            throw new BusinessException("下单失败");
        }

        OrderSaveBO orderSaveBO = new OrderSaveBO();
        orderSaveBO.setAccountId(accountId);
        orderSaveBO.setCampaignId(orderSubmitParam.getCampaginId());
        orderSaveBO.setPayOrderNumber(payOrder.getNumber());
        orderSaveBO.setOrderStatus(orderStatus);
        orderSaveBO.setDeductionPointAmount(deductionPointAmount);
        orderSaveBO.setCartProductIdList(cartProductIdList);
        orderSaveBO.setOrderList(orderList);
        orderSaveBO.setItemOrderExList(itemOrderExList);
        orderSaveBO.setOrderShipList(orderShipList);
        orderSaveBO.setVoucherSkus(orderSubmitParam.getVoucherSkus());
        orderSaveBO.setSeckillNum(orderSubmitParam.getSeckillNum());
        //验证秒杀信息是否合格
        if (StringUtil.isNotEmpty(orderSubmitParam.getSeckillNum())) {
            String[] seckills = orderSubmitParam.getSeckillNum().split(":");
            Integer count = Integer.parseInt(seckills[1]);
            Long seckillId = Long.parseLong(seckills[0]);
            Seckill seckill = seckillService.getSeckill(seckillId);
            if (count > seckill.getLeftStorage()) {
                throw new BusinessException("秒杀库存不够！");
            }
            seckillService.validateSeckillEffective(seckill, true);
        }
        //验证优惠卷的扣减是否合格
        if (StringUtil.isNotEmpty(orderSubmitParam.getVoucherSkus())) {
            voucherService.validateEmployeeCreditInfoByOrderVouchers(orderSubmitParam.getVoucherSkus());
        }
        //处理优惠卷的扣减
        if (StringUtil.isNotEmpty(orderSaveBO.getVoucherSkus())) {
            voucherService.updateEmployeeCreditInfoByOrderVouchers(orderSaveBO.getVoucherSkus());
        }
        return orderSaveBO;
    }

    /**
     * 组装skuBO数据
     *
     * @param skuParamMap
     * @param skuList
     * @param productMap
     * @param supplierMap
     * @return
     */
    private List<SkuBO> assembleSkuBOS(Map<Long, Integer> skuParamMap,
                                       List<SkuEntity> skuList,
                                       Map<Long, ProductEntity> productMap,
                                       Map<Long, String> supplierMap,
                                       Long enterprId) {


        List<Long> skuIdList = CollectionExtUtil.getPropertyList(skuList, SkuEntity::getId);
        Map<Long, String> skuSpecValue = productService.getSkuSpecValue(skuIdList);
        Map<Long, SkuPriceBean> skuPriceMap = skuService.singleCustomPrice(skuIdList, enterprId);

        return CollectionExtUtil.copyListWithCheck(skuList, skuEntity -> {

            ProductEntity productEntity = productMap.get(skuEntity.getSpuCode());
            if (!skuPriceMap.get(skuEntity.getId()).isCustomer()) {
                if (productEntity.getGoodsState() != 1) {
                    throw new BusinessException(String.format("【%s】已下架", productEntity.getGoodsName()));
                }
                if (!Integer.valueOf(1).equals(skuEntity.getStatus())) {
                    SkuEntity skuDetailInfo = skuService.getSkuDetailInfo(skuEntity.getId());
                    throw new BusinessException("抱歉,【" + skuDetailInfo.getGoodsName() + "(" + skuDetailInfo.getGoodsSpec() + ")】已下架");
                }
            }
            Seckill seckill = null;
            if (skuEntity.getSeckillId() != null) {
                seckill = seckillService.getSeckill(skuEntity.getSeckillId());
            }
            if (seckill == null && skuEntity.getGoodsStorge() < skuParamMap.get(skuEntity.getId()) || seckill != null && seckill.getLeftStorage() < 1) {
                throw new BusinessException(String.format("【%s】库存不足", productEntity.getGoodsName()));
            }
            BigDecimal goodsPrice = skuPriceMap.get(skuEntity.getId()).getGoodsPrice();

            SkuBO skuBO = new SkuBO();
            skuBO.setProductId(skuEntity.getSpuCode());
            skuBO.setId(skuEntity.getId());
            //设置秒杀ID
            skuBO.setSeckillId(skuEntity.getSeckillId());
            skuBO.setSpuCode(skuEntity.getSpuCode());
            skuBO.setSkuCode(skuEntity.getSkuCode());
            skuBO.setGoodsSpec(skuSpecValue.getOrDefault(skuEntity.getId(), skuEntity.getGoodsSpec()));
            skuBO.setGoodsMarketprice(skuEntity.getGoodsMarkprice().multiply(CommonConstant.BIG_DECIMAL_100).longValue());
            skuBO.setGoodsStorge(skuEntity.getGoodsStorge());
            skuBO.setPayStorge(skuEntity.getPayStorge());
            String goodsImage = skuEntity.getGoodsImage();
            if (StringUtils.isEmpty(goodsImage)) {//商品主图
                goodsImage = productEntity.getGoodsImage();
            }
            skuBO.setGoodsImage(goodsImage);
            /*******/
            skuBO.setSellerId(productEntity.getSupplierId());
            skuBO.setBrandName(productEntity.getBrandName());
            skuBO.setGoodsType(productEntity.getGoodsType());
            skuBO.setIsCross(productEntity.getIsCross());
            String goodsName = skuEntity.getGoodsName();
            if (StringUtils.isEmpty(goodsName)) {// 商品名称
                goodsName = productEntity.getGoodsName();
            }
            skuBO.setGoodsName(goodsName);
            skuBO.setGoodsNameEn(productEntity.getGoodsNameEn());
            skuBO.setGoodsState(productEntity.getGoodsState());
            skuBO.setGoodsFreight(productEntity.getGoodsFreight().multiply(CommonConstant.BIG_DECIMAL_100).longValue());
            skuBO.setCostPrice(skuEntity.getGoodsCostprice().multiply(CommonConstant.BIG_DECIMAL_100).longValue());
            skuBO.setGoodsPrice(goodsPrice.multiply(CommonConstant.BIG_DECIMAL_100).longValue());
            skuBO.setQuantity(skuParamMap.get(skuEntity.getId()));
            skuBO.setSellerName(supplierMap.get(skuBO.getSellerId()));
            return skuBO;
        });
    }

    /**
     * 组装订单确认页数据
     *
     * @param supplierMap
     * @param skuBOList
     * @return
     */
    private OrderConfirmVO assembleOrderConfirmVO(Map<Long, String> supplierMap,
                                                  List<SkuBO> skuBOList,
                                                  Long accountId,
                                                  Long activityId,
                                                  Long userReceiveAddrId, Long seckillId) {


        Map<Long, List<SkuBO>> listMap = CollectionExtUtil.groupAndMapping(skuBOList, SkuBO::getSellerId, s -> s);
        List<OrderConfirmVO.SellerOrderVO> sellerOrderVOS = Lists.newArrayList();

        OrderConfirmVO confirmVO = new OrderConfirmVO();

        confirmVO.setTotalGoodsPrice(BigDecimal.ZERO);
        confirmVO.setTotalFreightPrice(BigDecimal.ZERO);
        for (Map.Entry<Long, List<SkuBO>> entry : listMap.entrySet()) {
            Long sellerId = entry.getKey();
            List<SkuBO> skuBOS = entry.getValue();
            OrderConfirmVO.SellerOrderVO sellerOrderVO = new OrderConfirmVO.SellerOrderVO();
            sellerOrderVO.setSellerId(sellerId);
            sellerOrderVO.setSellerName(supplierMap.get(sellerId));
            sellerOrderVO.setTotalFreightPrice(BigDecimal.ZERO);
            sellerOrderVO.setTotalGoodsPrice(BigDecimal.ZERO);
            List<OrderConfirmVO.ItemOrderVO> itemOrderVOS = Lists.newArrayList();


            for (SkuBO skuBO : skuBOS) {

                Long totalPrice = skuBO.getGoodsPrice() * skuBO.getQuantity();
                Long totalFreightPrice = skuBO.getGoodsFreight() * skuBO.getQuantity();

                OrderConfirmVO.ItemOrderVO itemOrderVO = new OrderConfirmVO.ItemOrderVO();
                itemOrderVO.setGoodsPrice(MoneyUtil.changeF2Y(skuBO.getGoodsPrice().intValue()));
                //设置秒杀的单个价格
                if (seckillId != null) {
                    Seckill seckill = seckillService.getSeckill(seckillId);
                    itemOrderVO.setGoodsPrice(seckill.getSeckillPrice());
                }
                itemOrderVO.setId(skuBO.getProductId());
                itemOrderVO.setSkuId(skuBO.getId());
                ProductEntity productEntity = productService.selectById(skuBO.getSpuCode());
                itemOrderVO.setSkuCode(skuBO.getSkuCode());
                itemOrderVO.setSpuCode(skuBO.getSpuCode());
                itemOrderVO.setTitle(skuBO.getGoodsName());
                itemOrderVO.setTitleEn(skuBO.getGoodsNameEn());
                List<Long> voucherIds = JsonUtil.parseObject(productEntity.getVoucherIds(),
                        new TypeReference<List<Long>>() {
                });
                if (null != voucherIds) {
                    voucherIds.addAll(voucherService.fingFullRangeVoucherIds());
                    itemOrderVO.setVouchers(findVouchersBySku(voucherIds));
                } else {
                    itemOrderVO.setVouchers(findVouchersBySku(voucherService.fingFullRangeVoucherIds()));
                }
                itemOrderVO.setGoodsImage(skuBO.getGoodsImage());
                itemOrderVO.setGoodsSpec(skuBO.getGoodsSpec());
                itemOrderVO.setQuantity(skuBO.getQuantity());
                itemOrderVO.setFreight(MoneyUtil.changeF2Y(skuBO.getGoodsFreight().intValue()));
                itemOrderVO.setTotalGoodsPrice(MoneyUtil.changeF2Y(totalPrice.intValue()));
                //设置秒杀的价格
                if (seckillId != null) {
                    Seckill seckill = seckillService.getSeckill(seckillId);
                    itemOrderVO.setTotalGoodsPrice(seckill.getSeckillPrice());
                }
                itemOrderVO.setTotalFreightPrice(MoneyUtil.changeF2Y(totalFreightPrice.intValue()));
                itemOrderVOS.add(itemOrderVO);
                sellerOrderVO.setTotalFreightPrice(sellerOrderVO.getTotalFreightPrice().add(itemOrderVO.getTotalFreightPrice()));
                sellerOrderVO.setTotalGoodsPrice(sellerOrderVO.getTotalGoodsPrice().add(itemOrderVO.getTotalGoodsPrice()));
            }

            if (Objects.nonNull(userReceiveAddrId) && userReceiveAddrId != 0) {
                /**京东运费计算*/
                if (thirdPlaformSeller.getJd().getId().equals(sellerOrderVO.getSellerId())) {
                    Double jdFreight = ijdOrderService.freight(skuBOS, userReceiveAddrId);
                    sellerOrderVO.setTotalFreightPrice(BigDecimal.valueOf(jdFreight));
                } else if (thirdPlaformSeller.getYx().getId().equals(sellerOrderVO.getSellerId())) {
                    /**严选订单运费*/

                    /**严选订单88包邮，不满88每单收取10元运费*/
                    Integer yxFreight =
                            iyxOrderService.freight(Long.valueOf(MoneyUtil.changeY2F(sellerOrderVO.getTotalGoodsPrice())));
                    sellerOrderVO.setTotalFreightPrice(MoneyUtil.changeF2Y(yxFreight));

                }
            }
            sellerOrderVO.setItemList(itemOrderVOS);
            sellerOrderVOS.add(sellerOrderVO);
            confirmVO.setTotalGoodsPrice(confirmVO.getTotalGoodsPrice().add(sellerOrderVO.getTotalGoodsPrice()));
            confirmVO.setTotalFreightPrice(confirmVO.getTotalFreightPrice().add(sellerOrderVO.getTotalFreightPrice()));

        }
        /**订单总金额*/
        Long totalAmount =
                (long) MoneyUtil.changeY2F(confirmVO.getTotalFreightPrice().add(confirmVO.getTotalGoodsPrice()).doubleValue());

        EnterprFestivalPacket festivalPacket = enterprFestivalService.getById(activityId);
        Long campaginId = festivalPacket.getCreditType().equals(CreditTypeEnum.PUTONG.getValue()) ? 0 :
                festivalPacket.getCampaignId();
        EmployeeCreditInfo employeeCreditInfo = employeeCreditInfoService.getEmployeeCreditInfo(accountId, campaginId);
        Long userAmount = Objects.isNull(employeeCreditInfo) ? 0L :
                MoneyUtil.changeY2F(employeeCreditInfo.getCredit()).longValue();
        Long canUsePointAmount = totalAmount < userAmount ? totalAmount : userAmount;
        BigDecimal totalRealPrice =
                confirmVO.getTotalGoodsPrice().add(confirmVO.getTotalFreightPrice()).subtract(MoneyUtil.changeF2Y(canUsePointAmount.intValue()));

        confirmVO.setPointAmount(MoneyUtil.changeF2Y(userAmount.intValue()));
        confirmVO.setCanUsePointAmount(MoneyUtil.changeF2Y(canUsePointAmount.intValue()));
        confirmVO.setSellerOrderList(sellerOrderVOS);
        confirmVO.setTotalRealPrice(totalRealPrice);
        return confirmVO;
    }

    /**
     * 查询商家级订单
     *
     * @param sellerOrderNumber
     * @param accountId
     * @return
     */
    private Order getSellerOrder(Long sellerOrderNumber, Long accountId, boolean isSystemCancel) {
        List<Order> sellerOrderList = orderService.listByNumberAndLevel(sellerOrderNumber, 2);
        if (CollectionUtils.isEmpty(sellerOrderList)) {
            throw new BusinessException("订单不存在");
        }
        Order sellerOrder = sellerOrderList.get(0);
        if (!isSystemCancel && !sellerOrder.getBuyerUserId().equals(accountId)) {
            throw new BusinessException("非法请求");
        }
        return sellerOrder;
    }

    public ProductValidateResult validateOrderInfo(OrderBuyParam orderBuyParam, Long userReceiveAddrId) {
        try {
            return orderInfoValidateService.validateOrderInfo(orderBuyParam, userReceiveAddrId);
        } catch (Exception e) {
            throw new BusinessException("验证订单信息出错");
        }

    }

    // 分配会员卡积分到各个子订单中，按照订单所使用的积分等比分配
    private void distributeCardAmountToOrder(List<Order> orderList, final BigDecimal totalCredit,
                                             final BigDecimal cardAmount) {
        Map<Integer, List<Order>> orderLevelMap = orderList.stream().collect(Collectors.groupingBy(Order::getLevel));
        List<Order> payOrderList = orderLevelMap.get(OrderLevel.PAY_LEVEL.code);
        if (payOrderList == null || payOrderList.size() != 1) {
            throw new BusinessException("订单信息错误!");
        }
        Long totalCreditFen = MoneyUtil.changeY2F(totalCredit).longValue();
        Long cardAmountFen = MoneyUtil.changeY2F(cardAmount).longValue();
        payOrderList.get(0).setCardAmount(cardAmountFen);
        Long remain = cardAmountFen;
        List<Order> productOrderList = orderLevelMap.get(OrderLevel.PRODUCT_LEVEL.code);
        Map<Long, Long> sellerOrderCardAmoutMap = new HashMap<>();
        for (int i = 0; i < productOrderList.size() - 1; i++) {
            Order item = productOrderList.get(i);
            item.setCardAmount(cardAmountFen * (item.getPointAmount() + item.getShipExpensePointAmount()) / totalCreditFen);
            remain -= item.getCardAmount();
            sellerOrderCardAmoutMap.compute(item.getParentNumber(), (key, oldValue) -> {
                return oldValue == null ? item.getCardAmount() : oldValue + item.getCardAmount();
            });
        }
        if (remain < 0) {
            throw new BusinessException("订单信息错误!");
        }
        Order lastProductOrder = productOrderList.get(productOrderList.size() - 1);
        lastProductOrder.setCardAmount(remain);
        sellerOrderCardAmoutMap.compute(lastProductOrder.getParentNumber(), (key, oldValue) -> {
            return oldValue == null ? lastProductOrder.getCardAmount() : oldValue + lastProductOrder.getCardAmount();
        });
        for (Order item : orderLevelMap.get(OrderLevel.SELLER_LEVEL.code)) {
            Long cAmount = sellerOrderCardAmoutMap.get(item.getNumber());
            if (cAmount == null) {
                throw new BusinessException("订单信息错误!");
            }
            item.setCardAmount(cAmount);
        }
    }

    /**
     * 查找某个sku下的所有优惠卷
     *
     * @param voucherIds
     * @return
     */
    private List<Voucher> findVouchersBySku(List<Long> voucherIds) {
        List<Voucher> list = new ArrayList<>();
        EmployeeCreditInfo employeeCreditInfo =
                employeeCreditInfoService.getEmployeeCreditInfo(SessionContextHolder.getSessionEmployeeInfo().getEmployeeId(), 0L);
        for (int i = 0, len = voucherIds.size(); i < len; i++) {
            Voucher voucher = voucherService.getVoucherByVoucherId(voucherIds.get(i));
            String receivedVouchers = employeeCreditInfo.getReceivedVouchers();
            Integer flag = 1;
            if (StringUtil.isNotEmpty(receivedVouchers)) {
                String[] vouchers = receivedVouchers.split(",");
                if (voucher != null) {
                    for (int j = 0, len1 = vouchers.length; j < len1; j++) {
                        Long voucherId = Long.parseLong(vouchers[j].split(":")[0]);
                        Integer count = Integer.parseInt(vouchers[j].split(":")[1]);
                        if (voucher.getVoucherId().equals(voucherId)) {
                            voucher.setNum(count);
                        }
                        if (voucherId.equals(voucherIds.get(i)) && count <= 0) {
                            flag = 2;
                            break;
                        } else if (voucherId.equals(voucherIds.get(i)) && count > 0) {
                            flag = 3;
                            break;
                        }
                        if (j == len1 - 1) {
                            if (flag == 1) {
                                flag = 2;
                            }
                        }
                    }
                    if (flag == 2) {
                        continue;
                    }
                } else {
                    if (voucher.getVoucherStatus() == 2) {
                        continue;
                    }
                }
            }
            if (voucher != null && System.currentTimeMillis() >= voucher.getValidateStartTime().getTime() && System.currentTimeMillis() < voucher.getValidateEndTime().getTime()) {
                list.add(voucher);
            }
        }
        return list;
    }

    /**
     * 查找某个供应商下的优惠卷优惠的金额
     *
     * @param sellerId
     * @return
     */
    private Long findVoucherBySellerId(Long sellerId, String voucherSkus) {
        if (!StringUtil.isEmpty(voucherSkus)) {
            String[] sellers = voucherSkus.split(";");
            for (int i = 0, len = sellers.length; i < len; i++) {
                Long sellerIdTemp = Long.parseLong(sellers[i].split(",")[0]);
                if (sellerId.equals(sellerIdTemp)) {
                    String[] temp = sellers[i].split(",")[2].split(":");
                    BigDecimal goodsPriceTemp = BigDecimal.ZERO;
                    for (int j = 0, len1 = temp.length; j < len1; j++) {
                        goodsPriceTemp = goodsPriceTemp.add(new BigDecimal(temp[j]));
                    }
                    return MoneyUtil.changeY2F(goodsPriceTemp).longValue();
                }
            }
        }
        return 0L;
    }

    /**
     * 查找某个sku下优惠卷优惠的金额
     *
     * @param skuId
     * @param voucherSkus
     * @return
     */
    private Long findVoucherBySkuId(Long skuId, String voucherSkus) {
        if (!StringUtil.isEmpty(voucherSkus)) {
            String[] sellers = voucherSkus.split(";");
            for (int i = 0, len = sellers.length; i < len; i++) {
                String[] skus = sellers[i].split(",");
                String[] skuIds = skus[1].split(":");
                String[] totalGoodsPrices = skus[2].split(":");
                for (int j = 0, len1 = skuIds.length; j < len1; j++) {
                    if (skuId.equals(skuIds[j])) {
                        return MoneyUtil.changeY2F(new BigDecimal(totalGoodsPrices[j])).longValue();
                    }
                }
            }
        }
        return 0L;
    }

    /**
     * 查询该订单下所有优惠卷优惠的金额
     *
     * @param voucherSkus
     * @return
     */
    private Long findTotalVouchersForPayOrder(String voucherSkus) {
        if (!StringUtil.isEmpty(voucherSkus)) {
            String[] sellerIds = voucherSkus.split(";");
            BigDecimal res = BigDecimal.ZERO;
            for (int i = 0, len = sellerIds.length; i < len; i++) {
                String[] skus = sellerIds[i].split(",");
                Long voucherId = Long.parseLong(skus[3]);
                Voucher voucher = voucherService.getVoucherByVoucherId(voucherId);
                res = res.add(voucher.getBenefitContent());
            }
            return MoneyUtil.changeY2F(res).longValue();
        }
        return 0L;
    }

    private Long findVoucherIdBySellerId(Long sellerId, String voucherSkus) {
        if (!StringUtil.isEmpty(voucherSkus)) {
            String[] sellers = voucherSkus.split(";");
            for (int i = 0, len = sellers.length; i < len; i++) {
                Long sellerIdTemp = Long.parseLong(sellers[i].split(",")[0]);
                Long voucherId = Long.parseLong(sellers[i].split(",")[3]);
                if (sellerId.equals(sellerIdTemp)) {
                    return voucherId;
                }
            }
        }
        return null;
    }

}






