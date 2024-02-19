package com.hewen;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication(scanBasePackages = "com.hewen")
@EnableTransactionManagement// 开启事务管理
@MapperScan("com.hewen.mappers")
@EnableScheduling// 开启定时任务
public class GraRearApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraRearApplication.class, args);
    }

}
