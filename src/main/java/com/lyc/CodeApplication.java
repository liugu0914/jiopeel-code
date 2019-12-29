package com.lyc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Package: com.lyc
 * @Author: lyc
 * @Date: 2019/9/29 17:07
 */
@Slf4j
@EnableTransactionManagement
@SpringBootApplication
public class CodeApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(CodeApplication.class, args);
        String port = applicationContext.getEnvironment().getProperty("server.port");
        log.info("jiopeel started at http://localhost:"+port);
    }

}
