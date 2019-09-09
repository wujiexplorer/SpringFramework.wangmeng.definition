package com.lx.benefits.bean.util;

import com.google.common.base.Joiner;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.FetchRet;
import com.qiniu.storage.model.FileListing;
import com.qiniu.util.Auth;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author by yingcai on 2017/2/14.
 */
@Component
public class QiniuUtils {


    private static Logger logger = LoggerFactory.getLogger(QiniuUtils.class);

    private final static String[] SUFFIX_ARRAY = new String[]{".jpg", ".jpeg", ".gif", ".png", ".bmp", ".js", ".css", ".less",".mp4", ".csv", ".xls", ".xlsx"};

    private UploadManager uploadManager;
    private BucketManager bucketManager;
    private Auth auth;

    //要上传的空间
    @Value("${qiniu.bucket}")
    public String BUCKET_NAME;
    @Value("${qiniu.path}")
    public String QINIU_URL;


    public QiniuUtils(@Value("${qiniu.access.key}") String ACCESS_KEY, @Value("${qiniu.secret.key}") String SECRET_KEY) {
        this.auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        Zone z = Zone.autoZone();
        Configuration c = new Configuration(z);
        this.uploadManager = new UploadManager(c);
        this.bucketManager = new BucketManager(auth, c);
    }

    public String upload(MultipartFile file) throws IOException {
        try {
            String fileName = StringUtils.lowerCase(file.getOriginalFilename());
            if (!StringUtils.endsWithAny(fileName, SUFFIX_ARRAY)) {
                throw new RuntimeException("文件格式错误");
            }
            String[] fileNameArray = StringUtils.split(fileName, ".");
            String suffix = fileNameArray[fileNameArray.length - 1];
            fileName = Joiner.on(".").join(DateUtil.getNowTimestamp13(), suffix);

            //调用put方法上传
            com.qiniu.http.Response res = uploadManager.put(file.getBytes(), fileName, auth.uploadToken(BUCKET_NAME));

            //打印返回的信息
            logger.info(res.bodyString());
            return QINIU_URL + "/" + fileName;
        } catch (QiniuException e) {
            com.qiniu.http.Response r = e.response;
            // 请求失败时打印的异常的信息
            logger.error(r.toString());
            try {
                //响应的文本信息
                logger.error(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
                logger.error(e1.getMessage(), e1);
            }
        }
        return null;
    }

    public String convert(String url,String bucket) {
        try {
            //调用put方法上传
            com.qiniu.http.Response response = bucketManager.changeType(bucket, url, BucketManager.StorageType.INFREQUENCY);
            response.close();
            return null;
        } catch (QiniuException e) {
        }
        return null;
    }


    public FileListing getAllString(String bucket, String marker) {
        try {

            //调用put方法上传
            FileListing response = bucketManager.listFiles(bucket, "", marker, 100, "");

            return response;
        } catch (QiniuException e) {
        }
        return null;
    }


    public String upload(String originalFilename, InputStream inputStream) throws IOException {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buff = new byte[1024]; //buff用于存放循环读取的临时数据
        int rc = 0;
        while ((rc = inputStream.read(buff)) != -1) {
            outputStream.write(buff, 0, rc);
        }
        outputStream.flush();
        outputStream.close();
        byte[] info = outputStream.toByteArray();

        try {
            String fileName = StringUtils.lowerCase(originalFilename);
            if (!StringUtils.endsWithAny(fileName, SUFFIX_ARRAY)) {
                throw new RuntimeException("文件格式错误");
            }
            String[] fileNameArray = StringUtils.split(fileName, ".");
            String suffix = fileNameArray[fileNameArray.length - 1];
            fileName = Joiner.on(".").join(DateUtil.getNowTimestamp13(), suffix);

            //调用put方法上传
            com.qiniu.http.Response res = uploadManager.put(info, fileName, auth.uploadToken(BUCKET_NAME));

            //打印返回的信息
            logger.info(res.bodyString());
            return QINIU_URL + "/" + fileName;
        } catch (QiniuException e) {
            com.qiniu.http.Response r = e.response;
            // 请求失败时打印的异常的信息
            logger.error(r.toString());
            try {
                //响应的文本信息
                logger.error(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
                logger.error(e1.getMessage(), e1);
            }
        }
        return null;
    }

    public String fetch(String remoteSrcUrl) throws IOException {
        int i = 0;
        //抓取网络资源到空间
        while (i <= 3) {
            try {
                FetchRet fetchRet = bucketManager.fetch(remoteSrcUrl, BUCKET_NAME);
                i = 4;
                return QINIU_URL + "/" + fetchRet.key;
            } catch (QiniuException ex) {
                i++;
                logger.error("url: {}", remoteSrcUrl, ex.response.toString());
            }
        }
        return "";
    }

    /**
     * 下载七牛文件系统中的excel文件并解析成list数据返回
     * @param filePath   filePath文件路径
     * @param <T>  返回的数据类型
     * @return   返回的数据类型
     */
    public <T> List<T> downloadExcelFile(String filePath) {
        List<T> resultList = new ArrayList<>();
        
        return resultList;
    }

}
