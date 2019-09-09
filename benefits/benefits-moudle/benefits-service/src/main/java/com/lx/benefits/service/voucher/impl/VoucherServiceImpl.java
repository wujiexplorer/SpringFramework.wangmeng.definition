package com.lx.benefits.service.voucher.impl;

import com.alibaba.fastjson.TypeReference;
import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.dto.admin.customized.GoodsModuleInfoDto;
import com.lx.benefits.bean.dto.enterpriseadmin.employee.EmployeeInfoDto;
import com.lx.benefits.bean.dto.product.ProductSearchBean;
import com.lx.benefits.bean.entity.employeecreditinfo.EmployeeCreditInfo;
import com.lx.benefits.bean.entity.employeeuserinfo.EmployeeUserInfo;
import com.lx.benefits.bean.entity.product.ProductEntity;
import com.lx.benefits.bean.entity.product.SkuEntity;
import com.lx.benefits.bean.entity.voucher.Voucher;
import com.lx.benefits.bean.entity.voucher.VoucherEmployeeInfo;
import com.lx.benefits.bean.entity.voucher.VoucherExample;
import com.lx.benefits.bean.entity.voucher.VoucherProductTemplate;
import com.lx.benefits.bean.enums.VoucherEnum;
import com.lx.benefits.bean.util.JsonUtil;
import com.lx.benefits.bean.util.Query;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.util.StringUtil;
import com.lx.benefits.bean.vo.order.OrderConfirmVO;
import com.lx.benefits.constant.MemberInfoConstant;
import com.lx.benefits.mapper.voucher.VoucherMapper;
import com.lx.benefits.service.employeecreditinfo.EmployeeCreditInfoService;
import com.lx.benefits.service.employeeuserinfo.EmployeeUserInfoService;
import com.lx.benefits.service.enterprcustomgoods.EnterprCustomGoodsService;
import com.lx.benefits.service.product.ProductService;
import com.lx.benefits.service.product.ProductTopicService;
import com.lx.benefits.service.product.SkuService;
import com.lx.benefits.service.voucher.VoucherProductTemplateService;
import com.lx.benefits.service.voucher.VoucherService;
import com.lx.benefits.support.order.OrderSupport;
import com.lx.benefits.utils.DateTimeUtils;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import sun.security.util.Length;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;

/**
 * User:wangmeng
 * Date:2019/8/8
 * Time:10:29
 * Version:2.x
 * Description:TODO
 **/
@Service
public class VoucherServiceImpl implements VoucherService {

    @Autowired
    private VoucherMapper voucherMapper;

    @Autowired
    private VoucherProductTemplateService voucherProductTemplateService;

    @Autowired
    private SkuService skuService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductTopicService productTopicService;

    @Autowired
    private EmployeeCreditInfoService employeeCreditInfoService;

    @Resource
    private OrderSupport orderSupport;

    @Autowired
    private EmployeeUserInfoService employeeUserInfoService;

    @Autowired
    private EnterprCustomGoodsService enterprCustomGoodsService;

    @Override
    @Transactional
    public Voucher insertVoucher(Voucher voucher) {
        Date startDate = DateTimeUtils.stringToTime(voucher.getStartTime(), DateTimeUtils.DEFAULT_TIME_FORMAT);
        Date endDate = DateTimeUtils.stringToTime(voucher.getEndTime(), DateTimeUtils.DEFAULT_TIME_FORMAT);
        voucher.setValidateStartTime(startDate);
        voucher.setValidateEndTime(endDate);
        voucher.setCreateTime(new Date());
        voucher.setIsStop((byte) 0);
        voucher.setLeftAmount(voucher.getTotalRelease());
        voucher.setVoucherType(0);
        voucher.setVoucherStatus(0);
        if (System.currentTimeMillis() >= startDate.getTime() && System.currentTimeMillis() < endDate.getTime()) {
            voucher.setVoucherStatus(1);
        }
        voucher.setReceivedAmount(0);
        voucher.setUsedAmount(0);
        validate(voucher);
        try {
            Integer count = voucherMapper.insertSelective(voucher);
        } catch (Exception e) {
            throw new BusinessException("优惠卷名称不能重复！");
        }
        process(voucher);
        validateTime(voucher);
        return voucher;
    }

    @Override
    public Integer updateVoucher(Voucher voucher) {
        voucher.setUpdateTime(new Date());
        Voucher voucherTemp = voucherMapper.selectByPrimaryKey(voucher.getVoucherId());
        if (null != voucher.getTotalRelease()) {
            if (voucher.getTotalRelease() < voucherTemp.getTotalRelease()) {
                throw new BusinessException("修改优惠卷总量只能增加不能减少！");
            }else{
                voucher.setLeftAmount(voucher.getTotalRelease()-voucherTemp.getTotalRelease()+voucherTemp.getLeftAmount());
            }
        }
        if (voucher.getIsStop() != null && voucher.getIsStop() == 1) {
            voucher.setVoucherStatus(2);
        }
        return voucherMapper.updateByPrimaryKeySelective(voucher);
    }

    @Override
    public PageResultBean<Voucher> findVouchers(String voucherName, Integer voucherStatus, Integer voucherType, PageBean pageBean) {
        VoucherExample voucherExample = new VoucherExample();
        VoucherExample.Criteria criteria = voucherExample.createCriteria();
        if (!StringUtil.isEmpty(voucherName)) {
            criteria.andVoucherNameLike("%" + voucherName + "%");
        }
        if (null != voucherStatus) {
            criteria.andVoucherStatusEqualTo(voucherStatus);
        }
        if (null != voucherType) {
            criteria.andVoucherTypeEqualTo(voucherType);
        }
        Integer count = voucherMapper.countByExample(voucherExample);
        if (count == 0) {
            return new PageResultBean<>(pageBean.getPage(), pageBean.getPageSize(), count, Collections.emptyList());
        }
        voucherExample.setOffset(pageBean.getOffset());
        voucherExample.setLimit(pageBean.getPageSize());
        voucherExample.setOrderByClause("voucher_id desc");
        List<Voucher> list = voucherMapper.selectByExample(voucherExample);
        validateEffectiveVoucher(list);
        return new PageResultBean<>(pageBean.getPage(), pageBean.getPageSize(), count, list);
    }

    @Override
    public List<Voucher> findTemplateVouchers() {
        List<Voucher> list = new ArrayList<>();
        List<Voucher> vouchers = voucherMapper.selectByExample(null);
        Boolean flag = false;
        if (vouchers.size() >= 1) {
            list.add(vouchers.get(0));
            for (int i = 0, len = vouchers.size(); i < len; i++) {
                flag = false;
                for (int j = 0, size = list.size(); j < size; j++) {
                    if (list.get(j).getUseThreshold().equals(vouchers.get(i).getUseThreshold())
                            && list.get(j).getBenefitContent().equals(vouchers.get(i).getBenefitContent())) {
                        flag = true;
                        break;
                    }
                }
                if (flag == false) {
                    list.add(vouchers.get(i));
                }
            }
            return list;
        }
        return null;
    }

    @Override
    public List<Voucher> findVouchersByEmployee() {
        List<Voucher> list = new ArrayList<>();
        List<Voucher> vouchers = voucherMapper.selectByExample(null);
        for (int i = 0, len = vouchers.size(); i < len; i++) {
            if (vouchers.get(i).getValidateStartTime().getTime() <= System.currentTimeMillis() &&
                    vouchers.get(i).getValidateEndTime().getTime() > System.currentTimeMillis() && vouchers.get(i).getVoucherStatus() != 2 && vouchers.get(i).getLeftAmount() !=0) {
                list.add(vouchers.get(i));
            }
        }
        validateEffectiveVoucher(list);
        showEmployeeReceivedVouchersNum(list);
        return list;
    }

