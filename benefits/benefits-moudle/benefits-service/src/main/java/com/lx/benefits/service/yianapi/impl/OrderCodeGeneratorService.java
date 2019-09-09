package com.lx.benefits.service.yianapi.impl;

import com.lx.benefits.bean.constants.Constant;
import com.lx.benefits.bean.enums.OrderCodeType;
import com.lx.benefits.service.yianapi.IOrderCodeGeneratorService;
import com.lx.benefits.utils.JedisDBUtil;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;



/**
 * 订单号生成器
 *
 * @author szy
 * @version 0.0.1
 */
@Service
public class OrderCodeGeneratorService implements IOrderCodeGeneratorService {

    /** 父订单号索引值 */
    String ORDER_CODE_INDEX = "so_c_idx_p";
    /** 子订单号索引值 */
    String SUB_CODE_INDEX = "so_c_idx_s";

    private static final Logger log = LoggerFactory.getLogger(OrderCodeGeneratorService.class);

    private static final String DATE_PATTERN = "yyMMdd";
    /**
     * 索引字符串长度
     */
    private static final Integer INDEX_STRING_LENGTH = 8;
    /**
     * 编号长度
     */
    private static final Integer CODE_LENGTH = 20;

    private static final Long MAX_CODE = 10000000L;

    @Autowired
    private JedisDBUtil jedisDBUtil;

    @Value("${isTest}")
    private String isTest;

    public Long generate(OrderCodeType type) {
        String dateStr = dateString();
        String indexStr = indexString(type);

        StringBuilder sb = new StringBuilder(CODE_LENGTH);
        switch (type) {
            case PARENT:
                sb.append(Constant.DOCUMENT_TYPE.SO_ORDER.code);
                break;
            case SON:
                sb.append(Constant.DOCUMENT_TYPE.SO_SUB_ORDER.code);
                break;
            case DSS_PAY:
                sb.append(Constant.DOCUMENT_TYPE.DSS_PAY.code);
                break;
            case SEAGOOR_PAY:
                sb.append(Constant.DOCUMENT_TYPE.SEAGOOR_PAY.code);
                break;
            case SEAGOOR_PAY_REFUND:
                sb.append(Constant.DOCUMENT_TYPE.SEAGOOR_PAY_REFUND.code);
                break;
            case MEMEBER_PAY:
                sb.append(Constant.DOCUMENT_TYPE.MEMBER_PAY.code);
                break;
            case POINT_RECHARGE:
            	sb.append(Constant.DOCUMENT_TYPE.POINT_RECHARGE.code);
                break;
            case CLIENT_ORDER:
                sb.append(Constant.DOCUMENT_TYPE.CLIENT_ORDER.code);
                break;
            case CLIENT_ORDER_REFUND:
                sb.append(Constant.DOCUMENT_TYPE.CLIENT_ORDER_REFUND.code);
                break;

            default:
                throw new IllegalArgumentException("未知的订单编号类型");
        }

        return Long.valueOf(sb.append(dateStr).append(indexStr).toString());
    }

    /**
     * 日期字符串
     *
     * @return
     */
    private String dateString() {
        Calendar currentTime = Calendar.getInstance();
        if (StringUtils.isNoneBlank(isTest) && (isTest.equalsIgnoreCase("YES") || isTest.equalsIgnoreCase("Y"))) {
            currentTime.set(Calendar.YEAR, currentTime.get(Calendar.YEAR) - 16);
        }
        return new SimpleDateFormat(DATE_PATTERN).format(currentTime.getTime());
    }

    /**
     * 自增码
     *
     * @return
     */
    public String indexString(OrderCodeType type) {
        long currentRandom = Math.abs(System.nanoTime() % 10);
        Long index = jedisDBUtil.incr((OrderCodeType.PARENT.equals(type) ? ORDER_CODE_INDEX : SUB_CODE_INDEX) + currentRandom);
        if (null == index) {
            log.error("生成订单编号异常：redis服务器获取自增值为空");
            index = System.currentTimeMillis();
        }
        if (currentRandom % 2 == 0) {
            index = MAX_CODE - 1 - index % MAX_CODE;
        }
        String idxStr = index.toString();
        int len = idxStr.length();
        StringBuilder sb = new StringBuilder(idxStr);
        if (len < INDEX_STRING_LENGTH) {
            return currentRandom + String.format("%07d", index);
        } else {
            return currentRandom + sb.delete(0, sb.length() - INDEX_STRING_LENGTH).toString();
        }
    }
}
