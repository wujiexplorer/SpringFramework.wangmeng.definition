package com.lx.benefits.bean.dto.product;


import com.lx.benefits.bean.base.dto.BaseVO;

public class ClassificationVO implements BaseVO {

	    private static final long serialVersionUID = -4531637895637194929L;
	   
	    private String name;//分类名称
	    private String id;//分类id
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
	    
	    

}
