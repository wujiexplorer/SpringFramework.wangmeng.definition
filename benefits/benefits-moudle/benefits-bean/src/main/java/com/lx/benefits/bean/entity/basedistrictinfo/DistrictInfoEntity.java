package com.lx.benefits.bean.entity.basedistrictinfo;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
  * @author szy
  * 地区信息
  */
@Data
public class DistrictInfoEntity  implements Serializable {

	private static final long serialVersionUID = 1450402786417L;

	/** 数据类型bigint(20)*/
	private Long id;
	
	/**父级ID 数据类型int(11)*/
	private Long parentId;
	
	/**地区名称 数据类型varchar(192)*/
	private String name;
	
	/**中文拼音 数据类型varchar(128)*/
	private String spelling;
	
	/**中文拼音简称 数据类型varchar(32)*/
	private String simpleSpelling;
	
	/**层级 层级 0-顶级 1-大洲 2-国家 3-政区 4-省 5-市  6-区 7-街道 数据类型tinyint(4)*/
	private Integer type;
	
	/** 数据类型varchar(100)*/
	private String pathUrl;
	
	/**排序 数据类型smallint(6)*/
	private Integer sortNo;
	
	/**是否有效：0-有效、1-无效，默认为0 数据类型tinyint(1)*/
	private Integer isDelete;
	
	/**创建时间 数据类型datetime*/
	private Date createTime;
	
	/** 更新时间 数据类型datetime*/
	private Date updateTime;
	
	/** 数据类型char(6)*/
	private String nationalCode;

	private Long jdAreaId;

	private Long jdParentId;

	private Integer selected;
}
