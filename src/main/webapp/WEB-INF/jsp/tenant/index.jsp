<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include.jsp" %>
<div class="col-md-9">
    <div class="panel panel-default" style="margin-top: 30px">
        <div class="panel-heading">
            租户
        </div>
        <div class="panel-body">
            <div class="row iMethod-toolbar">
                <div class="col-md-3">
                    <input type="text" class="iMethod-queryTenant" placeholder="enter for search">
                </div>
                <div class="col-md-2">
                    <div class="iMethod-currentStatus"></div>
                </div>
                <div class="col-md-2">
                    <div class="iMethod-serviceType"></div>
                </div>
                <div class="col-md-2">
                    <i class="iMethod-btn iMethod-btn-action  iMethod-tenantAdd">
                        新建租户
                    </i>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">总租户数${totalTenant!=null?totalTenant:0}，
                    未交付${currentStatus10Count!=null?currentStatus10Count:0} </div>
            </div>
            <div class="row">
                <div id="tenantTab">

                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var currentStatus =${iMethod:toJSONlLine(currentStatus)};
    var serviceType =${iMethod:toJSONlLine(serviceType)};
    seajs.use(["controller/tenant"], function (tenantCtl) {
        tenantCtl.tenantTable('tenantTab', currentStatus, serviceType);
    })
</script>