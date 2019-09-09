package com.lx.benefits.bean.dto.admin.campaign;

import lombok.Data;

/**
 * @author by yingcai on 2018/12/27.
 */
@Data
public class ImportCampainReqDto {

    /**
     * 企业id
     */
    private Long enterpriseId;

    /**
     * 节日礼金id
     */
    private Long campaignId;

    private String filePath;

    private String creditListFile;
}
