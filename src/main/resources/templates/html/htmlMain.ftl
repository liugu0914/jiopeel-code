<div class="container-fluid query-main" data-init ="${sys.lowBeanName}.init">
    <div class="row">
        <div class="col-12">
            <div class="card mb-2 mt-2">
                <div class="card-body pb-2">
                    <div class="row mb-2">
                        <div class="col-12">
                            <form action="${sys.urlPrefix}/${sys.lowBeanName}/${path.htmlMainPath}">
                                <div class="row align-items-end">
                                    <div class="col-md-10">
                                        <div class="row">
                                            <#list columns?if_exists as colum>
                                                private ${colum.columnType!''} ${colum.columnName!''};
                                                <div class="col-sm-4 col-lg-3 mb-2">
                                                    <label>${colum.remark!''}</label>
                                                    <input class="form-control" type="text" name="${colum.columnName!''}"/>
                                                </div>
                                            </#list>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="row">
                                            <div class="col-lg-6 mb-2">
                                                <button type="button" target="clear" class="btn btn-block btn-light ">
                                                    重置
                                                </button>
                                            </div>
                                            <div class="col-lg-6 mb-2">
                                                <button type="button" id="query" target="query"
                                                        data-target=".query-data"
                                                        class="btn btn-block btn-primary ">
                                                    查询
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 query-data">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var ${sys.lowBeanName}= {
        init:function (ele) {
            $('#query',ele).trigger('click');
        }
    }
</script>