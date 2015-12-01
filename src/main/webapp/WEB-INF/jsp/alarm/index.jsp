<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include.jsp" %>
<div class="col-md-9">
    <div class="panel panel-default" style="margin-top: 30px">
        <div class="panel-heading">
            预警
        </div>
        <div class="panel-body" id="alarmPanel">
            <div class="row">
                <div class="col-md-6">
                    <i class="iMethod-btn iMethod-btn-menu" data-alarm="tenant">租户预警</i>
                    <i class="iMethod-btn iMethod-btn-menu" data-alarm="course">课程预警</i>
                </div>
            </div>
            <div class="row iMethod-toolbar">
                <div class="col-md-3">
                    <input type="text" class="iMethod-query" placeholder="enter search for name">
                </div>
                <div class="col-md-2">
                    <div class="iMethod-expireStatue"></div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    当前全部预警租户
                    <i class="num countAll">0</i>，其中即将到期租户数
                    <i class="num count30">
                        0
                    </i>， 已过期租户数
                    <i class="num count20">
                        0
                    </i>
                </div>
            </div>
            <div class="row">

                <div class="alarm-table">

                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var expireStatue =${iMethod:toJSONlLine(expireStatue)};
    seajs.use(['controller/alarm'], function (alarmCtl) {
        alarmCtl.alarmPanel("alarmPanel", expireStatue);
    })
</script>
