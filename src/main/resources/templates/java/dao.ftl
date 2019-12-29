<#assign pkgname=(path.bean)?replace('/',".")>
<#assign start=pkgname?index_of(sys.company)>
<#assign pkgname=pkgname?substring(start)>
package ${pkgname!''};

import ${sys.company!''}.core.dao.BaseDao;
import ${path.beanPath!''};
import org.springframework.stereotype.Repository;

/**
* @description：${sys.des!''}DAO层实现
* @author     ：${sys.author!''}
* @date       ：${.now?string("yyyy-MM-dd HH:mm:ss")}
*/
@Repository
public class ${javaName!''} extends BaseDao<${javaName!''}>  {

}
