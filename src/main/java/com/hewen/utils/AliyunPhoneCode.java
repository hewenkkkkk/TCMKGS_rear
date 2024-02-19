package com.hewen.utils;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.tea.*;
import com.aliyun.dysmsapi20170525.*;
import com.aliyun.dysmsapi20170525.models.*;
import com.aliyun.teaopenapi.*;
import com.aliyun.teaopenapi.models.*;
import org.springframework.beans.factory.annotation.Value;

public class AliyunPhoneCode {

    @Value("${aliyun.oss.keyid}")
    public String accessKeyId;

    @Value("${aliyun.oss.keysecret}")
    public String accessKeySecret;

    public void sendCode(String phone, String code) throws Exception {
        Config config = new Config()
                //这里修改为我们上面生成自己的AccessKey ID
                .setAccessKeyId(accessKeyId)

                //这里修改为我们上面生成自己的AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        Client client = new Client(config);
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setSignName("图谱系统")//短信签名
                .setTemplateCode("SMS_269325361")//短信模板
                .setPhoneNumbers(phone)//这里填写接受短信的手机号码
                .setTemplateParam(code);//验证码
        // 复制代码运行请自行打印 API 的返回值
        client.sendSms(sendSmsRequest);
    }
}
