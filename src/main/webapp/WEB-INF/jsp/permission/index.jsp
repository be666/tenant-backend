<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include.jsp" %>
<div class="col-md-9">
    <div class="panel panel-default" style="margin-top: 30px">
        <div class="panel-heading">
            权限
        </div>
        <div class="panel-body" id="OsUserPanel">
            <div class="row">
                <div class="col-md-8">
                    <input type="text" name="search"/>
                    <i class="search">
                        查询
                    </i>
                </div>
                <div class="col-md-4">
                    <i class="btn iMethod-osUserAdd">
                        添加用户
                    </i>
                </div>
            </div>
            <div class="row">
                <div class="iMethod-OsUserTable">

                </div>
            </div>
        </div>
    </div>
</div>
<script>
    seajs.use(['controller/permission'], function (permissionCtl) {
        permissionCtl.osUserTable("OsUserPanel");
    })
</script>