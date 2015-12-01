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
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<div class="body"></div>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
<script src="${contextPath}/js/ui.min.js"></script>
<script src="${contextPath}/js/config.min.js"></script>
<script src="${contextPath}/js/plugin.min.js"></script>
<script>
    console.log("iMethod 1.0 ");
</script>
</body>
</html>
