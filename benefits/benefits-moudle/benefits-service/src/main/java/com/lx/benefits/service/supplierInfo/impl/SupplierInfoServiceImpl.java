package com.lx.benefits.service.supplierInfo.impl;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.admin.supplierinfo.SupplierInfoDto;
import com.lx.benefits.bean.dto.admin.supplierinfo.SupplierInfoPageRes;
import com.lx.benefits.bean.dto.admin.supplierinfo.SupplierInfoReq;
import com.lx.benefits.bean.entity.product.ProductEntity;
import com.lx.benefits.bean.entity.supplierInfo.SupplierInfo;
import com.lx.benefits.bean.entity.supplieruser.SupplierUser;
import com.lx.benefits.bean.entity.supplieruser.SupplierUserExample;
import com.lx.benefits.bean.util.BeansUtils;
import com.lx.benefits.bean.util.EncryptUtil;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.StringUtil;
import com.lx.benefits.mapper.product.ProductMapper;
import com.lx.benefits.mapper.supplierInfo.SupplierInfoMapper;
import com.lx.benefits.service.supplierInfo.SupplierInfoService;
import com.lx.benefits.service.supplieruser.SupplierUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * User: fan
 * Date: 2019/02/24
 * Time: 00:49
 */
@Service
public class SupplierInfoServiceImpl implements SupplierInfoService {

    @Autowired
    SupplierInfoMapper supplierInfoMapper;

    @Autowired
    SupplierUserService supplierUserService;

