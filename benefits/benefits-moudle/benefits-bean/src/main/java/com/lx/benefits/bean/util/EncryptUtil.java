package com.lx.benefits.bean.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.security.SecureRandom;
import java.security.Security;
import java.util.UUID;


/**
 * @author unknow on 2018-12-04 00:45.
 */
public class EncryptUtil {

    private static final String BASE62 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS7Padding";//默认的加密算法
    private static SecureRandom secureRandom = new SecureRandom();
    private final static Logger logger = LoggerFactory.getLogger(EncryptUtil.class);
    static {
        try {
            Security.addProvider(new BouncyCastleProvider());
        } catch (Exception e) {
            logger.error("初始化加密模块错误", e);
        }
    }

    /**
     * md5 加密方法
     * @param inputStr 入参字符串
     * @return md5后的字符串
     */
    public static String encryptByMd5(String inputStr) {
        if (null == inputStr || inputStr.isEmpty()) {
            return inputStr;
        }
        return DigestUtils.md5Hex(inputStr);
    }

    /**
     * sha1 加密方法
     * @param inputStr 入参字符串
     * @return sha1后的字符串
     */
    public static String encryptBySha1(String inputStr) {
        if (null == inputStr || inputStr.isEmpty()) {
            return inputStr;
        }
        return DigestUtils.sha1Hex(inputStr);
    }

    /**
     * 通过base62 压缩字符串
     * @param inputStr  压缩前的字符串
     * @return  压缩后的字符串
     */
    public static String encodeBase62(String inputStr) {
        if (null == inputStr || inputStr.length() == 0) {
            return inputStr;
        }
        
        try {
            byte[] input = inputStr.getBytes("UTF-8");
            int len = input.length;

            int iVal = 0;
            int iPos = 0;
            StringBuilder sb = new StringBuilder(4096);

            for (int i = 0; i < len; i++) {
                iVal = (iVal << 8) | (input[i] & 0xff);
                iPos += 8;
                while (iPos > 5) {
                    char c = BASE62.charAt(iVal >> (iPos -= 6));
                    sb.append((c == 'i') ? "ia" : ((c == '+') ? "ib" : (c == '/' ? "ic" : String.valueOf(c))));
                    iVal &= ((1 << iPos) - 1);
                }
            }

            if (iPos > 0) {
                char c = BASE62.charAt(iVal << (6 - iPos));
                sb.append((c == 'i') ? "ia" : ((c == '+') ? "ib" : (c == '/' ? "ic" : String.valueOf(c))));
            }
            return sb.toString();
        } catch (Exception e) {
            logger.error("encodeBase62 exec exception, req={}", inputStr, e);
        }
        return inputStr;
    }
    
    /**
     * 通过base62 解压字符串
     * @param inputStr 压缩编码后的字符串
     * @return 解压后的字符串
     */
    public static String decodeBase62(String inputStr) {
        if (null == inputStr || inputStr.length() == 0 ) {
            return inputStr;
        }
        ByteArrayOutputStream stream = null;
        try {
            byte[] inputStrBytes = inputStr.getBytes("UTF-8");
            int iLen = inputStrBytes.length;
            stream = new ByteArrayOutputStream(inputStrBytes.length);
            int idx = 0;
            int iPos = 0;
            int iVal = 0;
            for (int i = 0; i < iLen; i++) {
                char c = inputStr.charAt(i);
                if (c == 'i') {
                    c = inputStr.charAt(++i);
                    c = (c == 'a') ? 'i' : ((c == 'b') ? '+' : ((c == 'c') ? '/' : inputStr.charAt(--i)));
                }
                iVal = (iVal << 6) | BASE62.indexOf(c);
                iPos += 6;
                while (iPos  >7) {
                    byte ch = (byte) (iVal >> (iPos -= 8));
                    stream.write(ch);
                    iVal &= ((1 << iPos) - 1);
                }
            }
            return new String(stream.toByteArray(), "UTF-8");
        } catch (Exception e) {
            logger.error("decodeBase62 exec exception, req={}", inputStr, e);
        } finally {
            try {
                if (null != stream) {
                    stream.close();   
                }
            } catch (Exception ex) {
                logger.error("decodeBase62 exec stream close exception, req={}", inputStr, ex);
            }
            
        }
        return null;
    }

