/**
 * auth : iMethod
 * create_at: 15/11/14.
 * desc:
 * note:
 *  1.
 */
define('controller/tenant', ['service/tenant_service', "template"], function (require, exports, module) {

    var tenantService = require("service/tenant_service");

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
    iMethod.controller.tenant = module.exports;
});