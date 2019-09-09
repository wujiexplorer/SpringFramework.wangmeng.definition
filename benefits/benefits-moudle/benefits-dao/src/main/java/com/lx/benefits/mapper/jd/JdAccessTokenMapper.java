package com.lx.benefits.mapper.jd;


import com.lx.benefits.bean.dto.jd.token.JdAccessToken;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JdAccessTokenMapper {

    JdAccessToken getToken();

    int insert(JdAccessToken record);
}
