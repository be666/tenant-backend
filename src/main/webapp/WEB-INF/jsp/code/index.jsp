<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include.jsp" %>
<div id="codeLayout">

</div>
<script>
    var codes = ["currentStatus", "serviceType", "orgType", "schoolType", "courseType"];
    var currentStatus = ${iMethod:toJSONlLine("currentStatus")};
    var serviceType = ${iMethod:toJSONlLine("serviceType")};
    var orgType = ${iMethod:toJSONlLine("orgType")};
    var schoolType = ${iMethod:toJSONlLine("schoolType")};
    var courseType = ${iMethod:toJSONlLine("courseType")};
    seajs.use(["controller/code"], function (ctrl) {
        ctrl.layout("codeLayout", codes, currentStatus, serviceType, orgType, schoolType, courseType);
    })
</script>