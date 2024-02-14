package com.hewen;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication(scanBasePackages = "com.hewen")
@EnableTransactionManagement// 开启事务管理
//@EnableConfigurationProperties(AppConfig.class) // 添加此行
@MapperScan("com.hewen.mappers")
public class GraRearApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraRearApplication.class, args);
    }

}
