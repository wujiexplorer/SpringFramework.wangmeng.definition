package com.wangmeng.itree.common.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author idea
 * @date 2019/4/28
 * @Version V1.0
 */
public class ITreeConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(ITreeConfig.class);

    public static String INDEX_PAGE = "page/index.html";

    public static boolean INDEX_CHANGE=false;

    public static String NOT_FOUND_PAGE = "page/404_page.html";

    public static boolean NOT_FOUND_CHANGE=false;

    public static String UNKOWN_EXCEPTION_PAGE = "page/404_page.html";

    public static boolean UNKOWN_EXCEPTION_CHANGE=false;

    public static int PORT = 8080;

    private static final String DEFAULT_LOCALTION = "/config/itree-config.properties";

    private static final String INDEX_PAGE_KEY = "index.page";

    private static final String NOT_FOUND_PAGE_KEY = "not.found.page";

    private static final String UNKOWN_EXCEPTION_PAGE_KEY = "unkown.exception.page";

    private static final String PORT_KEY = "server.port";


    //公共应用类
    public static Class APPLICATION_CLASS;


    public static void init() {
        Properties properties = new Properties();
        try {
            InputStream in = APPLICATION_CLASS.getResourceAsStream("D:/code/SpringFramework.wangmeng.definition/extHttpServer/src/main/resources/config/itree-config.properties");
            if (in != null) {
                properties.load(in);
                if (properties.getProperty(INDEX_PAGE_KEY) != null) {
                    INDEX_PAGE = properties.getProperty(INDEX_PAGE_KEY);
                    INDEX_CHANGE=true;
                }
                if (properties.getProperty(NOT_FOUND_PAGE_KEY) != null) {
                    NOT_FOUND_PAGE = properties.getProperty(NOT_FOUND_PAGE_KEY);
                    NOT_FOUND_CHANGE=true;
                }

                if (properties.getProperty(UNKOWN_EXCEPTION_PAGE_KEY) != null) {
                    UNKOWN_EXCEPTION_PAGE = properties.getProperty(UNKOWN_EXCEPTION_PAGE_KEY);
                    UNKOWN_EXCEPTION_CHANGE=true;
                }

                if (properties.getProperty(PORT_KEY) != null) {
                    PORT = Integer.parseInt(properties.getProperty(PORT_KEY));
                }
            }

            System.out.println("[ITreeConfig] server初始化静态资源文件");
            System.out.println("[ITreeConfig] port: " + PORT + ", index_page: " + INDEX_PAGE + ",error_page: " + UNKOWN_EXCEPTION_PAGE);
        } catch (IOException e) {
            LOGGER.error("[ITreeConfig]异常为{}", e);
        }
    }

}
