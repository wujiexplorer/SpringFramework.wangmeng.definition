package com.lx.benefits.dao.supplieruser.impl;

import com.google.common.base.Preconditions;
import com.lx.benefits.bean.entity.supplieruser.SupplierUser;
import com.lx.benefits.bean.entity.supplieruser.SupplierUserExample;
import com.lx.benefits.bean.util.ValidationUtil;
import com.lx.benefits.dao.supplieruser.SupplierUserDao;
import com.lx.benefits.mapper.supplieruser.SupplierUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author by yingcai on 2018/12/3.
 */
@Service
public class SupplierUserDaoImpl implements SupplierUserDao {

    @Autowired
    private SupplierUserMapper supplierUserMapper;

    @Override
    public Long insert(SupplierUser supplierUser) {
        Preconditions.checkNotNull(supplierUser);
        ValidationUtil.validate(supplierUser);
        supplierUserMapper.insertSelective(supplierUser);
        return supplierUser.getId();

    }

    @Override
    public Integer update(SupplierUser supplierUser, SupplierUserExample example) {
        return supplierUserMapper.updateByExampleSelective(supplierUser, example);
    }

    @Override
    public List<SupplierUser> find(SupplierUserExample example) {
        return supplierUserMapper.selectByExample(example);
    }

    /**
     * 根据登录用户名查询供应商信息
     * @param LoginName 供应商登录用户名
     * @return SupplierUser
     */
    @Override
    public SupplierUser getSupplierUserByLoginName(String LoginName) {
        return supplierUserMapper.getSupplierUserByLoginName(LoginName);
    }
    
    @Override
    public Integer count(SupplierUserExample example) {
        return supplierUserMapper.countByExample(example);
    }

    @Override
    public SupplierUser selectBySupplierId(Long sid) {
        return supplierUserMapper.selectBySupplierId(sid);
    }
}
