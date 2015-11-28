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
    'view/tenant/info',
    "template"
], function (require, exports, module) {

    var userCtl = require("controller/user");
    var tenantService = require("service/tenant_service");
    var tenantInfo = require("view/tenant/info");
    var courseInfo = require("view/course/info");

    var utils = iMethod.utils;
    var _tenantTabId = null;
    var tableInit = function (pageMaker) {
        var tenantTab = $("#" + _tenantTabId);
        pageMaker = pageMaker || {};
        pageMaker['items'] = pageMaker['items'] || [];
        tenantTab.iMethodTable({

            dataList: pageMaker['items'],
            titles: [{
                key: "tenantId",
                name: "租户码"
            }, {
                key: "tenantName",
                name: "租户名称"
            }, {
                key: "startTime",
                name: "服务开通时间"
            }, {
                key: "endTime",
                name: "服务截止时间"
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

    exports.tenantTable = function (tenantTabId, currentStatus, serviceType) {
        _tenantTabId = tenantTabId;
        queryTenant();
        currentStatus = [{
            code: "",
            codeName: "请选择"
        }].concat(currentStatus);
        $(".iMethod-currentStatus").iMethodSelect({
            id: "code",
            text: "codeName",
            dataList: currentStatus || [],
            unSelected: {
                code: "",
                codeName: "请选择"
            }
        });
        serviceType = [{
            code: "",
            codeName: "请选择"
        }].concat(serviceType);
        $(".iMethod-serviceType").iMethodSelect({
            id: "code",
            text: "codeName",
            dataList: serviceType || [],
            unSelected: {
                code: "",
                codeName: "请选择"
            }
        });
    };


    var infoHash = iMethodHash();
    exports.info = function (tenant_info, course_info, currentStatus, serviceType, orgType, schoolType, region) {
        var $tenantInfo = $("#" + tenant_info);
        var $courseInfo = $("#" + course_info);

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
                codeName: "请选择"
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
                codeName: "请选择"
            }
        });

        $tenantInfo.find(".iMethod-schoolType").iMethodSelect({
            id: "code",
            text: "codeName",
            dataList: [],
            unSelected: {
                code: "",
                codeName: "请选择"
            }
        });

        $tenantInfo.find(".iMethod-province").iMethodSelect({
            id: "regionCode",
            text: "regionName",
            dataList: region,
            unSelected: {
                regionCode: "",
                regionName: "请选择"
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
                regionName: "请选择"
            }
        });

        $tenantInfo.find(".iMethod-serviceType").iMethodSelect({
            id: "code",
            text: "codeName",
            dataList: serviceType || [],
            unSelected: {
                code: "",
                codeName: "请选择"
            }
        });

    };
    iMethod.controller.tenant = module.exports;
});