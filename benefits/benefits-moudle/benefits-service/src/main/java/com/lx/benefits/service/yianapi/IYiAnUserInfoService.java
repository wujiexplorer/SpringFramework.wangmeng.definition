package com.lx.benefits.service.yianapi;

import com.lx.benefits.bean.enums.yianapi.model.YiAnResult;
import com.lx.benefits.bean.enums.yianapi.model.YiAnUserInfoResult;

public interface IYiAnUserInfoService {

    YiAnResult<YiAnUserInfoResult>  getUserInfo(String code);
}
