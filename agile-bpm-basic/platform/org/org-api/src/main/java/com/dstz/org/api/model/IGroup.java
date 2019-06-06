package com.dstz.org.api.model;

import java.io.Serializable;
import java.util.Map;

import com.dstz.base.api.model.IBaseModel;


/**
 * 描述：抽象用户组类型
 */
public interface IGroup extends Serializable {


    /**
     * 组织ID
     *
     * @return
     */
    String getGroupId();

    /**
     * 组织名称
     *
     * @return
     */
    String getGroupName();

    /**
     * 组织编码
     *
     * @return
     */
    String getGroupCode();

    /**
     * 组织类型。
     * 比如：org,role,pos
     *
     * @return
     */
    String getGroupType();

    /**
     * 上级ID
     *
     * @return
     */
    String getParentId();

}
