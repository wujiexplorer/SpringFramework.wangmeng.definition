package com.lx.benefits.service.order;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.bo.order.OrderOverview;
import com.lx.benefits.bean.bo.order.ProductOrderDetails;
import com.lx.benefits.bean.bo.order.SellerOrderReverseOverviewBO;
import com.lx.benefits.bean.bo.order.UserOrderOverviewBO;
import com.lx.benefits.bean.entity.order.Order;
import com.lx.benefits.bean.enums.OrderEnum;
import com.lx.benefits.bean.param.order.ItemOrderListQueryParam;
import com.lx.benefits.bean.vo.order.ItemOrderListVO;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* @ClassName: OrderService
* @Description:
* @author wind
* @date 2019-2-12
*/
public interface OrderService {
	
	
	
	/**
     * 添加记录
     * @param record
     * @return
     * @author wind
	 * @date 2019-2-12
     */
	Long doAddRecord(Order record) throws BusinessException;

	/**
	 * 保存订单信息
	 * @param list
	 * @return
	 * @throws BusinessException
	 */
	int doAddBatchRecord(List<Order> list) throws BusinessException;
	
	/**
     * 根据主键编辑项目信息
     * @param record
     * @return
     * @author wind
	 * @date 2019-2-12
     */
    int doModRecord(Order record) throws BusinessException;

	/**
	 * 更新店铺级订单对应支付订单号
	 * @param sellerOrderNumber
	 * @param payOrderNumber
	 * @return
	 */
	int doModSellerOrderParentNumber(Long sellerOrderNumber, Long payOrderNumber);

	/**
     * 更新商家级&商品级订单关联第三方订单编号
     * @param sellerOrderNumber
     * @return
     * @throws BusinessException
     */
    int modifyOrderThirdNumber(Long sellerOrderNumber,String thirdOrderNumber) throws BusinessException;

	/**
	 * 商家级订单取消
	 * @param userId
	 * @param sellerOrderNumber
	 */
	void cancelBySellerOrderNumber(Long userId,Long sellerOrderNumber,Long payOrderNumber);

	/**
	 * 商家级订单确认收货
	 * @param userId
	 * @param sellerOrderNumber
	 */
	void confirmReceiptBySellerOrderNumber(Long userId,Long sellerOrderNumber,Long payOrderNumber);

	/**
     * 根据主键获取项目信息
     * @param id
     * @return
     * @author wind
	 * @date 2019-2-12
     */
    Order getById(Long id) throws BusinessException;

	/**
	 * 根据订单编号&级别查询订单信息
	 * @return
	 * @throws BusinessException
	 */
	List<Order> listByNumberAndLevel(Long number,Integer level) throws BusinessException;
	/**
	 * 根据父级订单编号&级别查询订单信息
	 * @return
	 * @throws BusinessException
	 */
	List<Order> listByParentNumberAndLevel(Long parentNumber,Integer level) throws BusinessException;

	/**
	 * 根据父级订单编号集合&级别查询订单信息
	 * @param parentNumberList
	 * @param level
	 * @return
	 * @throws BusinessException
	 */
	List<Order> listByParentNumbersAndLevel(List<Long> parentNumberList, Integer level) throws BusinessException;

	/**
	 * 根据订单编号集合&级别查询订单信息
	 * @param numberList
	 * @param level
	 * @return
	 * @throws BusinessException
	 */
	List<Long> listByNumbersAndLevel(Long accountId, Integer level,Integer startRecord, Integer pageSize) throws BusinessException;
	
	/**
	 * 查询商品级订单列表
	 * @param itemOrderListQueryParam
	 * @return
	 */
	List<ItemOrderListVO> listItemOrderList(ItemOrderListQueryParam itemOrderListQueryParam);

	/**
	 * 查询商品级订单记录数
	 * @param itemOrderListQueryParam
	 * @return
	 */
	int getItemOrderCount(ItemOrderListQueryParam itemOrderListQueryParam);
	/**
	 * 查询用户店铺级订单列表
	 * @param buyerUserId
	 * @param status
	 * @param reverseStatus
	 * @param startRecord
	 * @param pageSize
	 * @return
	 */
	List<Order> listUserSellerOrderList(  Long buyerUserId, Integer status, Integer reverseStatus, Integer startRecord, Integer pageSize);

