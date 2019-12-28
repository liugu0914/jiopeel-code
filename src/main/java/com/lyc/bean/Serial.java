package com.lyc.bean;

import com.lyc.util.BaseUtil;
import lombok.Data;

@Data
public class Serial {

    private String bean;

    private String result;

    private String form;

    private String query;

    public Serial(){
        this.bean=BaseUtil.getRandom(17);
        this.result=BaseUtil.getRandom(17);
        this.form=BaseUtil.getRandom(17);
        this.query=BaseUtil.getRandom(17);
    }
}
