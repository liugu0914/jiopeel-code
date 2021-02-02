<#assign pkgname=(path.resultPath)?replace(('.'+javaName),"")>
package ${pkgname!''};

import ${path.beanPath!''};
import lombok.Data;


/**
* ${sys.des!''}返回层
* @author     ${sys.author!''}
* @date       ${.now?string("yyyy-MM-dd HH:mm:ss")}
*/
@Data
public class ${javaName!''} extends ${JavaNameMap.bean} {

    private static final long serialVersionUID = ${serial.result!''}L;

}
