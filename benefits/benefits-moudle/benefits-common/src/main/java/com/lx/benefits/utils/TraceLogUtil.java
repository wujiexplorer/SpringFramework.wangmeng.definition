package com.lx.benefits.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;


public class TraceLogUtil {

    /**
     * 日志追踪ID
     */
    public static final String TRACE_LOG_ID = "traceLogId";

    private TraceLogUtil() {
    }

    public static void startTrace() {
        setTraceLogId("");
    }

    public static void stopTrace() {
        MDC.clear();
    }

    /**
     * 设置日志追踪ID
     *
     * @param traceLogId 日志ID
     */
    public static void setTraceLogId(String traceLogId) {
        if (StringUtils.isBlank(traceLogId)) {
            traceLogId = createTraceLogId();
        }
        recordMDC(TRACE_LOG_ID, traceLogId);
    }

    /**
     * 获取日志追踪ID
     */
    public static String getTraceLogId() {
        return MDC.get(TRACE_LOG_ID);
    }

    public static String createTraceLogId() {
        return Long.toString(System.currentTimeMillis());
    }

    public static void recordMDC(String key, String value) {
        MDC.put(key, value);
    }

}
