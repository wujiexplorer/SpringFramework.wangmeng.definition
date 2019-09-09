package com.lx.benefits.web.controller.admin;


import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.base.dto.FLPageDto;
import com.lx.benefits.bean.dto.admin.address.AddressImportItemQueryDto;
import com.lx.benefits.bean.dto.admin.address.AddressImportReqDto;
import com.lx.benefits.bean.dto.admin.address.AddressInfoDto;
import com.lx.benefits.bean.dto.admin.order.OrderImportItemResDto;
import com.lx.benefits.bean.dto.admin.order.OrderImportResDto;
import com.lx.benefits.bean.entity.basedistrictinfo.DistrictInfo;
import com.lx.benefits.bean.entity.basedistrictinfo.DistrictInfoExample;
import com.lx.benefits.bean.entity.employeeuserinfo.EmployeeUserInfo;
import com.lx.benefits.bean.entity.memaddressimportitem.AddressImportItem;
import com.lx.benefits.bean.entity.memadressimport.AddressImportInfo;
import com.lx.benefits.bean.entity.memconsigneeaddress.ConsigneeAdress;
import com.lx.benefits.bean.entity.memconsigneeaddress.ConsigneeAdressExample;
import com.lx.benefits.bean.util.EasyExcelUtil;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.util.SessionFuliInfo;
import com.lx.benefits.service.basedistrictinfo.DistricitInfoService;
import com.lx.benefits.service.employeeuserinfo.EmployeeUserInfoService;
import com.lx.benefits.service.memaddressimport.AddressImportInfoService;
import com.lx.benefits.service.memaddressimportitem.AddressImportItemService;
import com.lx.benefits.service.memconsigneeadress.ConsigeneeAdressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

;

/**
 * 用户 - 收货地址控制器
 */
@Api(tags = "运营后台-管理员用户模块")
@RestController("addressController")
@RequestMapping("/admin/address")
public class AddressController {

    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    private AddressImportInfoService addressImportInfoService;

    @Autowired
    private AddressImportItemService addressImportItemService;

    @Autowired
    private EmployeeUserInfoService employeeUserInfoService;

    @Autowired
    private DistricitInfoService districitInfoService;

    @Autowired
    private ConsigeneeAdressService consigeneeAdressService;

    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @ApiOperation(value = "导入收货地址", response = Boolean.class)
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public JSONObject importExcel(@ApiParam(value = "Request", name = "reqDto") @RequestBody AddressImportReqDto reqDto) {
        if (null == reqDto) {
            return Response.fail("收货地址参数不能为空!");
        }
        String addressListFile = reqDto.getAddressListFile();
        if (null == addressListFile || addressListFile.isEmpty()) {
            return Response.fail("收货文件不能为空!");
        }
        try {
            SessionFuliInfo sessionFuliInfo = SessionContextHolder.getSessionFuliInfo();
            // 记录导入记录
            AddressImportInfo addressImportInfo = new AddressImportInfo();
            addressImportInfo.setEnterprId(0L);
            addressImportInfo.setOptUserId(sessionFuliInfo.getAdminId());
            addressImportInfo.setImportCount(0L);
            addressImportInfo.setFileUrl(addressListFile);
            addressImportInfo.setImportErr(0L);
            addressImportInfo.setImportSuc(0L);


            UrlResource resource = new UrlResource(addressListFile);
            List<Object> data = EasyExcelUtil.readExcelWithModel(resource.getInputStream(), AddressInfoDto.class, ExcelTypeEnum.XLSX);
            //10. 记录导入记录
            Long importId = addressImportInfoService.insertSelective(addressImportInfo);
            addressImportInfo.setImportId(importId);
            if (importId < 1) {
                return Response.fail("导入失败");
            }
            int line = 2;
            long importNum = 0;
            int latch = data.size() > 20 ? 20 : data.size();
            CountDownLatch countDownLatch = new CountDownLatch(latch);
            int[] temp = {0, 0};
            long startTime = System.currentTimeMillis();
            for (Object obj : data) {
                importNum++;
                AddressInfoDto addressInfoDto = (AddressInfoDto) obj;
                if (null == addressInfoDto) {
                    countDownLatch.countDown();
                    return Response.fail(String.format("第 %s 代信息不能为空!", line));
                }
                if (null == addressInfoDto.getName() || addressInfoDto.getName().trim().isEmpty()) {
                    return Response.fail(String.format("第 %s 行登录名不能为空!", line));
                }
                if (null == addressInfoDto.getUsername() || addressInfoDto.getUsername().trim().isEmpty()) {
                    return Response.fail(String.format("第 %s 行收件人不能为空!", line));
                }
                if (null == addressInfoDto.getTel() || addressInfoDto.getTel().trim().isEmpty()) {
                    return Response.fail(String.format("第 %s 行手机号不能为空!", line));
                }
                if (null == addressInfoDto.getProvince() || addressInfoDto.getProvince().trim().isEmpty()) {
                    return Response.fail(String.format("第 %s 行省份不能为空!", line));
                }
                if (null == addressInfoDto.getCity() || addressInfoDto.getCity().trim().isEmpty()) {
                    return Response.fail(String.format("第 %s 行城市不能为空!", line));
                }
                if (null == addressInfoDto.getCounty() || addressInfoDto.getCounty().trim().isEmpty()) {
                    return Response.fail(String.format("第 %s 行地区不能为空!", line));
                }
                if (null == addressInfoDto.getInfo() || addressInfoDto.getInfo().trim().isEmpty()) {
                    return Response.fail(String.format("第 %s 行详细地址不能为空!", line));
                }
                logger.info("创建收货地址,请求参数:{}", JSON.toJSONString(obj));
                CompletableFuture.supplyAsync(() -> createAddress(addressInfoDto, countDownLatch, temp, importId), threadPoolTaskExecutor);
                line++;
            }
            if (countDownLatch.await(300, TimeUnit.SECONDS)) {
                long endTime = System.currentTimeMillis();
                logger.info("处理完毕，成功{}，失败{}，共花费:{}ms", temp[0], temp[1], endTime - startTime);
            }
            AddressImportInfo updateAddress = new AddressImportInfo();
            updateAddress.setImportCount(importNum);
            updateAddress.setImportId(importId);
            addressImportInfoService.update(updateAddress);
        } catch (Exception e) {
            logger.error("代下单 {}", e.getMessage());
            return Response.fail("解析代下单文件异常");
        }
        return Response.succ("导入成功");
    }


