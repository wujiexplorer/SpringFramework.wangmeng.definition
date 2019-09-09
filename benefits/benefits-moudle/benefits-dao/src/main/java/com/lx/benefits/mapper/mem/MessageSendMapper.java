package com.lx.benefits.mapper.mem;

import com.lx.benefits.bean.entity.mem.MessageSend;
import java.util.List;
import java.util.Map;

public interface MessageSendMapper  {
	
	List<MessageSend> selectDynamicPageQueryWithLike(MessageSend sms);

	Long selectCountWithLike(MessageSend sms);

	Integer insert(MessageSend sms);

	List<MessageSend> queryByParam(Map<String, Object> params);

	Integer updateById(MessageSend messageSend);

	MessageSend queryById(Long id);



}
