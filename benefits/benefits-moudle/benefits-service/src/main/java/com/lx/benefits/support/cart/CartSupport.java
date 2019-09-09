package com.lx.benefits.support.cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import com.alibaba.fastjson.TypeReference;
import com.lx.benefits.bean.entity.employeecreditinfo.EmployeeCreditInfo;
import com.lx.benefits.bean.entity.voucher.Voucher;
import com.lx.benefits.bean.util.JsonUtil;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.util.StringUtil;
import com.lx.benefits.service.employeecreditinfo.EmployeeCreditInfoService;
import com.lx.benefits.service.voucher.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.apollo.common.annotation.RedisLock;
import com.apollo.common.exception.BusinessException;
import com.apollo.common.utils.bean.BeanUtil;
import com.apollo.common.utils.bean.CollectionExtUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lx.benefits.bean.entity.cart.CartProduct;
import com.lx.benefits.bean.entity.cart.CartProductArchive;
import com.lx.benefits.bean.entity.product.ProductEntity;
import com.lx.benefits.bean.entity.product.SkuEntity;
import com.lx.benefits.bean.entity.supplierInfo.SupplierInfo;
import com.lx.benefits.bean.param.cart.CartSaveParam;
import com.lx.benefits.bean.vo.cart.CartProductExtVO;
import com.lx.benefits.bean.vo.cart.CartVO;
import com.lx.benefits.bean.vo.product.SkuPriceBean;
import com.lx.benefits.service.cart.CartProductArchiveService;
import com.lx.benefits.service.cart.CartProductService;
import com.lx.benefits.service.enterprcustomgoods.EnterprCustomGoodsService;
import com.lx.benefits.service.product.ProductService;
import com.lx.benefits.service.product.SkuService;
import com.lx.benefits.service.supplierInfo.SupplierInfoService;

@Component
public class CartSupport {

    @Resource
    private CartProductService cartProductService;
    @Resource
    private CartProductArchiveService cartProductArchiveService;
    @Resource
    private SupplierInfoService supplierInfoService;
    @Autowired
    private EnterprCustomGoodsService  enterprCustomGoodsService;
    @Resource
    private SkuService skuService;
    @Resource
    private ProductService productService;

    @Autowired
    private VoucherService voucherService;

    @Autowired
    private EmployeeCreditInfoService employeeCreditInfoService;

