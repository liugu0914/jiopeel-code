<#assign pkgname=(path.logicPath)?replace(('.'+javaName),"")>
package ${pkgname!''};

import ${sys.company!''}.core.base.Base;
import ${sys.company!''}.core.bean.Page;
import ${sys.company!''}.core.config.exception.Assert;
import ${sys.company!''}.core.config.exception.ServerException;
import ${sys.company!''}.core.constant.Constant;
import ${sys.company!''}.core.logic.BaseLogic;
import ${sys.company!''}.core.util.BaseUtil;
import ${path.beanPath};
import ${path.formPath};
import ${path.queryPath};
import ${path.resultPath};
import ${path.daoPath};
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
* ${sys.des!''} Logic层
* @author     ${sys.author!''}
* @date       ${.now?string("yyyy-MM-dd HH:mm:ss")}
*/
@Slf4j
@Service
public class ${javaName!''} extends BaseLogic {

    @Resource
    private ${JavaNameMap.dao} dao;

    /**
    * 根据id获取应用信息与数据库一致
    * @param ：id
    * @return：${sys.beanName}
    * @author: ${sys.author!''}
    * @Date  : ${.now?string("yyyy-MM-dd HH:mm:ss")}
    */
    public ${JavaNameMap.bean} get(String id) {
        ${JavaNameMap.bean} bean = new ${JavaNameMap.bean}();
        if (!BaseUtil.empty(id))
            bean = dao.queryOneById(${JavaNameMap.bean}.class, id);
        return bean;
    }

    /**
    * 根据id获取应用信息 自定义
    * @param : id
    * @return: ${JavaNameMap.result}
    * @author: ${sys.author!''}
    * @Date  : ${.now?string("yyyy-MM-dd HH:mm:ss")}
    */
    public ${JavaNameMap.result} getInfo(String id) {
        ${JavaNameMap.result} bean = new ${JavaNameMap.result}();
        if (!BaseUtil.empty(id))
            bean = dao.queryOne("${sys.lowBeanName}.getInfo", id);
        return bean;
    }

    /**
    * 获取分页列表数据
    * @param : query 查询对象
    * @param : page  分页器
    * @return: Base
    * @author: ${sys.author!''}
    * @Date  : ${.now?string("yyyy-MM-dd HH:mm:ss")}
    */
    public Page<${JavaNameMap.result}> getListPage(${JavaNameMap.query} query, Page<${JavaNameMap.result}> page) {
        Page<${JavaNameMap.result}> PageList = dao.queryPageList("${sys.lowBeanName}.getListPage", query, page);
        return PageList;
    }


    /**
    * 根据搜索条件查询数据
    * @return: Base
    * @author: ${sys.author!''}
    * @Date  : ${.now?string("yyyy-MM-dd HH:mm:ss")}
    */
    public List<${JavaNameMap.result}> list() {
        List<${JavaNameMap.result}> list = dao.query("${sys.lowBeanName}.list");
        return list;
    }

    /**
    * 保存数据
    * @param : form 表单提交对象
    * @return: Base
    * @author: ${sys.author!''}
    * @Date  : ${.now?string("yyyy-MM-dd HH:mm:ss")}
    */
    @Transactional(rollbackFor = {Exception.class, ServerException.class})
    public Base save(${JavaNameMap.form} form) {
        CheckBean(form);
        String id = form.getId();
        ${JavaNameMap.bean} bean = new ${JavaNameMap.bean}();
        if (BaseUtil.empty(id)) {//添加
            BaseUtil.copyProperties(form, bean);
            if (BaseUtil.empty(bean.getId()))
                bean.createID();
            bean.createTime();
            bean.setEnable(Constant.ENABLE_YES);
            dao.add(bean);
        } else {//修改
            bean = get(id);
            BaseUtil.copyProperties(form, bean);
            bean.updTime();
            dao.upd(bean, "id", "id", "ctime");
        }
        return Base.suc();
    }

    /**
    * 删除
    * @param : ids
    * @return: Base
    * @author: ${sys.author!''}
    * @Date  : ${.now?string("yyyy-MM-dd HH:mm:ss")}
    */
    @Transactional(rollbackFor = {Exception.class, ServerException.class})
    public Base del(String ids) {
        Assert.isNull(ids, "未选择不能删除");
        String[] ids_ = ids.split(",");
        if (ids_.length <= 0)
            throw new ServerException("未选择不能删除");
        dao.delByIds(${JavaNameMap.bean}.class, ids_);
        return Base.suc("删除成功");
    }

    /**
    * 检查对象数据
    * @param : form 表单提交对象
    * @author: ${sys.author!''}
    * @Date  : ${.now?string("yyyy-MM-dd HH:mm:ss")}
    */
    private void CheckBean(${JavaNameMap.form} form) {
        Assert.isNull(form, "对象不能为空");
    <#list columns?if_exists as colum>
        Assert.isNull(form.get${(colum.columnName)?capitalize}(), "${colum.remark!''}不能为空");
    </#list>
    }

}
