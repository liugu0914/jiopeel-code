package com.lyc.bean;

import lombok.Data;

@Data
public class Path {
    public Path() {
    }

    public Path(String javaPath, String viewPath) {
        this.bean = javaPath + "/bean";
        this.result = this.bean + "/result";
        this.form = this.bean + "/form";
        this.query = this.bean + "/query";
        this.dao = javaPath + "/dao";
        this.daoMapper = this.dao + "/mapper";
        this.constant = javaPath + "/constant";
        this.event = javaPath + "/event";
        this.logic = javaPath + "/logic";
        this.html = viewPath;
    }

    private String bean;

    private String result;

    private String form;

    private String query;

    private String dao;

    private String daoMapper;

    private String constant;

    private String event;

    private String logic;

    private String html;
}
