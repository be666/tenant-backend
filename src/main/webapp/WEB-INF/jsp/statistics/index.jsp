<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include.jsp" %>
<div class="col-md-9">
    <div class="panel panel-default" style="margin-top: 30px">
        <div class="panel-heading">
            统计分析
        </div>
        <div class="panel-body" id="statisticsPanel">
            <div class="row">
                <div class="col-md-6">
                    <i class="iMethod-btn iMethod-btn-menu" data-bi="tenant">租户统计</i>
                    <i class="iMethod-btn iMethod-btn-menu" data-bi="course">课程统计</i>
                </div>
            </div>
            <div class="row iMethod-toolbar">
                <div class="col-md-3">
                    <input type="text" class="iMethod-query">
                </div>
                <div class="col-md-3 iMethod-hide">
                    <input type="datetime" class="iMethod-start">
                </div>
                <div class="col-md-3 iMethod-hide">
                    <input type="datetime" class="iMethod-end">
                </div>
                <div class="col-md-2">
                    <i class="iMethod-btn iMethod-query-click">
                        查询
                    </i>
                </div>
            </div>
            <div class="row">

                <div class="bi-table">

                </div>
            </div>
        </div>
    </div>
</div>
<script>
    seajs.use(['controller/statistics'], function (statisticsCtl) {
        statisticsCtl.statisticsPanel("statisticsPanel");
    })
</script>