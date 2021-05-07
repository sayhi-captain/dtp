package com.josh.dtp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 描述: 启动类
 *
 * @author Josh
 * @date 2021/4/25 3:40 下午
 */
@SpringBootApplication
public class DynamicThreadPoolMgrApp {
    public static void main(String[] args) {
        SpringApplication.run(DynamicThreadPoolMgrApp.class, args);
    }
}
