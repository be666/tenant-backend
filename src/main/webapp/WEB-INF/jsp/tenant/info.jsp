<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include.jsp" %>
租户管理 》 租户列表 》新建租户
<div class="col-md-9">
    <div class="list-group" style="margin-top: 30px" id="tenant_info">


    </div>
</div>
<script>
    var currentStatus =${iMethod:toJSONlLine(currentStatus)};
    var serviceType =${iMethod:toJSONlLine(serviceType)};
    var orgType =${iMethod:toJSONlLine(orgType)};
    var schoolType =${iMethod:toJSONlLine(schoolType)};
    var region =${iMethod:toJSONlLine(region)};
    seajs.use(['controller/tenant'], function (tenantCtl) {
        tenantCtl.tenantInfo("tenant_info", currentStatus, serviceType)
    })
</script>