<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include.jsp" %>
<div class="col-md-9">
    <div class="panel panel-default" style="margin-top: 30px">
        <div class="panel-heading">
            组织机构>人员管理
        </div>
        <div class="panel-body" id="orgUserPanel">
            <div class="row">
                <div class="col-md-8">
                    <input type="text" name="search"/>
                    <i class="search">
                        查询
                    </i>
                </div>
                <div class="col-md-4">
                    <i class="btn iMethod-orgUserAdd">
                        添加人员
                    </i>
                </div>
            </div>
            <div class="row">
                <div class="iMethod-orgUserTable">

                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var orgId =${orgId};
    var org =${iMethod:toJSONlLine(org)};
    seajs.use(['controller/user'], function (userCtl) {
        $(function () {
            userCtl.orgUserTable("orgUserPanel", orgId, org);
        })
    })

</script>
