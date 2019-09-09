package com.lx.benefits.bean.vo.navigation;



import com.lx.benefits.bean.dto.jd.annotation.Id;
import com.lx.benefits.bean.dto.jd.base.BaseDO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
  * @author szy
  * 分类导航类目表
  */
@Data
public class NavigationCategory extends BaseDO implements Serializable {

	private static final long serialVersionUID = 1456124164854L;

	/**类目id 数据类型bigint(11)*/
	@Id
	private Long id;
	
	/**类目名称 数据类型varchar(20)*/
	private String name;

	private String nameEn;

	/**导航类型 1 分类 2 品牌*/
	private Integer type;
	
	/**类目编码 数据类型varchar(50)*/
	private String code;
	
	/**类目级别:1-一级类目,2-二级类目,3-三级类目 数据类型tinyint(1)*/
	private Integer level;
	
	/**状态:0-无效 1-有效 数据类型tinyint(1)*/
	private Integer status;
	
	/**是否突出展示：0-否 1-是 数据类型tinyint(1)*/
	private Integer isHighlight;
	
	/**图片 数据类型varchar(2000)*/
	private String pic;
	
	/**父类目ID 数据类型bigint(11)*/
	private Long parentId;
	
	/**顺序 数据类型tinyint(4)*/
	private Integer sort;
	
	/**是否发布:0否 1是 数据类型tinyint(1)*/
	private Integer isPublish;
	
	/**创建人 数据类型varchar(50)*/
	private String createUser;
	
	/**创建时间 数据类型datetime*/
	private Date createTime;
	
	/**修改人 */
	private String updateUser;
	
	/**修改时间 数据类型datetime*/
	private Date updateTime;

	private List<Long> ids;
    /**过滤分类*/
	private List<Long> categoryIdsList;

}
