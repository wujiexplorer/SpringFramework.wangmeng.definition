package com.lx.benefits.bean.util;

import com.lx.benefits.bean.constants.Constant;
import com.lx.benefits.bean.dto.jd.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ldr on 2016/1/7.
 */
public class ImageUtil {

    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    public static String getImgFullUrl(Constant.IMAGE_URL_TYPE urlType, String key) {
        if (urlType == null || key == null || key.trim().isEmpty()) {
            return key;
        }
        return urlType.url + key;
    }

    public static String getCMSImgFullUrl(String key) {
        return getImgFullUrl(Constant.IMAGE_URL_TYPE.cmsimg, key);
    }

    public static byte[] readImage(String codeUrl) {
        InputStream inStream = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            URL url = new URL(codeUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            inStream = conn.getInputStream();
            byte[] b = new byte[512];
            int len;
            while ((len = inStream.read(b)) != -1) {
                outputStream.write(b, 0, len);
            }
            return outputStream.toByteArray();
        } catch (IOException e) {
            logger.error("read image error={}", e.getMessage());
            throw new ServiceException(e);
        } finally {
            try {
                if (inStream != null) inStream.close();
                if (outputStream != null) outputStream.close();
            } catch (Exception e) {
                logger.error("CLOSE_STEAM_ERROR", e);
            }

        }
    }
}
