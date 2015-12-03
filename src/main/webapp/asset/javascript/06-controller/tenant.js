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
    'service/serve_service',
    'service/org_service',
    'service/user_service',
    'view/tenant/info',
    'view/tenant/list_head',
    'view/tenant/list_body',
    'view/serve/info',
    'view/org/dialog',
    'view/user/dialog',
    "template"
], function (require, exports, module) {

    var userCtl = require("controller/user");
    var tenantService = require("service/tenant_service");
    var orgService = require("service/org_service");
    var serveService = require("service/serve_service");
    var userService = require("service/user_service");
    var tenantInfo = require("view/tenant/info");
    var serveInfo = require("view/serve/info");
    var orgDialog = require("view/org/dialog");
    var userDialog = require("view/user/dialog");

    var tenantListHead = require('view/tenant/list_head');
    var tenantListBody = require('view/tenant/list_body');
    var utils = iMethod.utils;
    var _tenantTabId = null;
    var tableInit = function (pageMaker) {
        var tenantTab = $("#" + _tenantTabId);
        pageMaker = pageMaker || {};
        pageMaker['items'] = pageMaker['items'] || [];
        tenantTab.iMethodTable({
            pk: "tenantId",
            pageCols: "10",
            templateHead: tenantListHead,
            templateBody: tenantListBody,
            dataList: pageMaker['items'],
            titles: [{
                key: "tenantId",
                name: "租户码"
            }, {
                key: "tenantName",
                name: "租户名称"
            }, {
                key: "startTime",
                name: "服务开通时间",
                render: function (obj, el) {
                    return utils.parseDate(el)
                }
            }, {
                key: "endTime",
                name: "服务截止时间",
                render: function (obj, el) {
                    return utils.parseDate(el)
                }
            }, {
                key: "currentStatusName",
                name: "当前状态"
            }, {
                key: "serviceTypeName",
                name: "服务类型"
            }, {
                key: "courseCount",
                name: "课程数"
            }, {
                key: "classCount",
                name: "班次数"
            }],
            page: {
                pageIndex: pageMaker['pageIndex'],
                pageSize: pageMaker['pageSize'],
                pages: pageMaker['pageArr'],
                totalPage: pageMaker['pageMax'],
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
            query: $(".iMethod-queryTenant").val() || "",
            pageIndex: index,
            pageSize: size,
            currentStatus: $(".iMethod-currentStatus").iMethodSelect().getSelected()['code'],
            serviceType: $(".iMethod-serviceType").iMethodSelect().getSelected()['code']
        });
    };

    exports.tenantTable = function (tenantTabId, currentStatus, serviceType) {
        _tenantTabId = tenantTabId;

        currentStatus = [{
            code: "",
            codeName: "选择状态"
        }].concat(currentStatus);

        $(".iMethod-currentStatus").iMethodSelect({
            id: "code",
            text: "codeName",
            dataList: currentStatus || [],
            unSelected: {
                code: "",
                codeName: "选择状态"
            },
            onChange: function () {
                queryTenant();
            }
        });
        serviceType = [{
            code: "",
            codeName: "选择服务类型"
        }].concat(serviceType);
        $(".iMethod-serviceType").iMethodSelect({
            id: "code",
            text: "codeName",
            dataList: serviceType || [],
            unSelected: {
                code: "",
                codeName: "选择服务类型"
            },
            onChange: function () {
                queryTenant();
            }
        });

        $(".iMethod-tenantAdd").on("click.tenantAdd", function () {
            window.location.href = iMethod.contextPath + "/tenant/new";
        });

        $("#" + _tenantTabId).on("click.course-manager", ".course-manager", function () {
            var $this = $(this);
            var tenantId = $this.closest("tr").attr("data-pk");
            window.location.href = iMethod.contextPath + "/tenant/" + tenantId + "/course";
        });

        $("#" + _tenantTabId).on("click.tenant-edit", ".tenant-edit", function () {
            var $this = $(this);
            var tenantId = $this.closest("tr").attr("data-pk");
            dialogTenantEdit(tenantId,serviceType);
        });

        var keyTimer;
        $(".iMethod-queryTenant").on("keyup.queryTenant", function () {
            var $this = $(this);
            if (event.keyCode == 13) {
                if (keyTimer != null) {
                    clearTimeout(keyTimer);
                }
                keyTimer = setTimeout(function () {
                    queryTenant();
                    clearTimeout(keyTimer);
                }, 500);
            }
        });
        queryTenant();
    };

    var dialogTenantEdit = function (tenantId,serviceType) {
        serveService.queryService(tenantId, "Tenant", function (dataMap) {
            var serve = dataMap['serve'];
            var serveDialog = iMethod.dialog({
                className: "iMethod-dialog-addOrg",
                title: "修改服务",
                content: serveInfo({
                    serve: serve
                }),
                buttons: [{
                    className: "iMethod-sure",
                    text: "添加"
                }, {
                    className: "iMethod-cancel",
                    text: "取消",
                    click: function () {
                        serveDialog.close();
                    }
                }]
            });

            serveDialog.target.find(".iMethod-start").val(new Date(serve['startTime']).Format("yyyy-MM-dd"));
            serveDialog.target.find(".iMethod-end").val(new Date(serve['endTime']).Format("yyyy-MM-dd"))
            serveDialog.target.find(".iMethod-serviceType").iMethodSelect({
                id: "code",
                text: "codeName",
                dataList: serviceType,
                unSelected: {
                    code: "",
                    codeName: "选择服务类型"
                },
                selected: {
                    code: serve['serviceType']
                }
            });
            serveDialog.target.on("click.iMethod-sure", ".iMethod-sure", function () {
                var _serve = {
                    serviceId: serve['serviceId']
                };
                _serve['startTime'] = new Date(serveDialog.target.find(".iMethod-start").val()).Format("yyyy-MM-dd 00:00:00");
                _serve['endTime'] = new Date(serveDialog.target.find(".iMethod-end").val()).Format("yyyy-MM-dd 00:00:00");
                _serve['serviceType'] = serveDialog.target.find(".iMethod-serviceType").iMethodSelect().getSelected()['code'];
                serveService.updateService(_serve, function (res) {
                    if (res.status == 1) {
                        serveDialog.close();
                        queryTenant();
                    } else if (res['msg']) {
                        iMethod.alert(res['msg']);
                    }
                });
            })
        })
    };


    var infoHash = iMethodHash();
    var _serviceType;
    exports.info = function (tenant_info, course_info, currentStatus, serviceType, orgType, schoolType, region) {
        var $tenantInfo = $("#" + tenant_info);
        var $courseInfo = $("#" + course_info);
        _serviceType = serviceType;
        $tenantInfo.html(tenantInfo());
        $courseInfo.html(courseInfo());
        $tenantInfo.on("click.select-sell", ".select-sell", function () {
            userCtl.selectUser(function (userList) {

            });
        });

        $tenantInfo.find(".iMethod-orgType").iMethodSelect({
            id: "code",
            text: "codeName",
            dataList: orgType,
            unSelected: {
                code: "",
                codeName: "选择机构类型"
            },
            onChange: function (obj) {
                if (obj['code'] == "10") {
                    $tenantInfo.find(".iMethod-schoolType").iMethodSelect({
                        dataList: schoolType
                    });
                    $tenantInfo.find(".iMethod-school").removeClass("iMethod-hide");
                } else {
                    $tenantInfo.find(".iMethod-schoolType").iMethodSelect({
                        dataList: []
                    });
                    $tenantInfo.find(".iMethod-school").addClass("iMethod-hide");
                }
            }
        });

        $tenantInfo.find(".iMethod-schoolType").iMethodSelect({
            id: "code",
            text: "codeName",
            dataList: [],
            unSelected: {
                code: "",
                codeName: "选择学校类型"
            }
        });

        $tenantInfo.find(".iMethod-schoolType").iMethodSelect({
            id: "code",
            text: "codeName",
            dataList: [],
            unSelected: {
                code: "",
                codeName: "选择学校类型"
            }
        });

        $tenantInfo.find(".iMethod-province").iMethodSelect({
            id: "regionCode",
            text: "regionName",
            dataList: region,
            unSelected: {
                regionCode: "",
                regionName: "选择省"
            },
            onChange: function (obj) {
                var _city = [];
                if (obj['regionCode'] != "") {
                    obj = utils.arrFind(region, function (el, i, arr) {
                        return el['regionCode'] == obj['regionCode'] ? el : null;
                    });
                    _city = obj[0]['childRegion'];
                }
                $tenantInfo.find(".iMethod-city").iMethodSelect({
                    dataList: _city
                })
            }
        });

        $tenantInfo.find(".iMethod-city").iMethodSelect({
            id: "regionCode",
            text: "regionName",
            dataList: [],
            unSelected: {
                regionCode: "",
                regionName: "选择市"
            }
        });

        $tenantInfo.find(".iMethod-serviceType").iMethodSelect({
            id: "code",
            text: "codeName",
            dataList: serviceType || [],
            unSelected: {
                code: "",
                codeName: "选择服务类型"
            }
        });

    };


    var selectOrg = function (callback) {

        var selectDialog = iMethod.dialog({
            className: "iMethod-dialog-addOrg",
            title: "选择机构",
            content: orgDialog(),
            buttons: []
        });
        var dateList;
        var queryOrg = function (index, size) {
            orgService.queryOrgList(function (dataMap) {
                var pageMaker = dataMap['pageMaker'];
                dateList = pageMaker['items'] || [];
                var pageIndex = pageMaker['pageIndex'];
                var pageSize = pageMaker['pageSize'];
                var totalPage = pageMaker['pageMax'];
                var rowCount = pageMaker['rowCount'];
                var pages = pageMaker['pageArr'];
                selectDialog.target.find(".iMethod-orgTable").iMethodTable({
                    pk: "orgId",
                    dataList: dateList,
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
                selectDialog.position();
            }, {
                pageIndex: index,
                pageSize: size
            });
        };

        selectDialog.target.on("click.iMethod-orgTable", ".iMethod-orgTable tr", function () {
            var $this = $(this);
            var orgId = $this.attr("data-pk");
            if (!utils.isEmptyStr(orgId)) {
                var org = utils.arrFind(dateList, function (el, i, arr) {
                        return el['orgId'] == orgId ? el : null;
                    })[0] || {orgId: orgId};
                callback(org);
                selectDialog.close();
            }
        });
        queryOrg();
    };

    var selectSUser = function (orgId, callback) {
        var selectDialog = iMethod.dialog({
            className: "iMethod-dialog-addOrg",
            title: "选择人员",
            content: userDialog(),
            buttons: []
        });
        var dateList;
        var queryUser = function (index, size) {
            userService.queryOrgUserList(orgId, function (dataMap) {
                var pageMaker = dataMap['pageMaker'];
                dateList = pageMaker['items'] || [];
                var pageIndex = pageMaker['pageIndex'];
                var pageSize = pageMaker['pageSize'];
                var totalPage = pageMaker['pageMax'];
                var rowCount = pageMaker['rowCount'];
                var pages = pageMaker['pageArr'];
                selectDialog.target.find(".iMethod-userTable").iMethodTable({
                    pk: "userId",
                    dataList: dateList,
                    titles: [{
                        key: "userCode",
                        name: '用户编号'
                    }, {
                        key: "userName",
                        name: '用户名'
                    }, {
                        key: "orgId",
                        name: '机构名称'
                    }, {
                        key: "mobile",
                        name: '电话'
                    }, {
                        key: "email",
                        name: '电子邮件'
                    }, {
                        key: "gender",
                        name: '性别'
                    }],
                    page: {
                        pageIndex: pageIndex,
                        pageSize: pageSize,
                        totalPage: totalPage,
                        pages: pages,
                        rowCount: dateList.length || 0,
                        pageClick: function (index, size) {
                            queryUser(index, size)
                        }
                    }
                });
                selectDialog.position();
            }, {
                pageIndex: index,
                pageSize: size
            });
        };

        selectDialog.target.on("click.iMethod-userTable", ".iMethod-userTable tr", function () {
            var $this = $(this);
            var userId = $this.attr("data-pk");
            if (!utils.isEmptyStr(userId)) {
                var user = utils.arrFind(dateList, function (el, i, arr) {
                        return el['userId'] == userId ? el : null;
                    })[0] || {userId: userId};
                callback(user);
                selectDialog.close();
            }
        });

        queryUser();
    };
    var _orgType;
    var _schoolType;
    var _province;
    exports.tenantInfo = function (tenant_info, currentStatus, serviceType, orgType, schoolType, province) {
        var $tenantInfo = $("#" + tenant_info);
        _orgType = orgType;
        _schoolType = schoolType;
        _province = province;
        $tenantInfo.html(tenantInfo());
        $tenantInfo.on("click.select-schoolOrg", ".iMethod-schoolOrg", function () {
            var $this = $(this);
            selectOrg(function (org) {
                $this.val(org['orgName']);
                $this.attr("data-orgId", org['orgId'])
            });
        });

        $tenantInfo.on("click.select-sellOrg", ".iMethod-sellOrg", function () {
            var $this = $(this);
            selectOrg(function (org) {
                $this.val(org['orgName']);
                $this.attr("data-orgId", org['orgId'])
            });
        });

        $tenantInfo.on("click.select-managerOrg", ".iMethod-managerOrg", function () {
            var $this = $(this);
            selectOrg(function (org) {
                $this.val(org['orgName']);
                $this.attr("data-orgId", org['orgId'])
            });
        });

        $tenantInfo.on("click.select-schoolUser", ".iMethod-schoolUser", function () {
            var $this = $(this);
            var orgId = $tenantInfo.find(".iMethod-schoolOrg").attr("data-orgId");
            selectSUser(orgId, function (user) {
                $(".school-user-warp").html(user['userName']);
                $this.attr("data-userId", user['userId'])
            });
        });
        $tenantInfo.on("click.select-sellUser", ".iMethod-sellUser", function () {
            var $this = $(this);
            var orgId = $tenantInfo.find(".iMethod-sellOrg").attr("data-orgId");
            selectSUser(orgId, function (user) {
                $(".sell-user-warp").html(user['userName']);
                $this.attr("data-userId", user['userId'])
            });
        });

        $tenantInfo.on("click.select-manageUser", ".iMethod-managerUser", function () {
            var $this = $(this);
            var orgId = $tenantInfo.find(".iMethod-managerOrg").attr("data-orgId");
            selectSUser(orgId, function (user) {
                $(".manager-user-warp").html(user['userName']);
                $this.attr("data-userId", user['userId'])
            });
        });
        $tenantInfo.on("click.select-managerSell", ".iMethod-managerSell", function () {
            var $this = $(this);
            var orgId = $tenantInfo.find(".iMethod-sellOrg").attr("data-orgId");
            selectSUser(orgId, function (user) {
                $this.val(user['userName']);
                $this.attr("data-userId", user['userId'])
            });
        });

        $tenantInfo.on("click.select-service", ".iMethod-service", function () {
            var $this = $(this);
            var orgId = $tenantInfo.find(".iMethod-managerOrg").attr("data-orgId");
            selectSUser(orgId, function (user) {
                $this.val(user['userName']);
                $this.attr("data-userId", user['userId'])
            });
        });

        $tenantInfo.find(".iMethod-serviceType").iMethodSelect({
            id: "code",
            text: "codeName",
            dataList: serviceType || [],
            unSelected: {
                code: "",
                codeName: "选择服务类型"
            }
        });

        $tenantInfo.find(".iMethod-currentStatus").iMethodSelect({
            id: "code",
            text: "codeName",
            dataList: currentStatus || [],
            unSelected: {
                code: "",
                codeName: "选择状态"
            }
        });

        $tenantInfo.on("click.cancel-tenant", ".cancel-tenant", function () {

        });
        $tenantInfo.on("click.save-tenant", ".save-tenant", function () {
            var tenant = {};
            tenant['shortName'] = $tenantInfo.find(".iMethod-shortName").val();
            tenant['tenantName'] = $tenantInfo.find(".iMethod-schoolOrg").val();
            tenant['orgName'] = $tenantInfo.find(".iMethod-shortName").val();
            tenant['serviceType'] = $tenantInfo.find(".iMethod-serviceType").iMethodSelect().getSelected()['code'];
            tenant['schoolOrg'] = $tenantInfo.find(".iMethod-schoolOrg").attr("data-orgId");
            tenant['schoolUser'] = $tenantInfo.find(".iMethod-schoolUser").attr("data-userId");
            tenant['sellOrg'] = $tenantInfo.find(".iMethod-sellOrg").attr("data-orgId");
            tenant['sellUser'] = $tenantInfo.find(".iMethod-sellUser").attr("data-userId");
            tenant['managerOrg'] = $tenantInfo.find(".iMethod-managerOrg").attr("data-orgId");
            tenant['managerUser'] = $tenantInfo.find(".iMethod-managerUser").attr("data-userId");
            tenant['managerSell'] = $tenantInfo.find(".iMethod-managerSell").attr("data-userId");
            tenant['serviceUser'] = $tenantInfo.find(".iMethod-service").attr("data-userId");
            tenant['resourceService'] = $tenantInfo.find(".resource-service").is("check") ? 1 : 0;
            tenant['scoreService'] = $tenantInfo.find(".score-service").is("check") ? 1 : 0;
            tenant['tenantTime'] = new Date($tenantInfo.find(".tenantTime").val()).Format("yyyy-MM-dd 00:00:00");
            tenant['currentStatus'] = $tenantInfo.find(".iMethod-currentStatus").iMethodSelect().getSelected()['code'];
            tenantService.saveTenant(tenant, function (res) {
                if (res.status == 1) {
                    window.location.href = iMethod.contextPath + "/tenant";
                } else {
                    alert(res['msg'])
                }
            })
        });

        $tenantInfo.on("click.iMethod-check", ".iMethod-check", function () {
            var $this = $(this);
            $this.toggleClass("check");
        })
    };


    iMethod.controller.tenant = module.exports;
});