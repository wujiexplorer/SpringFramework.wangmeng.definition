package com.lx.benefits.web.controller.enterprise;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.entity.memconsigneeaddress.ConsigneeAdress;
import com.lx.benefits.bean.entity.memconsigneeaddress.ConsigneeAdressExample;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.util.StringUtil;
import com.lx.benefits.dao.memconsigneeaddress.ConsignessAdressDao;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * User: fan
 * Date: 2019/02/23
 * Time: 20:46
 */
@RestController
@RequestMapping("/enterprise/address")
public class ShippingAddressController {

    @Autowired
    ConsignessAdressDao adressDao;

    @ApiOperation(value = "添加收货地址", response = JSONObject.class)
    @RequestMapping(value = "/addAddress", method = RequestMethod.POST)
    public JSONObject addAddress(@ApiParam(value = "Request", name = "adress") @RequestBody ConsigneeAdress adress) {
        Long employeeId =  SessionContextHolder.getSessionEmployeeInfo().getEmployeeId();
        if (null == employeeId || employeeId < 1) {
            return Response.fail("获取用户信息失败");
        } else {
            adress.setUserid(employeeId);
        }
        if (adress.getName() == null) {
            return Response.fail("收货收货人不能为空");
        }
        if (adress.getTel() == null) {
            return Response.fail("收货手机号码不能为空");
        }
        if (adress.getProvinceid() == null) {
            return Response.fail("省份ID不能为空");
        }
        if (adress.getProvince() == null) {
            return Response.fail("省份不能为空");
        }
        if (adress.getCityid() == null) {
            return Response.fail("城市ID不能为空");
        }
        if (adress.getCity() == null) {
            return Response.fail("城市不能为空");
        }
        if (adress.getDistrictid() == null) {
            return Response.fail("区县不能为空");
        }
        if (adress.getDistrict() == null) {
            return Response.fail("区县不能为空");
        }
        if (adress.getStreetid() == null) {
			adress.setStreetid(0L);
		}
        if (adress.getInfo() == null) {
            return Response.fail("收货详细地址不能为空");
        }
        Long insert = adressDao.insertSelective(adress);
        if (insert > 0) {
            return Response.succ("添加成功");
        } else {
            return Response.fail("添加失败");
        }

    }


    @ApiOperation(value = "查询收货地址", response = JSONObject.class)
    @RequestMapping(value = "/getAddressList", method = RequestMethod.GET)
    public JSONObject getAddressList() {
        Long employeeId = SessionContextHolder.getSessionEmployeeInfo().getEmployeeId();
        if (null == employeeId || employeeId < 0) {
            return Response.fail("获取用户信息失败");
        }
        List<ConsigneeAdress> list = adressDao.getAddressList(employeeId);

        for (ConsigneeAdress address : list) {
            String identityCard = address.getIdentityCard();
            if (!StringUtils.isEmpty(identityCard)) {
                identityCard = identityCard.replaceAll("(\\d{4})\\d{9}(\\d{5})","$1****$2");
                address.setIdentityCard(identityCard);
            }
        }
        return Response.succ(list);
    }

    @ApiOperation(value = "更新收货地址", response = JSONObject.class)
    @RequestMapping(value = "/updateAddress", method = RequestMethod.POST)
    public JSONObject updateAddress(@ApiParam(value = "Request", name = "adress") @RequestBody ConsigneeAdress adress) {
        Long employeeId = SessionContextHolder.getSessionEmployeeInfo().getEmployeeId();
        if (null == employeeId || employeeId < 0) {
            return Response.fail("获取用户信息失败");
        } else {
            adress.setUserid(employeeId);
        }
        if (adress.getId() == null) {
            return Response.fail("ID不能为空");
        }
        if (adress.getName() == null) {
            return Response.fail("收货收货人不能为空");
        }
        if (adress.getTel() == null) {
            return Response.fail("收货手机号码不能为空");
        }
        if (adress.getProvinceid() == null) {
            return Response.fail("省份ID不能为空");
        }
        if (adress.getProvince() == null) {
            return Response.fail("省份不能为空");
        }
        if (adress.getCityid() == null) {
            return Response.fail("城市ID不能为空");
        }
        if (adress.getCity() == null) {
            return Response.fail("城市不能为空");
        }
        if (adress.getDistrictid() == null) {
            return Response.fail("区县不能为空");
        }
        if (adress.getDistrict() == null) {
            return Response.fail("区县不能为空");
        }
		if (adress.getStreetid() == null) {
			adress.setStreetid(0L);
		}
        if (adress.getInfo() == null) {
            return Response.fail("收货详细地址不能为空");
        }
        if (adress.getIdentityCard() == null || StringUtil.isEmpty(adress.getIdentityCard())) {
            adress.setIdentityCard(null);
        }
        if (!StringUtil.isEmpty(adress.getIdentityCard()) && adress.getIdentityCard().contains("****")) {
            // 不更新身份证号
            adress.setIdentityCard(null);
        }
        if (adress.getIsdefault().equals("1")) {
            ConsigneeAdress consigneeAdress = new ConsigneeAdress();
            consigneeAdress.setIsdefault("0");
            consigneeAdress.setUpdateTime(new Date());
            ConsigneeAdressExample adressExample = new ConsigneeAdressExample();
            adressExample.createCriteria().andIdNotEqualTo(adress.getId());
            adressExample.createCriteria().andUserIdEqualTo(employeeId);
            adressDao.updateDefault(consigneeAdress, adressExample);
        }
        int update = adressDao.updateByExample(adress);
        if (update > 0) {
            return Response.succ("更新成功");
        } else {
            return Response.fail("更新失败");
        }
    }

    @ApiOperation(value = "删除收货地址", response = JSONObject.class)
    @RequestMapping(value = "/delAddress/{id}", method = RequestMethod.GET)
    public JSONObject delAddress(@ApiParam(value = "Request", name = "adress") @PathVariable Long id) {
        Long employeeId = SessionContextHolder.getSessionEmployeeInfo().getEmployeeId();
        if (null == employeeId || employeeId < 0) {
            return Response.fail("获取用户信息失败");
        }
        if (id == null) {
            return Response.fail("ID不能为空");
        }
        int del  = adressDao.delAddressList(id);
        if (del > 0) {
            return Response.succ("删除成功");
        } else {
            return Response.fail("删除失败");
        }
    }

    @ApiOperation(value = "设置默认收货地址", response = JSONObject.class)
    @RequestMapping(value = "/setDefaultAddress", method = RequestMethod.POST)
    public JSONObject setDefaultAddress(@ApiParam(value = "Request", name = "adress") @RequestBody ConsigneeAdress req) {
        Long employeeId = SessionContextHolder.getSessionEmployeeInfo().getEmployeeId();
        if (null == employeeId || employeeId < 0) {
            return Response.fail("获取用户信息失败");
        }
        if (req == null || req.getId() == null) {
            return Response.fail("ID不能为空");
        }
        ConsigneeAdress adress = new ConsigneeAdress();
        adress.setIsdefault("0");
        adress.setUpdateTime(new Date());
        ConsigneeAdressExample adressExample = new ConsigneeAdressExample();
        adressExample.createCriteria().andIdNotEqualTo(req.getId());
        adressExample.createCriteria().andUserIdEqualTo(employeeId);
        adressDao.updateDefault(adress, adressExample);
        adress.setId(req.getId());
        adress.setUserid(employeeId);
        adress.setIsdefault("1");
        adressDao.updateByExample(adress);
        return Response.succ("设置成功");
    }

}
