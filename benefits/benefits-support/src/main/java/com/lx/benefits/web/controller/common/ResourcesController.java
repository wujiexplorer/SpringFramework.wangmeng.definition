package com.lx.benefits.web.controller.common;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lx.benefits.bean.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author unknow on 2018-12-09 23:00.
 */
@Api(tags = "福粒平台-公共资源模块")
@RestController
@RequestMapping("/common/resources")
public class ResourcesController {

    @ApiOperation(value = "欢迎来到王者荣耀", response = JSONObject.class)
    @RequestMapping(value = "/helloworld", method = RequestMethod.GET)
    public JSONObject helloWorld() {

        JSONObject jsonObject = new JSONObject();

        String testStr = "enterprise12345678";
        jsonObject.put("testStr", testStr);
        jsonObject.put("md5", EncryptUtil.encryptByMd5(testStr));
        jsonObject.put("sha1", EncryptUtil.encryptBySha1(testStr));
        String aaaa = EncryptUtil.encodeBase62(testStr);
        jsonObject.put("encodeBase62", aaaa);
        String bbbb = EncryptUtil.decodeBase62(aaaa);
        jsonObject.put("decodeBase62", bbbb);
        jsonObject.put("decodeBase62_success", testStr.equals(bbbb));

        String aesEncrypt = EncryptUtil.aesEncrypt(testStr, EncryptUtil.encryptByMd5(testStr));
        jsonObject.put("aesEncrypt", aesEncrypt);
        String aesDecrypt = EncryptUtil.aesDecrypt(aesEncrypt, EncryptUtil.encryptByMd5(testStr));
        jsonObject.put("aesDecrypt", aesDecrypt);
        jsonObject.put("aesDecrypt_success", testStr.equals(aesDecrypt));

        SessionEnterpriseInfo sessionEnterpriseInfo = new SessionEnterpriseInfo();
        sessionEnterpriseInfo.setLoginName(testStr);
        sessionEnterpriseInfo.setEnterprId(12345678L);
        jsonObject.put("uuid", UUID.randomUUID().toString());
        String tokenSecret = EncryptUtil.encryptByMd5(UUID.randomUUID().toString());
        jsonObject.put("encodeToken_secret", tokenSecret);
        String token = EncryptUtil.aesEncrypt(JsonUtils.getJsonString(sessionEnterpriseInfo), tokenSecret);
        jsonObject.put("encodeToken", token);
        String viewToken = EncryptUtil.encodeToken(token, tokenSecret);
        jsonObject.put("encodeViewToken", viewToken);
        SessionTokenInfo sessionTokenInfo = EncryptUtil.decodeToken(viewToken);
        jsonObject.put("decodeToken_token", sessionTokenInfo.getToken());
        jsonObject.put("decodeToken_secret", sessionTokenInfo.getSecret());
        jsonObject.put("decodeToken_success", token.equals(sessionTokenInfo.getToken()) && tokenSecret.equals(sessionTokenInfo.getSecret()));
        SessionEnterpriseInfo info = JsonUtils.getObj(EncryptUtil.aesDecrypt(sessionTokenInfo.getToken(), sessionTokenInfo.getSecret()), new TypeReference<SessionEnterpriseInfo>() {
        });
        jsonObject.put("sessionEnterpriseInfo", info);
        return Response.succ(jsonObject);
    }
}
