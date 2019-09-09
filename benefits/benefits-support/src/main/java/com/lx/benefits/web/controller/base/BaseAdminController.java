package com.lx.benefits.web.controller.base;

import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.util.SessionFuliInfo;
import com.lx.benefits.bean.util.SessionSupplierInfo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author unknow on 2018-12-04 03:07.
 */
@Api(tags = "运营后台-页面接口模块")
@RestController
public class BaseAdminController {
    
    private SessionFuliInfo sessionFuliInfo = SessionContextHolder.getSessionFuliInfo();

    private SessionSupplierInfo sessionSupplierInfo = SessionContextHolder.getSessionSupplierInfo();

    protected SessionFuliInfo getSessionFuliInfo() {
        if (sessionFuliInfo != null) {
            return sessionFuliInfo;
        }
        return SessionContextHolder.getSessionFuliInfo();
    }

    protected SessionSupplierInfo getSessionSupplierInfo() {
        if (sessionSupplierInfo != null) {
            return sessionSupplierInfo;
        }
        return SessionContextHolder.getSessionSupplierInfo();
    }

}