package com.jiopeel.sys.bean;

import com.jiopeel.core.bean.Bean;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
* @description：${sys.des!''}
* @author     ：${sys.author!''}
* @date       ：${.now?string("yyyy-MM-dd HH:mm:ss")}
*/
@Data
public class ${sys.beanName!''} extends Bean implements Serializable {

    private static final long serialVersionUID = ${serial.bean!''}L;
    <#list columns?if_exists as colum>
    /**
    * ${colum.remark!''}
    */
    private ${colum.columnType!''} ${colum.columnName!''};
    </#list>

}
