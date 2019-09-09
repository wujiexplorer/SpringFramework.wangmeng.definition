package com.lx.benefits.mapper.supplierInfo;

import com.lx.benefits.bean.dto.admin.supplierinfo.SupplierInfoReq;
import com.lx.benefits.bean.entity.supplierInfo.SupplierInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SupplierInfoMapper {

    int delete(Long id);

    int insert(SupplierInfo record);

    SupplierInfo getSupplierInfoById(Long id);

    SupplierInfo getSupplierInfoByName(String name);

    List<SupplierInfo> getSupplierInfoList(SupplierInfoReq record);

    Integer getSupplierInfoCount(SupplierInfoReq record);

    int update(SupplierInfo record);

    List<SupplierInfo> selectByIds(List<Long> idList);

}