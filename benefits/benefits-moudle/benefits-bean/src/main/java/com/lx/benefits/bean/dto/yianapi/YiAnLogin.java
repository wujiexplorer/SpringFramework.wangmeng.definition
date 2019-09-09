package com.lx.benefits.bean.dto.yianapi;


import com.lx.benefits.bean.base.dto.BaseQuery;
import lombok.Data;

/**
 * Created by lidongri on 2018/11/15.
 */
@Data
public class YiAnLogin  extends BaseQuery {

    private static final long serialVersionUID = -5264970182198217891L;
    
    private String code;
    /**
     * 小程序code
     */
    private String wxcode;


}
