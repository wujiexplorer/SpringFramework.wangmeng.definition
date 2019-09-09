package com.lx.benefits.service.enterprwelcome;


import com.lx.benefits.bean.base.dto.WelcomeModuleInfoDto;

/**
 * @author by yingcai on 2018/12/18.
 */
public interface EnterprWelcomeService {


    Boolean setWelcome(WelcomeModuleInfoDto req);

    WelcomeModuleInfoDto welcomeDetailByCustomId(Long enterpriseId, Integer type, boolean isFront);

}
