package com.lx.benefits.bean.dto.admin.customized;


import java.util.List;

public class EnterprNoticeDto {
    /**
     * 定制模块id,自增主键ID
     */
    private Long id;
    /**
     * 所属企业客户id
     */
    private Long enterprId;

    private Integer isLoginRemind;

    private List<String> loginAttach;

    private Integer isUserRemind;

    private List<String> userAttach;


    private Integer isDeleted;

    private Integer created;

    private Integer updated;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEnterprId() {
        return enterprId;
    }

    public void setEnterprId(Long enterprId) {
        this.enterprId = enterprId;
    }

    public Integer getIsLoginRemind() {
        return isLoginRemind;
    }

    public void setIsLoginRemind(Integer isLoginRemind) {
        this.isLoginRemind = isLoginRemind;
    }


    public Integer getIsUserRemind() {
        return isUserRemind;
    }

    public void setIsUserRemind(Integer isUserRemind) {
        this.isUserRemind = isUserRemind;
    }



    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public Integer getUpdated() {
        return updated;
    }

    public void setUpdated(Integer updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "EnterprNoticeDto{" +
                "id=" + id +
                ", enterprId=" + enterprId +
                ", isLoginRemind=" + isLoginRemind +
                ", loginAttach=" + loginAttach +
                ", isUserRemind=" + isUserRemind +
                ", userAttach=" + userAttach +
                ", isDeleted=" + isDeleted +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }

    public List<String> getLoginAttach() {
        return loginAttach;
    }

    public void setLoginAttach(List<String> loginAttach) {
        this.loginAttach = loginAttach;
    }

    public List<String> getUserAttach() {
        return userAttach;
    }

    public void setUserAttach(List<String> userAttach) {
        this.userAttach = userAttach;
    }
}