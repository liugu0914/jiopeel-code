package com.lyc.bean;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Base implements Serializable {

    private static final long serialVersionUID = -1968010822265820703L;

    public Base(){
        this.updatedt=new Date();
        this.createdt=new Date();
    }
    /**
     * 创建时间
     */
    private Date createdt;

    /**
     * 修改时间
     */
    private Date updatedt;

}