    /**
     * AES 加密操作
     *
     * @param content 待加密内容
     * @param password 加密密码
     * @return 返回Base62转码后的加密数据
     */
    public static String aesEncrypt(String content, String password) {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM, "BC");// 创建密码器
            byte[] byteContent = content.getBytes("UTF-8");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(password.getBytes("UTF-8"), KEY_ALGORITHM));// 初始化为加密模式的密码器
            byte[] result = cipher.doFinal(byteContent);// 加密
            return encodeBase62(Base64.encodeBase64String(result));//通过Base62转码返回
        } catch (Exception ex) {
            logger.error("encrypt exception!", ex);
        }
        return null;
    }

    /**
     * AES 解密操作
     *
     * @param content 待解密内容
     * @param password 解密密码
     * @return 解密后的字符串
     */
    public static String aesDecrypt(String content, String password) {
        try {
            //实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM, "BC");

            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(password.getBytes("UTF-8"), KEY_ALGORITHM));

            //执行操作
            byte[] result = cipher.doFinal(Base64.decodeBase64(decodeBase62(content)));

            return new String(result, "UTF-8");
        } catch (Exception ex) {
            logger.error("decrypt exception!", ex);
        }
        return null;
    }

    /**
     * 对透传给前端的token信息encode
     * @param token  token
     * @param secret   secret
     * @return  encode后的token信息
     */
    public static String encodeToken(String token, String secret) {
        StringBuilder sb = new StringBuilder();
        char[] tokenArr = token.toCharArray();
        char[] secretArr = secret.toCharArray();
        int index = 0;
        for (char item: tokenArr) {
            if (index >= 6 && index - 6 < 32) {
                if (index % 2 == 0) {
                    sb.append(String.valueOf(secretArr[index - 6]).toUpperCase());
                } else {
                    sb.append(secretArr[index - 6]);
                }
            }
            sb.append(item);
            index++;
        }
        return sb.toString();
    }

    /**
     * 对前端传入的token信息进行decode
     * @param token  token
     * @return decode后的token信息
     */
    public static SessionTokenInfo decodeToken(String token) {
        if (null == token || token.length() < 70) {
            return null;
        }
        SessionTokenInfo sessionTokenInfo = new SessionTokenInfo();
        StringBuilder tsb = new StringBuilder();
        StringBuilder ssb = new StringBuilder();
        char[] tokenArr = token.toCharArray();
        int index = 0;
        for (char item: tokenArr) {
            if (index >= 6) {
                if (index % 2 == 0 && ssb.length() < 32) {
                    ssb.append(item);
                } else {
                    tsb.append(item);
                }
            } else {
                tsb.append(item);
            }
            index++;
        }
        sessionTokenInfo.setSecret(ssb.toString().toLowerCase());
        sessionTokenInfo.setToken(tsb.toString());
        return sessionTokenInfo;
    }
    
    /**
     * 密码生成规则
     * @param inputStr   用户输入的密码
     * @param secret     系统生成的secret码
     * @return  生成好的密码
     */
    public static String encodePassword(String inputStr, String secret) {
        StringBuilder sb = new StringBuilder(secret);
        return encryptBySha1(String.format("%s_%s", inputStr, encryptBySha1(sb.reverse().toString())));
    }

    /**
     * 生成yian用户salt
     * @return String
     */
    public static String getYianSalt() {
        byte[] bytes = new byte[16];
        secureRandom.nextBytes(bytes);
        String hexString = Hex.encodeHexString(bytes);
        try {
            return encryptBySha1(hexString);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
    
    /**
     * 生成yian用户密码
     * @param password   password
     * @param salt       salt
     * @return           String
     */
    public static String encodeYianPassword(String password, String salt) {
        return Hex.encodeHexString(DigestUtils.md5(password+salt));
    }

    /**
     * 生成用于混淆密码的secret
     * @return   生成好的secret码
     */
    public static String generateSecret() {
        return encryptBySha1(String.format("%s_%s", UUID.randomUUID().toString(), DateUtil.getMillisecondStr()));
    }
    
    public static void main(String[] args) {
        //supplier001
        String testStr = "supplier12345678";
        System.out.println(String.format("testStr=%s", testStr));
        System.out.println(String.format("md5=%s", encryptByMd5("supplier001")));
        System.out.println(String.format("sha1=%s", encryptBySha1(testStr)));
        String aaaa = encodeBase62(testStr);
        System.out.println(String.format("encodeBase62=%s", aaaa));
        String bbbb = decodeBase62(aaaa);
        System.out.println(String.format("decodeBase62=%s", bbbb));
        System.out.println(String.format("decodeBase62 success=%s", testStr.equals(bbbb)));
        
        String aesEncrypt = aesEncrypt(testStr, testStr);
        System.out.println(String.format("aesEncrypt=%s", aesEncrypt));
        String aesDecrypt = aesDecrypt(aesEncrypt, testStr);
        System.out.println(String.format("aesDecrypt=%s", aesDecrypt));
        System.out.println(String.format("aesDecrypt success=%s", testStr.equals(aesDecrypt)));
        String encodeToken = encodeToken("bWpFV3ROd2UrNnVkNkl5RVhqV3l6MXV4ODZ3QzdyZHYvQU53WEIwU2xwOD0", "e1cf90e510e5528781374698c7d0206acd3e7543");
        System.out.println(String.format("encodeToken=%s", encodeToken));
        SessionTokenInfo sessionTokenInfo = decodeToken(encodeToken);
        System.out.println(String.format("decodeToken.token=%s", sessionTokenInfo.getToken()));
        System.out.println(String.format("decodeToken.secret=%s", sessionTokenInfo.getSecret()));
        System.out.println(String.format("decodeToken success=%s", "e1cf90e510e5528781374698c7d0206acd3e7543".equals(sessionTokenInfo.getSecret()) && "bWpFV3ROd2UrNnVkNkl5RVhqV3l6MXV4ODZ3QzdyZHYvQU53WEIwU2xwOD0".equals(sessionTokenInfo.getToken())));

        System.out.println(String.format("test_admin_pwd=%s", encryptByMd5("test123456")));
        String generateSecret = generateSecret();
        System.out.println(String.format("generateSecret=%s", generateSecret));
        System.out.println(String.format("encodePassword=%s", encodePassword("test123456", generateSecret)));


        System.out.println("modifyTime".replaceAll("([A-Z])", "_$1").toLowerCase());
    }
}