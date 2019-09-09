package com.lx.benefits.bean.dto.deliverinfo;

import java.util.Date;

public class JingdongTokenResult {
	private Boolean success;
	private JingdongToken result;

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public JingdongToken getResult() {
		return result;
	}

	public void setResult(JingdongToken result) {
		this.result = result;
	}

	public static class JingdongToken {
		private String uid;
		private Date refresh_token_expires;// refresh_token的过期时间，秒级别
		private Date time;// 当前时间
		private Long expires_in;// Access_token的过期时间，秒级别,有效期24小时
		private String refresh_token;
		private String access_token;

		public String getUid() {
			return uid;
		}

		public void setUid(String uid) {
			this.uid = uid;
		}

		public Date getRefresh_token_expires() {
			return refresh_token_expires;
		}

		public void setRefresh_token_expires(Date refresh_token_expires) {
			this.refresh_token_expires = refresh_token_expires;
		}

		public Date getTime() {
			return time;
		}

		public void setTime(Date time) {
			this.time = time;
		}

		public Long getExpires_in() {
			return expires_in;
		}

		public void setExpires_in(Long expires_in) {
			this.expires_in = expires_in;
		}

		public String getRefresh_token() {
			return refresh_token;
		}

		public void setRefresh_token(String refresh_token) {
			this.refresh_token = refresh_token;
		}

		public String getAccess_token() {
			return access_token;
		}

		public void setAccess_token(String access_token) {
			this.access_token = access_token;
		}

	}
}
