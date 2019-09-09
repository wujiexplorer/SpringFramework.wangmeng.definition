package com.lx.benefits.service.voucher;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.entity.product.ProductEntity;
import com.lx.benefits.bean.entity.product.SkuEntity;
import com.lx.benefits.bean.entity.voucher.Voucher;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * User:wangmeng
 * Date:2019/8/8
 * Time:10:28
 * Version:2.x
 * Description:TODO
 **/
public interface VoucherService {
    /**
     * 新增优惠卷
     * @param voucher
     * @return
     */
    public Voucher insertVoucher(Voucher voucher);

    /**
     * 更新优惠卷
     * @param voucher
     * @return
     */
    public Integer updateVoucher(Voucher voucher);

    /**
     * 查询优惠卷列表
     * @param voucherName
     * @param voucherStatus
     * @param voucherType
     * @param pageBean
     * @return
     */
    public PageResultBean<Voucher> findVouchers(String voucherName, Integer voucherStatus, Integer voucherType, PageBean pageBean);

    /**
     * 查询优惠卷模板列表
     * @return
     */
    public List<Voucher> findTemplateVouchers();

    /**
     * 查询所有员工正在进行的优惠卷列表
     * @return
     */
    public List<Voucher> findVouchersByEmployee();

    /**
     * 处理已领取优惠卷逻辑
     * @param voucherId
     * @return
     */
    public Voucher processReceivedVouchers(Long voucherId);

    /**
     * 查询已优惠的商品
     * @param params
     * @return
     */
    public PageResultBean<SkuEntity> findSkusByVoucherId(Map<String,Object> params);

    /**
     * 查询已优惠的SPU商品
     * @param params
     * @return
     */
    public PageResultBean<ProductEntity> findSpusByVoucherId(Map<String,Object> params);

    /**
     * 查询适用场景是全场商品的优惠卷
     * @return
     */
    public List<Long> fingFullRangeVoucherIds();

    /**
     * 根据优惠卷ID查询优惠卷详情
     * @param voucherId
     * @return
     */
    public Voucher getVoucherByVoucherId(Long voucherId);

    /**
     * 验证购物车中的优惠卷是否领取
     * @param voucherIds
     * @return
     */
    public Boolean validateCartVouchers(String voucherIds);

    /**
     * 验证商品优惠卷阈值
     * @param userReceiveAddrId
     *
     * @return
     */
    public List<Voucher> validateVouchersThreshold(Long userReceiveAddrId);

    /**
     * 下订单后员工优惠卷信息的更新
     * @param voucherSkus
     */
    public Integer updateEmployeeCreditInfoByOrderVouchers(String voucherSkus);


    /**
     * 验证下订单时，每人限领优惠卷次数是否合格
     * @param voucherSkus
     */
    public void validateEmployeeCreditInfoByOrderVouchers(String voucherSkus);

    /**
     * 取消订单或退款时，优惠卷的返回
     * @param voucherIds
     */
    public Integer updateEmployeeCreditInfoByRefundOrderVouchers(List<Long> voucherIds,Long employeeId);

    /**
     * 更新优惠卷已使用优惠卷库存
     * @param voucherSkus
     * @return
     */
    public Integer updateVoucherUsed(String voucherSkus);

    /**
     * 第三方支付成功后，更新优惠卷已使用优惠卷库存
     * @param voucherId
     * @return
     */
    public Integer updateVoucherUsedByNoPay(Long voucherId);

    /**
     * 根据模板ID查询优惠卷列表
     * @param templateId
     * @return
     */
    public List<Voucher> findVouchersByTemplateId(Long templateId);

    public void processVoucherIdsBySpu(List<ProductEntity> list, Voucher voucher);

    public void processDelVoucherIdBySpu(List<ProductEntity> list, Voucher voucher);
}
