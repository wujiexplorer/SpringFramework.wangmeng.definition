package com.lx.benefits.service.supplieruser.impl;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.admin.account.ModifyPasswordReqDto;
import com.lx.benefits.bean.entity.supplierInfo.SupplierInfo;
import com.lx.benefits.bean.entity.supplieruser.SupplierUser;
import com.lx.benefits.bean.entity.supplieruser.SupplierUserExample;
import com.lx.benefits.bean.util.EncryptUtil;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.util.StringUtil;
import com.lx.benefits.dao.supplieruser.SupplierUserDao;
import com.lx.benefits.mapper.supplierInfo.SupplierInfoMapper;
import com.lx.benefits.service.supplieruser.SupplierUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author by yingcai on 2018/12/3.
 */
@Service
public class SupplierUserServiceImpl implements SupplierUserService {

    private Logger logger = LoggerFactory.getLogger(SupplierUserService.class);

    @Autowired
    private SupplierUserDao supplierUserDao;

    @Autowired
    private SupplierInfoMapper supplierInfoMapper;

    @Override
    public Long insert(SupplierUser supplierUser) {
        return supplierUserDao.insert(supplierUser);
    }

    @Override
    public Integer update(SupplierUser supplierUser, SupplierUserExample example) {
        return supplierUserDao.update(supplierUser, example);
    }

    @Override
    public List<SupplierUser> find(SupplierUserExample example) {
        return supplierUserDao.find(example);
    }

    /**
     * 根据登录用户名查询供应商信息
     * @param LoginName 供应商登录用户名
     * @return SupplierUser
     */
    @Override
    public SupplierUser getSupplierUserByLoginName(String LoginName) {
        return supplierUserDao.getSupplierUserByLoginName(LoginName);
    }

    @Override
    public Integer count(SupplierUserExample example) {
        return supplierUserDao.count(example);
    }

    @Override
    public JSONObject modifyPassword(ModifyPasswordReqDto reqDto) {
        Long supplierId  = SessionContextHolder.getSessionSupplierInfo().getSupplierId();
        if (null == supplierId || supplierId < 1) {
            return Response.fail("登录失效，请求重新登录");
        }
        SupplierInfo supplierInfo = supplierInfoMapper.getSupplierInfoById(supplierId);
        if (null == supplierInfo) {
            return Response.fail("用户不存在");
        }
        String password = reqDto.getPassword();
        if (null == password || StringUtil.isEmpty(password)) {
            return Response.fail("密码不能为空");
        }
        String repeatPassword = reqDto.getRepeatPassword();
        if (null == repeatPassword || StringUtil.isEmpty(repeatPassword)) {
            return Response.fail("确认密码不能为空");
        }
        if (!password.equals(repeatPassword)) {
            return Response.fail("两次密码不一致");
        }
        SupplierUser supplierUser = new SupplierUser();
        // 需要重新set值的字段
        if (null != reqDto.getPassword() && !reqDto.getPassword().isEmpty()) {
            //需要重新set值的字段
            String secret = EncryptUtil.generateSecret();
            supplierUser.setSalt_key(secret);
            supplierUser.setPassword(EncryptUtil.encodePassword(reqDto.getPassword(),secret));
        } else {
            supplierUser.setPassword(null);
        }
        SupplierUserExample example = new SupplierUserExample();
        example.createCriteria().andSupplier_idEqualTo(supplierId);
        int update =  supplierUserDao.update(supplierUser,example);
        if ( update > 0) {
            return  Response.succ("密码修改成功");
        }
        return Response.fail("密码修改失败");
    }

    @Override
    public SupplierUser selectBySupplierId(Long sid) {
        return supplierUserDao.selectBySupplierId(sid);
    }

}
