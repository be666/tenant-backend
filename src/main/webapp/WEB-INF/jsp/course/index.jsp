<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include.jsp" %>
<div class="col-md-9">
    <div class="panel panel-default" style="margin-top: 30px">
        <div class="panel-heading">
            课程
        </div>
        <div class="panel-body">
            <div class="row iMethod-toolbar">
                <div class="col-md-3">
                    <input type="text" class="iMethod-queryCourse" placeholder="enter for search">
                </div>
                <%--<div class="col-md-2">--%>
                    <%--<div class="iMethod-currentStatus"></div>--%>
                <%--</div>--%>

                <div class="col-md-2">
                    <div class="iMethod-tenantList"></div>
                </div>
                <div class="col-md-2">
                    <div class="iMethod-courseList"></div>
                </div>
                <div class="col-md-2">
                    <div class="iMethod-courseType"></div>
                </div>
            </div>
            <div class="row">
                <div id="courseTab">

                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var courseList = ${iMethod:toJSONlLine(courseList)};
    var tenantList = ${iMethod:toJSONlLine(tenantList)};
    var courseType = ${iMethod:toJSONlLine(courseType)};
    var currentStatus = ${iMethod:toJSONlLine(currentStatus)};
    seajs.use(["controller/course"], function (courseCtl) {
        $(function () {
            courseCtl.courseTab('courseTab',tenantList, courseList, courseType,currentStatus);
        })
    })
</script>