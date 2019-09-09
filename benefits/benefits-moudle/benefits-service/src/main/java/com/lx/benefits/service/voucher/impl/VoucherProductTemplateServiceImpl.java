package com.lx.benefits.service.voucher.impl;

import com.alibaba.fastjson.TypeReference;
import com.apollo.common.annotation.RedisLock;
import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.entity.product.BrandEntity;
import com.lx.benefits.bean.entity.product.CategoryEntity;
import com.lx.benefits.bean.entity.product.ProductEntity;
import com.lx.benefits.bean.entity.voucher.*;
import com.lx.benefits.bean.enums.VoucherEnum;
import com.lx.benefits.bean.util.JsonUtil;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.util.StringUtil;
import com.lx.benefits.mapper.voucher.VoucherProductTemplateMapper;
import com.lx.benefits.service.product.BrandService;
import com.lx.benefits.service.product.CategoryService;
import com.lx.benefits.service.product.ProductService;
import com.lx.benefits.service.product.ProductTopicService;
import com.lx.benefits.service.voucher.VoucherProductTemplateService;
import com.lx.benefits.service.voucher.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * User:wangmeng
 * Date:2019/8/8
 * Time:10:30
 * Version:2.x
 * Description:TODO
 **/
@Service
public class VoucherProductTemplateServiceImpl implements VoucherProductTemplateService {


    @Autowired
    private VoucherProductTemplateMapper voucherProductTemplateMapper;

    @Autowired
    private BrandService brandService;


    @Autowired
    private ProductService productService;


    @Autowired
    private CategoryService categoryService;

    @Autowired
    private VoucherService voucherService;

    @Autowired
    private ProductTopicService productTopicService;

    @Override
    @Transactional
    public VoucherProductTemplate insertVoucherProductTemplate(VoucherProductTemplate voucherProductTemplate) {
        validate(voucherProductTemplate);
        voucherProductTemplate.setDeleted((byte)0);
        voucherProductTemplate.setCreateTime(new Date());
        VoucherProductTemplateExample voucherProductTemplateExample = new VoucherProductTemplateExample();
        voucherProductTemplateExample.createCriteria().andDeletedEqualTo((byte)0);
        List<VoucherProductTemplate> list = voucherProductTemplateMapper.selectByExample(voucherProductTemplateExample);
        Integer count = voucherProductTemplateMapper.insertSelective(voucherProductTemplate);
        for(int i=0,len=list.size();i<len;i++){
            if(list.get(i).getTemplateName().equals(voucherProductTemplate.getTemplateName())){
                throw new BusinessException("商品模板名称不能重复！");
            }
        }
        processDuplicateSpu(voucherProductTemplate);
        return voucherProductTemplate;
    }