    @Autowired
    private ProductMapper productMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject insert(SupplierInfoDto record) {
        try {
            if (null == record) {
                return Response.fail("供应商信息不能为空");
            }
            if (null == record.getLoginName() || record.getLoginName().trim().isEmpty()) {
                return Response.fail("登录用户名不能为空");
            }
            if (null == record.getPassword() || record.getPassword().trim().isEmpty()) {
                return Response.fail("登录密码不能为空");
            }
            if (null == record.getName() || record.getName().trim().isEmpty()) {
                return Response.fail("供应商名称不能为空");
            }
            //判断供应商名称是否存在记录
            SupplierInfo fetchRes = supplierInfoMapper.getSupplierInfoByName(record.getName());
            if (null != fetchRes) {
                return Response.fail(String.format("供应商名称【%s】重复", record.getName()));
            }

            //判断登录名是否存在记录
            SupplierUser fetchResUser = supplierUserService.getSupplierUserByLoginName(record.getLoginName());
            if (null != fetchResUser) {
                return Response.fail(String.format("登录用户名【%s】重复", record.getLoginName()));
            }

            SupplierInfo supplierInfo = BeansUtils.copyProperties(record, SupplierInfo.class);
            supplierInfo.setId(null);
            supplierInfo.setCreateTime(new Date());
            supplierInfo.setUpdateTime(new Date());
            int row = supplierInfoMapper.insert(supplierInfo);
            if (row > 0) {
                SupplierUser supplierUser = new SupplierUser();
                String secret = EncryptUtil.generateSecret();
                supplierUser.setSalt_key(secret);
                supplierUser.setPassword(EncryptUtil.encodePassword(supplierInfo.getPassword(), secret));
                supplierUser.setLogin_name(supplierInfo.getLoginName());
                supplierUser.setSupplier_id(supplierInfo.getId());
                supplierUser.setPassword_update_time(new Date());
                supplierUser.setUser_name(supplierInfo.getName());
                supplierUser.setEmail(supplierInfo.getEmail());
                supplierUser.setPhone(supplierInfo.getMobile());
                supplierUser.setTelphone(supplierInfo.getTel());
                supplierUser.setAddress(supplierInfo.getAddress());
                supplierUser.setStatus(true);
                supplierUser.setCreate_time(new Date());
                supplierUser.setCreate_user("System");
                supplierUser.setDescription("");
                supplierUser.setUpdate_time(new Date());
                supplierUser.setUpdate_user("System");
                Long status = supplierUserService.insert(supplierUser);
                if (status < 1) {
                    throw new RuntimeException("失败");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("供应商名称或登录名重复");
        }
        return Response.succ("成功");
    }

    @Override
    public JSONObject getSupplierInfoById(Long id) {
        JSONObject jsonObject = new JSONObject();
        SupplierInfo supplierInfo = supplierInfoMapper.getSupplierInfoById(id);
        if (supplierInfo == null) {
            return Response.fail("用户不存在");
        }
        SupplierInfoDto supplierInfoDto = BeansUtils.copyProperties(supplierInfo, SupplierInfoDto.class);
        SupplierUser fetchResUser = supplierUserService.selectBySupplierId(id);
        supplierInfoDto.setLoginName(fetchResUser != null ? fetchResUser.getLogin_name() : null);
        supplierInfoDto.setPassword(null);
        jsonObject.put("info", supplierInfoDto);
        return Response.succ(jsonObject);
    }

    @Override
    public SupplierInfo getSupplierById(Long id) {
        SupplierInfo supplierInfo = supplierInfoMapper.getSupplierInfoById(id);
        return supplierInfo;
    }

    @Override
    public JSONObject getSupplierInfoList(SupplierInfoReq req) {
        req.setPage(req.getPage() > 0 ? (req.getPage() - 1) * req.getPageSize() : 0);
        JSONObject jsonObject = new JSONObject();
        Integer count = supplierInfoMapper.getSupplierInfoCount(req);
        List<SupplierInfo> supplierInfoList = supplierInfoMapper.getSupplierInfoList(req);
        List<SupplierInfoPageRes> supplierInfoDtoList = BeansUtils.copyArrayProperties(supplierInfoList, SupplierInfoPageRes.class);
        jsonObject.put("list", supplierInfoDtoList);
        jsonObject.put("count", count);
        return jsonObject;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject update(SupplierInfoDto record) {
        SupplierInfo supplierInfo = BeansUtils.copyProperties(record, SupplierInfo.class);
        supplierInfo.setUpdateTime(new Date());
        //判断供应商名称是否存在记录
        SupplierInfo fetchRes = supplierInfoMapper.getSupplierInfoByName(record.getName());
        if (null != fetchRes && !fetchRes.getId().equals(record.getId())) {
            return Response.fail(String.format("供应商名称【%s】重复", record.getName()));
        }
        int row = supplierInfoMapper.update(supplierInfo);
        if (row > 0) {
            //判断登录名是否存在记录
            SupplierUser fetchResUser = supplierUserService.getSupplierUserByLoginName(record.getLoginName());
            if (null != fetchResUser && !fetchResUser.getSupplier_id().equals(record.getId())) {
                return Response.fail(String.format("登录用户名【%s】重复", record.getLoginName()));
            }
            if (record.getPassword() != null && !StringUtil.isEmpty(record.getPassword())) {
                String secret = EncryptUtil.generateSecret();
                fetchResUser.setSalt_key(secret);
                fetchResUser.setPassword(EncryptUtil.encodePassword(record.getPassword(), secret));
            }
            // 只有用户名改变或密码改变才更新数据
            if (!record.getLoginName().equals(fetchResUser.getLogin_name()) || StringUtil.isNotBlank(record.getPassword())) {
                fetchResUser.setLogin_name(record.getLoginName());
                fetchResUser.setUpdate_time(new Date());
                SupplierUserExample example = new SupplierUserExample();
                example.createCriteria().andIdEqualTo(fetchResUser.getId());
                int update = supplierUserService.update(fetchResUser, example);
                if (update < 1) {
                    throw new RuntimeException("修改失败");
                }
            }
            ProductEntity product = new ProductEntity();
            product.setSupplierId(supplierInfo.getId());
            product.setSupplierName(supplierInfo.getName());
            productMapper.updateProduct(product);
            return Response.succ("成功");
        }
        return Response.fail("失败");
    }

    @Override
    public int delete(Long id) {
        return supplierInfoMapper.delete(id);
    }

    @Override
    public List<SupplierInfo> listByIds(List<Long> idList) {
        return supplierInfoMapper.selectByIds(idList);
    }
}
