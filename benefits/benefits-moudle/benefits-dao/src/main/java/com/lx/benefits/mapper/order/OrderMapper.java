package com.lx.benefits.mapper.order;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.bo.order.OrderOverview;
import com.lx.benefits.bean.bo.order.PaidOrderInfo;
import com.lx.benefits.bean.bo.order.SellerOrderInfo;
import com.lx.benefits.bean.bo.order.SellerOrderReverseOverviewBO;
import com.lx.benefits.bean.bo.order.UserOrderOverviewBO;
import com.lx.benefits.bean.entity.order.Order;
import com.lx.benefits.bean.param.order.ItemOrderListQueryParam;
import com.lx.benefits.bean.vo.order.ItemOrderListVO;
import com.lx.benefits.mapper.base.IBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
* @ClassName: OrderMapper
* @Description:
* @author wind
* @date 2019-2-12
*/
public interface OrderMapper extends IBaseMapper<Order> {
    /**
     * 商品级订单列表
     * @return
     */
    List<ItemOrderListVO> selectItemOrderList(ItemOrderListQueryParam itemOrderListQueryParam);
    /**
     * 订单数
     */
    int selectItemOrderCount(ItemOrderListQueryParam itemOrderListQueryParam);
    
    Order selectOrderByNumber(@Param("number") Long number);

    /**
     * 按照订单编号&级别查询订单信息
     * @param number
     * @param level
     * @return
     */
    List<Order> selectByNumberAndLevel(@Param("number") Long number, @Param("level")Integer level);

    /**
     * 按照父级订单编号&级别查询订单信息
     * @param parentNumber
     * @param level
     * @return
     */
    List<Order> selectByParentNumberAndLevel(@Param("parentNumber") Long parentNumber, @Param("level")Integer level);

    /**
     * 按照父级订单编号集合&级别查询订单信息
     * @param parentNumberList
     * @param level
     * @return
     */
    List<Order> selectByParentNumbersAndLevel(@Param("parentNumberList") List<Long> parentNumberList, @Param("level")Integer level);
    
    /**
     * 按照订单编号集合&级别查询订单信息
     * @param parentNumberList
     * @param level
     * @return
     */
    List<Long> selectByUserIdAndLevel(@Param("accountId") Long accountId, @Param("level")Integer level,@Param("startRecord") Integer startRecord, @Param("pageSize")Integer pageSize);
    
    /**
     * 用户商家级订单列表
     * @param buyerUserId
     * @param status
     * @param reverseStatus
     * @param startRecord
     * @param pageSize
     * @return
     */
    List<Order> selectUserSellerOrderList(
                                    @Param("buyerUserId") Long buyerUserId,
                                    @Param("status") Integer status,
                                    @Param("reverseStatus") Integer reverseStatus,
                                    @Param("startRecord")Integer startRecord,
                                    @Param("pageSize")Integer pageSize
                                    );

    /**
     * 用户商家级订单数量
     * @param buyerUserId
     * @param status
     * @param reverseStatus
     * @return
     */
    Integer selectUserSellerOrderCount(@Param("buyerUserId") Long buyerUserId,
                                          @Param("status") Integer status,
                                          @Param("reverseStatus") Integer reverseStatus);

    /**
     * 用户订单概况
     * @param buyerUserId
     * @return
     */
    UserOrderOverviewBO selectUserOrderOverview(@Param("buyerUserId") Long buyerUserId);

    /**
     * 查询商家级订单退款情况
     * @param sellerOrderNumber
     * @return
     */
    SellerOrderReverseOverviewBO selectReverseOrderCount(@Param("sellerOrderNumber") Long sellerOrderNumber);

    /**
     * 查询超时未支付订单列表
     * @param createTime
     * @param startRecord
     * @param pageSize
     * @return
     */
    List<Order> selectOvertimePaymentOrderList(@Param("createTime") Date createTime,
                                               @Param("minId") Long minId,
                                               @Param("startRecord")Integer startRecord,
                                               @Param("pageSize")Integer pageSize);

	/**
	 * 取消超时的秒杀订单
	 * @param createTime
	 * @param minId
	 * @param startRecord
	 * @param pageSize
	 * @return
	 */
	List<Order> selectOvertimePaymentSeckillOrderList(@Param("createTime") Date createTime,
											   @Param("minId") Long minId,
											   @Param("startRecord")Integer startRecord,
											   @Param("pageSize")Integer pageSize);

    /**
     * 查询待完结订单（发货超过15天订单自动完结）
     * @param shipTime
     * @param minId
     * @param startRecord
     * @param pageSize
     * @return
     */
    List<Order> selectNeedOverOrderList(@Param("shipTime") Date shipTime,
                                               @Param("minId") Long minId,
                                               @Param("startRecord")Integer startRecord,
                                               @Param("pageSize")Integer pageSize);

    
    /**
     * 
     * @param sellerId 供应商ID
     * @param level 订单类别
     * @return
     */
    List<Order> selectSellerPaidOrderListByLevel(@Param("sellerId") Long sellerId, @Param("level")Integer level);
    
    /**
     * 保存订单信息
     * @param orderList
     * @return
     */
    int insertBatch(List<Order> orderList);

    /**
     * 更新商家级订单状态 仅待支付，已支付状态订单可以取消
     * @param number
     * @param status
     * @param statusEx
     * @return
     */
    int updateSellerOrderStatus2Cancel(
            @Param("buyerUserId") Long buyerUserId,
            @Param("number") Long number,
            @Param("status")Integer status,
            @Param("statusEx")Integer statusEx);

