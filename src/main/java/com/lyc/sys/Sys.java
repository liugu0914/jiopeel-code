package com.lyc.sys;

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

}
