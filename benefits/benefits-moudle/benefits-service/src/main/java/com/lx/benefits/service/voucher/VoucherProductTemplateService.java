package com.lx.benefits.service.voucher;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.entity.voucher.VoucherProductTemplate;

import java.util.List;

/**
 * User:wangmeng
 * Date:2019/8/8
 * Time:10:29
 * Version:2.x
 * Description:TODO
 **/
public interface VoucherProductTemplateService {
    /**
     * 新增商品模板
     * @param voucherProductTemplate
     * @return
     */
    public VoucherProductTemplate insertVoucherProductTemplate(VoucherProductTemplate voucherProductTemplate);

    /**
     * 修改商品模板
     * @param voucherProductTemplate
     * @return
     */
    public Integer updateVoucherProductTemplate(VoucherProductTemplate voucherProductTemplate);

    /**
     * 查询商品模板信息
     * @param templateId
     * @return
     */
    public VoucherProductTemplate getVoucherProductTemplate(Long templateId);

    /**
     * 查询商品模板管理列表
     * @param templateName
     * @param pageBean
     * @return
     */
    public PageResultBean<VoucherProductTemplate> findVoucherProductTemplates(String templateName, PageBean pageBean);

    /**
     * 查询商品模板详情
     * @param templateId
     * @return
     */
    public VoucherProductTemplate getVoucherProductTemplateDetail(Long templateId);
}
