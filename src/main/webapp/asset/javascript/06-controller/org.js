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
                pageCols: "8",
                titles: [{
                    key: "orgCode",
                    name: '机构代码'
                }, {
                    key: "orgName",
                    name: '机构名称'
                }, {
                    key: "orgType",
                    name: '机构类型',
                    render: function (org, orgType) {
                        var _l = _orgType.length;
                        while (_l > 0) {
                            _l--;
                            var _ot = _orgType[_l];
                            if (_ot['code'] == orgType) {
                                return _ot['codeName'];
                            }
                        }
                        if (orgType == 0) {
                            return "root";
                        }
                        return "其他";
                    }
                }, {
                    key: "schoolType",
                    name: '学校类型',
                    render: function (org, schoolType) {
                        var _l = _schoolType.length;
                        while (_l > 0) {
                            _l--;
                            var _ot = _schoolType[_l];
                            if (_ot['code'] == schoolType) {
                                return _ot['codeName'];
                            }
                        }
                        return "其他";
                    }
                }, {
                    key: "province",
                    name: '省',
                    render: function (org, province) {
                        var _l = _province.length;
                        while (_l > 0) {
                            _l--;
                            var _ot = _province[_l];
                            if (_ot['regionCode'] == province) {
                                return _ot['regionName'];
                            }
                        }
                        return "其他";
                    }
                }, {
                    key: "city",
                    name: '市',
                    render: function (org, city) {
                        var _l = _province.length;
                        while (_l > 0) {
                            _l--;
                            var _ot = _province[_l];
                            var _city = _ot['childRegion'] || [];
                            var _cl = _city.length;
                            while (_cl) {
                                _cl--;
                                var _cot = _city[_cl];
                                if (_cot['regionCode'] == city) {
                                    return _cot['regionName'];
                                }
                            }

                        }
                        return "其他";
                    }
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

    /**
     * 新建,编辑
     */
    var dialogInfo = function () {
        var addDialog = iMethod.dialog({
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
                    orgName: "选择机构"
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
                codeName: "选择机构类型"
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
                codeName: "选择学校类型"
            }
        });

        addDialog.target.find(".iMethod-province").iMethodSelect({
            id: "regionCode",
            text: "regionName",
            dataList: _province,
            unSelected: {
                regionCode: "",
                regionName: "选择省"
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
                regionName: "选择市"
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
                addDialog.close();
                queryOrg();
            })
        })
    };


    var dialogInfoEdit = function (orgId) {
        orgService.queryOrg(orgId, function (dataMap) {
            var org = dataMap['org'];
            var editDialog = iMethod.dialog({
                className: "iMethod-dialog-addOrg",
                title: "添加机构",
                content: orgInfo({
                    org: org
                }),
                buttons: [{
                    className: "iMethod-sure",
                    text: "添加"
                }, {
                    className: "iMethod-cancel",
                    text: "取消",
                    click: function () {
                        editDialog.close();
                    }
                }]
            });
            orgService.queryOrgList(function (dataMap) {
                var pageMaker = dataMap['pageMaker'];
                var items = pageMaker['items'];
                items = [{orgId: "-1", orgName: "无"}].concat(items);
                editDialog.target.find(".iMethod-org").iMethodSelect({
                    id: "orgId",
                    text: "orgName",
                    dataList: items,
                    unSelected: {
                        orgId: "",
                        orgName: "选择机构"
                    },
                    selected: {
                        orgId: org['orgPid']
                    }
                });
            }, {
                pageIndex: 0,
                pageSize: 999
            });

            editDialog.target.find(".iMethod-orgType").iMethodSelect({
                id: "code",
                text: "codeName",
                dataList: _orgType,
                unSelected: {
                    code: "",
                    codeName: "选择机构类型"
                },
                selected: {
                    code: org['orgType']
                },
                onChange: function (obj) {
                    var _city = [];
                    if (obj['code'] == "10") {
                        editDialog.target.find(".iMethod-schoolType").iMethodSelect({
                            dataList: _schoolType
                        })
                    } else {
                        editDialog.target.find(".iMethod-schoolType").iMethodSelect({
                            dataList: []
                        })
                    }
                }
            });


            editDialog.target.find(".iMethod-schoolType").iMethodSelect({
                id: "code",
                text: "codeName",
                dataList: _schoolType,
                unSelected: {
                    code: "",
                    codeName: "选择学校类型"
                },
                selected: {
                    code: org['schoolType']
                }
            });

            editDialog.target.find(".iMethod-province").iMethodSelect({
                id: "regionCode",
                text: "regionName",
                dataList: _province,
                unSelected: {
                    regionCode: "",
                    regionName: "选择省"
                },
                selected: {
                    regionCode: org['province']
                },
                onChange: function (obj) {
                    var _city = [];
                    if (obj['regionCode'] != "") {
                        obj = utils.arrFind(_province, function (el, i, arr) {
                            return el['regionCode'] == obj['regionCode'] ? el : null;
                        });
                        _city = obj[0]['childRegion'];
                    }
                    editDialog.target.find(".iMethod-city").iMethodSelect({
                        dataList: _city,
                        selected: {
                            regionCode: org['city']
                        }
                    })
                }
            });

            editDialog.target.find(".iMethod-city").iMethodSelect({
                id: "regionCode",
                text: "regionName",
                dataList: [],
                selected: {
                    regionCode: org['city']
                },
                unSelected: {
                    regionCode: "",
                    regionName: "选择市"
                }
            });


            editDialog.target.on("click.iMethod-sure", ".iMethod-sure", function () {
                var _org = {
                    orgId: org['orgId']
                }
                _org['orgCode'] = editDialog.target.find(".orgCode").eq(0).val();
                _org['orgName'] = editDialog.target.find(".orgName").eq(0).val();
                _org['orgPid'] = editDialog.target.find(".iMethod-org").iMethodSelect().getSelected()['orgId'] || "-1";
                _org['orgType'] = editDialog.target.find(".iMethod-orgType").iMethodSelect().getSelected()['code'] || "";
                _org['schoolType'] = editDialog.target.find(".iMethod-schoolType").iMethodSelect().getSelected()['code'] || "";
                _org['province'] = editDialog.target.find(".iMethod-province").iMethodSelect().getSelected()['regionCode'] || "";
                _org['city'] = editDialog.target.find(".iMethod-city").iMethodSelect().getSelected()['regionCode'] || "";
                orgService.updateOrg(_org, function (org) {
                    editDialog.close();
                    queryOrg();
                })
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
            var $this = $(this);
            var pk = $this.closest("tr").attr("data-pk");
            dialogInfoEdit(pk);
        });
    }
    iMethod.controller.org = module.exports;
});
