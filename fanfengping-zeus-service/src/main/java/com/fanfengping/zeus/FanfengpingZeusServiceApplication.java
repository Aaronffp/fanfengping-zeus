package com.fanfengping.zeus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.fanfengping.zeus.repository")
public class FanfengpingZeusServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FanfengpingZeusServiceApplication.class, args);
    }
}
