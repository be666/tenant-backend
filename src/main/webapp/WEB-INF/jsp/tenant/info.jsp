<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include.jsp" %>
租户管理 》 租户列表 》新建租户
<div class="col-md-9">
    <div class="panel panel-default" style="margin-top: 30px">
        <div class="panel-heading">
            新建租户
        </div>
        <div class="panel-body">
            <div class="row">
                <div class="col-md-4 step_1">第一步:创建租户</div>
                <div class="col-md-4 step_2">第二步:课程管理</div>
                <div class="col-md-4 step_3">第三步:完成</div>
            </div>
        </div>
        <div class="panel-body">
            <div class="table" id="tenant_info">


            </div>
            <div class="table" id="course_info">


            </div>
        </div>
    </div>
</div>
<script>
    seajs.use(['controller/tenant'], function (tenantCtl) {
        tenantCtl.info("tenant_info", "course_info")
    })
</script>