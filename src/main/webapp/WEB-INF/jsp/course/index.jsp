<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include.jsp" %>
<div class="col-md-9">
    <div class="panel panel-default" style="margin-top: 30px">
        <div class="panel-heading">
            课程
        </div>
        <div class="panel-body">
            <div class="table" id="couserTab">

            </div>
        </div>
    </div>
</div>
<script>
    var pageMaker=${iMethod:toJSONlLine(pageMaker)}
            seajs.use(["controller/course"], function (tenantCtl) {
                tenantCtl.table('couserTab',pageMaker);
            })
</script>