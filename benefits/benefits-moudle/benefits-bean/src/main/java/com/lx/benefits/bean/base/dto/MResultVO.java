package com.lx.benefits.bean.base.dto;



import com.lx.benefits.bean.enums.MResultInfo;
import com.lx.benefits.bean.exception.MobileException;
import com.lx.benefits.bean.util.StringUtil;

import java.io.Serializable;

/**
 * 移动API统一返回对象
 * @author zhuss
 * @2016年1月2日 下午2:51:22
 * @param <T>
 */
public class MResultVO<T> implements Serializable{
	private static final long serialVersionUID = -5605031430528107472L;
	public static final String DEFAULT_FAILED= "-1";
	private T data;
    
    private String code;
    private String msg;
    
    public MResultVO(String msg) {
		super();
		this.code = DEFAULT_FAILED;
		this.msg = msg == null?"系统错误":msg;
	}
    
    public MResultVO(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
    
    public MResultVO(String msg, T data) {
		super();
		this.data = data;
		this.code = DEFAULT_FAILED;
		//this.code = code == null||code == 0?DEFAULT_FAILED:code.toString();
		this.msg = msg == null? MResultInfo.OPERATION_FAILED.message:msg;
	}
    
	public MResultVO(String code, String msg, T data) {
		super();
		this.data = data;
		this.msg = msg;
		this.code = code;
	}
	
	public MResultVO(MResultInfo errorcode, T data) {
		super();
		this.data = data;
		this.code = errorcode.code;
		this.msg = errorcode.message;
	}
	
	public MResultVO(MResultInfo errorcode) {
		super();
		this.code = errorcode.code;
		this.msg = errorcode.message;
	}
	
	public MResultVO(MobileException me, T data) {
		super();
		this.data = data;
		this.code = me.getErrorCode();
		this.msg = me.getMessage();
	}
	
	public MResultVO(MobileException me) {
		super();
		this.code = me.getErrorCode();
		this.msg = me.getMessage();
	}
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public boolean success(){
		if(StringUtil.equals(this.code, "0"))return true;
		return false;
	}
}
