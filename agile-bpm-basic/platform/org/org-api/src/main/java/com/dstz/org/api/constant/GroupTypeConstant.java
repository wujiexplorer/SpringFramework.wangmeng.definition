package com.dstz.org.api.constant;

import com.dstz.base.api.exception.BusinessException;

/**
 * 系统支持的组类型
 */
public enum GroupTypeConstant {
    ORG("org", "组织"),
    POST("post", "岗位"),
    ROLE("role", "角色");
    private String key;
    private String label;

    GroupTypeConstant(String key, String label) {
        this.key = key;
        this.label = label;
    }

    public String key() {
        return key;
    }

    public String label() {
        return label;
    }
 
    
    public static GroupTypeConstant fromStr(String key) {
    	
        for (GroupTypeConstant e : GroupTypeConstant.values()) {
            if (key.equals(e.key)) return e;
        }
        
        throw new BusinessException("获取 GroupType 失败".concat(key));
    }

}
