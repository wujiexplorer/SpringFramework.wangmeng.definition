package com.lx.benefits.mapper.client;

import com.lx.benefits.bean.entity.client.ClientMessage;
import com.lx.benefits.bean.entity.client.ClientMessageCondition;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClientMessageMapper {
    long countByExample(ClientMessageCondition example);

    int deleteByExample(ClientMessageCondition example);

    int deleteByPrimaryKey(String messageId);

    int insert(ClientMessage record);

    int insertSelective(ClientMessage record);

    List<ClientMessage> selectByExample(ClientMessageCondition example);

    ClientMessage selectByPrimaryKey(String messageId);

    int updateByExampleSelective(@Param("record") ClientMessage record, @Param("example") ClientMessageCondition example);

    int updateByExample(@Param("record") ClientMessage record, @Param("example") ClientMessageCondition example);

    int updateByPrimaryKeySelective(ClientMessage record);

    int updateByPrimaryKey(ClientMessage record);

	int udateMessageStatus(@Param("messageId")String messageId, @Param("isSuccess")Boolean isSuccess);
}