	/**
	 * 查询用户店铺级订单数量
	 * @param buyerUserId
	 * @param status
	 * @param reverseStatus
	 * @return
	 */
	Integer getUserSellerOrderCount(Long buyerUserId, Integer status, Integer reverseStatus);

	/**
	 * 查询商家级订单退款情况
	 * @param sellerOrderNumber
	 * @return
	 */
	SellerOrderReverseOverviewBO getReverseOrderCount(Long sellerOrderNumber);

	/**
	 * 商品级订单逆向状态改为进入退款流程
	 * @param itemOrderNumber
	 * @return
	 */
	int modItemOrderReverseStatusFirstStep(Long itemOrderNumber);
	/**
	 * 商品级订单逆向状态改为退款完结
	 * @param itemOrderNumber
	 * @return
	 */
	int modItemOrderReverseStatusOver(Long itemOrderNumber, OrderEnum.REVERSE_STATUS reverseStatusEnum);

	UserOrderOverviewBO getUserOrderView(Long buyerUserId);

	/**
	 * 查询超时未支付订单列表
	 * @param createTime
	 * @param minId
	 * @param startRecord
	 * @param pageSize
	 * @return
	 */
	List<Order> listOvertimePaymentOrder(Date createTime,Long minId,  Integer startRecord, Integer pageSize);

	/**
	 * 查询超时未支付的秒杀订单列表
	 * @param createTime
	 * @param minId
	 * @param startRecord
	 * @param pageSize
	 * @return
	 */
	List<Order> listOvertimePaymentSeckillOrder(Date createTime,Long minId,  Integer startRecord, Integer pageSize);

	List<Order> listSellerPaidOrderListByLevel(Long sellerId, Integer level);

	/**
	 * 需要完结订单（超过15天订单自动完结）
	 * @param createTime
	 * @param minId
	 * @param startRecord
	 * @param pageSize
	 * @return
	 */
	List<Order> listNeedOverOrder(Date createTime, Long minId, Integer startRecord, Integer pageSize);

	/**
	 * 更新订单支付状态
	 * @param orderNumberList
	 * @return
	 */
	int modifyBatchOrderPayStatus(List<Long> orderNumberList,Long orderPayId);

	/**
	 * 更新订单发货状态
	 * @param orderNumber
	 * @return
	 */
	int modifyOrderShipStatus(Long orderNumber,Long sellerOrderNumber);

	/**
	 * 关闭商家级订单
	 * @param sellerOrderNumber
	 * @return
	 */
	int modifyOrderCloseStatus(Long sellerOrderNumber);
	
	/**
	 * 批量更新虚拟商品发货状态
	 * @param sellerOrderNumber
	 * @return
	 */
	int modifyBatchOrderShipStatus(List<Long> orderNumberList,Long orderPayId);
	
	Order getOrderByNumber(Long number);

	/**
	 * 获取企业订单概览信息（订单量，订单总额，消费总额）
	 * 
	 * @param enterprId
	 * @return
	 */
	OrderOverview getEnterpriseOrderOverview(Long enterprId);
	
	/**
	 * 查询供应商商品级订单列表
	 * @param itemOrderListQueryParam
	 * @return
	 */
	List<ItemOrderListVO> getItemOrderList(ItemOrderListQueryParam itemOrderListQueryParam);

	/**
	 * 查询供应商商品级订单记录数
	 * @param itemOrderListQueryParam
	 * @return
	 */
	int ItemOrderCount(ItemOrderListQueryParam itemOrderListQueryParam);

	/**
	 * 订单概览
	 * @return
	 */
	public Map<String,Object> orderOverview();

	ProductOrderDetails getProductOrderDetailsByOrderNumber(Long orderNumber);

	Object orderFinanceCheck(Long enterprId, PageBean pageBean, Date startTime, Date endTime);
	
	/**
	 * 
	 * @Title: listVirtualGoodsOrder   
	 * @Description:  查询虚拟商品订单  
	 * @param: @param sellerOrders
	 * @param: @return      
	 * @return: List<Order>      
	 * @throws
	 */
	List<Order> listVirtualGoodsOrder(List<Long> sellerOrders);
	List<Order> listVirtualOrders(List<Long> sellerOrders);

}

