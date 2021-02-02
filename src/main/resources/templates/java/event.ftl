<#assign pkgname=(path.eventPath)?replace(('.'+javaName),"")>
package ${pkgname!''};

import ${path.formPath!''};
import ${path.queryPath!''};
import ${path.resultPath!''};
import ${sys.company!''}.core.base.Base;
import ${sys.company!''}.core.bean.Page;
import ${sys.company!''}.core.event.BaseEvent;
import ${sys.company!''}.core.util.WebUtil;
import ${path.logicPath};
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
* ${sys.des!''} Event层
* @author     ${sys.author!''}
* @date       ${.now?string("yyyy-MM-dd HH:mm:ss")}
*/
@Controller
@RequestMapping("${sys.urlPrefix}/${sys.lowBeanName}")
public class ${javaName!''} extends BaseEvent {

    @Resource
    private ${JavaNameMap.logic} logic;

    /**
    * 获取查询主页面
    * @return  String
    * @author  ${sys.author!''}
    * @date    ${.now?string("yyyy-MM-dd HH:mm:ss")}
    */
    @RequestMapping(value = "${path.htmlMainPath}", method = {RequestMethod.GET})
    public String main() {
        return "${sys.htmlUri}/${sys.lowBeanName}/${path.htmlMainPath}";
    }

    /**
    * 获取分页列表数据
    * @param   query 查询数据
    * @param   page 分页数据
    * @param   model 模型域
    * @return  String
    * @author  ${sys.author!''}
    * @date   ${.now?string("yyyy-MM-dd HH:mm:ss")}
    */
    @RequestMapping(value = "${path.htmlDataPath}", method = {RequestMethod.POST})
    public String data(@ModelAttribute ${JavaNameMap.query} query, Page<${JavaNameMap.result}> page, Model model) {
        Page<${JavaNameMap.result}> PageData = logic.getListPage(query, page);
        model.addAttribute("PageData", PageData);
        return "${sys.htmlUri}/${sys.lowBeanName}/${path.htmlDataPath}";
    }

    /**
    * 添加或修改页面
    * @param   model 模型域
    * @return  String
    * @author  ${sys.author!''}
    * @date    ${.now?string("yyyy-MM-dd HH:mm:ss")}
    */
    @RequestMapping(value = "${path.htmlInfoPath}", method = {RequestMethod.POST})
    public String info(Model model) {
        Map<String, String> map = WebUtil.getParam2Map(request);
        model.addAttribute("bean", logic.getInfo(map.get("id")));
        return "${sys.htmlUri}/${sys.lowBeanName}/${path.htmlInfoPath}";
    }

    /**
    * 根据id获取数据
    * @param   id
    * @return  Base
    * @author  ${sys.author!''}
    * @date    ${.now?string("yyyy-MM-dd HH:mm:ss")}
    */
    @ResponseBody
    @RequestMapping(value = "get/{id}", method = {RequestMethod.GET})
    public Base get(@PathVariable("id") String id) {
        return Base.suc(logic.get(id));
    }

    /**
    * 根据id获取数据
    * @param   id
    * @return  Base
    * @author  ${sys.author!''}
    * @date    ${.now?string("yyyy-MM-dd HH:mm:ss")}
    */
    @ResponseBody
    @RequestMapping(value = "getInfo/{id}", method = {RequestMethod.GET})
    public Base getInfo(@PathVariable("id") String id) {
        return Base.suc(logic.getInfo(id));
    }

    /**
    * 获取分页列表数据
    * @param   query
    * @return  Base
    * @author  ${sys.author!''}
    * @date    ${.now?string("yyyy-MM-dd HH:mm:ss")}
    */
    @ResponseBody
    @RequestMapping(value = "getListPage", method = {RequestMethod.POST})
    public Base getListPage(@ModelAttribute ${JavaNameMap.query} query, Page<${JavaNameMap.result}> page) {
        return Base.suc(logic.getListPage(query,page));
    }

    /**
    * 获取列表数据
    * @return  Base
    * @author  ${sys.author!''}
    * @Date    ${.now?string("yyyy-MM-dd HH:mm:ss")}
    */
    @ResponseBody
    @RequestMapping(value = "getList", method = {RequestMethod.POST})
    public Base list() {
        return Base.suc(logic.list());
    }

    /**
    * 保存
    * @param   form
    * @return  Base
    * @author  ${sys.author!''}
    * @date    ${.now?string("yyyy-MM-dd HH:mm:ss")}
    */
    @ResponseBody
    @RequestMapping(value = "save", method = {RequestMethod.POST})
    public Base save(@ModelAttribute ${JavaNameMap.form} form) {
        return logic.save(form);
    }

    /**
    * 删除
    * @param   ids
    * @return  Base
    * @author  ${sys.author!''}
    * @date    ${.now?string("yyyy-MM-dd HH:mm:ss")}
    */
    @ResponseBody
    @RequestMapping(value = "del", method = {RequestMethod.POST})
    public Base del(@RequestParam("id") String ids) {
        return logic.del(ids);
    }
}
