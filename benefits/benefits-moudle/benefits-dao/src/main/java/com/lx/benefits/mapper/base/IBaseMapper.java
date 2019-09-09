package com.lx.benefits.mapper.base;

import org.apache.ibatis.annotations.Param;

/**
 * 基础类 MAPPER
 *
 * @author wind
 */
public interface IBaseMapper<T> {

    /**
     * 插入记录
     *
     * @param obj
     * @return
     */
    Long insert(T obj);

    /**
     * 插入记录(有效字段,即非空字段)
     *
     * @param obj
     * @return
     */
    Long insertSelective(T obj);

    /**
     * 物理删除记录
     *
     * @param seq
     * @return
     */
    @Deprecated
    <K> int deleteByPrimaryKey(@Param("id") K seq);

    /**
     * 更新记录(有效字段,即非空字段)
     *
     * @param obj
     * @return
     */
    int updateByPrimaryKeySelective(T obj);

    /**
     * 根据主键 返回记录
     *
     * @param seq
     * @return
     */
    <K> T selectByPrimaryKey(@Param("id") K seq);

}
