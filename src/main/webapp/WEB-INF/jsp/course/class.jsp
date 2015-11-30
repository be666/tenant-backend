<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include.jsp" %>
<div class="col-md-9">
    <div class="panel panel-default" style="margin-top: 30px">
        <div class="panel-heading">
            班次
        </div>
        <div class="panel-body">
            <div class="row">
                <div class="col-md-2">
                    <input type="text" class="iMethod-queryClass">
                </div>
                <div class="col-md-2">
                    <div class="iMethod-currentStatus"></div>
                </div>
                <div class="col-md-2">
                    <i class="iMethod-btn iMethod-courseClassAdd">
                        新建班次
                    </i>
                </div>
            </div>
            <div class="row">
                <div id="courseClassTab">

                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var finishStatus =${iMethod:toJSONlLine(finishStatus)};
    seajs.use(["controller/class"], function (classCtl) {
        $(function () {
            classCtl.courseClassTab('courseClassTab',"${courseId}", finishStatus);
        })
    })
</script>