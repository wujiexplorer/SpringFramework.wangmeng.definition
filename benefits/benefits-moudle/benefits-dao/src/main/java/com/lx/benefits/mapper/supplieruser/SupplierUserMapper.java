package com.lx.benefits.mapper.supplieruser;

import com.lx.benefits.bean.entity.supplieruser.SupplierUser;
import com.lx.benefits.bean.entity.supplieruser.SupplierUserExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SupplierUserMapper {

    int countByExample(SupplierUserExample example);

    int deleteByExample(SupplierUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SupplierUser record);

    int insertSelective(SupplierUser record);

    List<SupplierUser> selectByExampleWithBLOBs(SupplierUserExample example);

    List<SupplierUser> selectByExample(SupplierUserExample example);

    /**
     * 根据登录用户名查询供应商信息
     * @param LoginName 供应商登录用户名
     * @return SupplierUser
     */
    SupplierUser getSupplierUserByLoginName(String LoginName);

    SupplierUser selectByPrimaryKey(Long id);

    SupplierUser selectBySupplierId(@Param("sid") Long sid);

    int updateByExampleSelective(@Param("record") SupplierUser record, @Param("example") SupplierUserExample example);

    int updateByExampleWithBLOBs(@Param("record") SupplierUser record, @Param("example") SupplierUserExample example);

    int updateByExample(@Param("record") SupplierUser record, @Param("example") SupplierUserExample example);

    int updateByPrimaryKeySelective(SupplierUser record);

    int updateByPrimaryKeyWithBLOBs(SupplierUser record);

    int updateByPrimaryKey(SupplierUser record);
}