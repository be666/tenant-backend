<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include.jsp" %>
<div class="col-md-9">
    <div class="panel panel-default" style="margin-top: 30px">
        <div class="panel-heading">
            班次
        </div>
        <div class="panel-body">
            <div class="row iMethod-toolbar">
                <div class="col-md-3">
                    <input type="text" class="iMethod-queryClass" placeholder="enter for search">
                </div>
                <div class="col-md-2">
                    <div class="iMethod-currentStatus"></div>
                </div>
                <div class="col-md-2">
                    <div class="iMethod-tenant"></div>
                </div>
                <div class="col-md-2">
                    <div class="iMethod-course"></div>
                </div>
            </div>
            <div class="row">
                <div id="classTab">

                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var courseList =${iMethod:toJSONlLine(courseList)};
    var tenantList =${iMethod:toJSONlLine(tenantList)};
    var finishStatus =${iMethod:toJSONlLine(finishStatus)};
    seajs.use(["controller/class"], function (classCtl) {
        $(function () {
            classCtl.classTab('classTab', courseList, tenantList, finishStatus);
        })
    })
</script>