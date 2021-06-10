package com.lyc.bean;

import com.lyc.sys.Constant;
import lombok.Data;

@Data
public class Path {
    public Path() {
    }

    public Path(Sys sys) {
        String beanPath = sys.getBeanPath(),
                eventPath = sys.getEventPath(),
                logicPath = sys.getLogicPath(),
                viewPath = sys.getViewPath(),
                beanName = sys.getBeanName(),
                LowBeanName = sys.getLowBeanName(),
                suffix = sys.getSuffix(),
                company = sys.getCompany().replace(".", Constant.FILE_DIVISION);
        // ============ bean =============
        this.bean = beanPath + Constant.FILE_DIVISION + "bean";
        this.beanFile = this.bean + Constant.FILE_DIVISION + beanName + Constant.FILE_JAVA;
        this.beanPath = this.beanFile.substring(this.beanFile.indexOf(company))
                .replace(Constant.FILE_DIVISION, ".").replace(Constant.FILE_JAVA, "");
        this.result = this.bean + Constant.FILE_DIVISION + "result";
        this.resultFile = this.result + Constant.FILE_DIVISION + beanName + "Result" + Constant.FILE_JAVA;
        this.resultPath = this.resultFile.substring(this.resultFile.indexOf(company))
                .replace(Constant.FILE_DIVISION, ".").replace(Constant.FILE_JAVA, "");
        this.form = this.bean + Constant.FILE_DIVISION + "form";
        this.formFile = this.form + Constant.FILE_DIVISION + beanName + "Form" + Constant.FILE_JAVA;
        this.formPath = this.formFile.substring(this.formFile.indexOf(company))
                .replace(Constant.FILE_DIVISION, ".").replace(Constant.FILE_JAVA, "");
        this.query = this.bean + Constant.FILE_DIVISION + "query";
        this.queryFile = this.query + Constant.FILE_DIVISION + beanName + "Query" + Constant.FILE_JAVA;
        this.queryPath = this.queryFile.substring(this.queryFile.indexOf(company))
                .replace(Constant.FILE_DIVISION, ".").replace(Constant.FILE_JAVA, "");

        // ============ event =============
        this.event = eventPath;
        this.eventFile = this.event + Constant.FILE_DIVISION + beanName + "Event" + Constant.FILE_JAVA;
        this.eventPath = this.eventFile.substring(this.eventFile.indexOf(company))
                .replace(Constant.FILE_DIVISION, ".").replace(Constant.FILE_JAVA, "");

        // ============ logic =============
        this.dao = logicPath + Constant.FILE_DIVISION + "dao";
        this.daoFile = this.dao + Constant.FILE_DIVISION + beanName + "Mapper" + Constant.FILE_JAVA;
        this.daoPath = this.daoFile.substring(this.daoFile.indexOf(company))
                .replace(Constant.FILE_DIVISION, ".").replace(Constant.FILE_JAVA, "");
        this.mapper = this.dao + Constant.FILE_DIVISION + "mapper";
        this.mapperFile = this.mapper + Constant.FILE_DIVISION + beanName + "Mapper" + Constant.FILE_XML;
        this.mapperPath = this.mapperFile.substring(this.mapperFile.indexOf(company))
                .replace(Constant.FILE_DIVISION, ".").replace(Constant.FILE_JAVA, "");
        this.logic = logicPath + Constant.FILE_DIVISION + "logic";
        this.logicFile = this.logic + Constant.FILE_DIVISION + beanName + "Logic" + Constant.FILE_JAVA;
        this.logicPath = this.logicFile.substring(this.logicFile.indexOf(company))
                .replace(Constant.FILE_DIVISION, ".").replace(Constant.FILE_JAVA, "");
        this.logicImpl = this.logic+ Constant.FILE_DIVISION + "impl";
        this.logicImplFile = this.logicImpl + Constant.FILE_DIVISION + beanName + "LogicImpl" + Constant.FILE_JAVA;
        this.logicImplPath = this.logicImplFile.substring(this.logicImplFile.indexOf(company))
                .replace(Constant.FILE_DIVISION, ".").replace(Constant.FILE_JAVA, "");

        // ============ 模板 =============
        this.html = viewPath + Constant.FILE_DIVISION + LowBeanName;
        this.htmlMainFile = this.html + Constant.FILE_DIVISION + "main" + suffix;
        this.htmlMainPath = "main";
        this.htmlDataFile = this.html + Constant.FILE_DIVISION + "data" + suffix;
        this.htmlDataPath = "data";
        this.htmlInfoFile = this.html + Constant.FILE_DIVISION + "info" + suffix;
        this.htmlInfoPath = "info";
    }

    private String bean;

    private String beanFile;

    private String beanPath;

    private String result;

    private String resultFile;

    private String resultPath;

    private String form;

    private String formFile;

    private String formPath;

    private String query;

    private String queryFile;

    private String queryPath;

    private String dao;

    private String daoFile;

    private String daoPath;

    private String mapper;

    private String mapperFile;

    private String mapperPath;

    private String event;

    private String eventFile;

    private String eventPath;

    private String logic;

    private String logicFile;

    private String logicPath;

    private String logicImpl;

    private String logicImplFile;

    private String logicImplPath;

    private String html;

    private String htmlMainFile;

    private String htmlMainPath;

    private String htmlDataFile;

    private String htmlDataPath;

    private String htmlInfoFile;

    private String htmlInfoPath;
}
