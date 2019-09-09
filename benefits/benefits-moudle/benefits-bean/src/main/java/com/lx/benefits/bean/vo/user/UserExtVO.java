package com.lx.benefits.bean.vo.user;

import com.lx.benefits.bean.entity.user.User;
import lombok.Data;

/**
* @ClassName: UserExtVO
* @Description:
* @author wind
* @date 2019-2-11
*/
@Data
public class UserExtVO extends User {

    /**
     * 用户所在公司ID
     */
    private Long companyId;

    /**
     *下单来源平台，0:全终端(未知平台下单),1:IOS下单,2:Android下单,3:H5下单
     */
    private int platform;

}
