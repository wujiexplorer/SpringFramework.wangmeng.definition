package com.lx.benefits.service.supplierInfo;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.admin.supplierinfo.SupplierInfoDto;
import com.lx.benefits.bean.dto.admin.supplierinfo.SupplierInfoReq;
import com.lx.benefits.bean.entity.supplierInfo.SupplierInfo;

import java.util.List;

/**
 * User: fan
 * Date: 2019/02/24
 * Time: 00:49
 */
public interface SupplierInfoService {

    /**
     * 增加供应商
     *
     * @param record
     * @return
     */
    JSONObject insert(SupplierInfoDto record);

    /**
     * 查询供应商详细信息
     *
     * @param id
     * @return
     */
    JSONObject getSupplierInfoById(Long id);

    SupplierInfo getSupplierById(Long id);

    /**
     * 分页查询供应商信息
     *
     * @return
     */
    JSONObject getSupplierInfoList(SupplierInfoReq req);

    /**
     * 更新供应商
     *
     * @param record
     * @return
     */
    JSONObject update(SupplierInfoDto record);

    /**
     * 删除供应商
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 查询供应商列表
     * @param idList
     * @return
     */
    List<SupplierInfo> listByIds(List<Long> idList);

}
