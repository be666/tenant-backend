/**
 * auth : bqxu
 * create_at: 15/11/20.
 * desc:
 * note:
 *  1.
 */
define('controller/org', [
    "view/org/info",
    "view/org/list",
    'service/org_service',
    "template"
], function (require, exports, module) {
    var orgService = require("service/org_service");
    var orgList = require("view/org/list");
    var orgInfo = require("view/org/info");
    var _orgTabId = null;

    var utils=iMethod.utils;
    /**
     * 机构列表
     * @param pageMaker
     */
    var table = function (pageMaker) {
        var orgTab = $("#" + _orgTabId);
        pageMaker = pageMaker || {};
        pageMaker['items'] = pageMaker['items'] || [];
        orgTab.iMethodTable({
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

    var selectCallback;
    var selectDialog;

    /**
     * 选择弹出层
     */
    exports.dialogOrg = function (callback) {
        selectCallback = callback;
        selectDialog = iMethod.dialog({
            className: "iMethod-dialog-org",
            title: "选择机构",
            content: orgList(),
            buttons: []
        });
        dialogTable();
        selectDialog.target.on("click.iMethod-orgAdd", ".iMethod-orgAdd", function () {
            dialogInfo()
        })
        selectDialog.target.on("click.iMethod-orgSelect", ".iMethod-orgSelect", function () {
            var user = {};
            selectCallback(user)
        })

    };

    var dialogTable = function (index, size) {
        orgService.queryOrgList(function (pageMarker) {
            var dateList = pageMarker['items'] || [];
            var curPage = pageMarker['curPage'];
            var pageSize = pageMarker['pageSize'];
            var totalPage = pageMarker['totalPage'];
            selectDialog.target.find(".iMethod-orgTable").iMethodTable({
                dataList: dateList,
                titles: null,
                page: {
                    curPage: curPage,
                    pageSize: pageSize,
                    totalPage: totalPage,
                    rowCount: dateList.length || 0,
                    pageClick: function (index, size) {
                        dialogTable(index, size)
                    }
                }
            });
        }, {
            pageIndex: index,
            pageSize: size
        });
    };


    var addDialog;
    /**
     * 新建,编辑
     */
    var dialogInfo = function () {
        addDialog = iMethod.dialog({
            className: "iMethod-dialog-addOrg",
            title: "添加机构",
            content: orgInfo(),
            buttons: [{
                className: "iMethod-sure",
                text: "添加"
            }, {
                className: "iMethod-cancel",
                text: "取消",
                click: function () {
                    addDialog.close();
                }
            }]
        });
        addDialog.target.on("click.iMethod-sure", ".iMethod-sure", function () {
            var org = $(".form-org", addDialog.target).serializeArray();
            orgService.saveOrg(utils.Array2Obj(org), function (org) {
                selectCallback(org);
            })
        })
    };

    /**
     * 查看
     */
    exports.view = function () {

    };

    iMethod.controller.org = module.exports;
});