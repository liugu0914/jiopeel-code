<#assign pkgname=(path.eventPath)?replace(('.'+javaName),"")>
package ${pkgname!''};

import ${sys.company!''}.core.event.BaseEvent;
import ${sys.company!''}.core.base.Base;
import ${sys.company!''}.core.base.StateCode;
import ${sys.company!''}.core.bean.Page;
import ${sys.company!''}.core.bean.QueryPage;
import ${path.formPath!''};
import ${path.queryPath!''};
import ${path.resultPath!''};
import ${path.logicPath};
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* ${sys.des!''} Event层
* @author     ${sys.author!''}
* @date       ${.now?string("yyyy-MM-dd HH:mm:ss")}
*/
@RestController
@RequestMapping("${sys.urlPrefix}/${sys.lowBeanName}")
public class ${javaName!''} extends BaseEvent {

    @Resource
    private ${JavaNameMap.logic} logic;

    /**
    * 获取分页列表数据
    *
    * @param queryPage 分页参数
    * @return Base
    * @author lyc
    * @Date 2021年3月1日17:00:40
    */
    @PostMapping("getListPage")
    public Base<Page<${JavaNameMap.result}>> getListPage(@RequestBody QueryPage<${JavaNameMap.query}, ${JavaNameMap.result}> queryPage) {
        Page<${JavaNameMap.result}> pageData = logic.getListPage(queryPage);
        return new Base<>(StateCode.SUCCESS, pageData);
    }

    /**
    * 获取分页列表数据
    *
    * @return Base
    * @author lyc
    * @Date 2021年3月1日17:00:40
    */
    @GetMapping("getList")
    public Base<List<${JavaNameMap.result}>> getList() {
        return new Base<>(StateCode.SUCCESS, logic.list());
    }


    /**
    * 根据Id获取信息获取
    *
    * @param id Id
    * @return Base
    * @author lyc
    * @date 2021年3月1日17:04:10
    */
    @GetMapping("getOneById")
    public Base<${JavaNameMap.result}> getOneById(@RequestParam("id") String id) {
        return new Base<>(StateCode.SUCCESS, logic.getInfo(id));
    }

    /**
    * 保存
    *
    * @param form 表单数据
    * @return Base
    * @author lyc
    * @date 2021年3月1日17:04:10
    */
    @PostMapping("save")
    public Base save(@RequestBody ${JavaNameMap.form} form) {
        return Base.judge(logic.save(form));
    }

    /**
    * 删除
    *
    * @param ids id集合
    * @return Base
    * @author lyc
    * @date 2021年3月1日17:04:10
    */
    @GetMapping("del")
    public Base del(@RequestParam("ids") String ids) {
        return Base.judge(logic.del(ids));
    }
}