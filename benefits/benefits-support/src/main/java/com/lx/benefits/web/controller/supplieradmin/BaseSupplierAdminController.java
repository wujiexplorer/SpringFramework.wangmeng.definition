package com.lx.benefits.web.controller.supplieradmin;

import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.util.SessionSupplierInfo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author unknow on 2018-12-04 01:46.
 */
@Api(tags = "供应商后台-页面接口模块")
@RestController
public class BaseSupplierAdminController {
    
    protected SessionSupplierInfo sessionSupplierInfo = SessionContextHolder.getSessionSupplierInfo();

}
