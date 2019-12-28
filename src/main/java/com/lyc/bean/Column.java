package com.lyc.bean;

import lombok.Data;

@Data
public class Column {
    /**
     * 字段名
     */
    private String columnName;

    /**
     * 字段类型
     */
    private String columnType;

    /**
     * 长度
     */
    private Integer len;

    /**
     * 是否为空
     */
    private String isNull;

    /**
     * 备注
     */
    private String remark;
}
