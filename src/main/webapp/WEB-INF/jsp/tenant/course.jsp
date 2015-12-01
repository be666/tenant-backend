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
                <div class="col-md-2">
                    <div class="iMethod-courseType"></div>
                </div>
                <div class="col-md-2">
                    <div class="iMethod-tenantList"></div>
                </div>
                <div class="col-md-2">
                    <div class="iMethod-courseList"></div>
                </div>
                <div class="col-md-3">
                    <i class="iMethod-btn iMethod-btn-action  iMethod-courseAdd">
                        新建课程
                    </i>
                    <i class="iMethod-btn iMethod-btn-action  iMethod-courseBuy">
                        购买课程
                    </i>
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
    var serviceType =${iMethod:toJSONlLine(serviceType)};
    seajs.use(["controller/course"], function (courseCtl) {
        $(function () {
            courseCtl.orgCourseTab('courseTab', "${tenantId}", courseType, serviceType, tenantList, courseList, currentStatus);
        })
    })
</script>