    @Override
    @Transactional
    @RedisLock(name = "VoucherProductTemplate",keys = {"#voucherProductTemplate.templateId"})
    public Integer updateVoucherProductTemplate(VoucherProductTemplate voucherProductTemplate) {
        voucherProductTemplate.setUpdateTime(new Date());
        voucherProductTemplate.setUpdater(SessionContextHolder.getCurrentLoginName());
        List<Voucher> vouchers = voucherService.findVouchersByTemplateId(voucherProductTemplate.getTemplateId());
        if(voucherProductTemplate.getDeleted() != null && voucherProductTemplate.getDeleted() == 1){
            Boolean flag = false;
            if(!vouchers.isEmpty()){
                for(int i=0,len=vouchers.size();i<len;i++){
                    if(vouchers.get(i).getVoucherStatus() != 2){
                        flag = true;
                    }
                }
            }
            if(flag == true){
                throw new BusinessException("该商品模板关联的优惠卷没有停止，不能删除！");
            }
        }
        List<Voucher> vouchersTemp = voucherService.findVouchersByTemplateId(voucherProductTemplate.getTemplateId());
        VoucherProductTemplate voucherProductTemplateTemp =
                voucherProductTemplateMapper.selectByPrimaryKey(voucherProductTemplate.getTemplateId());
        if(voucherProductTemplateTemp.getProductRange().equals(voucherProductTemplate.getProductRange()) &&
        voucherProductTemplateTemp.getProductItems().equals(voucherProductTemplate.getProductItems())){
            //no handle
        }else{
            if(!vouchersTemp.isEmpty()){
                for(int i=0,len=vouchersTemp.size();i<len;i++){
                    if (voucherProductTemplate.getProductRange().intValue() == (VoucherEnum.CATEGORY.getCode())) {
                        processCategoryProducts(voucherProductTemplateTemp, vouchersTemp.get(i),false);
                        processCategoryProducts(voucherProductTemplate, vouchersTemp.get(i),true);
                    } else if (voucherProductTemplate.getProductRange().intValue() == (VoucherEnum.BRAND.getCode())) {
                        processBrandProducts(voucherProductTemplateTemp, vouchersTemp.get(i),false);
                        processBrandProducts(voucherProductTemplate, vouchersTemp.get(i),true);
                    } else if (voucherProductTemplate.getProductRange().intValue() == VoucherEnum.TOPIC.getCode()) {
                        processTopicProducts(voucherProductTemplateTemp, vouchersTemp.get(i),false);
                        processTopicProducts(voucherProductTemplate, vouchersTemp.get(i),true);
                    } else if (voucherProductTemplate.getProductRange().intValue() == (VoucherEnum.PRODUCT.getCode())) {
                        processSpuProducts(voucherProductTemplateTemp, vouchersTemp.get(i),false);
                        processSpuProducts(voucherProductTemplate, vouchersTemp.get(i),true);
                    }
                }
            }

        }
        Integer count = voucherProductTemplateMapper.updateByPrimaryKeySelective(voucherProductTemplate);
        return count;
    }


