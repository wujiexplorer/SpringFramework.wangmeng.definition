package com.lx.benefits.service.yianapi;

import com.lx.benefits.bean.enums.yianapi.model.kuaidi100query.Kuaidi100Line;
import com.lx.benefits.bean.enums.yianapi.model.kuaidi100query.Kuaidi100Result;

public interface IKuaidi100QueryService {

    Kuaidi100Result<Kuaidi100Line> query(String com,String num);
}