    @Override
    @Transactional
    public Voucher processReceivedVouchers(Long voucherId) {
        if (null == voucherId) {
            throw new BusinessException("优惠卷ID参数不能为空！");
        }
        Long employeeId = SessionContextHolder.getSessionEmployeeInfo().getEmployeeId();
        Voucher voucher = voucherMapper.selectByPrimaryKey(voucherId);
        EmployeeCreditInfo employeeCreditInfo = employeeCreditInfoService.getEmployeeCreditInfo(employeeId, 0L);
        if (null != employeeCreditInfo) {
            if (StringUtil.isEmpty(employeeCreditInfo.getReceivedVouchers())) {
                EmployeeCreditInfo employeeCreditInfoTemp = new EmployeeCreditInfo();
                voucher.setNum(1);
                VoucherEmployeeInfo voucherEmployeeInfo = new VoucherEmployeeInfo();
                voucherEmployeeInfo.setEmployeeId(employeeId);
                voucherEmployeeInfo.setNum(1);
                voucher.setVoucherEmployeeInfo(voucherEmployeeInfo);
                employeeCreditInfoTemp.setReceivedVouchers(voucher.getVoucherId() + ":1,");
                employeeCreditInfoTemp.setEmployeeId(employeeId);
                employeeCreditInfoService.updateGrainIdByEmployeeIdSelective(employeeCreditInfoTemp);
            } else {
                List<String> list = new ArrayList<>(Arrays.asList(employeeCreditInfo.getReceivedVouchers().split(",")));
                StringBuilder stringBuilder = new StringBuilder();
                Boolean flag = false;
                for (int i = 0, len = list.size(); i < len; i++) {
                    String temp = null;
                    Long voucherIdTemp = Long.parseLong(list.get(i).split(":")[0]);
                    Integer count = Integer.parseInt(list.get(i).split(":")[1]);
                    if (voucherIdTemp.equals(voucher.getVoucherId()) && count >= voucher.getLimitNum() && voucher.getLimitNum() != 0) {
                        throw new BusinessException("该优惠卷每人限领次数不超过" + count);
                    } else if ((voucherIdTemp.equals(voucher.getVoucherId()) && count < voucher.getLimitNum()) || (voucher.getLimitNum() == 0 && voucherIdTemp.equals(voucher.getVoucherId()))) {
                        count++;
                        stringBuilder.append(voucherIdTemp + ":" + count + ",");
                        VoucherEmployeeInfo voucherEmployeeInfo = new VoucherEmployeeInfo();
                        voucherEmployeeInfo.setEmployeeId(employeeId);
                        voucherEmployeeInfo.setNum(count);
                        voucher.setVoucherEmployeeInfo(voucherEmployeeInfo);
                        voucher.setNum(count);
                        flag = true;
                        continue;
                    }
                    stringBuilder.append(list.get(i) + ",");
                }
                if (flag == false) {
                    VoucherEmployeeInfo voucherEmployeeInfo = new VoucherEmployeeInfo();
                    voucherEmployeeInfo.setEmployeeId(employeeId);
                    voucherEmployeeInfo.setNum(1);
                    voucher.setVoucherEmployeeInfo(voucherEmployeeInfo);
                    voucher.setNum(1);
                    stringBuilder.append(voucher.getVoucherId() + ":1,");
                }
                String resStr = stringBuilder.toString();
                EmployeeCreditInfo employeeCreditInfoTemp = new EmployeeCreditInfo();
                employeeCreditInfoTemp.setReceivedVouchers(resStr);
                employeeCreditInfoTemp.setEmployeeId(employeeId);
                employeeCreditInfoService.updateGrainIdByEmployeeIdSelective(employeeCreditInfoTemp);
            }
        }
        //领取优惠卷，优惠卷剩余量与已领取量处理
        Voucher voucherTemp = new Voucher();
        voucherTemp.setVoucherId(voucherId);
        voucherTemp.setReceivedAmount(voucher.getReceivedAmount() + 1);
        voucherTemp.setLeftAmount(voucher.getLeftAmount() - 1);
        voucherMapper.updateByPrimaryKeySelective(voucherTemp);
        voucher.setLeftAmount(voucherTemp.getLeftAmount());
        voucher.setReceivedAmount(voucherTemp.getReceivedAmount());
        if(voucher.getLeftAmount()<0){
            throw new BusinessException("优惠劵已经领完！");
        }
        processReceivedVouchersNum(employeeId,voucher.getNum(),voucherId);
        return voucher;
    }

    @Override
    public PageResultBean<SkuEntity> findSkusByVoucherId(Map<String, Object> params) {
        if (params.isEmpty()) {
            throw new BusinessException("params参数不能为空！");
        }
        Integer count = skuService.countByVoucherId(params);
        if (count == 0) {
            return new PageResultBean<>((Integer) (params.get("page")), (Integer) (params.get("pageSize")), count, Collections.emptyList());
        }
        Query query = new Query(params);
        List<SkuEntity> list = skuService.selectByVoucherId(query);
        if (!list.isEmpty()) {
            for (int i = 0, len = list.size(); i < len; i++) {
                List<Long> voucherIds = JsonUtil.parseObject(list.get(i).getVoucherIds(), new TypeReference<List<Long>>() {
                });
                List<Voucher> vouchers = new ArrayList<>();
                for (int j = 0, size = voucherIds.size(); j < voucherIds.size(); j++) {
                    Voucher voucher = voucherMapper.selectByPrimaryKey(voucherIds.get(j));
                    vouchers.add(voucher);
                }
                list.get(i).setVouchers(vouchers);
            }
        }
        return new PageResultBean<>((Integer) (params.get("page")), (Integer) (params.get("pageSize")), count, list);
    }

    @Override
    public PageResultBean<ProductEntity> findSpusByVoucherId(Map<String, Object> params) {
        if (params.isEmpty()) {
            throw new BusinessException("params参数不能为空！");
        }
        ProductSearchBean productSearchBean = assembleProductSearchBean();
        params.put("searchBean",productSearchBean);
        Integer count = productService.countSpusByVoucherId(params);
        if (count == 0) {
            return new PageResultBean<>((Integer) (params.get("page")), (Integer) (params.get("pageSize")), count, Collections.emptyList());
        }
        Query query = new Query(params);
        List<ProductEntity> list = productService.selectSpusByVoucherId(query);
        if (!list.isEmpty()) {
            for (int i = 0, len = list.size(); i < len; i++) {
                List<Long> voucherIds = JsonUtil.parseObject(list.get(i).getVoucherIds(), new TypeReference<List<Long>>() {
                });
                List<Voucher> vouchers = new ArrayList<>();
                EmployeeCreditInfo employeeCreditInfo =
                        employeeCreditInfoService.getEmployeeCreditInfo(SessionContextHolder.getSessionEmployeeInfo().getEmployeeId(), 0L);
                String receivedVouchers = employeeCreditInfo.getReceivedVouchers();
                for (int j = 0, size = voucherIds.size(); j < voucherIds.size(); j++) {
                    Voucher voucher = voucherMapper.selectByPrimaryKey(voucherIds.get(j));
                    Integer flag = 1;
                    if(StringUtil.isNotEmpty(receivedVouchers)){
                        String[] receivedVouchersArray = receivedVouchers.split(",");
                        for(int z=0,len1 = receivedVouchersArray.length;z<len1;z++){
                            Long voucherId = Long.parseLong(receivedVouchersArray[z].split(":")[0]);
                            Integer num = Integer.parseInt(receivedVouchersArray[z].split(":")[1]);
                            if(voucherId.equals(voucherIds.get(j))&& num <= 0){
                                flag = 2;
                                break;
                            }else if(voucherId.equals(voucherIds.get(j))&& num > 0){
                                flag = 3;
                                break;
                            }
                            if(z == len1-1){
                                if(flag == 1){
                                    flag = 2;
                                }
                            }
                        }
                        if(flag == 2){
                            continue;
                        }
                    }else{
                       if(voucher.getVoucherStatus() == 2){
                           continue;
                       }
                    }
                    if (voucher != null && System.currentTimeMillis() >= voucher.getValidateStartTime().getTime() && System.currentTimeMillis() < voucher.getValidateEndTime().getTime()) {
                        vouchers.add(voucher);
                    }
                }
                list.get(i).setVouchers(vouchers);
//                List<SkuEntity> skuEntities = productService.getSkuBySpuCode(list.get(i).getSpuCode(), 1);
//                BigDecimal goodsPrice = skuEntities.get(0).getGoodsPrice();
//                for (int z = 0, len1 = skuEntities.size(); z < len1; z++) {
//                    BigDecimal temp = skuEntities.get(z).getGoodsPrice();
//                    if (goodsPrice.compareTo(temp) > -1) {
//                        goodsPrice = temp;
//                    }
//                }
//                list.get(i).setMinGoodsPrice(goodsPrice);
//                if (list.get(i).getVouchers().get(0) != null) {
//                    list.get(i).setSortDate(list.get(i).getVouchers().get(0).getCreateTime());
//                }
            }
        }
        //list = sortedSpus(list, params);

        return new PageResultBean<>((Integer) (params.get("page")), (Integer) (params.get("pageSize")), count, list);
    }

