package com.lx.benefits.service.yianapi;

import com.lx.benefits.bean.base.dto.ResultInfo;
import com.lx.benefits.bean.dto.mem.ClientUnionCode;
import com.lx.benefits.bean.entity.memunioninfo.MemUnionInfo;

/**
 * Created by softw on 2019/3/1.
 */
public interface ClientUnionCodeService {

    ClientUnionCode queryByClientUnionCode(ClientUnionCode info);

    ResultInfo<MemUnionInfo>  getUnoinInfo(Long userId);

    ResultInfo<ClientUnionCode> code(Long memberId);

}
