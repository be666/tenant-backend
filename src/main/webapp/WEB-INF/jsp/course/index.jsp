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
    var courseList = ${iMethod:toJSONlLine(courseList)};
    var tenantList = ${iMethod:toJSONlLine(tenantList)};
    seajs.use(["controller/course"], function (courseCtl) {
        $(function () {
            courseCtl.table('couserTab', courseList, tenantList);
        })
    })
</script>