    @Override
    public List<Long> fingFullRangeVoucherIds() {
        List<Voucher> list = voucherMapper.selectByExample(null);
        List<Long> voucherIds = new ArrayList<>();
        for (int i = 0, len = list.size(); i < len; i++) {
            if (list.get(i).getUseCase() == 0) {
                voucherIds.add(list.get(i).getVoucherId());
            }
        }
        return voucherIds;
    }

    @Override
    public Voucher getVoucherByVoucherId(Long voucherId) {
        if (null == voucherId) {
            throw new BusinessException("优惠卷ID参数不能为空！");
        }
        return voucherMapper.selectByPrimaryKey(voucherId);
    }

    @Override
    public Boolean validateCartVouchers(String voucherIds) {
        if (StringUtil.isEmpty(voucherIds)) {
            throw new BusinessException("优惠卷IDs参数不能为空！");
        }
        Boolean flag = false;
        String[] tem = voucherIds.split(",");
        Long[] temLong = new Long[tem.length];
        for (int i = 0, len = tem.length; i < len; i++) {
            temLong[i] = Long.parseLong(tem[i]);
        }
        List<Long> voucherIdList = new ArrayList<>(Arrays.asList(temLong));
        List<Voucher> vouchers = findVouchersByEmployee();
        List<Long> voucherIdsTemp = new ArrayList<>();
        for (int i = 0, len = vouchers.size(); i < len; i++) {
            voucherIdsTemp.add(vouchers.get(i).getVoucherId());
        }
        List<Long> resVoucherIds = new ArrayList<>();
        for (int i = 0, len = voucherIdList.size(); i < len; i++) {
            if (voucherIdsTemp.contains(voucherIdList.get(i))) {
                resVoucherIds.add(voucherIdList.get(i));
            }
        }
        List<Long> resVoucherList = new ArrayList<>();
        EmployeeCreditInfo employeeCreditInfo = employeeCreditInfoService.getEmployeeCreditInfo(SessionContextHolder.getSessionEmployeeInfo().getEmployeeId(), 0L);
        String receivedVouchers = employeeCreditInfo.getReceivedVouchers();
        if (StringUtil.isNotEmpty(receivedVouchers)) {
            String[] array = receivedVouchers.split(",");
            for (int i = 0, len = resVoucherIds.size(); i < len; i++) {
                Boolean flagBit = false;
                for (int j = 0, size = array.length; j < size; j++) {
                    Long voucherId = Long.parseLong(array[j].split(":")[0]);
                    Integer count = Integer.parseInt(array[j].split(":")[1]);
                    Voucher voucher = getVoucherByVoucherId(resVoucherIds.get(i));
                    Integer limitNum = voucher.getLimitNum();
                    if ((voucherId.equals(resVoucherIds.get(i)) && count < limitNum) || (limitNum == 0 && voucherId.equals(resVoucherIds.get(i)))) {
                        if (count < 1) {
                            resVoucherList.add(resVoucherIds.get(i));
                        }
                        flagBit = true;
                        break;
                    } else if (voucherId.equals(resVoucherIds.get(i)) && count >= limitNum && limitNum != 0) {
                        flagBit = true;
                        break;
                    }
                }
                if (flagBit == false) {
                    resVoucherList.add(resVoucherIds.get(i));
                }
            }
            if (!resVoucherList.isEmpty()) {
                flag = true;
            }
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public List<Voucher> validateVouchersThreshold(Long userReceiveAddrId) {
        //String sellIds =assembleSellerIdsAndSkuInfos(userReceiveAddrId,true);
        String skuInfos = assembleSellerIdsAndSkuInfos(userReceiveAddrId, false);
        //String[] sellerIdsArray = sellIds.split(",");
        List<Long> resVoucherIds = new ArrayList<>();
        if (StringUtil.isNotEmpty(skuInfos)) {
            String[] skuInfoTemp = skuInfos.split(";");
            String[] skuInfoArray = new String[skuInfoTemp.length];
            int index = 0;
            for(int z=0,len2=skuInfoTemp.length;z<len2;z++){
                if(StringUtil.isNotEmpty(skuInfoTemp[z])){
                    skuInfoArray[index] = skuInfoTemp[z];
                    index++;
                }
            }
            List<Long> voucherIds = new ArrayList<>();
            for (int i = 0, len = skuInfoArray.length; i < index; i++) {
                String[] voucherIdsTemp = skuInfoArray[i].split(":")[2].split("@");
                for (int j = 0, len1 = voucherIdsTemp.length; j < len1; j++) {
                    voucherIds.add(Long.parseLong(voucherIdsTemp[j]));
                }
            }
            Set<Long> sets = new HashSet<>(voucherIds);
            resVoucherIds = new ArrayList<>(sets);
        }
        List<Voucher> vouchers = processSatisfyConditionVouchers(resVoucherIds, skuInfos);
        //        List<Voucher> resVouchers = new ArrayList<>();
//        List<Voucher> resultVouchers = new ArrayList<>();
//        for (int j = 0, len = skuInfoArray.length; j < len; j++) {
//            String[] skus = skuInfoArray[j].split(";");
//            List<Long> vouchers = new ArrayList<>();
//            for (int z = 0, len1 = skus.length; z < len1; z++) {
//                String[] voucherIds = skus[z].split(":")[2].split("@");
//                for (int m = 0, len2 = voucherIds.length; m < len2; m++) {
//                    vouchers.add(Long.parseLong(voucherIds[m]));
//                }
//                vouchers.add(0L);
//            }
//            String temp = locateAllVoucherIds(vouchers.toArray(new Long[0]));
//            String[] resVoucherIds = temp.split(":");
//            List<Long> benchmarkIndexs = new ArrayList<>();
//            for (int i = 0, len3 = resVoucherIds.length; i < len3; i++) {
//                List<Long> resIds = JsonUtil.parseObject(resVoucherIds[i], new TypeReference<List<Long>>() {
//                });
//                if (resIds.get(0) == 0) {
//                    for (int k = 1, len4 = resIds.size(); k < len4; k++) {
//                        benchmarkIndexs.add(resIds.get(k));
//                    }
//                }
//            }
//            List<Voucher> voucherTemp = new ArrayList<>();
//            for (int n = 0, len5 = resVoucherIds.length; n < len5; n++) {
//                List<Long> resIds = JsonUtil.parseObject(resVoucherIds[n], new TypeReference<List<Long>>() {
//                });
//                if (resIds.get(0) == 0) {
//                    continue;
//                }
        //             Voucher temp1 = processSingleVoucherBySkus(sellerIdsArray[j], benchmarkIndexs, resIds, skus);
//                voucherTemp.add(temp1);
//            }
//            resVouchers.addAll(voucherTemp);
//        }
//        for (int i = 0, len6 = resVouchers.size(); i < len6; i++) {
//            if (resVouchers.get(i) == null) {
//                continue;
//            }
//            resultVouchers.add(resVouchers.get(i));
//        }
        vouchers = processEmployeeMatchVouchersRes(vouchers);
        //resultVouchers = processDuplicateVouchers(resultVouchers);
        return vouchers;
    }

    /**
     * 下订单时优惠卷的扣减
     *
     * @param voucherSkus
     */
    @Override
    public Integer updateEmployeeCreditInfoByOrderVouchers(String voucherSkus) {
        String[] sellers = voucherSkus.split(";");
        List<Long> voucherIds = new ArrayList<>();
        for (int i = 0, len = sellers.length; i < len; i++) {
            String[] skus = sellers[i].split(",");
            voucherIds.add(Long.parseLong(skus[2]));
        }
        EmployeeCreditInfo employeeCreditInfo = employeeCreditInfoService.getEmployeeCreditInfo(SessionContextHolder.getSessionEmployeeInfo().getEmployeeId(), 0L);
        String receivedVouchers = employeeCreditInfo.getReceivedVouchers();
        StringBuilder stringBuilder = new StringBuilder();
        String[] temp = receivedVouchers.split(",");
        for (int j = 0, len1 = temp.length; j < len1; j++) {
            Long voucherId = Long.parseLong(temp[j].split(":")[0]);
            Integer count = Integer.parseInt(temp[j].split(":")[1]);
            Boolean flag = false;
            for (int i = 0, len = voucherIds.size(); i < len; i++) {
                if (voucherIds.get(i).equals(voucherId)) {
                    count--;
                    stringBuilder.append(voucherId + ":" + count + ",");
                    flag = true;
                    continue;
                }
            }
            if (flag == false) {
                stringBuilder.append(voucherId + ":" + count + ",");
            }
        }
        String resStr = stringBuilder.toString();
        EmployeeCreditInfo employeeCreditInfoTemp = new EmployeeCreditInfo();
        employeeCreditInfoTemp.setReceivedVouchers(resStr);
        employeeCreditInfoTemp.setEmployeeId(SessionContextHolder.getSessionEmployeeInfo().getEmployeeId());
        return employeeCreditInfoService.updateGrainIdByEmployeeIdSelective(employeeCreditInfoTemp);
    }

    @Override
    public void validateEmployeeCreditInfoByOrderVouchers(String voucherSkus) {
        String[] sellers = voucherSkus.split(";");
        List<Long> voucherIds = new ArrayList<>();
        for (int i = 0, len = sellers.length; i < len; i++) {
            String[] skus = sellers[i].split(",");
            voucherIds.add(Long.parseLong(skus[2]));
        }
        EmployeeCreditInfo employeeCreditInfo = employeeCreditInfoService.getEmployeeCreditInfo(SessionContextHolder.getSessionEmployeeInfo().getEmployeeId(), 0L);
        String receivedVouchers = employeeCreditInfo.getReceivedVouchers();
        StringBuilder stringBuilder = new StringBuilder();
        String[] temp = receivedVouchers.split(",");
        for (int j = 0, len1 = temp.length; j < len1; j++) {
            Long voucherId = Long.parseLong(temp[j].split(":")[0]);
            Integer count = Integer.parseInt(temp[j].split(":")[1]);
            Voucher voucher = voucherMapper.selectByPrimaryKey(voucherId);
            Boolean flag = false;
            for (int i = 0, len = voucherIds.size(); i < len; i++) {
                if (voucherIds.get(i).equals(voucherId)) {
                    count--;
                    if(count < 0){
                        throw new BusinessException("该员工已领取的满"+voucher.getUseThreshold()+"减"+voucher.getBenefitContent()+"的优惠卷已经用完！");
                    }
                    stringBuilder.append(voucherId + ":" + count + ",");
                    flag = true;
                    continue;
                }
            }
            if (flag == false) {
                stringBuilder.append(voucherId + ":" + count + ",");
            }
        }
    }

    /**
     * 取消订单或退款，优惠卷的返回
     *
     * @param voucherIds
     */
    @Override
    public Integer updateEmployeeCreditInfoByRefundOrderVouchers(List<Long> voucherIds,Long employeeId) {
        EmployeeCreditInfo employeeCreditInfo = employeeCreditInfoService.getEmployeeCreditInfo(employeeId, 0L);
        String receivedVouchers = employeeCreditInfo.getReceivedVouchers();
        StringBuilder stringBuilder = new StringBuilder();
        String[] temp = receivedVouchers.split(",");
        for (int j = 0, len1 = temp.length; j < len1; j++) {
            Long voucherId = Long.parseLong(temp[j].split(":")[0]);
            Integer count = Integer.parseInt(temp[j].split(":")[1]);
            Boolean flag = false;
            for (int i = 0, len = voucherIds.size(); i < len; i++) {
                if (voucherIds.get(i).equals(voucherId)) {
                    count++;
                    stringBuilder.append(voucherId + ":" + count + ",");
                    flag = true;
                    continue;
                }
            }
            if (flag == false) {
                stringBuilder.append(voucherId + ":" + count + ",");
            }
        }
        String resStr = stringBuilder.toString();
        EmployeeCreditInfo employeeCreditInfoTemp = new EmployeeCreditInfo();
        employeeCreditInfoTemp.setReceivedVouchers(resStr);
        employeeCreditInfoTemp.setEmployeeId(employeeId);
        return employeeCreditInfoService.updateGrainIdByEmployeeIdSelective(employeeCreditInfoTemp);
    }

    @Override
    public Integer updateVoucherUsed(String voucherSkus) {
        String[] sellerIds = voucherSkus.split(";");
        for (int i = 0, len = sellerIds.length; i < len; i++) {
            String[] skus = sellerIds[i].split(",");
            Long voucherId = Long.parseLong(skus[2]);
            Voucher voucher = voucherMapper.selectByPrimaryKey(voucherId);
            Voucher voucherTemp = new Voucher();
            voucherTemp.setVoucherId(voucherId);
            voucherTemp.setUsedAmount(voucher.getUsedAmount() + 1);
            voucherMapper.updateByPrimaryKeySelective(voucherTemp);
        }
        return 1;
    }

    @Override
    public Integer updateVoucherUsedByNoPay(Long voucherId) {
        if(voucherId == null){
            throw new BusinessException("优惠劵ID参数不能为空！");
        }
        Voucher voucherTemp = voucherMapper.selectByPrimaryKey(voucherId);
        Voucher voucher = new Voucher();
        voucher.setVoucherId(voucherId);
        voucher.setUsedAmount(voucherTemp.getUsedAmount()+1);
        return voucherMapper.updateByPrimaryKeySelective(voucher);
    }

    @Override
    public List<Voucher> findVouchersByTemplateId(Long templateId) {
        if(templateId == null){
            throw new BusinessException("商品模板ID参数不能为空！");
        }
        VoucherExample voucherExample = new VoucherExample();
        voucherExample.createCriteria().andUseCaseEqualTo(templateId.intValue());
        return voucherMapper.selectByExample(voucherExample);
    }

    /**
     * 验证优惠卷信息参数
     *
     * @param voucher
     */
    private void validate(Voucher voucher) {
        if (null == voucher) {
            throw new BusinessException("优惠卷信息不能为空！");
        } else if (StringUtil.isEmpty(voucher.getVoucherName())) {
            throw new BusinessException("优惠卷名称不能为空！");
        } else if (null == voucher.getTotalRelease()) {
            throw new BusinessException("优惠卷发放总量不能为空！");
        } else if (null == voucher.getUseCase()) {
            throw new BusinessException("优惠卷使用场景不能为空！");
        } else if (null == voucher.getUseThreshold()) {
            throw new BusinessException("优惠卷使用门槛不能为空!");
        } else if (null == voucher.getBenefitContent()) {
            throw new BusinessException("优惠卷优惠内容不能为空！");
        } else if (null == voucher.getValidateStartTime()) {
            throw new BusinessException("优惠卷开始时间不能为空！");
        } else if (null == voucher.getValidateEndTime()) {
            throw new BusinessException("优惠卷结束时间不能为空！");
        } else if (null == voucher.getLimitNum()) {
            throw new BusinessException("优惠卷没人限领次数不能为空！");
        } else if (StringUtil.isEmpty(voucher.getUseDesc())) {
            throw new BusinessException("优惠卷使用说明不能为空！");
        }
    }

    /**
     * 处理优惠卷商品
     *
     * @param voucher
     */
    private void process(Voucher voucher) {
        VoucherProductTemplate voucherProductTemplate = voucherProductTemplateService.getVoucherProductTemplate(voucher.getUseCase().longValue());
        if (voucher.getUseCase().equals(VoucherEnum.FULLRANGE.getCode())) {
            // processFullRangeProducts(voucher); 反向处理，否则性能堪忧
        } else if (voucherProductTemplate.getProductRange().intValue() == (VoucherEnum.CATEGORY.getCode())) {
            processCategoryProducts(voucherProductTemplate, voucher);
        } else if (voucherProductTemplate.getProductRange().intValue() == (VoucherEnum.BRAND.getCode())) {
            processBrandProducts(voucherProductTemplate, voucher);
        } else if (voucherProductTemplate.getProductRange().intValue() == VoucherEnum.TOPIC.getCode()) {
            processTopicProducts(voucherProductTemplate, voucher);
        } else if (voucherProductTemplate.getProductRange().intValue() == (VoucherEnum.PRODUCT.getCode())) {
            processSpuProducts(voucherProductTemplate, voucher);
        }
    }

    /**
     * 处理全场商品
     *
     * @param voucher
     */
    private void processFullRangeProducts(Voucher voucher) {
        Map<String, Object> params = new HashMap<>();
        params.put("start", 0);
        params.put("limit", Integer.MAX_VALUE);
        List<SkuEntity> list = skuService.selectPageList(params);
        if (!list.isEmpty()) {
            processVoucherIds(list, voucher);
        }
    }

    /**
     * 处理类目商品
     *
     * @param voucherProductTemplate
     */
    private void processCategoryProducts(VoucherProductTemplate voucherProductTemplate, Voucher voucher) {
        List<Integer> list = JsonUtil.parseObject(voucherProductTemplate.getProductItems(), new TypeReference<List<Integer>>() {
        });
        List<Long> spuCodes = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> params = new HashMap<>();
            params.put("categoryId3", list.get(i));
            List<ProductEntity> productEntities = productService.queryByParam(params);
            for (int j = 0; j < productEntities.size(); j++) {
                spuCodes.add(productEntities.get(j).getSpuCode());
            }
        }
        Map<String, Object> params = new HashMap<>();
        params.put("spuCodeList", spuCodes);
        List<ProductEntity> productEntities = productService.queryByParam(params);
        if (!productEntities.isEmpty()) {
            processVoucherIdsBySpu(productEntities, voucher);
        }
//        List<SkuEntity> skuEntities = skuService.selectSkuBySpuCode(params);
//        if (!skuEntities.isEmpty()) {
//            processVoucherIds(skuEntities, voucher);
//        }
    }

    /**
     * 处理商标商品
     *
     * @param voucherProductTemplate
     */
    private void processBrandProducts(VoucherProductTemplate voucherProductTemplate, Voucher voucher) {
        List<Integer> list = JsonUtil.parseObject(voucherProductTemplate.getProductItems(), new TypeReference<List<Integer>>() {
        });
        List<Long> spuCodes = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> params = new HashMap<>();
            params.put("brandId", list.get(i));
            List<ProductEntity> productEntities = productService.queryByParam(params);
            for (int j = 0; j < productEntities.size(); j++) {
                spuCodes.add(productEntities.get(j).getSpuCode());
            }
        }
        Map<String, Object> params = new HashMap<>();
        params.put("spuCodeList", spuCodes);
        List<ProductEntity> productEntities = productService.queryByParam(params);
        if (!productEntities.isEmpty()) {
            processVoucherIdsBySpu(productEntities, voucher);
        }
//        List<SkuEntity> skuEntities = skuService.selectSkuBySpuCode(params);
//        if (!skuEntities.isEmpty()) {
//            processVoucherIds(skuEntities, voucher);
//        }
    }

