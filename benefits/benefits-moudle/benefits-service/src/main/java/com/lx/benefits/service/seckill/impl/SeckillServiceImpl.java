package com.lx.benefits.service.seckill.impl;

import com.alibaba.fastjson.TypeReference;
import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.dto.admin.customized.GoodsModuleInfoDto;
import com.lx.benefits.bean.dto.product.ProductSearchBean;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfo;
import com.lx.benefits.bean.entity.product.ProductEntity;
import com.lx.benefits.bean.entity.product.SkuEntity;
import com.lx.benefits.bean.entity.seckill.Seckill;
import com.lx.benefits.bean.entity.seckill.SeckillExample;
import com.lx.benefits.bean.entity.seckill.SeckillOrder;
import com.lx.benefits.bean.entity.seckill.SeckillOrderReq;
import com.lx.benefits.bean.enums.SeckillEnum;
import com.lx.benefits.bean.util.JsonUtil;
import com.lx.benefits.bean.util.Query;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.util.StringUtil;
import com.lx.benefits.mapper.seckill.SeckillMapper;
import com.lx.benefits.service.enterprcustomgoods.EnterprCustomGoodsService;
import com.lx.benefits.service.enterprise.EnterpriseService;
import com.lx.benefits.service.product.ProductService;
import com.lx.benefits.service.product.SkuService;
import com.lx.benefits.service.seckill.SeckillOrderService;
import com.lx.benefits.service.seckill.SeckillService;
import com.lx.benefits.utils.DateTimeUtils;
import io.swagger.models.auth.In;
import org.bouncycastle.jcajce.provider.digest.Skein;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * User:wangmeng
 * Date:2019/8/20
 * Time:11:47
 * Version:2.x
 * Description:TODO
 **/
