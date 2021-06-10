<#assign pkgname=(path.logicPath)?replace(('.'+javaName),"")>
package ${pkgname!''};

import ${sys.company!''}.core.bean.Page;
import ${sys.company!''}.core.bean.QueryPage;
import ${path.beanPath};
import ${path.formPath};
import ${path.queryPath};
import ${path.resultPath};

import java.util.List;

/**
* ${sys.des!''} Logic层
* @author     ${sys.author!''}
* @date       ${.now?string("yyyy-MM-dd HH:mm:ss")}
*/
public interface ${javaName!''} {


    /**
    * 根据id获取应用信息与数据库一致
    * @param   id
    * @return  ${sys.beanName}
    * @author  ${sys.author!''}
    * @date    ${.now?string("yyyy-MM-dd HH:mm:ss")}
    */
    ${JavaNameMap.bean} get(String id);

    /**
    * 根据id获取应用信息 自定义
    * @param   id
    * @return  ${JavaNameMap.result}
    * @author  ${sys.author!''}
    * @date    ${.now?string("yyyy-MM-dd HH:mm:ss")}
    */
    ${JavaNameMap.result} getInfo(String id);

    /**
    * 获取分页列表数据
    * @param   queryPage 查询对象
    * @return  Page<${JavaNameMap.result}>
    * @author  ${sys.author!''}
    * @date    ${.now?string("yyyy-MM-dd HH:mm:ss")}
    */
    Page<${JavaNameMap.result}> getListPage(QueryPage<${JavaNameMap.query},${JavaNameMap.result}> queryPage);


    /**
    * 根据搜索条件查询数据
    * @return  List<${JavaNameMap.result}>
    * @author  ${sys.author!''}
    * @date    ${.now?string("yyyy-MM-dd HH:mm:ss")}
    */
    List<${JavaNameMap.result}> list();

    /**
    * 保存数据
    * @param   form 表单提交对象
    * @return  boolean
    * @author  ${sys.author!''}
    * @date    ${.now?string("yyyy-MM-dd HH:mm:ss")}
    */
    boolean save(${JavaNameMap.form} form);

    /**
    * 删除
    * @param   ids
    * @return  boolean
    * @author  ${sys.author!''}
    * @date    ${.now?string("yyyy-MM-dd HH:mm:ss")}
    */
    boolean del(String ids);
}