    /**
     * 处理类目商品
     *
     * @param voucherProductTemplate
     */
    private void processCategoryProducts(VoucherProductTemplate voucherProductTemplate, Voucher voucher,
                                           Boolean flag) {
        List<Integer> list = JsonUtil.parseObject(voucherProductTemplate.getProductItems(), new TypeReference<List<Integer>>() {
        });
        List<Long> spuCodes = new ArrayList<>();
        if (voucherProductTemplate.getProductRange().intValue() == (VoucherEnum.CATEGORY.getCode())) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> params = new HashMap<>();
                params.put("categoryId3", list.get(i));
                List<ProductEntity> productEntities = productService.queryByParam(params);
                for (int j = 0; j < productEntities.size(); j++) {
                    spuCodes.add(productEntities.get(j).getSpuCode());
                }
            }
        } else if (voucherProductTemplate.getProductRange().intValue() == (VoucherEnum.BRAND.getCode())) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> params = new HashMap<>();
                params.put("brandId", list.get(i));
                List<ProductEntity> productEntities = productService.queryByParam(params);
                for (int j = 0; j < productEntities.size(); j++) {
                    spuCodes.add(productEntities.get(j).getSpuCode());
                }
            }
        } else if (voucherProductTemplate.getProductRange().intValue() == VoucherEnum.TOPIC.getCode()) {
            for (int i = 0; i < list.size(); i++) {
                List<ProductEntity> productEntities = productTopicService.getTopicProducts(list.get(i));
                for (int j = 0; j < productEntities.size(); j++) {
                    spuCodes.add(productEntities.get(j).getSpuCode());
                }
            }
        } else if (voucherProductTemplate.getProductRange().intValue() == (VoucherEnum.PRODUCT.getCode())) {
             spuCodes = JsonUtil.parseObject(voucherProductTemplate.getProductItems(), new TypeReference<List<Long>>() {
        });
        }

        Map<String, Object> params = new HashMap<>();
        params.put("spuCodeList", spuCodes);
        List<ProductEntity> productEntities = productService.queryByParam(params);
        if (!productEntities.isEmpty()) {
            if(flag == false){
                voucherService.processDelVoucherIdBySpu(productEntities,voucher);
            }else if(flag == true){
                voucherService.processVoucherIdsBySpu(productEntities, voucher);
            }
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
    private void processBrandProducts(VoucherProductTemplate voucherProductTemplate, Voucher voucher,Boolean flag) {
        List<Integer> list = JsonUtil.parseObject(voucherProductTemplate.getProductItems(), new TypeReference<List<Integer>>() {
        });
        List<Long> spuCodes = new ArrayList<>();
        if (voucherProductTemplate.getProductRange().intValue() == (VoucherEnum.CATEGORY.getCode())) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> params = new HashMap<>();
                params.put("categoryId3", list.get(i));
                List<ProductEntity> productEntities = productService.queryByParam(params);
                for (int j = 0; j < productEntities.size(); j++) {
                    spuCodes.add(productEntities.get(j).getSpuCode());
                }
            }
        } else if (voucherProductTemplate.getProductRange().intValue() == (VoucherEnum.BRAND.getCode())) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> params = new HashMap<>();
                params.put("brandId", list.get(i));
                List<ProductEntity> productEntities = productService.queryByParam(params);
                for (int j = 0; j < productEntities.size(); j++) {
                    spuCodes.add(productEntities.get(j).getSpuCode());
                }
            }
        } else if (voucherProductTemplate.getProductRange().intValue() == VoucherEnum.TOPIC.getCode()) {
            for (int i = 0; i < list.size(); i++) {
                List<ProductEntity> productEntities = productTopicService.getTopicProducts(list.get(i));
                for (int j = 0; j < productEntities.size(); j++) {
                    spuCodes.add(productEntities.get(j).getSpuCode());
                }
            }
        } else if (voucherProductTemplate.getProductRange().intValue() == (VoucherEnum.PRODUCT.getCode())) {
            spuCodes = JsonUtil.parseObject(voucherProductTemplate.getProductItems(), new TypeReference<List<Long>>() {
            });
        }
        Map<String, Object> params = new HashMap<>();
        params.put("spuCodeList", spuCodes);
        List<ProductEntity> productEntities = productService.queryByParam(params);
        if (!productEntities.isEmpty()) {
            if(flag == false){
                voucherService.processDelVoucherIdBySpu(productEntities,voucher);
            }else if(flag == true){
                voucherService.processVoucherIdsBySpu(productEntities, voucher);
            }
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
    private void processTopicProducts(VoucherProductTemplate voucherProductTemplate, Voucher voucher,Boolean flag) {
        List<Integer> list = JsonUtil.parseObject(voucherProductTemplate.getProductItems(), new TypeReference<List<Integer>>() {
        });
        List<Long> spuCodes = new ArrayList<>();
        if (voucherProductTemplate.getProductRange().intValue() == (VoucherEnum.CATEGORY.getCode())) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> params = new HashMap<>();
                params.put("categoryId3", list.get(i));
                List<ProductEntity> productEntities = productService.queryByParam(params);
                for (int j = 0; j < productEntities.size(); j++) {
                    spuCodes.add(productEntities.get(j).getSpuCode());
                }
            }
        } else if (voucherProductTemplate.getProductRange().intValue() == (VoucherEnum.BRAND.getCode())) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> params = new HashMap<>();
                params.put("brandId", list.get(i));
                List<ProductEntity> productEntities = productService.queryByParam(params);
                for (int j = 0; j < productEntities.size(); j++) {
                    spuCodes.add(productEntities.get(j).getSpuCode());
                }
            }
        } else if (voucherProductTemplate.getProductRange().intValue() == VoucherEnum.TOPIC.getCode()) {
            for (int i = 0; i < list.size(); i++) {
                List<ProductEntity> productEntities = productTopicService.getTopicProducts(list.get(i));
                for (int j = 0; j < productEntities.size(); j++) {
                    spuCodes.add(productEntities.get(j).getSpuCode());
                }
            }
        } else if (voucherProductTemplate.getProductRange().intValue() == (VoucherEnum.PRODUCT.getCode())) {
            spuCodes = JsonUtil.parseObject(voucherProductTemplate.getProductItems(), new TypeReference<List<Long>>() {
            });
        }
        Map<String, Object> params = new HashMap<>();
        params.put("spuCodeList", spuCodes);
        List<ProductEntity> productEntities = productService.queryByParam(params);
        if (!productEntities.isEmpty()) {
            if(flag == false){
                voucherService.processDelVoucherIdBySpu(productEntities,voucher);
            }else if(flag == true){
                voucherService.processVoucherIdsBySpu(productEntities, voucher);
            }
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
    private void processSpuProducts(VoucherProductTemplate voucherProductTemplate, Voucher voucher,Boolean flag) {
        List<Integer> list = JsonUtil.parseObject(voucherProductTemplate.getProductItems(), new TypeReference<List<Integer>>() {
        });
        List<Long> spuCodes = new ArrayList<>();
        if (voucherProductTemplate.getProductRange().intValue() == (VoucherEnum.CATEGORY.getCode())) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> params = new HashMap<>();
                params.put("categoryId3", list.get(i));
                List<ProductEntity> productEntities = productService.queryByParam(params);
                for (int j = 0; j < productEntities.size(); j++) {
                    spuCodes.add(productEntities.get(j).getSpuCode());
                }
            }
        } else if (voucherProductTemplate.getProductRange().intValue() == (VoucherEnum.BRAND.getCode())) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> params = new HashMap<>();
                params.put("brandId", list.get(i));
                List<ProductEntity> productEntities = productService.queryByParam(params);
                for (int j = 0; j < productEntities.size(); j++) {
                    spuCodes.add(productEntities.get(j).getSpuCode());
                }
            }
        } else if (voucherProductTemplate.getProductRange().intValue() == VoucherEnum.TOPIC.getCode()) {
            for (int i = 0; i < list.size(); i++) {
                List<ProductEntity> productEntities = productTopicService.getTopicProducts(list.get(i));
                for (int j = 0; j < productEntities.size(); j++) {
                    spuCodes.add(productEntities.get(j).getSpuCode());
                }
            }
        } else if (voucherProductTemplate.getProductRange().intValue() == (VoucherEnum.PRODUCT.getCode())) {
            spuCodes = JsonUtil.parseObject(voucherProductTemplate.getProductItems(), new TypeReference<List<Long>>() {
            });
        }
        Map<String, Object> params = new HashMap<>();
        params.put("spuCodeList", spuCodes);
        List<ProductEntity> productEntities = productService.queryByParam(params);
        if (!productEntities.isEmpty()) {
            if(flag == false){
                voucherService.processDelVoucherIdBySpu(productEntities,voucher);
            }else if(flag == true){
                voucherService.processVoucherIdsBySpu(productEntities, voucher);
            }
        }
