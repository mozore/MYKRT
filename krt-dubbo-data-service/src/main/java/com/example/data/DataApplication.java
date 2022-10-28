package com.example.data;

import com.example.data.netty.NettyClient;
import com.example.data.netty.config.NettyConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class DataApplication {

    @Autowired
    NettyConfig nettyConfig;
    public static void main(String[] args) {
        SpringApplication.run(DataApplication.class, args);
    }

}
