package com.lyc.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class Base implements Serializable {

    private static final long serialVersionUID = -3540383839044057287L;

    public Base() {
    }

    public Base(Integer code, String message) {
        this.status = code;
        this.message = message;
    }

    public Base(Integer code, String message, Object data) {
        this.status = code;
        this.message = message;
        this.data = data;
    }
    /**
     * 数据返回
     */
    private Object data;

    /**
     * 状态码
     */
    private Integer status;

    /**
     * 消息通知
     */
    private String message;

    /**
     * 返回成功或失败
     */
    private boolean result = true;

}
