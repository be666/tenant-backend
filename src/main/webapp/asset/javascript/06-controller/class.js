/**
 * auth : bqxu
 * create_at: 15/11/14.
 * desc:
 * note:
 *  1.
 */
define('controller/class', [
    'service/class_service',
    "template"
], function (require, exports, module) {


    var classService = require("service/class_service");

    var _classTabId = null;
    var tableInit = function (pageMaker) {
        var classTab = $("#" + _classTabId);
        pageMaker = pageMaker || {};
        pageMaker['items'] = pageMaker['items'] || [];
        classTab.iMethodTable({
            dataList: pageMaker['items'],
            titles: null,
            page: {
                curPage: pageMaker['pageIndex'],
                pageSize: pageMaker['pageSize'],
                totalPage: pageMaker['totalPage'],
                rowCount: pageMaker['items'].length,
                pageClick: function (index, size) {
                    queryCourse(index, size);
                }
            }
        })
    };

    var queryCourse = function (index, size) {
        classService.queryClass(function (dataMap) {
            var pageMaker = dataMap['pageMaker'];
            tableInit(pageMaker);
        }, {
            pageIndex: index,
            pageSize: size,
            currentStatus: null,
            currentStage: null
        });
    };

    exports.table = function (classTabId, pageMaker) {
        _classTabId = classTabId;
        tableInit(pageMaker);
    };

    iMethod.controller.class = module.exports;
});