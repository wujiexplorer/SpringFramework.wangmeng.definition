package com.lx.benefits.bean.constants;

/**
 * User:wangmeng
 * Date:2019/7/11
 * Time:10:42
 * Version:2.x
 * Description:TODO
 **/
public enum AgentOpreativeType {

    NO_AGENT_OPREATIVE(0,"关闭代运营"),
    YES_AGENT_OPREATIVE(1,"开启代运营");

    private AgentOpreativeType(Integer code,String desc){
        this.code = code;
        this.desc = desc;
    }

    private Integer code;
    private String desc;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
