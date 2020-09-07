package com.lyc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.ImageBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Package: com.lyc
 * @Author: lyc
 * @Date: 2019/9/29 17:07
 */
@Slf4j
@SpringBootApplication
public class CodeApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(CodeApplication.class);
        application.setBannerMode(Banner.Mode.CONSOLE);
        ConfigurableApplicationContext context = application.run(args);
        String port = context.getEnvironment().getProperty("server.port");
        log.info("Jiopeel Code started at http://localhost:"+port);
    }

}
