package com.lx.benefits.sdk.geetest;

import lombok.Data;

@Data
public class GeetestResult {

	private boolean isSuccess;
	private String challenge;
	private String geetestId;

	public GeetestResult(boolean isSuccess, String challenge, String geetestId) {
		this.isSuccess = isSuccess;
		this.challenge = challenge;
		this.geetestId = geetestId;
	}

	public GeetestResult(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

}
