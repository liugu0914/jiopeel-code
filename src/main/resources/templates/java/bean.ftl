<#assign pkgname=(path.beanPath)?replace(('.'+javaName),"")>
<#assign hasDate=false>
<#assign hasDecimal=false>
<#list columns?if_exists as colum>
    <#if colum.columnType == "Date">
        <#assign hasDate=true>
    </#if>
    <#if colum.columnType == "BigDecimal">
        <#assign hasDecimal=true>
    </#if>
</#list>
package ${pkgname!''};

import ${sys.company!''}.core.bean.Bean;
import lombok.Data;

<#if hasDate>import java.util.Date;</#if>
<#if hasDecimal>import java.math.BigDecimal;</#if>

/**
* ${sys.des!''} 实体层
* @author     ${sys.author!''}
* @date       ${.now?string("yyyy-MM-dd HH:mm:ss")}
*/
@Data
public class ${javaName} extends Bean {

    private static final long serialVersionUID = ${serial.bean}L;

    <#list columns?if_exists as colum>
    /**
    * ${colum.remark!''}
    */
    private ${colum.columnType!''} ${colum.columnName!''};
    </#list>

}
