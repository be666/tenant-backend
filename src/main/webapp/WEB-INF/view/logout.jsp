<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title><tiles:getAsString name="title" defaultValue="imethod"/></title>
    <%--user-scalable=no 移动 禁用缩放--%>
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link rel="stylesheet" type="text/css" href="${contextPath}/css/boot.min.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/css/imethod.min.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/css/plugin.min.css"/>
    <script src="${contextPath}/js/lib.min.js" id="seajsnode"></script>
    <script src="${contextPath}/js/core.min.js"></script>
    <script>
        seajs.config({
            base: "${contextPath}/js"
        });
        iMethod.contextPath = "${contextPath}";
    </script>
</head>
<body>
<div class="iMethod-header-warp">
    <div class="row iMethod-header clear-fix">
        <div class="col-md-8 iMethod-header-left">
            <div class="iMethod-logo">
                <a href="${contextPath}/">
                    高校邦租户管理系统
                </a>
            </div>
        </div>
    </div>
</div>
<div class="iMethod-body">
    <div class="container">
        <div class="row" style="text-align: center;font-size: 32px;line-height: 1.5">
            您已登出系统,欢迎使用!
        </div>
        <div class="row" style="text-align: center">
            <div class="iMethod-btn" style="padding: 20px 40px;font-size: 32px;line-height: 1.5">
                <a href="${contextPath}/login">重新登陆</a>!
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
<script src="${contextPath}/js/ui.min.js"></script>
<script src="${contextPath}/js/config.min.js"></script>
<script src="${contextPath}/js/plugin.min.js"></script>
<script>
    console.log("iMethod 1.0 ");
</script>
</body>
</html>
