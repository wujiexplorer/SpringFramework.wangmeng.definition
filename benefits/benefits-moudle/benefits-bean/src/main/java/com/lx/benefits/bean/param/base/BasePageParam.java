package com.lx.benefits.bean.param.base;

import com.apollo.common.exception.ArgumentException;
import com.apollo.common.response.enums.CommonMsgEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class BasePageParam implements Serializable{

    private static final long serialVersionUID = 6241910691765425606L;
    private int page = 1;
    private int pageSize = 20;

    public int getPage() {
        return this.page <= 0 ? 1 : this.page;
    }

    public int getPageSize() {
        if(this.pageSize > 20){
            throw new ArgumentException(CommonMsgEnum.ILLEGAL_ARGUMENT.getCodeMsg());
        }
        return this.pageSize;
    }

    public Integer getStartRecord(){
        return (getPage()-1)*getPageSize();
    }

}
