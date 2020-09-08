<div class="row mb-2">
    <div class="col-sm-8">
        <a href="${sys.urlPrefix}/${sys.lowBeanName}/${path.htmlInfoPath}" class="btn btn-success btn-sm mb-2 mr-1" target="modal">
            <i class="cs cs-jia"></i>添加
        </a>
        <a href="${sys.urlPrefix}/${sys.lowBeanName}/${path.htmlInfoPath}" class="btn btn-info btn-sm mb-2 mr-1" target="modal" data-cus="Tool.checkedData('id';true)">
            <i class="cs cs-bianji"></i>编辑
        </a>
        <a href="${sys.urlPrefix}/${sys.lowBeanName}/del" target="ajax" data-warn="确认删除选中数据?" data-cus="Tool.checkedData('id')" class="btn btn-danger btn-sm mb-2 mr-1">
            <i class="cs cs-jian"></i>删除
        </a>
    </div>
    <div class="col-sm-4">
        <div class="text-sm-right">
            <button type="button" class="btn btn-light btn-sm mb-2 mr-1">
                <i class="mdi mdi-settings"></i>
            </button>
        </div>
    </div>
</div>
<div class="row mb-2">
    <div class="col-12">
        <div class="table-responsive">
            <table class="table table-hover table-centered mb-0" id="main-table">
                <thead>
                <tr>
                    <th style="width: 20px;">
                        <div class="custom-control custom-checkbox">
                            <label class="custom-control-label">
                                <input type="checkbox" class="custom-control-input chk-all">
                                <label class="custom-control-label" ></label>
                            </label>
                        </div>
                    </th>
                    <th>#</th>
                    <#list columns?if_exists as colum>
                    <th>${colum.remark!''}</th>
                    </#list>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <#noparse>[#list (PageData.result)?if_exists as bean]</#noparse>
                    <tr>
                        <td>
                            <div class="custom-control custom-checkbox">
                                <label class="custom-control-label">
                                    <input type="checkbox" class="custom-control-input chk" data-id="<#noparse>${bean.id}</#noparse>">
                                    <label class="custom-control-label" ></label>
                                </label>
                            </div>
                        </td>
                        <td><#noparse>${bean_index+1}</#noparse></td>
                        <#list columns?if_exists as colum>
                        <#if colum.columnName == "enable">
                        <#noparse>
                        <td>
                            <input type="checkbox" id="switch${bean.id}" [#if bean.enable?? &&
                                   bean.enable=='1']checked[/#if] data-switch="success">
                            <label for="switch${bean.id}" data-on-label="是" data-off-label="否"
                                   class="mb-0 d-block"></label>
                        </td>
                        </#noparse>
                        <#else>
                        <td><#noparse>${</#noparse>bean.${colum.columnName}<#noparse>!''}</#noparse></td>
                        </#if>
                        </#list>
                        <td>
                            <a href="${sys.urlPrefix}/${sys.lowBeanName}/${path.htmlInfoPath}" target="modal" data-id="<#noparse>${bean.id}</#noparse>" show="tooltip"  data-title="编辑">
                                <i class="cs cs-bianji"></i>
                            </a>
                            <a href="${sys.urlPrefix}/${sys.lowBeanName}/del" target="ajax" data-id="<#noparse>${bean.id}</#noparse>" data-warn="确认删除数据?"  show="tooltip" data-title="删除">
                                <i class="cs cs-shanchu"></i>
                            </a>
                        </td>
                    </tr>
                <#noparse>[/#list]</#noparse>
                </tbody>
            </table>
        </div>
    </div>
</div>
<#noparse>[@c.page PageData=PageData /]</#noparse>