    public CartVO list(Long accountId,Long enterprId){


        List<CartVO.CartSellerVO> cartSellerVOS = Lists.newArrayList();
        CartVO cartVO = new CartVO();
        cartVO.setTotalPrice(BigDecimal.ZERO);
        cartVO.setTotalFreight(BigDecimal.ZERO);
        cartVO.setList(cartSellerVOS);
        List<CartProduct> cartProducts = cartProductService.listCartProduct(accountId);
        if(CollectionUtils.isEmpty(cartProducts)){
            return cartVO;
        }
        List<Integer> skuIdList = Lists.newArrayListWithExpectedSize(cartProducts.size());
        List<Long> skuIdLongList = Lists.newArrayListWithExpectedSize(cartProducts.size());
        Map<Long,CartProduct> cartProductMap = Maps.newHashMapWithExpectedSize(cartProducts.size());
        List<Long> sellerIdList = Lists.newArrayList();
        cartProducts.forEach(cartProduct -> {
            sellerIdList.add(cartProduct.getSellerId());
            skuIdList.add(cartProduct.getSkuId().intValue());
            skuIdLongList.add(cartProduct.getSkuId());
            cartProductMap.put(cartProduct.getSkuId(),cartProduct);
        });
        Map<Long,String> skuSpecMap=productService.getSkuSpecValue(skuIdLongList);
        List<SupplierInfo> supplierInfoList = supplierInfoService.listByIds(sellerIdList);
        Map<Long,SupplierInfo> supplierInfoMap = CollectionExtUtil.toMap(supplierInfoList,SupplierInfo::getId);
        List<SkuEntity> skuEntityList = skuService.listByIdList(skuIdList);
        List<Long> spuCodes = CollectionExtUtil.getPropertyList(skuEntityList,SkuEntity::getSpuCode);
        List<ProductEntity> productEntityList = productService.listBySpuCodeList(spuCodes);
        Map<Long,ProductEntity> productEntityMap = CollectionExtUtil.toMap(productEntityList,ProductEntity::getSpuCode);

        Map<Long, SkuPriceBean> skuPriceMap = skuService.singleCustomPrice(skuIdLongList,enterprId);

        List<CartProductExtVO> list = CollectionExtUtil.copyListWithCheck(skuEntityList, skuEntity -> {
            CartProduct cartProduct = cartProductMap.get(skuEntity.getId());
            ProductEntity productEntity = productEntityMap.get(skuEntity.getSpuCode());
            SkuPriceBean goodsPrice = skuPriceMap.get(skuEntity.getId());
            CartProductExtVO cartProductExtVO = new CartProductExtVO();
            cartProductExtVO.setCartProductId(cartProduct.getId());
            List<Long> voucherIds = JsonUtil.parseObject(productEntity.getVoucherIds(),new TypeReference<List<Long>>(){});
            if(null != voucherIds){
                voucherIds.addAll(voucherService.fingFullRangeVoucherIds());
                cartProductExtVO.setVouchers(this.findVouchersBySpu(voucherIds));
            }else{
                cartProductExtVO.setVouchers(findVouchersBySpu(voucherService.fingFullRangeVoucherIds()));
            }
            cartProductExtVO.setSpuCode(skuEntity.getSpuCode());
            cartProductExtVO.setSkuId(skuEntity.getId());
            cartProductExtVO.setGoodStorage(skuEntity.getGoodsStorge());
            cartProductExtVO.setStatus(skuEntity.getStatus());
			String goodsName = skuEntity.getGoodsName();
			if (StringUtils.isEmpty(goodsName)) {// 处理商名称
				cartProductExtVO.setTitle(productEntity.getGoodsName());
			} else {
				cartProductExtVO.setTitle(goodsName);
			}
			cartProductExtVO.setTitleEn(productEntity.getGoodsNameEn());
			cartProductExtVO.setSpec(skuSpecMap.getOrDefault(skuEntity.getId(), skuEntity.getGoodsSpec()));
			String goodsImage = skuEntity.getGoodsImage();
			if (StringUtils.isEmpty(goodsImage)) {// 处理商品主图
				cartProductExtVO.setSkuImg(productEntity.getGoodsImage());
			} else {
				cartProductExtVO.setSkuImg(goodsImage);
			}
            cartProductExtVO.setMarketPrice(skuEntity.getGoodsMarkprice());
            cartProductExtVO.setSalePrice(goodsPrice.getGoodsPrice());
            cartProductExtVO.setFreight(productEntity.getGoodsFreight());
            cartProductExtVO.setActivityId(cartProduct.getActivityId());
            cartProductExtVO.setChecked(cartProduct.getChecked());
            cartProductExtVO.setCount(cartProduct.getCount());
            cartProductExtVO.setSellerId(cartProduct.getSellerId());
			cartProductExtVO.setGoodsState(goodsPrice.isCustomer() ? 1 : productEntity.getGoodsState());
            BigDecimal totalPrice = goodsPrice.getGoodsPrice().multiply(BigDecimal.valueOf(cartProduct.getCount().longValue()));
            cartProductExtVO.setTotalGoodsPrice(totalPrice);
            BigDecimal totalFreightPrice = productEntity.getGoodsFreight().multiply(BigDecimal.valueOf(cartProduct.getCount().longValue()));
            cartProductExtVO.setTotalFreightPrice(totalFreightPrice);
            return cartProductExtVO;
        });

        Map<Long,List<CartProductExtVO>> cartMap = CollectionExtUtil.groupAndMapping(list,CartProductExtVO::getSellerId,cp->cp);


        cartMap.forEach((sellerId,cartProductExts)->{
            SupplierInfo supSupplierInfo = supplierInfoMap.get(sellerId);

            CartVO.CartSellerVO cartSellerVO = new CartVO.CartSellerVO();
            cartSellerVO.setSellerId(sellerId);
            cartSellerVO.setSellerName(supSupplierInfo.getName());
            cartSellerVO.setTotalPrice(BigDecimal.ZERO);
            cartSellerVO.setTotalFreight(BigDecimal.ZERO);
            cartProductExts.forEach(cartProductExtVO -> {
                if (cartProductExtVO.getChecked()==1) {
                    BigDecimal totalPrice = cartProductExtVO.getSalePrice().multiply(BigDecimal.valueOf(cartProductExtVO.getCount().longValue()));
                    BigDecimal totalFreight = cartProductExtVO.getFreight().multiply(BigDecimal.valueOf(cartProductExtVO.getCount().longValue()));
                    cartSellerVO.setTotalPrice(cartSellerVO.getTotalPrice().add(totalPrice));
                    cartSellerVO.setTotalFreight(cartSellerVO.getTotalFreight().add(totalFreight));
                }
            });
            cartSellerVO.setSkuList(cartProductExts);

            cartVO.setTotalPrice(cartVO.getTotalPrice().add(cartSellerVO.getTotalPrice()));
            cartVO.setTotalFreight(cartVO.getTotalFreight().add(cartSellerVO.getTotalFreight()));
            cartSellerVOS.add(cartSellerVO);
        });

        cartSellerVOS.sort(Comparator.comparing(CartVO.CartSellerVO::getSellerId));
        return cartVO;
    }