    /**
     * 更新商品级订单状态 仅待支付，已支付状态订单可以取消
     * @param sellerOrderNumber
     * @param status
     * @param statusEx
     * @return
     */
    int updateItemOrderStatus2Cancel(
            @Param("buyerUserId") Long buyerUserId,
            @Param("sellerOrderNumber") Long sellerOrderNumber,
            @Param("status") Integer status,
            @Param("statusEx") Integer statusEx);


    /**
     * 更新商家级订单状态 仅2:订单已支付,3:订单已发货,4:订单已收货状态订单可以完结
     * @param number
     * @param status
     * @param statusEx
     * @return
     */
    int updateSellerOrderStatus2Over(
            @Param("buyerUserId") Long buyerUserId,
            @Param("number") Long number,
            @Param("status")Integer status,
            @Param("statusEx")Integer statusEx);

    /**
     * 更新商品级订单状态 仅2:订单已支付,3:订单已发货,4:订单已收货态订单可以完结
     * @param parentNumber
     * @param status
     * @param statusEx
     * @return
     */
    int updateItemOrderStatus2Over(
            @Param("buyerUserId") Long buyerUserId,
            @Param("parentNumber") Long parentNumber,
            @Param("status")Integer status,
            @Param("statusEx")Integer statusEx);

    /**
     * 更新商品及订单逆向状态为进入退款流程
     * @param number
     * @return
     */
    int updateItemOrderReverseStatusFirstStep(@Param("number") Long number);

    /**
     * 更新商品及订单逆向状态为完结状态
     * @param number
     * @param reverseStatus
     * @return
     */
    int updateItemOrderReverseStatusOver(@Param("number") Long number,@Param("reverseStatus")Integer reverseStatus);

    /**
     * 更新订单支付状态
     * @param orderNumberList
     * @return
     */
    int updateBatchOrderPayStatus(@Param("orderNumberList") List<Long> orderNumberList,@Param("orderPayId")Long orderPayId);

    /**
     * 更新订单发货状态
     * @param orderNumber
     * @return
     */
    int updateOrderShipStatus(@Param("orderNumber") Long orderNumber, @Param("shipTime")Date shipTime);
    
    /**
	 * 批量更新虚拟商品发货状态
	 * @param sellerOrderNumber
	 * @return
	 */
	int updateBatchOrderShipStatus(@Param("orderNumberList") List<Long> orderNumberList,@Param("orderPayId") Long orderPayId);

    String getEmailByNumber(@Param("orderNumber") Long orderNumber);
    
    int updateOrderCloseStatus(@Param("sellerOrderNumber") Long sellerOrderNumber);

    /**
     * 更新商品级订单第三方订单编号
     * @param sellerOrderNumber
     * @param thirdOrderNumber
     * @return
     */
    int updateItemOrderThirdOrderNumber(@Param("sellerOrderNumber") Long sellerOrderNumber,
                                        @Param("thirdOrderNumber") String thirdOrderNumber);

    /**
     * 更新商家级订单第三方订单编号
     * @param sellerOrderNumber
     * @param thirdOrderNumber
     * @return
     */
    int updateSellerOrderThirdOrderNumber(@Param("sellerOrderNumber") Long sellerOrderNumber,
                                      @Param("thirdOrderNumber") String thirdOrderNumber);

    /**
     * 更新店铺级订单对应支付订单号
     * @param sellerOrderNumber
     * @param payOrderNumber
     * @return
     */
    int updateSellerOrderParentNumber(@Param("sellerOrderNumber") Long sellerOrderNumber,
                                      @Param("payOrderNumber") Long payOrderNumber);
    
    /**
	 * 获取企业订单概览
	 * 
	 * @param enterprId
	 *            企业ID
	 * @return
	 */
    OrderOverview getEnterpriseOrderOverview(@Param("enterprId")Long enterprId);

    /**
     * 供应商商品级订单列表
     * @return
     */
    List<ItemOrderListVO> queryItemOrderList(ItemOrderListQueryParam itemOrderListQueryParam);
    /**
     * 订单数
     */
    int queryItemOrderCount(ItemOrderListQueryParam itemOrderListQueryParam);

	/**
	 * 根据支付级订单号获取商品级订单信息
	 * 
	 * @param payOrderNumber
	 *            支付级订单号
	 * @return
	 */
	List<SellerOrderInfo> getProductOrderDetailsByPayNumber(@Param("payOrderNumber") Long payOrderNumber);
	
	/**
	 * 获取某个企业已支付状态支付级订单数
	 * 
	 * @param enterprId
	 *            企业ID
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return
	 */
	int countPaidOrderByEnterpr(@Param("enterprId") Long enterprId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);
	
	/**
	 * 获取支付级订单信息
	 * 
	 * @param enterprId
	 *            企业ID
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param pageBean
	 *            分页信息
	 * @return
	 */
	List<PaidOrderInfo> selectPaidOrderByEnterpr(@Param("enterprId")Long enterprId, @Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("pageBean")PageBean pageBean);
	
	/**
	 * 获取支付级订单总积分
	 * 
	 * @param enterprId
	 *            企业ID
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return
	 */
	BigDecimal sumPaidOrderPointsByEnterpr(@Param("enterprId")Long enterprId, @Param("startTime")Date startTime, @Param("endTime")Date endTime);
	
	/**
	 * 
	 * @Title: listVirtualGoodsOrder   
	 * @Description:  查询虚拟商品订单  
	 * @param: @param itemOrderNumbers
	 * @param: @return      
	 * @return: List<Order>      
	 * @throws
	 */
	List<Order> listVirtualGoodsOrder(@Param("sellerOrders") List<Long> sellerOrders);
	List<Order> listVirtualOrders(@Param("itemOrderNumbers") List<Long> sellerOrders);

}
