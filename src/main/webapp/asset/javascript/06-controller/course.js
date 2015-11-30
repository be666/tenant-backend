/**
 * auth : iMethod
 * create_at: 15/11/14.
 * desc:
 * note:
 *  1.
 */
define('controller/course', [
    'service/course_service',
    'view/course/info',
    'view/course/list_body',
    'view/course/list_head',
    "template"
], function (require, exports, module) {

    var courseService = require("service/course_service");
    var courseInfo = require("view/course/info");
    var courseListHead = require('view/course/list_head');
    var courseListBody = require('view/course/list_body');
    var _tenantTabId = null;
    var _tenantId = null;
    var _courseType = null;
    var _serviceType = null;
    var tableInit = function (pageMaker) {
        var tenantTab = $("#" + _tenantTabId);
        pageMaker = pageMaker || {};
        pageMaker['items'] = pageMaker['items'] || [];
        tenantTab.iMethodTable({
            dataList: pageMaker['items'],
            templateHead: courseListHead,
            templateBody: courseListBody,
            titles: null,
            page: {
                pageIndex: pageMaker['pageIndex'],
                pageSize: pageMaker['pageSize'],
                totalPage: pageMaker['totalPage'],
                rowCount: pageMaker['items'].length,
                pageClick: function (index, size) {
                    queryCourse(index, size);
                }
            }
        })
    };

    var orgCourseTable = function (pageMaker) {
        var tenantTab = $("#" + _tenantTabId);
        pageMaker = pageMaker || {};
        pageMaker['items'] = pageMaker['items'] || [];
        tenantTab.iMethodTable({
            dataList: pageMaker['items'],
            titles: null,
            page: {
                pageIndex: pageMaker['pageIndex'],
                pageSize: pageMaker['pageSize'],
                totalPage: pageMaker['totalPage'],
                rowCount: pageMaker['items'].length,
                pageClick: function (index, size) {
                    queryOrgCourse(index, size);
                }
            }
        })
    };

    var queryCourse = function (index, size) {
        courseService.queryCourse(function (dataMap) {
            var pageMaker = dataMap['pageMaker'];
            tableInit(pageMaker);
        }, {
            pageIndex: index,
            pageSize: size,
            currentStatus: null,
            currentStage: null
        });
    };


    var queryOrgCourse = function (index, size) {
        courseService.queryOrgCourse(_tenantId, function (dataMap) {
            var pageMaker = dataMap['pageMaker'];
            orgCourseTable(pageMaker);
        }, {
            pageIndex: index,
            pageSize: size,
            currentStatus: null,
            currentStage: null
        });
    };

    /**
     * 课程管理
     * @param tenantTabId
     */
    exports.courseTab = function (tenantTabId) {
        _tenantTabId = tenantTabId;
        queryCourse();
    };


    var dialogCourse = function () {
        var addDialog = iMethod.dialog({
            className: "iMethod-dialog-addCourse",
            title: "添加课程",
            content: courseInfo(),
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
        addDialog.target.find(".iMethod-serviceType").iMethodSelect({
            id: "code",
            text: "codeName",
            dataList: _serviceType || [],
            unSelected: {
                code: "",
                codeName: "请选择"
            }
        });
        addDialog.target.find(".iMethod-courseType").iMethodSelect({
            id: "code",
            text: "codeName",
            dataList: _courseType || [],
            unSelected: {
                code: "",
                codeName: "请选择"
            }
        });

        addDialog.target.on("click.iMethod-sure", ".iMethod-sure", function () {
            var course = {};
            course['name'] = addDialog.target.find(".iMethod-name").val();
            course['courseType'] = addDialog.target.find(".iMethod-courseType").iMethodSelect().getSelected()['code'];
            course['serviceType'] = addDialog.target.find(".iMethod-serviceType").iMethodSelect().getSelected()['code'];
            course['serviceTime'] = addDialog.target.find(".iMethod-serviceTime").val();
            course['courseMoney'] = addDialog.target.find(".iMethod-courseMoney").val();
            course['videoTime'] = addDialog.target.find(".iMethod-videoTime").val();
            course['courseScore'] = addDialog.target.find(".iMethod-courseScore").val();
            course['chapterMoney'] = addDialog.target.find(".iMethod-chapterMoney").val();
            course['chapterNum'] = addDialog.target.find(".iMethod-chapterNum").val();
            course['chapterAll'] = addDialog.target.find(".iMethod-chapterAll").val();
            course['peopleMoney'] = addDialog.target.find(".iMethod-peopleMoney").val();
            course['peopleNum'] = addDialog.target.find(".iMethod-peopleNum").val();
            course['peopleAll'] = addDialog.target.find(".iMethod-peopleAll").val();
            courseService.saveCourse(_tenantId, course, function (course) {
                addDialog.close();
                queryOrgCourse();
            });
        });
    };
    /**
     * 租户下课程管理
     * @param tenantTabId
     * @param tenantId
     */
    exports.orgCourseTab = function (tenantTabId, tenantId, courseType, serviceType) {
        _tenantTabId = tenantTabId;
        _tenantId = tenantId;
        _courseType = courseType;
        _serviceType = serviceType;
        queryOrgCourse();
        $(".iMethod-courseAdd").on("click", function () {
            //window.location.href = iMethod.contextPath + "/tenant/" + tenantId + "/course/new";
            dialogCourse();
        });
    };

    exports.courseInfo = function () {

    };

    iMethod.controller.tenant = module.exports;
});