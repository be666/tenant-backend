<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include.jsp" %>
<div class="col-md-9">
    <div class="panel panel-default" style="margin-top: 30px">
        <div class="panel-heading">
            组织机构
        </div>
        <div class="panel-body" id="orgPanel">
            <div class="row iMethod-toolbar">
                <div class="col-md-8">
                    <input type="text" name="search"/>
                    <i class="search">
                        查询
                    </i>
                </div>
                <div class="col-md-4">
                    <i class="iMethod-btn iMethod-btn-action iMethod-orgAdd">
                        添加机构
                    </i>
                </div>
            </div>
            <div class="row">
                <div class="iMethod-orgTable">

                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">

    var orgType =${iMethod:toJSONlLine(orgType)};
    var schoolType =${iMethod:toJSONlLine(schoolType)};
    var region =${iMethod:toJSONlLine(region)};
    seajs.use(['controller/org'], function (orgCtl) {
        $(function () {
            orgCtl.orgTable("orgPanel", orgType, schoolType, region);
        })
    })

</script>