@Service
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    private SeckillMapper seckillMapper;

    @Autowired
    private SkuService skuService;

    @Autowired
    private ProductService productService;

    @Autowired
    private SeckillOrderService seckillOrderService;


    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired
    private EnterprCustomGoodsService enterprCustomGoodsService;

    @Override
    @Transactional
    public Seckill insertSeckill(Seckill seckill) {
        Date startDate = DateTimeUtils.stringToTime(seckill.getStartTime(), DateTimeUtils.DEFAULT_TIME_FORMAT);
        Date endDate = DateTimeUtils.stringToTime(seckill.getEndTime(), DateTimeUtils.DEFAULT_TIME_FORMAT);
        seckill.setValidateStartTime(startDate);
        seckill.setValidateEndTime(endDate);
        validate(seckill);
        seckill.setIsStop(0);
        seckill.setAlreadyBuy(0);
        seckill.setStatus((byte)0);
        seckill.setCreateTime(new Date());
        seckill.setLeftStorage(seckill.getSeckillStorage());
        try{
            Integer count = seckillMapper.insertSelective(seckill);
        }catch (Exception e){
            throw new BusinessException("秒杀名称不能重复！");
        }
        SkuEntity skuEntity = skuService.getBySkuId(seckill.getSkuId());
        if(skuEntity.getGoodsPrice().compareTo(seckill.getSeckillPrice()) == -1){
            throw new BusinessException("秒杀价格不能高于销售价格！");
        }
        if(skuEntity.getGoodsStorge() < seckill.getSeckillStorage()){
            throw new BusinessException("秒杀库存不能大于实际库存！");
        }
        if(endDate.getTime() < System.currentTimeMillis() || startDate.getTime() > endDate.getTime()){
            throw new BusinessException("结束有效期不能选择以前的时间周期且开始时间不能大于结束时间！");
        }
        processSkuBySeckillIds(seckill);
        processDuplicateSeckillId(seckill);
        syncSeckillAndSKuStorage(seckill,false);
        return seckill;
    }

    @Override
    public Seckill getSeckill(Long seckillId) {
        if(seckillId == null){
            throw new BusinessException("秒杀ID不能为空！");
        }
        return seckillMapper.selectByPrimaryKey(seckillId);
    }

    @Override
    public Integer updateSeckill(Seckill seckill) {
        seckill.setUpdateTime(new Date());
        if(seckill.getIsStop() != null && seckill.getIsStop() == 1){
            seckill.setStatus(SeckillEnum.STOPED.getCode().byteValue());
            Seckill seckillTemp = seckillMapper.selectByPrimaryKey(seckill.getSeckillId());
            syncSeckillAndSKuStorage(seckillTemp,true);
        }
        return seckillMapper.updateByPrimaryKeySelective(seckill);
    }

    @Override
    @Transactional
    public PageResultBean<Seckill> findSeckills(String seckillName, Integer status, PageBean pageBean) {
        SeckillExample seckillExample = new SeckillExample();
        SeckillExample.Criteria criteria = seckillExample.createCriteria();
        if(StringUtil.isNotEmpty(seckillName)){
            criteria.andSeckillNameLike("%"+seckillName+"%");
        }
        if(status != null){
            criteria.andStatusEqualTo(status.byteValue());
        }
        Integer count = seckillMapper.countByExample(seckillExample);
        if(count == 0){
            return new PageResultBean<>(pageBean.getPage(),pageBean.getPageSize(),count, Collections.emptyList());
        }
        seckillExample.setLimit(pageBean.getPageSize());
        seckillExample.setOffset(pageBean.getOffset());
        seckillExample.setOrderByClause("seckill_id desc");
        List<Seckill> list = seckillMapper.selectByExample(seckillExample);
        for(int i=0,len=list.size();i<len;i++){
            SkuEntity skuEntity = skuService.getBySkuId(list.get(i).getSkuId());
            ProductEntity productEntity = productService.selectById(skuEntity.getSpuCode());
            if(skuEntity.getGoodsName() != null){
                list.get(i).setGoodsName(skuEntity.getGoodsName());
            }else {
                list.get(i).setGoodsName(productEntity.getGoodsName());
            }
            if(list.get(i).getStatus().intValue() == SeckillEnum.STOPED.getCode()){
                continue;
            }
            if(list.get(i).getValidateStartTime().getTime()<= System.currentTimeMillis() && list.get(i).getValidateEndTime().getTime()>=System.currentTimeMillis()){
                Seckill seckill = new Seckill();
                seckill.setStatus(SeckillEnum.STARTING.getCode().byteValue());
                seckill.setSeckillId(list.get(i).getSeckillId());
                seckillMapper.updateByPrimaryKeySelective(seckill);
                list.get(i).setStatus(SeckillEnum.STARTING.getCode().byteValue());
            }
            if(list.get(i).getValidateEndTime().getTime() < System.currentTimeMillis() ||
            list.get(i).getSeckillStorage()<=(list.get(i).getAlreadyBuy())){
                Seckill seckill = new Seckill();
                seckill.setSeckillId(list.get(i).getSeckillId());
                seckill.setStatus(SeckillEnum.STOPED.getCode().byteValue());
                seckillMapper.updateByPrimaryKeySelective(seckill);
                seckill = seckillMapper.selectByPrimaryKey(seckill.getSeckillId());
                syncSeckillAndSKuStorage(seckill,true);
                list.get(i).setStatus(SeckillEnum.STOPED.getCode().byteValue());
            }
        }
        return new PageResultBean<>(pageBean.getPage(),pageBean.getPageSize(),count,list);
    }

    @Override
    public PageResultBean<SkuEntity> findSkusBySeckillId(Map<String, Object> params) {
        if(params.isEmpty()){
            throw new BusinessException("params参数不能为空！");
        }
        ProductSearchBean productSearchBean = assembleProductSearchBean();
        params.put("searchBean",productSearchBean);
        //Integer count = skuService.countSkuByseckillId(params);
//        if(count == 0){
//            return new PageResultBean<>(Integer.parseInt(params.get("page").toString()),Integer.parseInt(params.get("pageSize").toString()),count,Collections.emptyList());
//        }
        Query query = new Query(params);
        List<SkuEntity> list = skuService.selectSkuByseckillId(query);
        list = processNoPassedSkuIds(list);
        //list = processNoPassedSeckillId(list);
        Map<String,Object> paramsTemp = new HashMap<>();
        paramsTemp.put("page",1);
        paramsTemp.put("pageSize",Integer.MAX_VALUE);
        Query queryTemp = new Query(paramsTemp);
        List<SkuEntity> listTemp = skuService.selectSkuByseckillId(query);
        listTemp = processNoPassedSkuIds(listTemp);
        //listTemp = processNoPassedSeckillId(listTemp);
        Integer count = listTemp.size();
        return new PageResultBean<>(Integer.parseInt(params.get("page").toString()),Integer.parseInt(params.get("pageSize").toString()),count,list);
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

    @Override
    public Boolean validateSeckillEffective(Seckill seckill, Boolean flag) {
        //过滤不合格的秒杀信息
        if(flag == false){
            if(seckill.getStatus()==SeckillEnum.STOPED.getCode().byteValue()){
                //no handle
            }else if(seckill.getValidateStartTime().getTime() <= System.currentTimeMillis() && seckill.getValidateEndTime().getTime()>=System.currentTimeMillis()){
                Seckill seckillTemp = new Seckill();
                seckillTemp.setStatus(SeckillEnum.STARTING.getCode().byteValue());
                seckillTemp.setSeckillId(seckill.getSeckillId());
                seckillMapper.updateByPrimaryKeySelective(seckillTemp);
                return true;
            }else if(seckill.getValidateEndTime().getTime() < System.currentTimeMillis() ||
                    seckill.getSeckillStorage()<=seckill.getAlreadyBuy()){
                Seckill seckillTemp = new Seckill();
                seckillTemp.setSeckillId(seckill.getSeckillId());
                seckillTemp.setStatus(SeckillEnum.STOPED.getCode().byteValue());
                seckillMapper.updateByPrimaryKeySelective(seckillTemp);
                syncSeckillAndSKuStorage(seckill,true);
                return false;
            }
        }else if(flag == true){//下单时，不符合秒杀资格的过滤
            if(seckill.getStatus()==SeckillEnum.STOPED.getCode().byteValue()){
                throw new BusinessException("该秒杀商品已经过期！");
            }else if(seckill.getValidateStartTime().getTime() <= System.currentTimeMillis() && seckill.getValidateEndTime().getTime()>=System.currentTimeMillis()){
                Seckill seckillTemp = new Seckill();
                seckillTemp.setStatus(SeckillEnum.STARTING.getCode().byteValue());
                seckillTemp.setSeckillId(seckill.getSeckillId());
                seckillMapper.updateByPrimaryKeySelective(seckillTemp);
            }else if(seckill.getValidateEndTime().getTime() < System.currentTimeMillis() ||
                    seckill.getSeckillStorage()<=seckill.getAlreadyBuy()){
                Seckill seckillTemp = new Seckill();
                seckillTemp.setSeckillId(seckill.getSeckillId());
                seckillTemp.setStatus(SeckillEnum.STOPED.getCode().byteValue());
                seckillMapper.updateByPrimaryKeySelective(seckillTemp);
                throw new BusinessException("该秒杀商品已经过期或者已经卖完！");
            }
        }
        return false;
    }

    @Override
    public PageResultBean<SeckillOrder> selectOrderBySeckillId(SeckillOrderReq seckillOrderReq) {
        Integer count = seckillOrderService.countOrderBySeckillId(seckillOrderReq);
        if(count ==0){
            return  new PageResultBean<>(seckillOrderReq.getPage(),seckillOrderReq.getPageSize(),count,Collections.emptyList());
        }
        seckillOrderReq.setOffset(seckillOrderReq.getOffset());
        List<SeckillOrder> list = seckillOrderService.selectOrderBySeckillId(seckillOrderReq);
        for(int i=0,len=list.size();i<len;i++){
            EnterprUserInfo enterprUserInfo = enterpriseService.getEnterprise(list.get(i).getEnterprId());
            list.get(i).setEnterprName(enterprUserInfo.getEnterprName());
        }
        return new PageResultBean<>(seckillOrderReq.getPage(),seckillOrderReq.getPageSize(),count,list);
    }

    @Override
    @Transactional
    public void returnSkuStorageBySeckillEnd(Seckill seckill,Integer num) {
        if(num == null || seckill == null){
            throw new BusinessException("待支付的秒杀订单数量或秒杀信息参数不能为空！");
        }
        if(System.currentTimeMillis()> seckill.getValidateEndTime().getTime() || seckill.getStatus() == 2){
            Seckill seckillTemp = new Seckill();
            SkuEntity skuEntity = skuService.getBySkuId(seckill.getSkuId());
            seckillTemp.setSeckillId(seckill.getSeckillId());
            seckillTemp.setStatus(SeckillEnum.STOPED.getCode().byteValue());
            seckillMapper.updateByPrimaryKeySelective(seckillTemp);
            SkuEntity skuEntityTemp = new SkuEntity();
            skuEntityTemp.setId(seckill.getSkuId());
            skuEntityTemp.setGoodsStorge(skuEntity.getGoodsStorge()+num);
            skuService.update(skuEntityTemp);
        }
    }

    private void validate(Seckill seckill){
        if(seckill == null){
            throw new BusinessException("秒杀信息不能为空！");
        }else if(StringUtil.isEmpty(seckill.getSeckillName()) || seckill.getSeckillName().length()>8){
            throw new BusinessException("秒杀名称不能为空且长度不能超过8位！");
        }else if(null == seckill.getValidateStartTime()){
            throw new BusinessException("有效期开始时间不能为空！");
        }else if(null == seckill.getValidateEndTime()){
            throw new BusinessException("有效期结束时间不能为空！");
        }else if(null == seckill.getSkuId()){
            throw new BusinessException("skuID编号不能为空！");
        }else if(null == seckill.getSeckillPrice()){
            throw new BusinessException("秒杀价不能为空！");
        }else if(null == seckill.getSeckillStorage()){
            throw new BusinessException("秒杀库存不能为空！");
        }else if(null == seckill.getLimitPerUser()){
            throw new BusinessException("每人限购次数不能为空！");
        }else if(null == seckill.getOrderCancelTime()){
            throw new BusinessException("订单自动取消时间不能为空！");
        }
    }

    /**
     * 处理Sku中的秒杀IDs
     * @param seckill
     */
    private void processSkuBySeckillIds(Seckill seckill){
        StringBuilder stringBuilder = new StringBuilder();
        SkuEntity skuEntity = skuService.getBySkuId(seckill.getSkuId());
        if (StringUtil.isEmpty(skuEntity.getSeckillIds())) {
            SkuEntity skuEntityTemp = new SkuEntity();
            skuEntityTemp.setId(seckill.getSkuId());
            skuEntityTemp.setSeckillIds("[" + seckill.getSeckillId() + "]");
            skuService.update(skuEntityTemp);
        }
        if (!StringUtil.isEmpty(skuEntity.getSeckillIds())) {
            List<Long> seckillIds = JsonUtil.parseObject(skuEntity.getSeckillIds(), new TypeReference<List<Long>>() {
            });
            if (!seckillIds.contains(seckill.getSeckillId())) {
                String temp = skuEntity.getSeckillIds().replace("]", "");
                stringBuilder.append(temp + "," + seckill.getSeckillId() + "]");
            }
            SkuEntity skuEntityTemp = new SkuEntity();
            skuEntityTemp.setId(seckill.getSkuId());
            skuEntityTemp.setSeckillIds(stringBuilder.toString());
            skuService.update(skuEntityTemp);
        }
    }

    /**
     * 处理重复的秒杀信息
     * @param seckill
     */
    private void processDuplicateSeckillId(Seckill seckill){
        PageBean pageBean = new PageBean();
        pageBean.setPage(1);
        pageBean.setPageSize(Integer.MAX_VALUE);
        PageResultBean<Seckill> pageResultBean = findSeckills(null,0,pageBean);
        List<Seckill> list = pageResultBean.getList();
        for(int i=0,len=list.size();i<len;i++){
            if(seckill.getSeckillId().equals(list.get(i).getSeckillId())){
                continue;
            }
            if(seckill.getSkuId().equals(list.get(i).getSkuId())){
                throw new BusinessException("该商品在未开始的秒杀商品中，请重新选择商品！");
            }
        }
        pageResultBean = findSeckills(null,1,pageBean);
        list = pageResultBean.getList();
        for(int i=0,len=list.size();i<len;i++){
            if(seckill.getSeckillId().equals(list.get(i).getSeckillId())){
                continue;
            }
            if(seckill.getSkuId().equals(list.get(i).getSkuId())){
                throw new BusinessException("该商品在进行秒杀商品中，请重新选择商品！");
            }
        }
    }

    /**
     * 过滤Sku中秒杀不合格的商品
     * @param skuEntities
     * @return
     */
    private List<SkuEntity> processNoPassedSkuIds(List<SkuEntity> skuEntities){
        List<SkuEntity> resSkuEntities = new ArrayList<>();
        for(int i=0,len = skuEntities.size();i<len;i++){
            SkuEntity skuEntity = skuEntities.get(i);
            List<Long> seckillIds = JsonUtil.parseObject(skuEntity.getSeckillIds(),new TypeReference<List<Long>>(){});
            for(int j=0,len1=seckillIds.size();j<len1;j++){
                Seckill seckill = seckillMapper.selectByPrimaryKey(seckillIds.get(j));
                if(validateSeckillEffective(seckill,false)){
                    skuEntities.get(i).setSeckill(seckill);
                }
            }
            if(skuEntities.get(i).getSeckill() == null){
                continue;
            }
            ProductEntity productEntity = productService.selectById(skuEntity.getSpuCode());
            if(skuEntity.getGoodsName() != null){
                skuEntities.get(i).setGoodsName(skuEntity.getGoodsName());
            }else {
                skuEntities.get(i).setGoodsName(productEntity.getGoodsName());
            }
            resSkuEntities.add(skuEntities.get(i));
        }
        return resSkuEntities;
    }

    /**
     * 同步秒杀库存与SKU库存
     * @param seckill
     */
    private void syncSeckillAndSKuStorage(Seckill seckill,Boolean flag){
        SkuEntity skuEntity = skuService.getBySkuId(seckill.getSkuId());
        //新建秒杀，扣库存
        if(flag == false){
            SkuEntity skuEntityTemp = new SkuEntity();
            Integer seckillStorage = seckill.getSeckillStorage();
            Integer skuStorage = skuEntity.getGoodsStorge();
            Integer resStorage = skuStorage - seckillStorage;
            skuEntityTemp.setGoodsStorge(resStorage);
            skuEntityTemp.setId(seckill.getSkuId());
            skuService.update(skuEntityTemp);
        }else if(flag == true){//秒杀结束，回库存
            SkuEntity skuEntityTemp = new SkuEntity();
            Integer seckillStorage = seckill.getLeftStorage();
            Integer skuStorage = skuEntity.getGoodsStorge();
            Integer resStorage = skuStorage + seckillStorage;
            skuEntityTemp.setGoodsStorge(resStorage);
            skuEntityTemp.setId(seckill.getSkuId());
            skuService.update(skuEntityTemp);
        }
    }

    /**
     * 显示正常秒杀的商品，删除不合格的
     * @param list
     * @return
     */
    private List<SkuEntity> processNoPassedSeckillId(List<SkuEntity> list){
        List<SkuEntity> resList = new ArrayList<>();
        List<ProductEntity> productEntities = productService.getBlackProductsByPage();
        for(int i=0,len=list.size();i<len;i++){
            if(list.get(i).getSeckill() == null){
                continue;
            }
            Boolean flag = false;
            for(int j=0,len1=productEntities.size();j<len1;j++){
                if(list.get(i).getId().equals(productEntities.get(j).getSkuId())){
                    flag = true;
                }
            }
            if(flag == true){
                continue;
            }
            resList.add(list.get(i));
        }
        return resList;
    }
}
