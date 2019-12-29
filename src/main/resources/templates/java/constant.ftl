<#assign pkgname=(path.bean)?replace('/',".")>
<#assign start=pkgname?index_of(sys.company)>
<#assign pkgname=pkgname?substring(start)>
package ${pkgname!''};

/**
* @description：${sys.des!''} 常量
* @author     ：${sys.author!''}
* @date       ：${.now?string("yyyy-MM-dd HH:mm:ss")}
*/
public class ${javaName!''} {

}
