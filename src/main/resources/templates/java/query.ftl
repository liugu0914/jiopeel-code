<#assign pkgname=(path.bean)?replace('/',".")>
<#assign start=pkgname?index_of(sys.company)>
<#assign pkgname=pkgname?substring(start)>
package ${pkgname!''};

import ${sys.company!''}.core.bean.Bean;
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
