package com.lx.benefits.service.jd;

import com.lx.benefits.bean.dto.jd.res.JDResult;
import com.lx.benefits.bean.dto.jd.token.JDToken;

/**
 * User: fan
 * Date: 2019/02/25
 * Time: 18:26
 */
public interface IJDAccessTokenService {

    String getToken();

    JDResult<JDToken> getJdTokenJDFromJD() throws Exception;

    String freshToken() throws Exception;

    JDResult<JDToken> refreshJdTokenJDFromJD() throws Exception;

}
