package com.lx.benefits.web.controller.enterpriseadmin;

import com.lx.benefits.bean.util.SessionEnterpriseInfo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author unknow on 2018-12-04 03:08.
 */
@Api(tags = "企业后台-页面接口模块")
@RestController
public class BaseEnterpriseAdminController{
    
    protected SessionEnterpriseInfo sessionEnterpriseInfo;

}