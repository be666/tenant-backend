<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include.jsp" %>
<style>
    .iMethod-btn-panel {
        padding: 20px 30px;
        background-color: #fcf8e3;
        border-color: #faebcc;
        border-radius: 5px;
        margin-right: 50px;
    }
</style>
<div class="col-md-9">
    <div class="panel panel-default" style="margin-top: 30px ;padding: 20px 50px 300px 50px">
        <div class="row" style="line-height: 1.5;font-size: 28px">
            高校邦欢迎你！
        </div>
        <div class="row" style="margin: 40px auto  0 auto">
            您可以进行如下快捷操作：
        </div>

        <div class="row" style="margin: 10px 30px;">
            <div class="iMethod-btn iMethod-btn-panel">
                <a href="${contextPath}/org">
                    快速创建用户
                </a>

            </div>
            <div class="iMethod-btn iMethod-btn-panel">
                <a href="${contextPath}/alarm">
                    预警管理
                </a>
            </div>
            <div class="iMethod-btn iMethod-btn-panel">
                <a href="${contextPath}/bi">
                    BI统计查阅
                </a>
            </div>
        </div>
    </div>
</div>
