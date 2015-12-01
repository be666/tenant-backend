/**
 * auth : iMethod
 * create_at: 15/11/20.
 * desc:
 * note:
 *  1.
 */
define('controller/org', [
    "view/org/info",
    "view/org/list_head",
    "view/org/list_body",
    'service/org_service',
    "template"
], function (require, exports, module) {
    var orgService = require("service/org_service");
    var orgListHead = require("view/org/list_head");
    var orgListBody = require("view/org/list_body");
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
            var totalPage = pageMaker['pageMax'];
            var rowCount = pageMaker['rowCount'];
            var pages = pageMaker['pageArr'];
            $orgTab.find(".iMethod-orgTable").iMethodTable({
                pk: "orgId",
                templateHead: orgListHead,
                templateBody: orgListBody,
                dataList: dateList,
                pageCols:"8",
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
                    pages: pages,
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
        iMethod.checkBox({
            root: $orgTab,
            all: ".select-all",
            item: ".select-item"
        })
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
            items = [{orgId: "-1", orgName: "无"}].concat(items);
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
            var org = {};
            org['orgCode'] = addDialog.target.find(".orgCode").eq(0).val();
            org['orgName'] = addDialog.target.find(".orgName").eq(0).val();
            org['orgPid'] = addDialog.target.find(".iMethod-org").iMethodSelect().getSelected()['orgId'] || "-1";
            org['orgType'] = addDialog.target.find(".iMethod-orgType").iMethodSelect().getSelected()['code'] || "";
            org['schoolType'] = addDialog.target.find(".iMethod-schoolType").iMethodSelect().getSelected()['code'] || "";
            org['province'] = addDialog.target.find(".iMethod-province").iMethodSelect().getSelected()['regionCode'] || "";
            org['city'] = addDialog.target.find(".iMethod-city").iMethodSelect().getSelected()['regionCode'] || "";
            orgService.saveOrg(org, function (org) {
                queryOrg();
            })
        })
    };

    /**
     * 查看
     */
    exports.view = function () {

    };

    exports.orgTable = function (orgTabId, orgType, schoolType, province) {
        _orgTabId = orgTabId;
        _orgType = orgType;
        _schoolType = schoolType;
        _province = province;
        var $orgTab = $("#" + orgTabId);
        queryOrg();
        $orgTab.on("click.iMethod-orgAdd", ".iMethod-orgAdd", function () {
            dialogInfo()
        });
        $orgTab.on("click.iMethod-orgSelect", ".user-manager", function () {
            var $this = $(this);
            var pk = $this.closest("tr").attr("data-pk");
            window.location.href = iMethod.contextPath + "/org/" + pk + "/user";
        });
        $orgTab.on("click.iMethod-orgSelect", ".org-edit", function () {

        });
    }
    iMethod.controller.org = module.exports;
});
