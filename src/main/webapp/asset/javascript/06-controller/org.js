/**
 * auth : iMethod
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
    var _orgType = null;
    var _schoolType = null;
    var _province = null;

    var utils = iMethod.utils;

    var selectCallback;
    var selectDialog;

    var queryOrg = function (index, size) {
        var $orgTab = $("#" + _orgTabId);
        orgService.queryOrgList(function (dataMap) {
            var pageMaker = dataMap['pageMaker'];
            var dateList = pageMaker['items'] || [];
            var pageIndex = pageMaker['pageIndex'];
            var pageSize = pageMaker['pageSize'];
            var totalPage = pageMaker['totalPage'];
            $orgTab.find(".iMethod-orgTable").iMethodTable({
                dataList: dateList,
                titles: [{
                    key: "orgCode",
                    name: '机构代码'
                }, {
                    key: "orgName",
                    name: '机构名称'
                }, {
                    key: "orgType",
                    name: '机构类型'
                }, {
                    key: "schoolType",
                    name: '学校类型'
                }, {
                    key: "province",
                    name: '省'
                }, {
                    key: "city",
                    name: '市'
                }],
                page: {
                    pageIndex: pageIndex,
                    pageSize: pageSize,
                    totalPage: totalPage,
                    rowCount: dateList.length || 0,
                    pageClick: function (index, size) {
                        queryOrg(index, size)
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
        orgService.queryOrgList(function (dataMap) {
            var pageMaker = dataMap['pageMaker'];
            var items = pageMaker['items'];
            addDialog.target.find(".iMethod-org").iMethodSelect({
                id: "orgId",
                text: "orgName",
                dataList: items,
                unSelected: {
                    orgId: "",
                    orgName: "请选择"
                }
            });
        }, {
            pageIndex: 0,
            pageSize: 999
        });

        addDialog.target.find(".iMethod-orgType").iMethodSelect({
            id: "code",
            text: "codeName",
            dataList: _orgType,
            unSelected: {
                code: "",
                codeName: "请选择"
            },
            onChange: function (obj) {
                var _city = [];
                if (obj['code'] == "10") {
                    addDialog.target.find(".iMethod-schoolType").iMethodSelect({
                        dataList: _schoolType
                    })
                } else {
                    addDialog.target.find(".iMethod-schoolType").iMethodSelect({
                        dataList: []
                    })
                }
            }
        });

        addDialog.target.find(".iMethod-schoolType").iMethodSelect({
            id: "code",
            text: "codeName",
            dataList: [],
            unSelected: {
                code: "",
                codeName: "请选择"
            }
        });

        addDialog.target.find(".iMethod-schoolType").iMethodSelect({
            id: "code",
            text: "codeName",
            dataList: _schoolType,
            unSelected: {
                code: "",
                codeName: "请选择"
            }
        });

        addDialog.target.find(".iMethod-province").iMethodSelect({
            id: "regionCode",
            text: "regionName",
            dataList: _province,
            unSelected: {
                regionCode: "",
                regionName: "请选择"
            },
            onChange: function (obj) {
                var _city = [];
                if (obj['regionCode'] != "") {
                    obj = utils.arrFind(_province, function (el, i, arr) {
                        return el['regionCode'] == obj['regionCode'] ? el : null;
                    });
                    _city = obj[0]['childRegion'];
                }
                addDialog.target.find(".iMethod-city").iMethodSelect({
                    dataList: _city
                })
            }
        });

        addDialog.target.find(".iMethod-city").iMethodSelect({
            id: "regionCode",
            text: "regionName",
            dataList: [],
            unSelected: {
                regionCode: "",
                regionName: "请选择"
            }
        });


        addDialog.target.on("click.iMethod-sure", ".iMethod-sure", function () {
            var org = $(".form-org", addDialog.target).serializeArray();
            orgService.saveOrg(utils.Array2Obj(org), function (org) {

            })
        })
    };

    /**
     * 查看
     */
    exports.view = function () {

    };

    exports.table = function (orgTabId, orgType, schoolType, province) {
        _orgTabId = orgTabId;
        _orgType = orgType;
        _schoolType = schoolType;
        _province = province;
        var $orgTab = $("#" + orgTabId);
        queryOrg();
        $orgTab.on("click.iMethod-orgAdd", ".iMethod-orgAdd", function () {
            dialogInfo()
        })
        $orgTab.on("click.iMethod-orgSelect", ".iMethod-orgSelect", function () {
            var user = {};
            selectCallback(user)
        })
    }
    iMethod.controller.org = module.exports;
});
