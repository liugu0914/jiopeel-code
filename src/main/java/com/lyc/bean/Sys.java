package com.lyc.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class Sys {

    /**
     * 作者
     */
    @Value("${code.author}")
    private  String author;

    /**
     * 公司名
     */
    @Value("${code.company}")
    private  String company;

    /**
     * 生成模板的后缀
     */
    @Value("${code.view.suffix}")
    private  String suffix;

    /**
     * 输出的模板路径
     */
    @Value("${code.view.path}")
    private  String viewPath;

    /**
     * 输出的java路径
     */
    @Value("${code.java.path}")
    private  String javaPath;

    /**
     * 表名
     */
    private  String tabName;

    /**
     * 表描述
     */
    private  String des;

    /**
     * 实例名称
     */
    private  String beanName;

    /**
     * 小写实例名称
     */
    private  String lowbeanName;

    /**
     * java uri
     */
    private  String javauri;

    /**
     * java uri
     */
    private  String htmluri;

}
