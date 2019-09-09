package com.lx.benefits.bean.dto.wechat;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;


@Data
public class Code2SessionDTO {
    @JSONField(name = "errcode")
    private String errCode;

    @JSONField(name = "errmsg")
    private String errMsg;

    @JSONField(name = "openid")
    private String openId;

    @JSONField(name = "session_key")
    private String sessionKey;

    @JSONField(name = "unionid")
    private String unionId;
}
