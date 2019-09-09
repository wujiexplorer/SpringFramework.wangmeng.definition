package com.lx.benefits.web.util;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
public class LocalIpUtil {
    private static String localHost = "";

    private LocalIpUtil() {

    }

    public static String getLocalHost() {
        if (localHost.equals("")) {
            try {
                localHost = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                log.error("获取本地IP失败");
            }
        }
        return localHost;
    }
}
