<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 15/10/16
  Time: 10:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="header iMethod-header">
  <div class="iMethod-header-content clear-fix">
    <div class="iMethod-header-left">
      <div class="iMethod-logo">
        <a href="#">
          <img class="logo-img" alt=""/>
        </a>
      </div>

    </div>
    <div class="iMethod-header-right">

      <div class="iMethod-user-info" id="iMethodUser">
        <sapn class="text">
          <span class="user-name">${LUser.user.userName}</span>
          <i class="iMethod-icon-toggle"></i>
          <i class="iMethod-icon-shadow"></i>
          <ul class="dropdown-nav">
            <li><a href="#">个人设置</a></li>
            <li><a href="#">退出</a></li>
          </ul>
        </sapn>
      </div>
    </div>
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