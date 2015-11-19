/**
 * auth : iMethod
 * create_at: 15/11/14.
 * desc:
 * note:
 *  1.
 */
define('controller/tenant', [
    "controller/user",
    'service/tenant_service',
    'view/tenant/info',
    'view/course/info',
    "template"
], function (require, exports, module) {

    var userCtl = require("controller/user");
    var tenantService = require("service/tenant_service");
    var tenantInfo = require("view/tenant/info");
    var courseInfo = require("view/course/info");

    var _tenantTabId = null;
    var tableInit = function (pageMaker) {
        var tenantTab = $("#" + _tenantTabId);
        pageMaker = pageMaker || {};
        pageMaker['items'] = pageMaker['items'] || [];
        tenantTab.iMethodTable({
            dataList: pageMaker['items'],
            titles: null,
            page: {
                curPage: pageMaker['pageIndex'],
                pageSize: pageMaker['pageSize'],
                totalPage: pageMaker['totalPage'],
                rowCount: pageMaker['items'].length,
                pageClick: function (index, size) {
                    queryTenant(index, size);
                }
            }
        })
    };

    var queryTenant = function (index, size) {
        tenantService.queryTenant(function (dataMap) {
            var pageMaker = dataMap['pageMaker'];
            tableInit(pageMaker);
        }, {
            pageIndex: index,
            pageSize: size,
            currentStatus: null,
            currentStage: null
        });
    };

    exports.table = function (tenantTabId, pageMaker) {
        _tenantTabId = tenantTabId;
        tableInit(pageMaker);
    };


    var infoHash = iMethodHash();
    exports.info = function (tenant_info, course_info) {
        var $tenantInfo = $("#" + tenant_info);
        var $courseInfo = $("#" + course_info);
        $tenantInfo.html(tenantInfo());
        $courseInfo.html(courseInfo());
        $tenantInfo.on("click.select-sell", ".select-sell", function () {
            userCtl.selectUser(function (userList) {

            });
        });

    };
    iMethod.controller.tenant = module.exports;
});