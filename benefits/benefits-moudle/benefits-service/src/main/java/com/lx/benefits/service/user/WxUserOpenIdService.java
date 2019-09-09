package com.lx.benefits.service.user;

import java.util.List;

import com.lx.benefits.bean.entity.user.WxUserOpenId;

public interface WxUserOpenIdService {
	
	
	List<WxUserOpenId> getUserByUserId(Long userId);

	/**
	 * 解绑微信对应的用户
	 * 
	 * @param wxOpenId
	 *            微信openId
	 * @return
	 */
	int unbindUserByOpenId(String wxOpenId);

	/**
	 * 根据用户ID解绑所有的微信信息
	 * 
	 * @param userId
	 *            用户ID
	 * @return
	 */
	int unbindUserByUserId(Long userId);

	/**
	 * 根据微信openID 获取被绑定的用户信息
	 * 
	 * @param wxOpenId
	 *            微信openId
	 * @return
	 */
	WxUserOpenId getByOpenId(String wxOpenId);

	/**
	 * 微信绑定账号（一个账号可以被多个微信账号绑定）
	 * 
	 * @param userId
	 *            用户ID
	 * @param wxOpenId
	 *            微信openId
	 */
	void bindUser(Long userId, String wxOpenId);

}
