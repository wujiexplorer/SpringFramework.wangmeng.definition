package com.lx.benefits.dao.memberdetail;


import com.lx.benefits.bean.entity.memberdetail.MemberDetail;

import java.util.List;

/**
 * @author unknow on 2019-01-06 01:38.
 */
public interface MemberDetailDao {
    int batchInsert(List<MemberDetail> memberDetailList);
}
