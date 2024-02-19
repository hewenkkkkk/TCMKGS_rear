package com.hewen.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.hewen.entity.Config.AppConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;


public class AliyunOSSUtil {

    @Resource
    private AppConfig appConfig;

    public String uploadFile(MultipartFile multipartFile) {
        /** 创建OSSClient实例。*/
        OSS ossClient = new OSSClientBuilder().build(appConfig.getEndpoint(), appConfig.getAccessKeyId(),appConfig.getAccessKeySecret());
        try {
            /** 获取上传文件输入流*/
            InputStream inputStream = multipartFile.getInputStream();

            /**获取文件名称*/
            String filename = multipartFile.getOriginalFilename();

            /**调用oss方法实现上传*/
            //第一个参数 Bucket名称
            //第二个参数 上传到oss文件路径或文件名称
            //第三个参数 上传文件输入流
            ossClient.putObject(appConfig.getBucketName(), "gra/qua/"+filename, inputStream);

            /** 返回上传到阿里OSS的路径*/
            String url = "https://".concat("pic.lamper.top/gra/qua").concat("/").concat(filename);

            return url;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }


}

