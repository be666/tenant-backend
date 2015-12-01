<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 15/10/16
  Time: 10:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="iMethod-header-warp">
    <div class="row iMethod-header clear-fix">
        <div class="col-md-8 iMethod-header-left">
            <div class="iMethod-logo">
                <a href="#">
                    <img class="logo-img" alt=""/>
                </a>
            </div>

        </div>
        <c:if test="${!LUser.guest}">
            <div class="col-md-4 iMethod-header-right">

                <div class="iMethod-user-info" id="iMethodUser">

                    <sapn class="text">
                        <span class="user-name">${LUser.user.userName}</span>
                        <span class="nav-toggle">&#9660</span>
                        <ul class="iMethod-hide">
                            <li><a href="#">个人设置</a></li>
                            <li><a href="#">退出</a></li>
                        </ul>
                    </sapn>
                </div>
            </div>
        </c:if>
    </div>
</div>
<script>
    $(function () {
        var $toggle = $(".iMethod-icon-toggle", "#iMethodUser");
        $toggle.on("click", function () {
            $(".text").toggleClass("open");
        })
    })
</script>