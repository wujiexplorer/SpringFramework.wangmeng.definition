package com.lx.benefits.bean.dto.yianapi;


import java.util.Date;


/**
 * 账户返回对象
 * @author zhuss
 * @2016年1月3日 下午5:31:15
 */
public class AccountVO {

	private static final long serialVersionUID = 5364318244658799106L;

	private String token;//用户凭证
	private String tel;//手机号
	private String name;//昵称
	private String source = "0";//来源:0普通 1微信公众号2微信联合登录3QQ联合登录
	private String isneedbindtel; //是否需要绑定手机 0否 1是
	private String promoterinfo;
	private String headimg;//头像
	private String showfastshoporder="0";//是否显示速购店铺订单
	private String showfastorder="0";//是否显示速购配送订单

	private String address ;//地址
	private String contact;//联系人
	
	private String channelCode;
	
	private Date termOfValidity;//有效期为空  代表永久有效
	private String  isOverdue;//是否过期   0 过期  1 未过期
	
	
	/**企业个性化参数*/
	private String isEntUser; // 是否企业用户 1 是，0否
	private String isIndividuation;
	private String enterpriseLogo;
	private String enterpriseTopLogo;// 移动端首页顶部logo
	private String enterpriseName;
	private String employeename;//员工真实姓名
	private String loginName;


	public AccountVO() {
		super();
	}
	public AccountVO(String token, String tel, String name) {
		super();
		this.token = token;
		this.tel = tel;
		this.name = name;
	}

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getIsneedbindtel() {
		return isneedbindtel;
	}
	public void setIsneedbindtel(String isneedbindtel) {
		this.isneedbindtel = isneedbindtel;
	}
	public String getPromoterinfo() {
		return promoterinfo;
	}
	public void setPromoterinfo(String promoterinfo) {
		this.promoterinfo = promoterinfo;
	}
	public String getHeadimg() {
		return headimg;
	}
	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}
	public String getShowfastshoporder() {
		return showfastshoporder;
	}
	public void setShowfastshoporder(String showfastshoporder) {
		this.showfastshoporder = showfastshoporder;
	}
	public String getShowfastorder() {
		return showfastorder;
	}
	public void setShowfastorder(String showfastorder) {
		this.showfastorder = showfastorder;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public String getIsIndividuation() {
		return isIndividuation;
	}
	public void setIsIndividuation(String isIndividuation) {
		this.isIndividuation = isIndividuation;
	}
	public String getEnterpriseLogo() {
		return enterpriseLogo;
	}
	public void setEnterpriseLogo(String enterpriseLogo) {
		this.enterpriseLogo = enterpriseLogo;
	}
	public String getEnterpriseTopLogo() {
		return enterpriseTopLogo;
	}
	public void setEnterpriseTopLogo(String enterpriseTopLogo) {
		this.enterpriseTopLogo = enterpriseTopLogo;
	}
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	public String getIsEntUser() {
		return isEntUser;
	}
	public void setIsEntUser(String isEntUser) {
		this.isEntUser = isEntUser;
	}
	public Date getTermOfValidity() {
		return termOfValidity;
	}
	public void setTermOfValidity(Date termOfValidity) {
		this.termOfValidity = termOfValidity;
	}
	public String getIsOverdue() {
		return isOverdue;
	}
	public void setIsOverdue(String isOverdue) {
		this.isOverdue = isOverdue;
	}
	public String getEmployeename() {
		return employeename;
	}
	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
}