//        params.put("spuCodes", spuCodes);
//        List<SkuEntity> skuEntities = skuService.selectSkuBySpuCode(params);
//        if (!skuEntities.isEmpty()) {
//            processVoucherIds(skuEntities, voucher);
//        }
    }


    @Override
    public VoucherProductTemplate getVoucherProductTemplate(Long templateId) {
        return voucherProductTemplateMapper.selectByPrimaryKey(templateId);
    }

    @Override
    public PageResultBean<VoucherProductTemplate> findVoucherProductTemplates(String templateName, PageBean pageBean) {
        VoucherProductTemplateExample voucherProductTemplateExample = new VoucherProductTemplateExample();
        VoucherProductTemplateExample.Criteria criteria =  voucherProductTemplateExample.createCriteria();
        if(!StringUtil.isEmpty(templateName)){
            criteria.andTemplateNameLike("%"+templateName+"%");
        }
        criteria.andDeletedEqualTo((byte)0);
        Integer count = voucherProductTemplateMapper.countByExample(voucherProductTemplateExample);
        if(count == 0){
            return  new PageResultBean<>(pageBean.getPage(),pageBean.getPageSizeExtend(),count, Collections.emptyList());
        }
        voucherProductTemplateExample.setLimit(pageBean.getPageSizeExtend() );
        voucherProductTemplateExample.setOffset(pageBean.getOffsetExtend());
        voucherProductTemplateExample.setOrderByClause("template_id desc");
        List<VoucherProductTemplate> list = voucherProductTemplateMapper.selectByExample(voucherProductTemplateExample);
        return new PageResultBean<>(pageBean.getPage(),pageBean.getPageSizeExtend(),count,list);
    }

    @Override
    public VoucherProductTemplate getVoucherProductTemplateDetail(Long templateId) {
        if(templateId == null){
            throw new BusinessException("商品模板ID不能为空！");
        }
        VoucherProductTemplate voucherProductTemplate = voucherProductTemplateMapper.selectByPrimaryKey(templateId);
        if(voucherProductTemplate.getProductRange().intValue() ==(VoucherEnum.CATEGORY.getCode())){
            List<Long> categoryId3 = JsonUtil.parseObject(voucherProductTemplate.getProductItems(),new TypeReference<List<Long>>(){});
            String categoryId1 = "[";
            String categoryId2 = "[";
            for(int i=0,len=categoryId3.size();i<len;i++){
                CategoryEntity categoryEntity = categoryService.selectById(categoryId3.get(i));
                categoryId2 = categoryId2+categoryEntity.getParentId()+",";
                categoryEntity = categoryService.selectById(categoryEntity.getParentId());
                categoryId1 = categoryId1+categoryEntity.getParentId()+",";
            }
            categoryId2 = categoryId2 +"]";
            categoryId1 = categoryId1 + "]";
            categoryId1 = categoryId1.replace(",]","]");
            categoryId2 = categoryId2.replace(",]","]");
            voucherProductTemplate.setCategoryId1(categoryId1);
            voucherProductTemplate.setCategoryId2(categoryId2);
            return  voucherProductTemplate;
        }else if(voucherProductTemplate.getProductRange().intValue()==(VoucherEnum.BRAND.getCode())){
            List<Integer> brandIds = JsonUtil.parseObject(voucherProductTemplate.getProductItems(),new TypeReference<List<Integer>>(){});
            List<VoucherBrandInfo> list = new ArrayList<>();
            for(int i=0,len=brandIds.size();i<len;i++){
                BrandEntity brandEntity = brandService.selectBrandById(brandIds.get(i).longValue());
                VoucherBrandInfo voucherBrandInfo = new VoucherBrandInfo();
                voucherBrandInfo.setBrandId(brandEntity.getId().intValue());
                voucherBrandInfo.setBrandName(brandEntity.getName());
                list.add(voucherBrandInfo);
            }
            voucherProductTemplate.setVoucherBrandInfos(list);
        }else if(voucherProductTemplate.getProductRange().intValue()==(VoucherEnum.PRODUCT.getCode())){
            List<Long> spuCodes = JsonUtil.parseObject(voucherProductTemplate.getProductItems(),new TypeReference<List<Long>>(){});
            List<VoucherSpuInfo> list = new ArrayList<>();
            for(int i=0,len=spuCodes.size();i<len;i++){
                ProductEntity productEntity = productService.selectById(spuCodes.get(i));
                VoucherSpuInfo voucherSpuInfo = new VoucherSpuInfo();
                voucherSpuInfo.setSpuCode(spuCodes.get(i));
                voucherSpuInfo.setBrandName(productEntity.getBrandName());
                voucherSpuInfo.setProductImage(productEntity.getGoodsImage());
                voucherSpuInfo.setProductName(productEntity.getGoodsName());
                voucherSpuInfo.setSupplierName(productEntity.getSupplierName());
                list.add(voucherSpuInfo);
            }
            voucherProductTemplate.setVoucherSpuInfos(list);
        }
        return voucherProductTemplate;
    }


    private void validate(VoucherProductTemplate voucherProductTemplate){
        if(null == voucherProductTemplate){
            throw new BusinessException("商品模板信息体不能为空！");
        }else if(StringUtil.isEmpty(voucherProductTemplate.getTemplateName())){
            throw new BusinessException("商品模板名称不能为空！");
        }else if(null == voucherProductTemplate.getProductRange()){
            throw new BusinessException("商品模板类型不能为空！");
        }else if(StringUtil.isEmpty(voucherProductTemplate.getProductItems())){
            throw new BusinessException("商品模板类型对应的具体商品不能为空！");
        }
    }

    /**
     * 添加Spu时，处理重复的Spu
     * @param voucherProductTemplate
     */
    private void processDuplicateSpu(VoucherProductTemplate voucherProductTemplate){
        if(voucherProductTemplate.getProductRange().intValue() == VoucherEnum.PRODUCT.getCode()){
            List<Long> spus = JsonUtil.parseObject(voucherProductTemplate.getProductItems(),new TypeReference<List<Long>>(){});
            if(spus.size() > 50){
                throw new BusinessException("最多50个商品！");
            }
            Set<Long> sets = new HashSet<>(spus);
            if(sets.size() < spus.size()){
                throw new BusinessException("添加商品时，有重复的商品！");
            }
        }
    }
}
