package com.lx.benefits.bean.entity.memadressimport;

public class AddressImportInfo {
    /**
     * 导入记录id,自增主键ID
     */
    private Long importId;

    /**
     * 所属企业id
     */
    private Long enterprId;

    /**
     * 导入的文件路径
     */
    private String fileUrl;

    /**
     * 操作人id
     */
    private Long optUserId;

    /**
     * 导入记录数
     */
    private Long importCount;

    /**
     * 成功数
     */
    private Long importSuc;

    /**
     * 失败数
     */
    private Long importErr;

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

    public Long getImportId() {
        return importId;
    }

    public void setImportId(Long importId) {
        this.importId = importId;
    }

    public Long getEnterprId() {
        return enterprId;
    }

    public void setEnterprId(Long enterprId) {
        this.enterprId = enterprId;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl == null ? null : fileUrl.trim();
    }

    public Long getOptUserId() {
        return optUserId;
    }

    public void setOptUserId(Long optUserId) {
        this.optUserId = optUserId;
    }

    public Long getImportCount() {
        return importCount;
    }

    public void setImportCount(Long importCount) {
        this.importCount = importCount;
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

    public Long getImportSuc() {
        return importSuc;
    }

    public void setImportSuc(Long importSuc) {
        this.importSuc = importSuc;
    }

    public Long getImportErr() {
        return importErr;
    }

    public void setImportErr(Long importErr) {
        this.importErr = importErr;
    }
}