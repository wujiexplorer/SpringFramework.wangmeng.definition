package com.lx.benefits.bean.entity.memberinfo;

import java.util.Date;

public class MemberInfo {
    private Long id;

    /**
     * 登录显示名
     */
    private String nick_name;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 随机salt
     */
    private String salt;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 是否手机验证
     */
    private Integer is_mobile_verified;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 修改时间
     */
    private Date modify_time;

    /**
     * 平台来源 
     */
    private Integer plat_form;

    /**
     * 状态1:正常 0:不可用
     */
    private Integer state;

    /**
     * 关联推广员ID
     */
    private Long promoter_id;

    /**
     * 绑定的店铺ID
     */
    private Long shop_promoter_id;

    /**
     * 扫码关注推广员ID
     */
    private Long scan_promoter_id;

    /**
     * 网站来源 1:来自西狗
     */
    private Integer source;

    /**
     * 自定义用户名
     */
    private String user_name;

    /**
     * 渠道代码
     */
    private String channel_code;

    private String tpin;

    /**
     * 用户来源站点
     */
    private String advert_from;

    /**
     * 会员截止日期（只针对钻石用户）
     */
    private Date term_of_validity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name == null ? null : nick_name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getIs_mobile_verified() {
        return is_mobile_verified;
    }

    public void setIs_mobile_verified(Integer is_mobile_verified) {
        this.is_mobile_verified = is_mobile_verified;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Date getModify_time() {
        return modify_time;
    }

    public void setModify_time(Date modify_time) {
        this.modify_time = modify_time;
    }

    public Integer getPlat_form() {
        return plat_form;
    }

    public void setPlat_form(Integer plat_form) {
        this.plat_form = plat_form;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getPromoter_id() {
        return promoter_id;
    }

    public void setPromoter_id(Long promoter_id) {
        this.promoter_id = promoter_id;
    }

    public Long getShop_promoter_id() {
        return shop_promoter_id;
    }

    public void setShop_promoter_id(Long shop_promoter_id) {
        this.shop_promoter_id = shop_promoter_id;
    }

    public Long getScan_promoter_id() {
        return scan_promoter_id;
    }

    public void setScan_promoter_id(Long scan_promoter_id) {
        this.scan_promoter_id = scan_promoter_id;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name == null ? null : user_name.trim();
    }

    public String getChannel_code() {
        return channel_code;
    }

    public void setChannel_code(String channel_code) {
        this.channel_code = channel_code == null ? null : channel_code.trim();
    }

    public String getTpin() {
        return tpin;
    }

    public void setTpin(String tpin) {
        this.tpin = tpin == null ? null : tpin.trim();
    }

    public String getAdvert_from() {
        return advert_from;
    }

    public void setAdvert_from(String advert_from) {
        this.advert_from = advert_from == null ? null : advert_from.trim();
    }

    public Date getTerm_of_validity() {
        return term_of_validity;
    }

    public void setTerm_of_validity(Date term_of_validity) {
        this.term_of_validity = term_of_validity;
    }
    
    
}