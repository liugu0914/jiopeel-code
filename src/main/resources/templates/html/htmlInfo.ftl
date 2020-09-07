<div class="modal-content">
    <div class="modal-header modal-colored-header bg-dark">
        <h5 class="modal-title" ><#noparse>[#if bean.id??]编辑[#else]添加[/#if]</#noparse></h5>
        <div>
            <i class="cs cs-shuaxin pointer" show="tooltip" target="modal-reflesh" data-title="刷新" ></i>
            <i class="cs cs-fangda pointer" show="tooltip" target="modal-expand"  data-title="缩放"  ></i>
            <i class="cs cs-close pointer" show="tooltip" target="modal-close"  data-title="关闭"  ></i>
        </div>
    </div>
    <div class="modal-body">
        <form class="needs-validation pl-3 pr-3" novalidate  action="${sys.urlPrefix}/${sys.lowBeanName}/save">
            <input type="hidden" name="id" value="<#noparse>${bean.id!''}</#noparse>">
            <#list columns?if_exists as colum>
                <#if colum.columnName == "enable">
                    <#noparse>
            <div class="form-group" style="justify-content: space-between;display: flex;align-items: center;">
                <label class="mb-0">是否可用</label>
                <input type="checkbox" onchange="Add.isEnable()" id="enable" [#if !bean.enable?? || bean.enable=='1']checked[/#if]
                       data-switch="success">
                <label  class="mb-0" for="enable" data-on-label="是" data-off-label="否"></label>
                <input type="hidden" name="enable" value="${(bean.enable)!1}">
            </div>
                    </#noparse>
                <#else>
            <div class="form-group">
                <label>${colum.remark!''}</label>
                <input class="form-control" type="text" name="${colum.columnName}" value="<#noparse>${</#noparse>bean.${colum.columnName}<#noparse>!''}</#noparse>"
                       placeholder="${colum.remark!''}">
            </div>
                </#if>
            </#list>
        </form>
    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-secondary" target="modal-close">关闭</button>
        <button type="button" class="btn btn-primary" target="form">保存</button>
    </div>
</div>