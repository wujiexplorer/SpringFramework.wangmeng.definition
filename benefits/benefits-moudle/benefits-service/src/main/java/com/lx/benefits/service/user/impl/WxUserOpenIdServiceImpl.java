package com.lx.benefits.service.user.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.lx.benefits.bean.entity.user.WxUserOpenId;
import com.lx.benefits.bean.entity.user.WxUserOpenIdCondition;
import com.lx.benefits.bean.enums.DeleteStatus;
import com.lx.benefits.mapper.user.WxUserOpenIdMapper;
import com.lx.benefits.service.user.WxUserOpenIdService;

import lombok.extern.slf4j.Slf4j;

@Service("wxUserOpenIdService")
@Slf4j
public class WxUserOpenIdServiceImpl implements WxUserOpenIdService {

	@Autowired
	private WxUserOpenIdMapper wxUserOpenIdMapper;

	@Override
	public List<WxUserOpenId> getUserByUserId(Long userId) {
		Assert.notNull(userId, "用户ID不能为空!");
		WxUserOpenIdCondition example = new WxUserOpenIdCondition();
		example.createCriteria().andUserIdEqualTo(userId).andIsDeleteEqualTo(DeleteStatus.NO.status);
		return wxUserOpenIdMapper.selectByExample(example);
	}

	@Override
	public int unbindUserByOpenId(String wxOpenId) {
		Assert.hasText(wxOpenId, "微信openId不能为空!");
		WxUserOpenIdCondition example = new WxUserOpenIdCondition();
		example.createCriteria().andOpenIdEqualTo(wxOpenId).andIsDeleteEqualTo(DeleteStatus.NO.status);
		WxUserOpenId record = new WxUserOpenId();
		record.setIsDelete(DeleteStatus.YES.status);
		record.setUpdateTime(new Date());
		return wxUserOpenIdMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int unbindUserByUserId(Long userId) {
		Assert.notNull(userId, "用户ID不能为空!");
		WxUserOpenIdCondition example = new WxUserOpenIdCondition();
		example.createCriteria().andUserIdEqualTo(userId).andIsDeleteEqualTo(DeleteStatus.NO.status);
		WxUserOpenId record = new WxUserOpenId();
		record.setIsDelete(DeleteStatus.YES.status);
		record.setUpdateTime(new Date());
		return wxUserOpenIdMapper.updateByExampleSelective(record, example);
	}

	@Override
	public WxUserOpenId getByOpenId(String openId) {
		if (StringUtils.isEmpty(openId)) {
			return null;
		}
		WxUserOpenIdCondition example = new WxUserOpenIdCondition();
		example.createCriteria().andOpenIdEqualTo(openId).andIsDeleteEqualTo(DeleteStatus.NO.status);
		List<WxUserOpenId> wxUsers = wxUserOpenIdMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(wxUsers)) {
			return null;
		}
		WxUserOpenId result = wxUsers.remove(wxUsers.size() - 1);
		if (wxUsers.size() > 0) {// 存在脏数据（一个微信绑定了多个账号，清除）
			for (WxUserOpenId item : wxUsers) {
				WxUserOpenId record = new WxUserOpenId();
				record.setId(item.getId());
				record.setUpdateTime(new Date());
				record.setIsDelete(DeleteStatus.YES.status);
				wxUserOpenIdMapper.updateByPrimaryKeySelective(record);
				log.error("clear the dirty recorder of wexin bindding, the recorder message is {}", JSON.toJSONString(item));
			}
		}
		return result;
	}

	@Transactional
	@Override
	public void bindUser(Long userId, String wxOpenId) {
		Assert.notNull(userId, "用户ID不能为空!");
		Assert.hasText(wxOpenId, "微信openId不能为空!");
		WxUserOpenIdCondition example = new WxUserOpenIdCondition();
		example.createCriteria().andOpenIdEqualTo(wxOpenId).andIsDeleteEqualTo(DeleteStatus.NO.status);
		List<WxUserOpenId> oldUserInfos = wxUserOpenIdMapper.selectByExample(example);
		Date nowTime = new Date();
		if (CollectionUtils.isEmpty(oldUserInfos)) {
			WxUserOpenId record = new WxUserOpenId();
			record.setOpenId(wxOpenId);
			record.setUserId(userId);
			record.setCreateTime(nowTime);
			wxUserOpenIdMapper.insertSelective(record);
			return;
		}
		WxUserOpenId existRecorder = null;
		for (WxUserOpenId item : oldUserInfos) {
			if (wxOpenId.equals(item.getOpenId()) && userId.equals(item.getUserId())) {
				existRecorder = item;
				break;
			}
		}
		if (existRecorder != null) {// 当前微信已经绑定该账号
			oldUserInfos.remove(existRecorder);
		} else {
			WxUserOpenId oldRecorder = oldUserInfos.remove(0);
			WxUserOpenId record = new WxUserOpenId();
			record.setId(oldRecorder.getId());
			record.setUserId(userId);
			record.setUpdateTime(nowTime);
			wxUserOpenIdMapper.updateByPrimaryKeySelective(record);
		}
		for (WxUserOpenId item : oldUserInfos) {
			WxUserOpenId record = new WxUserOpenId();
			record.setId(item.getId());
			record.setUpdateTime(nowTime);
			record.setIsDelete(DeleteStatus.YES.status);
			wxUserOpenIdMapper.updateByPrimaryKeySelective(record);
		}
	}

}
