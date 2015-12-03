/**
 * auth : iMethod
 * create_at: 15/11/15.
 * desc:
 * note:
 *  1.
 */
define('controller/alarm', [
    'service/alarm_service',
    "view/alarm/alarm_course_body",
    "view/alarm/alarm_course_head",
    "view/alarm/alarm_tenant_body",
    "view/alarm/alarm_tenant_head",
    "template"
], function (require, exports, module) {

    var _alarmPanel;
    var _expireStatue;
    var utils = iMethod.utils;

    var alarm_course_body = require("view/alarm/alarm_course_body");
    var alarm_course_head = require("view/alarm/alarm_course_head");
    var alarm_tenant_body = require("view/alarm/alarm_tenant_body");
    var alarm_tenant_body = require("view/alarm/alarm_tenant_body");
    var alarmService = require("service/alarm_service");

    var _hash = new iMethodHash();
    exports.alarmPanel = function (alarmPanel, expireStatue) {
        _alarmPanel = alarmPanel;
        _expireStatue = expireStatue;
        publishAlarmHash();
        initAlarmHash();
        var _dd = [{
            code: "",
            codeName: "请选择"
        }].concat(_expireStatue);
        $("#" + _alarmPanel).find(".iMethod-expireStatue").iMethodSelect({
            id: "code",
            text: "codeName",
            dataList: _dd,
            unSelected: {
                code: "",
                codeName: "选择状态"
            },
            onChange: function (obj) {
                var code = obj['code'];
                _hash.change({
                    expireStatus: code
                })
            }
        });
        $("#" + _alarmPanel).on("click.iMethod-btn-menu", ".iMethod-btn-menu", function () {
            var $this = $(this);
            var st = $this.attr("data-alarm");
            _hash.change({
                serviceType: st
            })
        })
        var keyTimer;
        $("#" + _alarmPanel).on("keyup.iMethod-query", ".iMethod-query", function (event) {
            var $this = $(this);
            if (event.keyCode == 13) {
                if (keyTimer != null) {
                    clearTimeout(keyTimer);
                }
                keyTimer = setTimeout(function () {
                    _hash.change({
                        query: $this.val()
                    });
                    clearTimeout(keyTimer);
                }, 500);
            }
        })
    };

    var publishAlarmHash = function () {
        _hash.publish("alarm", function (obj) {
            alarmPanel();
        })
    };

    var initAlarmHash = function () {
        var hash = _hash.getHash();
        if (hash['state'] != "alarm") {
            hash = {
                "state": "alarm"
            }
        }
        if (utils.nothing(hash['serviceType'])) {
            hash['serviceType'] = "tenant"
        }
        if (utils.nothing(hash['expireStatus'])) {
            hash['expireStatus'] = "10"
        }
        if (utils.nothing(hash['pageIndex'])) {
            hash['pageIndex'] = 1;
        }
        if (utils.nothing(hash['pageSize'])) {
            hash['pageSize'] = 1;
        }
        _hash.init(hash);

    };

    var titleTenant = [{
        key: "tenantId",
        name: "租户id"
    }, {
        key: "tenantName",
        name: "租户名称"
    }, {
        key: "courseNum",
        name: "课程数"
    }, {
        key: "startTime",
        name: "开始时间",
        render:function(obj,el){
            return utils.parseDate(el)
        }
    }, {
        key: "endTime",
        name: "结束时间",
        render:function(obj,el){
            return utils.parseDate(el)
        }
    }, {
        key: "expireStatusName",
        name: "当前状态"
    }];
    var titleCourse = [{
        key: "courseId",
        name: "课程Id"
    }, {
        key: "courseName",
        name: "课程名称"
    }, {
        key: "startTime",
        name: "开始时间",
        render:function(obj,el){
            return utils.parseDate(el)
        }
    }, {
        key: "endTime",
        name: "结束时间",
        render:function(obj,el){
            return utils.parseDate(el)
        }
    }, {
        key: "expireStatusName",
        name: "当前状态"
    }, {
        key: "courseTypeName",
        name: "课程类型"
    }, {
        key: "tenantName",
        name: "租户名称"
    }];
    var alarmPanel = function () {
        var obj = _hash.getHash();
        var serviceType = obj['serviceType'];
        var expireStatus = obj['expireStatus'];
        var pageIndex = obj['pageIndex'];
        var pageSize = obj['pageSize'];
        var query = obj['query'];

        alarmService.queryAlarmList(function (dataMap) {
            var pageMaker = dataMap['pageMaker'];
            var serviceType = dataMap['serviceType'];
            $(".iMethod-btn-menu").removeClass("active");
            $("[data-alarm=" + serviceType + "]").addClass("active");
            var dateList = pageMaker['items'] || [];
            var pageIndex = pageMaker['pageIndex'];
            var pageSize = pageMaker['pageSize'];
            var totalPage = pageMaker['pageMax'];
            var rowCount = pageMaker['rowCount'];
            var pages = pageMaker['pageArr'];
            var title = titleTenant;
            var pageCols = '7';
            var body = alarm_tenant_body;
            var head = alarm_course_head;
            var pk = 'tenantId';
            if (serviceType == "course") {
                title = titleCourse;
                pageCols = '8';
                body = alarm_course_body;
                head = alarm_course_head;
                pk = 'courseId'
            }

            $("#" + _alarmPanel).find(".alarm-table").iMethodTable({
                dataList: dateList,
                pageCols: pageCols,
                templateHead: head,
                templateBody: body,
                titles: title,
                pk: pk,
                page: {
                    pageIndex: pageIndex,
                    pageSize: pageSize,
                    totalPage: totalPage,
                    pages: pages,
                    rowCount: dateList.length || 0,
                    pageClick: function (index, size) {
                        _hash.change({
                            pageIndex: index,
                            pageSize: size
                        })
                    }
                }
            });
            $(".countAll").html(dataMap['countAll']);
            $(".count30").html(dataMap['count30']);
            $(".count20").html(dataMap['count20']);
        }, {
            query: query,
            serviceType: serviceType,
            expireStatus: expireStatus,
            pageIndex: pageIndex,
            pageSize: pageSize
        })
    };
    iMethod.controller.alarm = module.exports;
});