    /**
     * 加购 购物车最大20条记录
     * @param cardSaveParam
     * @param enterprId 
     */
    @RedisLock(name = "cart",keys = {"#accountId"})
    @Transactional(rollbackFor = Exception.class)
    public void addCart(CartSaveParam cardSaveParam, Long accountId, Long enterprId){
        if (cardSaveParam.getActivityId()!=0) {
            throw new BusinessException("活动商品只能立即购买");
        }
        SkuEntity skuEntity = skuService.getBySkuId(cardSaveParam.getSkuId());
        if (Objects.isNull(skuEntity)) {
            throw new BusinessException("该商品不存在");
        }else if(skuEntity.getGoodsStorge()<1){
        	 throw new BusinessException("库存不足");
        }
        ProductEntity productEntity = productService.selectById(skuEntity.getSpuCode());
        if (Objects.isNull(productEntity)) {
            throw new BusinessException("该商品不存在");
        }
        Map<Long, SkuPriceBean> priceList = skuService.singleCustomPrice(Arrays.asList(skuEntity.getId()), enterprId);
        SkuPriceBean skuPriceBean = priceList.get(skuEntity.getId());
		boolean isCustomer = skuPriceBean == null ? false : skuPriceBean.isCustomer();
		if (!isCustomer) {
			if (skuEntity.getStatus() != 1 || productEntity.getGoodsState() != 1) {
				throw new BusinessException("该商品已下架");
			}
			boolean available = enterprCustomGoodsService.checkAvailable(enterprId, productEntity, skuEntity);
			if (!available) {
				throw new BusinessException("您没有权限购买此商品!");
			}
		}
        //TODO 查询商品信息
        CartProduct cartProduct = new CartProduct();
        cartProduct.setSkuId(cardSaveParam.getSkuId());
        cartProduct.setCount(cardSaveParam.getCount());
        cartProduct.setActivityId(cardSaveParam.getActivityId());
        cartProduct.setChecked(cardSaveParam.getChecked());
        cartProduct.setAccountId(accountId);
        //正式用户
        cartProduct.setType(1);
        cartProduct.setSellerId(productEntity.getSupplierId());
        //保存或更新
        cartProductService.doSaveRecord(cartProduct,cardSaveParam.getSource());
    }

