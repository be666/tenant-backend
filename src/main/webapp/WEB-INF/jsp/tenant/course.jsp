<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include.jsp" %>
<div class="col-md-9">
    <div class="panel panel-default" style="margin-top: 30px">
        <div class="panel-heading">
            课程
        </div>
        <div class="panel-body">
            <div class="row">
                <div class="col-md-2">
                    <input type="text" class="iMethod-queryTenant">
                </div>
                <div class="col-md-2">
                    <div class="iMethod-currentStatus"></div>
                </div>
                <div class="col-md-2">
                    <div class="iMethod-serviceType"></div>
                </div>
                <div class="col-md-2">
                    <i class="iMethod-btn iMethod-courseAdd">
                        新建课程
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
    var courseType =${iMethod:toJSONlLine(courseType)};
    var serviceType =${iMethod:toJSONlLine(serviceType)};
    seajs.use(["controller/course"], function (courseCtl) {
        $(function () {
            courseCtl.orgCourseTab('courseTab', "${tenantId}",courseType,serviceType);
        })
    })
</script>