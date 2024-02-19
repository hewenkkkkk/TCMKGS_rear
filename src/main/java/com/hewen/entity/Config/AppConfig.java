package com.hewen.entity.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("com.hewen.entity.Config.AppConfig")
public class AppConfig {

    @Value("${aliyun.oss.file.endPoint}")
    public String endpoint;

    @Value("${aliyun.oss.keyid}")
    public String accessKeyId;

    @Value("${aliyun.oss.keysecret}")
    public String accessKeySecret;

    @Value("${aliyun.oss.file.bucketname}")
    public String bucketName;

    @Value("${tencent.sms.SecretId}")
    public String tenXunSmsSecretId;

    @Value("${tencent.sms.SecretKey}")
    public String tenXunSmsSecretKey;

    @Value("${tencent.sms.appid}")
    public String tenXunSmsAppId;

    @Value("${tencent.sms.signName}")
    public String tenXunSmsSignName;

    @Value("${tencent.sms.templateId}")
    public String tenXunSmsTemplateId;

    public String getTenXunSmsTemplateId() {
        return tenXunSmsTemplateId;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public String getBucketName() {
        return bucketName;
    }

    public String getTenXunSmsSecretId() {
        return tenXunSmsSecretId;
    }

    public String getTenXunSmsSecretKey() {
        return tenXunSmsSecretKey;
    }

    public String getTenXunSmsAppId() {
        return tenXunSmsAppId;
    }

    public String getTenXunSmsSignName() {
        return tenXunSmsSignName;
    }
}
