package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 启动
 *
 * @author hedongzhou
 * @since 2019/10/16
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class Application {

    /**
     * main
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
