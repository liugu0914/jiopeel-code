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
     * 是否生产模板文件 1是 0否
     */
    private  String view;

    /**
     * 输出的模板路径
     */
    @Value("${code.view.path}")
    private  String viewPath;


    /**
     * 是否生产bean文件 1是 0否
     */
    private  String bean;

    /**
     * 输出的bean路径
     */
    @Value("${code.java.beanPath}")
    private  String beanPath;

    /**
     * 是否生产event文件 1是 0否
     */
    private  String event;

    /**
     * 输出的event路径
     */
    @Value("${code.java.eventPath}")
    private  String eventPath;

    /**
     * 是否生产logic文件 1是 0否
     */
    private  String logic;

    /**
     * 输出的logic路径
     */
    @Value("${code.java.logicPath}")
    private  String logicPath;

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
    private  String lowBeanName;

    /**
     * url 前缀
     */
    @Value("${code.view.urlPrefix}")
    private  String urlPrefix;

    /**
     * html uri
     */
    private  String htmlUri;

}
