package com.lx.benefits.bean.dto.mail;


import lombok.Data;

import java.util.List;

/**
 * @author unknow on 2018-12-09 23:54.
 */
@Data
public class SendMailDto {
    /**
     * 邮箱
     */
    private String email;
    /**
     * 企业id
     */
    private Long enterprId;
    /**
     * 活动id
     */
    private Long campaignId;
    /**
     * 邮件标题
     */
    private String title;
    /**
     * 邮件内容
     */
    private String body;
}
