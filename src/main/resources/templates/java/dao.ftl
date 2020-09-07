<#assign pkgname=(path.daoPath)?replace(('.'+javaName),"")>
package ${pkgname!''};

import ${sys.company!''}.core.dao.BaseDao;
import ${path.beanPath!''};
import org.springframework.stereotype.Repository;

/**
* @description：${sys.des!''} DAO层实现
* @author     ：${sys.author!''}
* @date       ：${.now?string("yyyy-MM-dd HH:mm:ss")}
*/
@Repository
public class ${javaName!''} extends BaseDao<${JavaNameMap.bean!''}>  {

}
