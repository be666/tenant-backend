<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include.jsp" %>
<div class="col-md-3">
    <div class="iMethod-nav" id="iMethodNav">

    </div>
</div>
<script>
    seajs.use(['controller/common/portal'], function (portalCtl) {
        $(function () {
            portalCtl.loadMenu("iMethodNav");
        })
    });
</script>