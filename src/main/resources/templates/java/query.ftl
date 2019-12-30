<#assign pkgname=(path.queryPath)?replace(('.'+javaName),"")>
package ${pkgname!''};

import ${path.beanPath!''};
import lombok.Data;

import java.io.Serializable;

/**
* @description：${sys.des!''}查询层
* @author     ：${sys.author!''}
* @date       ：${.now?string("yyyy-MM-dd HH:mm:ss")}
*/
@Data
public class ${javaName!''} extends ${JavaNameMap.bean} implements Serializable {

    private static final long serialVersionUID = ${serial.query!''}L;

}