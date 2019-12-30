package com.lyc.bean;

import com.lyc.sys.Constant;
import lombok.Data;

@Data
public class Path {
    public Path() {
    }

    public Path(Sys sys) {
        String javaPath = sys.getJavaPath().replace("\\",Constant.FILE_DIVISION),
                viewPath = sys.getViewPath().replace("\\",Constant.FILE_DIVISION),
                beanName = sys.getBeanName(),
                lowbeannam = sys.getLowbeanName(), suffix = sys.getSuffix(),
                company=sys.getCompany().replace(".",Constant.FILE_DIVISION);
        this.bean = javaPath + Constant.FILE_DIVISION+"bean";
        this.beanFile = this.bean + Constant.FILE_DIVISION + beanName + Constant.FILE_JAVA;
        this.beanPath=this.beanFile.substring(this.beanFile.indexOf(company))
                .replace(Constant.FILE_DIVISION,".").replace(Constant.FILE_JAVA,"");
        this.result = this.bean + Constant.FILE_DIVISION+"result";
        this.resultFile = this.result + Constant.FILE_DIVISION + beanName + "Result" + Constant.FILE_JAVA;
        this.resultPath=this.resultFile.substring(this.resultFile.indexOf(company))
                .replace(Constant.FILE_DIVISION,".").replace(Constant.FILE_JAVA,"");
        this.form = this.bean + Constant.FILE_DIVISION+"form";
        this.formFile = this.form + Constant.FILE_DIVISION + beanName + "Form" + Constant.FILE_JAVA;
        this.formPath=this.formFile.substring(this.formFile.indexOf(company))
                .replace(Constant.FILE_DIVISION,".").replace(Constant.FILE_JAVA,"");
        this.query = this.bean + Constant.FILE_DIVISION+"query";
        this.queryFile=this.query+Constant.FILE_DIVISION + beanName + "Query" + Constant.FILE_JAVA;
        this.queryPath=this.queryFile.substring(this.queryFile.indexOf(company))
                .replace(Constant.FILE_DIVISION,".").replace(Constant.FILE_JAVA,"");
        this.dao = javaPath + Constant.FILE_DIVISION+"dao";
        this.daoFile=this.dao+Constant.FILE_DIVISION + beanName + "Dao" + Constant.FILE_JAVA;
        this.daoPath=this.daoFile.substring(this.daoFile.indexOf(company))
                .replace(Constant.FILE_DIVISION,".").replace(Constant.FILE_JAVA,"");
        this.mapper = this.dao +Constant.FILE_DIVISION+ "mapper";
        this.mapperFile=this.mapper+Constant.FILE_DIVISION + lowbeannam + Constant.FILE_XML;
        this.mapperPath=this.mapperFile.substring(this.mapperFile.indexOf(company))
                .replace(Constant.FILE_DIVISION,".").replace(Constant.FILE_JAVA,"");
        this.constant = javaPath + Constant.FILE_DIVISION+"constant";
        this.constantFile=this.constant+Constant.FILE_DIVISION + beanName + "Constant" + Constant.FILE_JAVA;
        this.constantPath=this.constantFile.substring(this.constantFile.indexOf(company))
                .replace(Constant.FILE_DIVISION,".").replace(Constant.FILE_JAVA,"");
        this.event = javaPath + Constant.FILE_DIVISION+"event";
        this.eventFile=this.event+Constant.FILE_DIVISION + beanName + "Event" + Constant.FILE_JAVA;
        this.eventPath=this.eventFile.substring(this.eventFile.indexOf(company))
                .replace(Constant.FILE_DIVISION,".").replace(Constant.FILE_JAVA,"");
        this.logic = javaPath + Constant.FILE_DIVISION+"logic";
        this.logicFile=this.logic+Constant.FILE_DIVISION + beanName + "Logic" + Constant.FILE_JAVA;
        this.logicPath=this.logicFile.substring(this.logicFile.indexOf(company))
                .replace(Constant.FILE_DIVISION,".").replace(Constant.FILE_JAVA,"");
        this.html = viewPath+Constant.FILE_DIVISION+lowbeannam;
        this.htmlAddFile=this.html+Constant.FILE_DIVISION + "add"+suffix;
        this.htmlUpdFile=this.html+Constant.FILE_DIVISION + "upd"+suffix;
        this.htmlIndexFile=this.html+Constant.FILE_DIVISION + "main"+suffix;
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

    private String constant;

    private String constantFile;

    private String constantPath;

    private String event;

    private String eventFile;

    private String eventPath;

    private String logic;

    private String logicFile;

    private String logicPath;

    private String html;

    private String htmlIndexFile;

    private String htmlUpdFile;

    private String htmlAddFile;
}
