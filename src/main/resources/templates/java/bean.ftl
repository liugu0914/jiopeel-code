<#assign pkgname=(path.beanPath)?replace(('.'+javaName),"")>
package ${pkgname!''};

import ${sys.company!''}.core.bean.Bean;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
* @description：${sys.des!''}实体层
* @author     ：${sys.author!''}
* @date       ：${.now?string("yyyy-MM-dd HH:mm:ss")}
*/
@Data
public class ${javaName!''} extends Bean implements Serializable {

    private static final long serialVersionUID = ${serial.bean!''}L;
    <#list columns?if_exists as colum>
    /**
    * ${colum.remark!''}
    */
    private ${colum.columnType!''} ${colum.columnName!''};
    </#list>

}
