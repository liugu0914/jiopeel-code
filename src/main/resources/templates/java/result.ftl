<#assign pkgname=(path.bean)?replace('/',".")>
<#assign start=pkgname?index_of(sys.company)>
<#assign pkgname=pkgname?substring(start)>
package ${pkgname!''};

import ${sys.company!''}.core.bean.Bean;
import ${path.bean!''};
import lombok.Data;

import java.io.Serializable;

/**
* @description：${sys.des!''}返回层
* @author     ：${sys.author!''}
* @date       ：${.now?string("yyyy-MM-dd HH:mm:ss")}
*/
@Data
public class ${javaName!''} extends ${JavaNameMap.result} implements Serializable {

    private static final long serialVersionUID = ${serial.result!''}L;

}
