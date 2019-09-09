package com.lx.benefits.mapper.order;
import com.lx.benefits.bean.entity.order.RefundPackage;
import com.lx.benefits.mapper.base.IBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @ClassName: RefundPackageMapper
* @Description:
* @author wind
* @date 2019-2-11
*/
public interface RefundPackageMapper extends IBaseMapper<RefundPackage> {

    /**
     * 查询退款物流信息
     * @param refundApplyNumberList
     * @return
     */
    List<RefundPackage> selectLogisticsByRefundNumbers(@Param("refundApplyNumberList") List<Long> refundApplyNumberList);
	
}