    private String createAddress(AddressInfoDto addressInfoDto, CountDownLatch countDownLatch, int[] temp, Long importId) {
        String result = "";
        AddressImportItem addressImportItem = new AddressImportItem();
        ConsigneeAdress consigneeAdress = new ConsigneeAdress();
        Long empolyeeId = 0L;
        Long enterprId = 0L;
        try {
            PropertyUtils.copyProperties(addressImportItem, addressInfoDto);
            PropertyUtils.copyProperties(consigneeAdress, addressInfoDto);
            String loginName = addressInfoDto.getName();
            if (!StringUtils.isEmpty(loginName)) {
                EmployeeUserInfo employeeUserInfo = employeeUserInfoService.findByLoginName(loginName);
                if (employeeUserInfo != null) {
                    empolyeeId = employeeUserInfo.getEmployeeId();
                    consigneeAdress.setUserid(empolyeeId);
                    consigneeAdress.setName(addressInfoDto.getUsername());
                    enterprId = employeeUserInfo.getEnterprId();
                } else {
                    throw new RuntimeException("员工不存在");
                }
            } else {
                throw new RuntimeException("员工不存在");
            }

            String province = addressInfoDto.getProvince();
            if (!StringUtils.isEmpty(province)) {
                DistrictInfoExample pExample = new DistrictInfoExample();
                pExample.createCriteria().andNameEqualTo(province).andIsDeleteTo(0);
                DistrictInfo providInfo = districitInfoService.findByName(pExample);
                if (null != providInfo) {
                    consigneeAdress.setProvinceid(providInfo.getId());
                } else {
                    throw new RuntimeException("收货省份未找到");
                }
            } else {
                throw new RuntimeException("收货省份未找到");
            }

            String city = addressInfoDto.getCity();
            if (!StringUtils.isEmpty(city)) {
                DistrictInfoExample cExample = new DistrictInfoExample();
                cExample.createCriteria().andNameEqualTo(city).andIsDeleteTo(0);
                DistrictInfo cityidInfo = districitInfoService.findByName(cExample);
                if (null != cityidInfo) {
                    consigneeAdress.setCityid(cityidInfo.getId());
                } else {
                    throw new RuntimeException("收货城市未找到");
                }
            } else {
                throw new RuntimeException("收货城市错误");
            }

            String county = addressInfoDto.getCounty();
            if (!StringUtils.isEmpty(county)) {
                DistrictInfoExample dExample = new DistrictInfoExample();
                dExample.createCriteria().andNameEqualTo(county).andIsDeleteTo(0).andParentIdTo(consigneeAdress.getCityid());
                DistrictInfo districtidInfo = districitInfoService.findByName(dExample);
                if (null != districtidInfo) {
                    consigneeAdress.setDistrictid(districtidInfo.getId());
                    consigneeAdress.setDistrict(districtidInfo.getName());
                } else {
                    throw new RuntimeException("收货城区未找到");
                }
            } else {
                throw new RuntimeException("收货城区未找到");
            }

            String streetname = addressInfoDto.getStreet();
            if (!StringUtils.isEmpty(streetname)) {
                DistrictInfoExample sExample = new DistrictInfoExample();
                sExample.createCriteria().andNameEqualTo(streetname).andIsDeleteTo(0).andParentIdTo(consigneeAdress.getDistrictid());
                DistrictInfo streetidInfo = districitInfoService.findByName(sExample);
                if (null != streetidInfo) {
                    consigneeAdress.setStreetid(streetidInfo.getId());
                }
            } else {
                consigneeAdress.setStreet("");
            }

            if (null == consigneeAdress.getIdentityCard()) {
                consigneeAdress.setIdentityCard("");
            }
            // 默认收获地址
            consigneeAdress.setIsdefault("1");
            // 收获地址
            Long addressStatus = consigeneeAdressService.insert(consigneeAdress);
            logger.info("新增收获地址 {} insert={} ", JSON.toJSONString(consigneeAdress), addressStatus);
            if (addressStatus > 0 && empolyeeId > 0) {
                temp[0] += 1;

                // 修改地址默认状态
                ConsigneeAdress adress = new ConsigneeAdress();
                adress.setIsdefault("0");
                ConsigneeAdressExample adressExample = new ConsigneeAdressExample();
                adressExample.createCriteria().andIdNotEqualTo(addressStatus);
                adressExample.createCriteria().andUserIdEqualTo(empolyeeId);
                Integer defaultUpdate = consigeneeAdressService.updateDefault(adress, adressExample);
                logger.info("更新收获地址默认状态 defaultUpdate={} ", defaultUpdate);
                addressImportItem.setImportMsg("成功");
                addressImportItem.setStatus(0);
            } else {
                addressImportItem.setStatus(1);
                temp[1] += 1;
                addressImportItem.setImportMsg("失败");
            }
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                addressImportItem.setImportMsg(e.getMessage());
            }
            addressImportItem.setStatus(1);
            logger.info("createAddress 新增第异常 {}", e.getMessage());
            temp[1] += 1;
        } finally {
            countDownLatch.countDown();
        }

