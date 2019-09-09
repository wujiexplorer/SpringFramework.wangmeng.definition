package com.lx.benefits.mapper.user;
import com.lx.benefits.bean.entity.user.UserReceiveAddress;
import com.lx.benefits.mapper.base.IBaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @ClassName: UserReceiveAddressMapper
* @Description:
* @author wind
* @date 2019-2-11
*/
public interface UserReceiveAddressMapper extends IBaseMapper<UserReceiveAddress> {
    /**
     * 查询用户默认收货地址
     * @param userId
     * @return
     */
    UserReceiveAddress selectUserDefaultAddress(@Param("userId") Long userId);
	
}