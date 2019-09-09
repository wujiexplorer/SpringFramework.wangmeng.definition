package com.lx.benefits.service.supplieruser;


import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.admin.account.ModifyPasswordReqDto;
import com.lx.benefits.bean.entity.supplieruser.SupplierUser;
import com.lx.benefits.bean.entity.supplieruser.SupplierUserExample;

import java.util.List;

/**
 * @author by Ginger on 2017/8/23.
 */
public interface SupplierUserService {

    Long insert(SupplierUser supplierUser);


    Integer update(SupplierUser supplierUser, SupplierUserExample example);

    List<SupplierUser> find(SupplierUserExample example);

    /**
     * 根据登录用户名查询供应商信息
     * @param LoginName 供应商登录用户名
     * @return SupplierUser
     */
    SupplierUser getSupplierUserByLoginName(String LoginName);
    
    Integer count(SupplierUserExample example);

    JSONObject modifyPassword(ModifyPasswordReqDto req);

    SupplierUser selectBySupplierId(Long sid);


}
