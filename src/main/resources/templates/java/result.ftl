<#assign pkgname=(path.resultPath)?replace(('.'+javaName),"")>
package ${pkgname!''};

import ${path.beanPath!''};
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
* ${sys.des!''}返回层
* @author     ${sys.author!''}
* @date       ${.now?string("yyyy-MM-dd HH:mm:ss")}
*/
@Data
@EqualsAndHashCode(callSuper = true)
public class ${javaName!''} extends ${JavaNameMap.bean} {

    private static final long serialVersionUID = ${serial.result!''}L;

}