        try {
            // 更新导入状态
            AddressImportInfo addressImportInfo = new AddressImportInfo();
            addressImportInfo.setImportErr((long) temp[1]);
            addressImportInfo.setImportSuc((long) temp[0]);
            addressImportInfo.setImportId(importId);
            addressImportInfo.setEnterprId(enterprId);
            Integer update = addressImportInfoService.update(addressImportInfo);
            logger.info("更新导入记录 {} update={} ", JSON.toJSONString(addressImportInfo), update);

            // 地址详情
            addressImportItem.setImportId(importId);
            if (null == addressImportItem.getIdentityCard()) {
                addressImportItem.setIdentityCard("");
            }
            Long insert = addressImportItemService.insertSelective(addressImportItem);
            logger.info("详情导入记录 {} update={} ", JSON.toJSONString(addressImportItem), insert);
        } catch (Exception e) {
            logger.info("新增详情记录 Exception {} ", e.getMessage());
        }
        return result;
    }


    @ApiOperation(value = "导入记录列表", response = OrderImportResDto.class)
    @RequestMapping(value = "/importlist", method = RequestMethod.GET)
    public JSONObject importList(@ApiParam(value = "Request", name = "req") @ModelAttribute FLPageDto req) {
        return addressImportInfoService.selectByExample(req);
    }

    @ApiOperation(value = "订单导入详情列表", response = OrderImportItemResDto.class)
    @RequestMapping(value = "/importitem", method = RequestMethod.GET)
    public JSONObject importitem(@ApiParam(value = "Request", name = "req") @ModelAttribute AddressImportItemQueryDto req) {
        return addressImportItemService.selectByExample(req);
    }

}
