package com.lx.benefits.bean.dto.mem;


import java.io.Serializable;

/**
 * Created by sxy on 2017/10/13.
 */
public class AliMessageReturn implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 4797775025824348890L;

		private String code;
		private String message;
		private String requestId;
		private String bizId;
		private String content;
		private String errCode;
		private String outId;
		private String phoneNum;
		private String receiveDate;
		private String sendDate;
		private Long sendStatus;
		private String template;
		private String totalCount;
		
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public String getRequestId() {
			return requestId;
		}
		public void setRequestId(String requestId) {
			this.requestId = requestId;
		}
		public String getBizId() {
			return bizId;
		}
		public void setBizId(String bizId) {
			this.bizId = bizId;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public String getErrCode() {
			return errCode;
		}
		public void setErrCode(String errCode) {
			this.errCode = errCode;
		}
		public String getOutId() {
			return outId;
		}
		public void setOutId(String outId) {
			this.outId = outId;
		}
		public String getPhoneNum() {
			return phoneNum;
		}
		public void setPhoneNum(String phoneNum) {
			this.phoneNum = phoneNum;
		}
		public String getReceiveDate() {
			return receiveDate;
		}
		public void setReceiveDate(String receiveDate) {
			this.receiveDate = receiveDate;
		}
		public String getSendDate() {
			return sendDate;
		}
		public void setSendDate(String sendDate) {
			this.sendDate = sendDate;
		}
		public Long getSendStatus() {
			return sendStatus;
		}
		public void setSendStatus(Long sendStatus) {
			this.sendStatus = sendStatus;
		}
		public String getTemplate() {
			return template;
		}
		public void setTemplate(String template) {
			this.template = template;
		}
		public String getTotalCount() {
			return totalCount;
		}
		public void setTotalCount(String totalCount) {
			this.totalCount = totalCount;
		}
		
		
		
}
