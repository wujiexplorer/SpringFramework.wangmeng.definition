package com.lx.benefits.mapper.order;
import com.lx.benefits.bean.entity.order.OrderPayRecord;
import com.lx.benefits.mapper.base.IBaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @ClassName: OrderPayRecordMapper
* @Description:
* @author wind
* @date 2019-3-1
*/
public interface OrderPayRecordMapper extends IBaseMapper<OrderPayRecord> {

    OrderPayRecord selectByPayMark(@Param("payMark") String payMark , @Param("payChannel") Integer payChannel);


    int updatePayStatusByPayMark(
            @Param("payMark") String payMark ,
            @Param("payTid") String payTid,
            @Param("payChannel") Integer payChannel,
            @Param("isPay") Integer isPay
    );
	
}