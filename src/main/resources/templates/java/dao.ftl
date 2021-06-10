<#assign pkgname=(path.daoPath)?replace(('.'+javaName),"")>
package ${pkgname!''};

import ${sys.company!''}.core.dao.MasterMapper;
import ${path.beanPath!''};
import org.apache.ibatis.annotations.Mapper;

/**
* ${sys.des!''} DAO层实现
* @author     ${sys.author!''}
* @date       ${.now?string("yyyy-MM-dd HH:mm:ss")}
*/
@Mapper
public interface ${javaName!''} extends MasterMapper<${JavaNameMap.bean!''}>  {

}
