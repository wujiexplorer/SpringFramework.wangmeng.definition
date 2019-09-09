package com.lx.benefits.mapper.jd;

import com.lx.benefits.bean.dto.jd.JdMessageLine;
import com.lx.benefits.bean.dto.jd.JdMessageLineReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JdMessageLineMapper {

    JdMessageLine getMessage(@Param("id") Long id);

    Integer batchInsert(List<JdMessageLine> list);

    Integer updateByJdMessageId(JdMessageLine line);

    Integer deleteByObject(JdMessageLine line);

    List<JdMessageLine> getMessageList(JdMessageLineReq req);

    Integer getMessageListCount(JdMessageLineReq req);
}