    /**
     * 购物车选中
     * @param cartProductIdList
     */
    @RedisLock(name = "cart",keys = {"#accountId"})
    @Transactional(rollbackFor = Exception.class)
    public void modChecked(List<Long> cartProductIdList,Long accountId,Integer checked){

        List<CartProduct> existCartProductList = cartProductService.listByIdList(accountId,cartProductIdList);
        if (CollectionUtils.isEmpty(existCartProductList)) {
            return;
        }
        List<Long> existCartProductIdList = CollectionExtUtil.getPropertyList(existCartProductList,CartProduct::getId);
        cartProductService.modCheckedByIdList(accountId,existCartProductIdList,checked);

//        List<CartProductArchive> archiveList = CollectionExtUtil.copyListWithCheck(existCartProductList,c->{
//            CartProductArchive cartProductArchive = BeanUtil.copySpring(c,CartProductArchive.class);
//            cartProductArchive.setId(null);
//            cartProductArchive.setSourceId(c.getId());
//            cartProductArchive.setChecked(checked);
//            cartProductArchive.setDeletedType(1);
//            return cartProductArchive;
//        });
//        cartProductArchiveService.doAddBatchRecord(archiveList);
    }

    /**
     * 删除购物车
     * @param cartProductIdList
     */
    @RedisLock(name = "cart",keys = {"#accountId"})
    @Transactional(rollbackFor = Exception.class)
    public void removeCart(List<Long> cartProductIdList,Long accountId,Boolean flag){
    	if(flag) {
    		Map<String,Long> cartProductId = new HashMap<>();
    		cartProductId.put("cartId",cartProductIdList.get(0));
    		cartProductId.put("accountId",accountId);
    		cartProductIdList.clear();
    		cartProductIdList.add(cartProductService.queryCartId(cartProductId));
    	}
        List<CartProduct> existCartProductList = cartProductService.listByIdList(accountId,cartProductIdList);
        if (CollectionUtils.isEmpty(existCartProductList)) {
            return;
        }
        List<Long> existCartProductIdList = CollectionExtUtil.getPropertyList(existCartProductList,CartProduct::getId);
        cartProductService.delByIdList(accountId,existCartProductIdList);

        List<CartProductArchive> archiveList = CollectionExtUtil.copyListWithCheck(existCartProductList,c->{
            CartProductArchive cartProductArchive = BeanUtil.copySpring(c,CartProductArchive.class);
            cartProductArchive.setId(null);
            cartProductArchive.setSourceId(c.getId());
            //用户删除
            cartProductArchive.setDeletedType(1);
            return cartProductArchive;
        });
        cartProductArchiveService.doAddBatchRecord(archiveList);
    }

    private List<Voucher> findVouchersBySpu(List<Long> voucherIds){
        List<Voucher> list = new ArrayList<>();
        EmployeeCreditInfo employeeCreditInfo =
                employeeCreditInfoService.getEmployeeCreditInfo(SessionContextHolder.getSessionEmployeeInfo().getEmployeeId(), 0L);
        String receivedVouchers = employeeCreditInfo.getReceivedVouchers();
        for(int i=0,len=voucherIds.size();i<len;i++){
            Voucher voucher = voucherService.getVoucherByVoucherId(voucherIds.get(i));
            Integer flag = 1;
            if(StringUtil.isNotEmpty(receivedVouchers)){
                String[] receivedVouchersArray = receivedVouchers.split(",");
                for(int z=0,len1 = receivedVouchersArray.length;z<len1;z++){
                    Long voucherId = Long.parseLong(receivedVouchersArray[z].split(":")[0]);
                    Integer num = Integer.parseInt(receivedVouchersArray[z].split(":")[1]);
                    if(voucherId.equals(voucherIds.get(i))&& num <= 0){
                        flag = 2;
                        break;
                    }else if(voucherId.equals(voucherIds.get(i))&& num > 0){
                        flag = 3;
                        break;
                    }
                    if(z == len1-1){
                        if(flag == 1){
                            flag = 2;
                        }
                    }
                }
                if(flag == 2){
                    continue;
                }
            }else {
                if (voucher.getVoucherStatus() == 2) {
                    continue;
                }
            }
            if(voucher != null && System.currentTimeMillis()>= voucher.getValidateStartTime().getTime() && System.currentTimeMillis()<= voucher.getValidateEndTime().getTime()){
                list.add(voucher);
            }
        }
        return list;
    }

}
