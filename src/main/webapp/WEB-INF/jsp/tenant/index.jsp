<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include.jsp" %>
<div class="col-md-9">
    <div class="panel panel-default" style="margin-top: 30px">
        <div class="panel-heading">
            租户
        </div>
        <div class="panel-body">
            <div class="table" id="tenantTab">

            </div>
        </div>
    </div>
</div>
<script>
    var pageMaker=${iMethod:toJSONlLine(pageMaker)}
    seajs.use(["controller/tenant"], function (tenantCtl) {
        tenantCtl.table('tenantTab',pageMaker);
    })
</script>