package com.lx.benefits.dao.creditoperateinfo;


import com.lx.benefits.bean.entity.creditoperateinfo.CreditOperateInfo;
import com.lx.benefits.bean.entity.creditoperateinfo.CreditOperateInfoExample;

import java.util.HashMap;
import java.util.List;

/**
 * 积分操作记录
 *
 * @author luojie
 */
public interface CreditOperateInfoDao {


    /**
     * 分页获取操作记录表数据
     *
     * @param queryMap 参数
     * @return
     */
    List<CreditOperateInfo> pageByMap(HashMap<String, Object> queryMap);

    List<CreditOperateInfo> find(CreditOperateInfoExample example);

    /**
     * 插入记录
     *
     * @param record
     * @return
     */
    int insertSelective(CreditOperateInfo record);

    /**
     * 更新操作
     *
     * @param record 操作记录
     * @return
     */
    int updateByPrimaryKeySelective(CreditOperateInfo record);

    int update(CreditOperateInfo record, CreditOperateInfoExample example);

    /**
     * 查询操作记录列表
     *
     * @param parentOptId 父类id
     * @return
     */
    List<CreditOperateInfo> listByParentOptId(Long parentOptId);

    int countByExample(CreditOperateInfoExample example);
}
