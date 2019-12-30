<#assign pkgname=(path.constantPath)?replace(('.'+javaName),"")>
package ${pkgname!''};

/**
* @description：${sys.des!''} 常量
* @author     ：${sys.author!''}
* @date       ：${.now?string("yyyy-MM-dd HH:mm:ss")}
*/
public class ${javaName!''} {

}
