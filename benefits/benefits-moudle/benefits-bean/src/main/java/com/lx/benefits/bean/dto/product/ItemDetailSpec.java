package com.lx.benefits.bean.dto.product;

import com.lx.benefits.bean.dto.jd.annotation.Virtual;
import com.lx.benefits.bean.dto.jd.base.BaseDO;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
  * @author szy
  * 商品销售规格信息
  */
public class ItemDetailSpec extends BaseDO implements Serializable {

	private static final long serialVersionUID = 1450873698778L;

	/** 数据类型bigint(20)*/
	@Id
	private Long id;
	
	/**prid纬度 detailid 数据类型bigint(20)*/
	private Long detailId;
	
	/**规格id 数据类型bigint(20)*/
	private Long specId;
	
	/**规格所属规格组 数据类型bigint(20)*/
	private Long specGroupId;
	
	/**排序，当前规格所属组排列顺序 数据类型int(11)*/
	private Integer sort;
	
	/**添加时间 数据类型datetime*/
	private Date createTime;
	
	/**修改时间 数据类型datetime*/
	private Date updateTime;

	/** 规格名称 */
	@Virtual
	private String specName; 	
	/** 规格组名称 */
	@Virtual
	private String specGroupName;
	
	public Long getId(){
		return id;
	}
	public Long getDetailId(){
		return detailId;
	}
	public Long getSpecId(){
		return specId;
	}
	public Long getSpecGroupId(){
		return specGroupId;
	}
	public Integer getSort(){
		return sort;
	}
	public Date getCreateTime(){
		return createTime;
	}
	public Date getUpdateTime(){
		return updateTime;
	}
	public void setId(Long id){
		this.id=id;
	}
	public void setDetailId(Long detailId){
		this.detailId=detailId;
	}
	public void setSpecId(Long specId){
		this.specId=specId;
	}
	public void setSpecGroupId(Long specGroupId){
		this.specGroupId=specGroupId;
	}
	public void setSort(Integer sort){
		this.sort=sort;
	}
	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	public void setUpdateTime(Date updateTime){
		this.updateTime=updateTime;
	}
	public String getSpecName() {
		return specName;
	}
	public void setSpecName(String specName) {
		this.specName = specName;
	}
	public String getSpecGroupName() {
		return specGroupName;
	}
	public void setSpecGroupName(String specGroupName) {
		this.specGroupName = specGroupName;
	}
}
