<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include.jsp" %>
课程管理 》 课程列表 》新建课程
<div class="col-md-9">
    <div class="list-group" style="margin-top: 30px" id="course_info">


    </div>
</div>
<script>
    var currentStatus =${iMethod:toJSONlLine(currentStatus)};
    var serviceType =${iMethod:toJSONlLine(serviceType)};
    var orgType =${iMethod:toJSONlLine(orgType)};
    var schoolType =${iMethod:toJSONlLine(schoolType)};
    var region =${iMethod:toJSONlLine(region)};
    seajs.use(['controller/course'], function (courseCtl) {
        courseCtl.courseInfo("course_info", currentStatus, serviceType)
    })
</script>