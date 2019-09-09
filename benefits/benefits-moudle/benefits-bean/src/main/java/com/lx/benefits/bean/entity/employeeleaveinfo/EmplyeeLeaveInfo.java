package com.lx.benefits.bean.entity.employeeleaveinfo;

public class EmplyeeLeaveInfo {
    /**
     * 离职记录id,自增主键ID
     */
    private Long leaveId;

    /**
     * 员工id
     */
    private Long employeeId;

    /**
     * 员工所属企业id
     */
    private Long enterprId;

    /**
     * 员工是否已离职{0: 在职; 1: 离职}
     */
    private Integer leaveStatus;

    /**
     * 操作人id
     */
    private Long optUserId;

    /**
     * 是否删除 {1:已删除; 0:未删除}
     */
    private Integer isDeleted;

    /**
     * 创建时间
     */
    private Integer created;

    /**
     * 更新时间
     */
    private Integer updated;

    public Long getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Long leaveId) {
        this.leaveId = leaveId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getEnterprId() {
        return enterprId;
    }

    public void setEnterprId(Long enterprId) {
        this.enterprId = enterprId;
    }

    public Integer getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(Integer leaveStatus) {
        this.leaveStatus = leaveStatus;
    }

    public Long getOptUserId() {
        return optUserId;
    }

    public void setOptUserId(Long optUserId) {
        this.optUserId = optUserId;
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
}