package com.lx.benefits.mapper.employeeimportdetail;

import com.lx.benefits.bean.entity.employeeimportdetail.EmplyeeImportDetail;
import com.lx.benefits.bean.entity.employeeimportdetail.EmplyeeImportDetailExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmplyeeImportDetailMapper {
    int countByExample(EmplyeeImportDetailExample example);

    int deleteByExample(EmplyeeImportDetailExample example);

    int deleteByPrimaryKey(Long detailId);

    int insert(EmplyeeImportDetail record);

    int insertSelective(EmplyeeImportDetail record);

    List<EmplyeeImportDetail> selectByExample(EmplyeeImportDetailExample example);

    EmplyeeImportDetail selectByPrimaryKey(Long detailId);

    int batchInsert(@Param("emplyeeImportDetailList") List<EmplyeeImportDetail> emplyeeImportDetailList);

    int updateByExampleSelective(@Param("record") EmplyeeImportDetail record, @Param("example") EmplyeeImportDetailExample example);

    int updateByExample(@Param("record") EmplyeeImportDetail record, @Param("example") EmplyeeImportDetailExample example);

    int updateByPrimaryKeySelective(EmplyeeImportDetail record);

    int updateByPrimaryKey(EmplyeeImportDetail record);
}