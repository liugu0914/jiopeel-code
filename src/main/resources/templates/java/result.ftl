<#assign pkgname=(path.resultPath)?replace(('.'+javaName),"")>
package ${pkgname!''};

import ${path.beanPath!''};
import lombok.Data;

import java.io.Serializable;

/**
* ${sys.des!''}返回层
* @author     ${sys.author!''}
* @date       ${.now?string("yyyy-MM-dd HH:mm:ss")}
*/
@Data
public class ${javaName!''} extends ${JavaNameMap.bean} implements Serializable {

    private static final long serialVersionUID = ${serial.result!''}L;

}
