<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include.jsp" %>
<div class="col-md-3">
    <div class="panel panel-default" style="margin-top: 30px">
        <div class="panel-body">
            <div class="iMethod-nav" id="iMethodNav">

            </div>
        </div>
    </div>
</div>
<script>
    seajs.use(['controller/common/portal'], function (portalCtl) {
        $(function () {
            portalCtl.loadMenu("iMethodNav");
        })
    });
</script>