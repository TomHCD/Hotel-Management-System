package com.example.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;//hzh020.0618 额外加的
//(exclude= {DataSourceAutoConfiguration.class})//hzh020.0618 额外加的
@EnableScheduling
@SpringBootApplication

public class HotelApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelApplication.class, args);
    }

}