    /**
     * 处理专题商品
     *
     * @param voucherProductTemplate
     */
    private void processTopicProducts(VoucherProductTemplate voucherProductTemplate, Voucher voucher) {
        List<Integer> list = JsonUtil.parseObject(voucherProductTemplate.getProductItems(), new TypeReference<List<Integer>>() {
        });
        List<Long> spuCodes = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            List<ProductEntity> productEntities = productTopicService.getTopicProducts(list.get(i));
            for (int j = 0; j < productEntities.size(); j++) {
                spuCodes.add(productEntities.get(j).getSpuCode());
            }
        }
        Map<String, Object> params = new HashMap<>();
        params.put("spuCodeList", spuCodes);
        List<ProductEntity> productEntities = productService.queryByParam(params);
        if (!productEntities.isEmpty()) {
            processVoucherIdsBySpu(productEntities, voucher);
        }
//        params.put("spuCodes", spuCodes);
//        List<SkuEntity> skuEntities = skuService.selectSkuBySpuCode(params);
//        if (!skuEntities.isEmpty()) {
//            processVoucherIds(skuEntities, voucher);
//        }
    }

    /**
     * 处理Spu商品
     *
     * @param voucherProductTemplate
     */
    private void processSpuProducts(VoucherProductTemplate voucherProductTemplate, Voucher voucher) {
        List<Long> spuCodes = JsonUtil.parseObject(voucherProductTemplate.getProductItems(), new TypeReference<List<Long>>() {
        });
        Map<String, Object> params = new HashMap<>();
        params.put("spuCodeList", spuCodes);
        List<ProductEntity> productEntities = productService.queryByParam(params);
        if (!productEntities.isEmpty()) {
            processVoucherIdsBySpu(productEntities, voucher);
        }
//        params.put("spuCodes", spuCodes);
//        List<SkuEntity> skuEntities = skuService.selectSkuBySpuCode(params);
//        if (!skuEntities.isEmpty()) {
//            processVoucherIds(skuEntities, voucher);
//        }
    }

    /**
     * 处理优惠卷与商品的关联
     *
     * @param list
     * @param voucher
     */
    private void processVoucherIds(List<SkuEntity> list, Voucher voucher) {
        for (int i = 0; i < list.size(); i++) {
            StringBuilder stringBuilder = new StringBuilder();
            if (StringUtil.isEmpty(list.get(i).getVoucherIds())) {
                SkuEntity skuEntity = new SkuEntity();
                skuEntity.setVoucherIds("[" + voucher.getVoucherId() + "]");
                skuEntity.setId(list.get(i).getId());
                skuService.update(skuEntity);
            }
            if (!StringUtil.isEmpty(list.get(i).getVoucherIds())) {
                List<Long> voucherIds = JsonUtil.parseObject(list.get(i).getVoucherIds(), new TypeReference<List<Long>>() {
                });
                if (!voucherIds.contains(voucher.getVoucherId())) {
                    String temp = list.get(i).getVoucherIds().replace("]", "");
                    stringBuilder.append(temp + "," + voucher.getVoucherId() + "]");
                }
                SkuEntity skuEntity = new SkuEntity();
                skuEntity.setId(list.get(i).getId());
                skuEntity.setVoucherIds(stringBuilder.toString());
                skuService.update(skuEntity);
            }
        }
    }


    /**
     * 处理优惠卷与SPU商品的关联
     *
     * @param list
     * @param voucher
     */
    @Override
    public void processVoucherIdsBySpu(List<ProductEntity> list, Voucher voucher) {
        for (int i = 0; i < list.size(); i++) {
            StringBuilder stringBuilder = new StringBuilder();
            if (StringUtil.isEmpty(list.get(i).getVoucherIds())) {
                ProductEntity productEntity = new ProductEntity();
                productEntity.setSpuCode(list.get(i).getSpuCode());
                productEntity.setVoucherIds("[" + voucher.getVoucherId() + "]");
                productService.update(productEntity);
            }
            if (!StringUtil.isEmpty(list.get(i).getVoucherIds())) {
                List<Long> voucherIds = JsonUtil.parseObject(list.get(i).getVoucherIds(), new TypeReference<List<Long>>() {
                });
                if (!voucherIds.contains(voucher.getVoucherId())) {
                    String temp = list.get(i).getVoucherIds().replace("]", "");
                    stringBuilder.append(temp + "," + voucher.getVoucherId() + "]");
                }
                ProductEntity productEntity = new ProductEntity();
                productEntity.setSpuCode(list.get(i).getSpuCode());
                productEntity.setVoucherIds(stringBuilder.toString());
                productService.update(productEntity);
            }
        }
    }

    /**
     * 删除优惠卷与SPU商品的关联
     *
     * @param list
     * @param voucher
     */
    @Override
    public void processDelVoucherIdBySpu(List<ProductEntity> list, Voucher voucher) {
        for (int i = 0; i < list.size(); i++) {
            StringBuilder stringBuilder = new StringBuilder();
            if (!StringUtil.isEmpty(list.get(i).getVoucherIds())) {
                List<Long> voucherIds = JsonUtil.parseObject(list.get(i).getVoucherIds(), new TypeReference<List<Long>>() {
                });
                if(voucherIds.size() == 1){
                    ProductEntity productEntity = new ProductEntity();
                    productEntity.setSpuCode(list.get(i).getSpuCode());
                    productEntity.setVoucherIds(null);
                    productService.update(productEntity);
                    continue;
                }else{
                    stringBuilder.append("[");
                    for(int j=0,len1=voucherIds.size();j<len1;j++){
                        if(voucherIds.get(j).equals(voucher.getVoucherId())){
                            continue;
                        }
                        stringBuilder.append(voucherIds.get(j)+",");
                    }
                    stringBuilder.append("]");
                }
                ProductEntity productEntity = new ProductEntity();
                productEntity.setSpuCode(list.get(i).getSpuCode());
                productEntity.setVoucherIds(stringBuilder.toString().replace(",]","]"));
                productService.update(productEntity);
            }
        }
    }


    /**
     * 校验有效期内优惠卷的唯一
     *
     * @param voucher
     */
    private void validateTime(Voucher voucher) {
        List<Voucher> list = voucherMapper.selectByExample(null);
        for (int i = 0, len = list.size(); i < len - 1; i++) {
            if (((voucher.getValidateStartTime().getTime() >= list.get(i).getValidateStartTime().getTime() &&
                    voucher.getValidateStartTime().getTime() < list.get(i).getValidateEndTime().getTime()) ||
                    (voucher.getValidateEndTime().getTime() > list.get(i).getValidateStartTime().getTime() &&
                            voucher.getValidateEndTime().getTime() <= list.get(i).getValidateEndTime().getTime())) &&
                    (voucher.getUseThreshold().compareTo(list.get(i).getUseThreshold())==0 && voucher.getBenefitContent()
                            .compareTo(list.get(i).getBenefitContent()) == 0)) {
                throw new BusinessException("时间区域内满减重复！");
            }
            if (voucher.getValidateEndTime().getTime() < System.currentTimeMillis()) {
                throw new BusinessException("优惠卷有效结束时间不能设置为以前的时间！");
            }
        }
    }

    /**
     * locate array index for same element
     *
     * @param temp
     * @return
     */
    private String locateAllVoucherIds(Long[] temp) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < temp.length; i++) {
            long h = temp[i];
            boolean repeated = false;
            for (int j = 0; j < i; j++) {
                if (temp[j] == h) {
                    repeated = true;
                    break;
                }
            }
            if (!repeated) {
                StringBuffer sb = new StringBuffer();
                for (int j = 0; j < temp.length; j++) {
                    if (h == temp[j]) {
                        sb.append(",").append(j);
                    }
                }
                sb.insert(0, h).insert(0, "[").append(']');
                stringBuilder.append(sb.toString() + ":");
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 处理某个供应商下各个sku相同的优惠卷
     *
     * @param sellerId
     * @param benchmarkIndexs
     * @param resIds
     * @param skus
     * @return
     */
    private Voucher processSingleVoucherBySkus(String sellerId, List<Long> benchmarkIndexs, List<Long> resIds, String[] skus) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1, len = resIds.size(); i < len; i++) {
            for (int j = 0, len1 = benchmarkIndexs.size(); j < len1; j++) {
                if (resIds.get(i) < benchmarkIndexs.get(j)) {
                    list.add(j);
                    break;
                }
            }
        }
        BigDecimal resGoodPrice = BigDecimal.ZERO;
        String skuIds = "";
        String totalGoodsPriceTemp = "";
        String goodsProportionPrice = "";
        for (int i = 0, len = list.size(); i < len; i++) {
            BigDecimal totalGoodsPrice = new BigDecimal(skus[list.get(i)].split(":")[1]);
            skuIds += skus[list.get(i)].split(":")[0] + ",";
            totalGoodsPriceTemp += totalGoodsPrice + ",";
            resGoodPrice = resGoodPrice.add(totalGoodsPrice);
        }
        String[] totalSkuGoodsPrice = totalGoodsPriceTemp.split(",");
        BigDecimal totalSkusGoodsPrice = BigDecimal.ZERO;
        for (int i = 0, len = totalSkuGoodsPrice.length; i < len; i++) {
            totalSkusGoodsPrice = totalSkusGoodsPrice.add(new BigDecimal(totalSkuGoodsPrice[i]));
        }
        Voucher voucher = voucherMapper.selectByPrimaryKey(resIds.get(0));
        BigDecimal ret = BigDecimal.ZERO;
        for (int i = 0, len = list.size(); i < len; i++) {
            BigDecimal totalGoodsPrice = new BigDecimal(skus[list.get(i)].split(":")[1]);
            if (i == len - 1) {
                BigDecimal end = voucher.getBenefitContent().subtract(ret);
                goodsProportionPrice += end + ",";
                continue;
            }
            totalGoodsPrice = totalGoodsPrice.divide(totalSkusGoodsPrice, 2, BigDecimal.ROUND_HALF_UP);
            totalGoodsPrice = totalGoodsPrice.multiply(voucher.getBenefitContent());
            ret = ret.add(totalGoodsPrice);
            goodsProportionPrice += totalGoodsPrice + ",";
        }
        voucher.setSellerId(Long.parseLong(sellerId));
        voucher.setSkuIds(skuIds);
        voucher.setTotalGoodsPrice(totalGoodsPriceTemp);
        voucher.setGoodsProportionPrice(goodsProportionPrice);
        BigDecimal temp = voucher.getUseThreshold();
        if (resGoodPrice.compareTo(temp) > -1) {
            return voucher;
        }
        return null;
    }

    /**
     * 匹配用户下优惠卷与购物车下优惠卷
     *
     * @param resVouchers
     * @return
     */
    private List<Voucher> processEmployeeMatchVouchers(List<Voucher> resVouchers) {
        List<List<Voucher>> list = new ArrayList<>();
        List<Voucher> vouchers = new ArrayList<>();
        list = getGroupSellers(resVouchers);
        outer:
        for (int i = 0, len = list.size(); i < len; i++) {
            for (int j = 0, len1 = list.get(i).size(); j < len1; j++) {
                EmployeeCreditInfo employeeCreditInfo = employeeCreditInfoService.getEmployeeCreditInfo(SessionContextHolder.getSessionEmployeeInfo().getEmployeeId(), 0L);
                String[] temp = employeeCreditInfo.getReceivedVouchers().split(",");
                for (int z = 0, len2 = temp.length; z < len2; z++) {
                    Long voucherId = Long.parseLong(temp[z].split(":")[0]);
                    Integer count = Integer.parseInt(temp[z].split(":")[1]);
                    if (voucherId.equals(list.get(i).get(j).getVoucherId()) && count > 0) {
                        list.get(i).get(j).setFlag(true);
                        vouchers.add(list.get(i).get(j));
                        break outer;
                    }
                }
            }
        }
        return vouchers;
    }

    /**
     * 匹配用户下优惠卷与购物车下优惠卷
     *
     * @param vouchers
     * @return
     */
    private List<Voucher> processEmployeeMatchVouchersRes(List<Voucher> vouchers) {
        List<Voucher> resVouchers = new ArrayList<>();
        for (int i = 0, len = vouchers.size(); i < len; i++) {
            EmployeeCreditInfo employeeCreditInfo = employeeCreditInfoService.getEmployeeCreditInfo(SessionContextHolder.getSessionEmployeeInfo().getEmployeeId(), 0L);
            String[] temp = employeeCreditInfo.getReceivedVouchers().split(",");
            for (int j = 0, len1 = temp.length; j < len1; j++) {
                Long voucherId = Long.parseLong(temp[j].split(":")[0]);
                Integer count = Integer.parseInt(temp[j].split(":")[1]);
                if (voucherId.equals(vouchers.get(i).getVoucherId()) && count > 0) {
                    resVouchers.add(vouchers.get(i));
                }
            }
        }
        return resVouchers;
    }


    /**
     * 根据供应商分组
     *
     * @param resVouchers
     * @return
     */
    private List<List<Voucher>> getGroupSellers(List<Voucher> resVouchers) {
        List<List<Voucher>> list = new ArrayList<>();
        for (int i = 0, len = resVouchers.size(); i < len; i++) {
            Long sellerId = resVouchers.get(i).getSellerId();
            Boolean isRepeateable = false;
            List<Voucher> vouchers = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                if (resVouchers.get(j).getSellerId().equals(sellerId)) {
                    isRepeateable = true;
                    break;
                }
            }
            if (!isRepeateable) {
                for (int j = 0, len1 = resVouchers.size(); j < len1; j++) {
                    if (resVouchers.get(j).getSellerId().equals(sellerId)) {
                        vouchers.add(resVouchers.get(j));
                    }
                }
            }
            list.add(vouchers);
        }
        return list;
    }

    /**
     * 处理用户下重复的优惠卷
     *
     * @param resVouchers
     * @return
     */
    private List<Voucher> processDuplicateVouchers(List<Voucher> resVouchers) {
        List<Voucher> vouchers = new ArrayList<>();
        List<Voucher> resVoucherList = new ArrayList<>();
        List<List<Voucher>> list = new ArrayList<>();
        for (int i = 0, len = resVouchers.size(); i < len; i++) {
            Voucher voucher = resVouchers.get(i);
            Boolean isRepeateable = false;
            for (int j = 0; j < i; j++) {
                if (resVouchers.get(j).getUseThreshold().equals(voucher.getUseThreshold()) &&
                        resVouchers.get(j).getBenefitContent().equals(voucher.getBenefitContent()) &&
                        voucher.getFlag() == true) {
                    isRepeateable = true;
                    break;
                }
            }
            if (!isRepeateable) {
                for (int j = 0, len1 = resVouchers.size(); j < len1; j++) {
                    if (resVouchers.get(j).getUseThreshold().equals(voucher.getUseThreshold()) &&
                            resVouchers.get(j).getBenefitContent().equals(voucher.getBenefitContent()) &&
                            voucher.getFlag() == true) {
                        vouchers.add(resVouchers.get(j));
                    }
                }
            }
            list.add(vouchers);
        }
        for (int i = 0, len = list.size(); i < len; i++) {
            for (int j = 0, len1 = list.get(i).size(); j < len1; j++) {
                resVoucherList.add(list.get(i).get(0));
                break;
            }
        }
        return resVoucherList;
    }

    /**
     * 按照指定的条件排序
     *
     * @param list
     * @param params
     * @return
     */
    private List<ProductEntity> sortedSpus(List<ProductEntity> list, Map<String, Object> params) {
        if (Integer.parseInt(params.get("sort").toString()) == 3) {
            Collections.sort(list, new Comparator<ProductEntity>() {
                @Override
                public int compare(ProductEntity o1, ProductEntity o2) {
                    //降序
                    return o2.getSortDate().compareTo(o1.getSortDate());
                }
            });
        } else if (Integer.parseInt(params.get("sort").toString()) == 2) {
            Collections.sort(list, new Comparator<ProductEntity>() {
                @Override
                public int compare(ProductEntity o1, ProductEntity o2) {
                    //升序
                    return o1.getMinGoodsPrice().compareTo(o2.getMinGoodsPrice());
                }
            });
        } else if (Integer.parseInt(params.get("sort").toString()) == 1) {
            Collections.sort(list, new Comparator<ProductEntity>() {
                @Override
                public int compare(ProductEntity o1, ProductEntity o2) {
                    //降序
                    return o2.getMinGoodsPrice().compareTo(o1.getMinGoodsPrice());
                }
            });
        }
        return list;
    }

    /**
     * 验证优惠卷的有效期
     *
     * @param list
     */
    private void validateEffectiveVoucher(List<Voucher> list) {
        for (int i = 0, len = list.size(); i < len; i++) {
            if (list.get(i).getVoucherStatus() == 2) {
                //no handle
            } else if (System.currentTimeMillis() >= list.get(i).getValidateStartTime().getTime() && System.currentTimeMillis() <= list.get(i).getValidateEndTime().getTime()) {
                Voucher voucherTemp = new Voucher();
                voucherTemp.setVoucherId(list.get(i).getVoucherId());
                voucherTemp.setVoucherStatus(1);
                list.get(i).setVoucherStatus(1);
                voucherMapper.updateByPrimaryKeySelective(voucherTemp);
            } else if (System.currentTimeMillis() >= list.get(i).getValidateEndTime().getTime()) {
                Voucher voucherTemp = new Voucher();
                voucherTemp.setVoucherId(list.get(i).getVoucherId());
                voucherTemp.setVoucherStatus(2);
                list.get(i).setVoucherStatus(2);
                voucherMapper.updateByPrimaryKeySelective(voucherTemp);
            }
        }
    }

    /**
     * 组合供应商IDs和sku有关的信息
     *
     * @param userReceiveAddrId
     * @return
     */
    private String assembleSellerIdsAndSkuInfos(Long userReceiveAddrId, Boolean flag) {
        Long accountId = SessionContextHolder.getSessionEmployeeInfo().getEmployeeId();
        OrderConfirmVO orderConfirmVO = orderSupport.confirmOrder(userReceiveAddrId, accountId, null);
        List<OrderConfirmVO.SellerOrderVO> sellerOrderList = orderConfirmVO.getSellerOrderList();
        String sellerIds = "";
        StringBuilder stringBuilder = new StringBuilder();
        if (!sellerOrderList.isEmpty()) {
            sellerIds = sellerIds + sellerOrderList.get(0).getSellerId() + ",";
            List<OrderConfirmVO.ItemOrderVO> itemOrderVOs = sellerOrderList.get(0).getItemList();
            if (!itemOrderVOs.isEmpty()) {
                String temp = "";
                for (int j = 0, len1 = itemOrderVOs.size(); j < len1; j++) {
                    Long skuId = itemOrderVOs.get(j).getSkuId();
                    BigDecimal totalGoodsPrice = itemOrderVOs.get(j).getTotalGoodsPrice();
                    List<Voucher> vouchers = itemOrderVOs.get(j).getVouchers();
                    String voucherIds = "";
                    if (!vouchers.isEmpty()) {
                        voucherIds = vouchers.get(0).getVoucherId() + "@";
                        for (int z = 1, len2 = vouchers.size(); z < len2; z++) {
                            voucherIds = voucherIds + vouchers.get(z).getVoucherId() + "@";
                        }
                    }
                    temp = skuId + ":" + totalGoodsPrice.toString() + ":" + voucherIds + ";";
                    stringBuilder.append(temp);
                }
                stringBuilder.append(";");
            }
            for (int i = 1, len = sellerOrderList.size(); i < len; i++) {
                sellerIds = sellerIds + sellerOrderList.get(i).getSellerId() + ",";
                List<OrderConfirmVO.ItemOrderVO> itemList = sellerOrderList.get(i).getItemList();
                if (!itemList.isEmpty()) {
                    String temp = "";
                    for (int j = 0, len1 = itemList.size(); j < len1; j++) {
                        Long skuId = itemList.get(j).getSkuId();
                        BigDecimal totalGoodsPrice = itemList.get(j).getTotalGoodsPrice();
                        List<Voucher> vouchers = itemList.get(j).getVouchers();
                        String voucherIds = "";
                        if (!vouchers.isEmpty()) {
                            voucherIds = vouchers.get(0).getVoucherId() + "@";
                            for (int z = 1, len2 = vouchers.size(); z < len2; z++) {
                                voucherIds = voucherIds + vouchers.get(z).getVoucherId() + "@";
                            }
                             temp = skuId + ":" + totalGoodsPrice + ":" + voucherIds + ";";
                            stringBuilder.append(temp);
                        }
                    }
                    stringBuilder.append(";");
                }
            }
        }
        if (flag == true) {
            return sellerIds;
        } else {
            return stringBuilder.toString();
        }
    }

    /**
     * 处理满足条件的优惠卷
     *
     * @param resVoucherIds
     * @param skuInfos
     * @return
     */
    private List<Voucher> processSatisfyConditionVouchers(List<Long> resVoucherIds, String skuInfos) {
        List<Voucher> vouchers = new ArrayList<>();
        if (StringUtil.isNotEmpty(skuInfos)) {
            for (int i = 0, len = resVoucherIds.size(); i < len; i++) {
                //String[] skuInfoArray = skuInfos.split(";");
                String[] skuInfoTemp = skuInfos.split(";");
                String[] skuInfoArray = new String[skuInfoTemp.length];
                int index = 0;
                for(int z=0,len2=skuInfoTemp.length;z<len2;z++){
                    if(StringUtil.isNotEmpty(skuInfoTemp[z])){
                        skuInfoArray[index] = skuInfoTemp[z];
                        index++;
                    }
                }
                List<Long> voucherIds = new ArrayList<>();
                Voucher voucher = voucherMapper.selectByPrimaryKey(resVoucherIds.get(i));
                BigDecimal temp = BigDecimal.ZERO;
                String skuIds = "";
                String goodsProportionPrice = "";
                BigDecimal totalGoodsPrices = BigDecimal.ZERO;
                int realSkulen = 0;
                BigDecimal ret = BigDecimal.ZERO;
                for (int j = 0, len1 = skuInfoArray.length; j < index; j++) {
                    String[] voucherIdsTemp = skuInfoArray[j].split(":")[2].split("@");
                    String totalGoodsPrice = skuInfoArray[j].split(":")[1];
                    for (int z = 0, len2 = voucherIdsTemp.length; z < len2; z++) {
                        if (resVoucherIds.get(i).equals(Long.parseLong(voucherIdsTemp[z]))) {
                            totalGoodsPrices = totalGoodsPrices.add(new BigDecimal(totalGoodsPrice));
                            realSkulen = j;
                        }
                    }
                }
                for (int j = 0, len1 = skuInfoArray.length; j < index; j++) {
                    String[] voucherIdsTemp = skuInfoArray[j].split(":")[2].split("@");
                    String totalGoodsPrice = skuInfoArray[j].split(":")[1];
                    BigDecimal totalGoodsPriceTemp = BigDecimal.ZERO;
                    String skuId = skuInfoArray[j].split(":")[0];
                    for (int z = 0, len2 = voucherIdsTemp.length; z < len2; z++) {
                        if (resVoucherIds.get(i).equals(Long.parseLong(voucherIdsTemp[z]))) {
                            temp = temp.add(new BigDecimal(totalGoodsPrice));
                            skuIds += skuId + ",";
                            totalGoodsPriceTemp = new BigDecimal(totalGoodsPrice);
                        }
                    }
                    if(totalGoodsPriceTemp.compareTo(BigDecimal.ZERO) == 1){
                        if(j==realSkulen){
                            totalGoodsPriceTemp = voucher.getBenefitContent().subtract(ret);
                            goodsProportionPrice += totalGoodsPriceTemp + ",";
                            continue;
                        }
                        totalGoodsPriceTemp = new BigDecimal(totalGoodsPrice).divide(totalGoodsPrices, 4, BigDecimal.ROUND_HALF_UP);
                        totalGoodsPriceTemp = totalGoodsPriceTemp.multiply(voucher.getBenefitContent()).setScale(2, BigDecimal.ROUND_UP);
                        ret = ret.add(totalGoodsPriceTemp);
                        goodsProportionPrice += totalGoodsPriceTemp + ",";
                    }
                }
                if (temp.compareTo(voucher.getUseThreshold()) > -1) {
                    voucher.setSkuIds(skuIds);
                    voucher.setGoodsProportionPrice(goodsProportionPrice);
                    vouchers.add(voucher);
                }
            }
        }
        return vouchers;
    }

    /**
     * 处理员工已领取优惠卷数量
     * @param employeeId
     * @param num
     */
    private void processReceivedVouchersNum(Long employeeId,Integer num,Long voucherId){
        EmployeeInfoDto employeeInfoDto = employeeUserInfoService.findByEmployeeId(employeeId,true);
        Voucher voucher = voucherMapper.selectByPrimaryKey(voucherId);
        if (null != employeeInfoDto) {
            if (StringUtil.isEmpty(employeeInfoDto.getVouchersNum())) {
                EmployeeUserInfo employeeUserInfo = new EmployeeUserInfo();
                employeeUserInfo.setEmployeeId(employeeId);
                employeeUserInfo.setVouchersNum(voucherId+":1,");
                employeeUserInfoService.updateByPrimaryKeySelective(employeeUserInfo);
            } else {
                List<String> list = new ArrayList<>(Arrays.asList(employeeInfoDto.getVouchersNum().split(",")));
                StringBuilder stringBuilder = new StringBuilder();
                Boolean flag = false;
                for (int i = 0, len = list.size(); i < len; i++) {
                    Long voucherIdTemp = Long.parseLong(list.get(i).split(":")[0]);
                    Integer count = Integer.parseInt(list.get(i).split(":")[1]);
                    if (voucherIdTemp.equals(voucherId)) {
                        count++;
                        if (count > voucher.getLimitNum() && voucher.getLimitNum() != 0) {
                            throw new BusinessException("该优惠卷每人限领次数不超过" + voucher.getLimitNum());
                        }
                        stringBuilder.append(voucherIdTemp + ":" + count + ",");
                        flag = true;
                        continue;
                    }
                    stringBuilder.append(list.get(i) + ",");
                }
                if (flag == false) {
                    stringBuilder.append(voucherId + ":1,");
                }
                String resStr = stringBuilder.toString();
                EmployeeUserInfo employeeUserInfo = new EmployeeUserInfo();
                employeeUserInfo.setVouchersNum(resStr);
                employeeUserInfo.setEmployeeId(employeeId);
                employeeUserInfoService.updateByPrimaryKeySelective(employeeUserInfo);
            }
        }
    }

    /**
     * 显示员工各个优惠卷已领取数量
     * @param vouchers
     */
    private void showEmployeeReceivedVouchersNum(List<Voucher> vouchers){
        for(int i=0,len=vouchers.size();i<len;i++){
            EmployeeInfoDto employeeInfoDto = employeeUserInfoService.findByEmployeeId(SessionContextHolder.getSessionEmployeeInfo().getEmployeeId(),true);
            if(StringUtil.isNotEmpty(employeeInfoDto.getVouchersNum())){
                String[] vouchersNumArray = employeeInfoDto.getVouchersNum().split(",");
                for(int j=0,len1=vouchersNumArray.length;j<len1;j++){
                    Long voucherId = Long.parseLong(vouchersNumArray[j].split(":")[0]);
                    Integer count = Integer.parseInt(vouchersNumArray[j].split(":")[1]);
                    if(vouchers.get(i).getVoucherId().equals(voucherId)){
                        vouchers.get(i).setNum(count);
                    }
                }
            }
            EmployeeCreditInfo employeeCreditInfo = employeeCreditInfoService.getEmployeeCreditInfo(SessionContextHolder.getSessionEmployeeInfo().getEmployeeId(),0L);
            if(StringUtil.isNotEmpty(employeeCreditInfo.getReceivedVouchers())){
                String[] vouchersNoUsedArray = employeeCreditInfo.getReceivedVouchers().split(",");
                for(int j=0,len1=vouchersNoUsedArray.length;j<len1;j++){
                    Long voucherId = Long.parseLong(vouchersNoUsedArray[j].split(":")[0]);
                    Integer count = Integer.parseInt(vouchersNoUsedArray[j].split(":")[1]);
                    if(vouchers.get(i).getVoucherId().equals(voucherId)){
                        vouchers.get(i).setNoUsedNum(count);
                    }
                }
            }
        }
    }

    /**
     * 组合搜索商品的条件
     * @return
     */
    private ProductSearchBean assembleProductSearchBean(){
        ProductSearchBean productSearchBean = new ProductSearchBean();
        GoodsModuleInfoDto goodsModuleInfoDto =
                enterprCustomGoodsService.findByIdWithAgentNoCache(SessionContextHolder.getSessionEmployeeInfo().getEnterprId());
        if (!CollectionUtils.isEmpty(goodsModuleInfoDto.getSupplierIdsList())) {// 排除供应商
            productSearchBean.setExcludeSupplierIds(goodsModuleInfoDto.getSupplierIdsList());
        }
        if (!CollectionUtils.isEmpty(goodsModuleInfoDto.getCategoryIdsList())) {// 排除某些分类
            Map<Integer, List<Long>> categoryIdsMap = new HashMap<>(3);
            for (Long item : goodsModuleInfoDto.getCategoryIdsList()) {
                Integer type;
                if (item < 20000) {
                    type = 1;
                } else if (item < 30000) {
                    type = 2;
                } else {
                    type = 3;
                }
                categoryIdsMap.compute(type, (key, oldValue) -> {
                    if (oldValue == null) {
                        oldValue = new ArrayList<>();
                    } else {
                    }
                    oldValue.add(item);
                    return oldValue;
                });
            }
            productSearchBean.setExcludeCategoryIds(categoryIdsMap.get(1));
            productSearchBean.setExcludeCategoryId2s(categoryIdsMap.get(2));
            productSearchBean.setExcludeCategoryId3s(categoryIdsMap.get(3));
        }
        if (!CollectionUtils.isEmpty(goodsModuleInfoDto.getBrandIdsList())) {// 排除某些品牌
            productSearchBean.setExcludeBrandIds(goodsModuleInfoDto.getBrandIdsList());
        }
        if (!CollectionUtils.isEmpty(goodsModuleInfoDto.getTopicIdsList())) {// 排除某些主题商品
            List<Long> excludeTopicIds = goodsModuleInfoDto.getTopicIdsList();
            productSearchBean.setExcludeTopicIds(excludeTopicIds);
        }
        productSearchBean.setEnterprId(SessionContextHolder.getSessionEmployeeInfo().getEnterprId());
        return productSearchBean;
    }
}
