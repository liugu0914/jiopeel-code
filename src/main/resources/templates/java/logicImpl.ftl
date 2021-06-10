<#assign pkgname=(path.logicImplPath)?replace(('.'+javaName),"")>
package ${pkgname!''};

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import ${sys.company!''}.core.base.Base;
import ${sys.company!''}.core.bean.Page;
import ${sys.company!''}.core.bean.QueryPage;
import ${sys.company!''}.core.config.exception.Assert;
import ${sys.company!''}.core.config.exception.ServerException;
import ${sys.company!''}.core.logic.BaseLogic;
import ${sys.company!''}.core.util.BaseUtil;
import ${path.beanPath};
import ${path.formPath};
import ${path.queryPath};
import ${path.resultPath};
import ${path.daoPath};
import ${path.logicPath};
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
* ${sys.des!''} Logic层
* @author     ${sys.author!''}
* @date       ${.now?string("yyyy-MM-dd HH:mm:ss")}
*/
@Slf4j
@Service
public class ${javaName!''} extends BaseLogic implements ${JavaNameMap.logic}{

    @Resource
    private ${JavaNameMap.dao} ${sys.lowBeanName}Mapper;

    /**
    * 根据id获取应用信息与数据库一致
    * @param   id
    * @return  ${sys.beanName}
    * @author  ${sys.author!''}
    * @date    ${.now?string("yyyy-MM-dd HH:mm:ss")}
    */
    @Override
    public ${JavaNameMap.bean} get(String id) {
        ${JavaNameMap.bean} bean = new ${JavaNameMap.bean}();
        if (!BaseUtil.empty(id)){
            bean = ${sys.lowBeanName}Mapper.selectById(id);
        }
        return bean;
    }

    /**
    * 根据id获取应用信息 自定义
    * @param   id 主键ID
    * @return  ${JavaNameMap.result}
    * @author  ${sys.author!''}
    * @date    ${.now?string("yyyy-MM-dd HH:mm:ss")}
    */
    @Override
    public ${JavaNameMap.result} getInfo(String id) {
        ${JavaNameMap.result} bean = new ${JavaNameMap.result}();
        if (!BaseUtil.empty(id)){
            ${JavaNameMap.bean} item = get(id);
            BaseUtil.copyProperties(item, bean);
        }
        return bean;
    }

    /**
    * 获取分页列表数据
    * @param   queryPage 查询对象
    * @return  Page<${JavaNameMap.result}>
    * @author  ${sys.author!''}
    * @date    ${.now?string("yyyy-MM-dd HH:mm:ss")}
    */
    @Override
    public Page<${JavaNameMap.result}> getListPage(QueryPage<${JavaNameMap.query},${JavaNameMap.result}> queryPage) {
        Page<${JavaNameMap.result}> PageList = beanDao.queryPageList("${path.mapperPath}.getListPage", queryPage);
        return PageList;
    }


    /**
    * 根据搜索条件查询数据
    * @return  List<${JavaNameMap.result}>
    * @author  ${sys.author!''}
    * @date    ${.now?string("yyyy-MM-dd HH:mm:ss")}
    */
    @Override
    public List<${JavaNameMap.result}> list() {
        LambdaQueryWrapper<${JavaNameMap.bean}> ${sys.lowBeanName}Query = new LambdaQueryWrapper<>();
        List<${JavaNameMap.bean}> list = ${sys.lowBeanName}Mapper.selectList(${sys.lowBeanName}Query);
        if (list == null || list.isEmpty()){
            return new ArrayList<>();
        }
        return list.stream().map(item -> {
            ${JavaNameMap.result} result = new ${JavaNameMap.result}();
            BaseUtil.copyProperties(item, result);
            return result;
        }).collect(Collectors.toList());
    }

    /**
    * 保存数据
    * @param   form 表单提交对象
    * @return  Base
    * @author  ${sys.author!''}
    * @date    ${.now?string("yyyy-MM-dd HH:mm:ss")}
    */
    @Override
    @Transactional(rollbackFor = {Exception.class, ServerException.class})
    public boolean save(${JavaNameMap.form} form) {
        CheckBean(form);
        boolean flag;
        String id = form.getId();
        ${JavaNameMap.bean} bean = new ${JavaNameMap.bean}();
        if (BaseUtil.empty(id)) {//添加
            BaseUtil.copyProperties(form, bean);
            if (BaseUtil.empty(bean.getId()))
                bean.createID();
            bean.createTime();
            flag = ${sys.lowBeanName}Mapper.insert(bean)>0;
        } else {//修改
            bean = get(id);
            BaseUtil.copyProperties(form, bean);
            bean.updTime();
            flag = ${sys.lowBeanName}Mapper.updateById(bean) > 0;
        }
        return flag;
    }

    /**
    * 删除
    * @param   ids
    * @return  Base
    * @author  ${sys.author!''}
    * @date    ${.now?string("yyyy-MM-dd HH:mm:ss")}
    */
    @Override
    @Transactional(rollbackFor = {Exception.class, ServerException.class})
    public boolean del(String ids) {
        Assert.isNull(ids, "未选择不能删除");
        String[] ids_ = ids.split(",");
        if (ids_.length <= 0)
            throw new ServerException("未选择不能删除");
        return ${sys.lowBeanName}Mapper.deleteBatchIds(Arrays.asList(ids_))>0;
    }

    /**
    * 检查对象数据
    * @param   form 表单提交对象
    * @author  ${sys.author!''}
    * @date    ${.now?string("yyyy-MM-dd HH:mm:ss")}
    */
    private void CheckBean(${JavaNameMap.form} form) {
        Assert.isNull(form, "对象不能为空");
    <#list columns?if_exists as colum>
        Assert.isNull(form.get${(colum.columnName)?cap_first}(), "${colum.remark!''}不能为空");
    </#list>
    }
}
