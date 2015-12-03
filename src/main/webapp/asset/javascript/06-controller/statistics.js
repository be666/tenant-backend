/**
 * auth : iMethod
 * create_at:  15/12/2.
 * desc:
 * note:
 *  1.
 */
define('controller/statistics', [
    'service/statistics_service',
    "template"
], function (require, exports, module) {

    var utils = iMethod.utils;

    var statisticsService = require("service/statistics_service");
    var _hash = new iMethodHash();
    var _statisticsPanelId;
    exports.statisticsPanel = function (statisticsPanelId) {
        _statisticsPanelId = statisticsPanelId;
        publishBiHash();
        initBiHash();
        $("#" + _statisticsPanelId).on("click.iMethod-btn-menu", ".iMethod-btn-menu", function () {
            var $this = $(this);
            var st = $this.attr("data-bi");
            _hash.change({
                type: st
            })
        })
        $("#" + _statisticsPanelId).on("click.iMethod-query-click", ".iMethod-query-click", function (event) {
            statisticsPanel();
        })
    };
    var publishBiHash = function () {
        _hash.publish("bi", function (obj) {
            statisticsPanel();
        })
    };

    var initBiHash = function () {
        var hash = _hash.getHash();
        if (hash['state'] != "bi") {
            hash = {
                "state": "bi"
            }
        }
        if (utils.nothing(hash['type'])) {
            hash['type'] = "tenant"
        }
        if (utils.nothing(hash['pageSize'])) {
            hash['pageSize'] = 10;
        }
        if (utils.nothing(hash['pageIndex'])) {
            hash['pageIndex'] = 1;
        }
        if (utils.nothing(hash['query'])) {
            hash['query'] = "";
        }
        _hash.init(hash);
    };

    var titleCourse = [{
        key: "courseName",
        name: "课程名称"
    }, {
        key: "tenantNum",
        name: "租户名称"
    }, {
        key: "classNum",
        name: "班次数"
    }, {
        key: "pv",
        name: "pv"
    }, {
        key: "uv",
        name: "uv"
    }];
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
        key: "classesNum",
        name: "班次数"
    }, {
        key: "userNum",
        name: "用户数"
    }, {
        key: "activeNum",
        name: "激活数"
    }, {
        key: "uv",
        name: "uv"
    }, {
        key: "pv",
        name: "pv"
    }, {
        key: "userName",
        name: "责任人"
    }];
    var statisticsPanel = function () {
        var obj = _hash.getHash();
        var pageIndex = obj['pageIndex'];
        var pageSize = obj['pageSize'];
        var query = $(".iMethod-query").val();
        var type = obj['type'];
        var startDate = $(".iMethod-start").val();
        var endDate = $(".iMethod-end").val();

        statisticsService.queryStatisticsList(function (dataMap) {
            var pageMaker = dataMap['pageMaker'];
            var type = dataMap['type'];
            $(".iMethod-btn-menu").removeClass("active");
            $("[data-bi=" + type + "]").addClass("active");
            var dateList = pageMaker['items'] || [];
            var pageIndex = pageMaker['pageIndex'];
            var pageSize = pageMaker['pageSize'];
            var totalPage = pageMaker['pageMax'];
            var rowCount = pageMaker['rowCount'];
            var pages = pageMaker['pageArr'];
            var title = titleTenant;
            var pk = 'tenantId';
            if (type == "course") {
                title = titleCourse;
                pk = 'courseId'
            }

            $("#" + _statisticsPanelId).find(".bi-table").iMethodTable({
                dataList: dateList,
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
        }, {
            query: query,
            type: type,
            startDate: startDate,
            endDate: endDate,
            pageIndex: pageIndex,
            pageSize: pageSize
        })
    }
    iMethod.controller.statistics = module.exports;
});