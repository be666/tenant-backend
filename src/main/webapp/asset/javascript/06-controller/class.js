/**
 * auth : iMethod
 * create_at: 15/11/14.
 * desc:
 * note:
 *  1.
 */
define('controller/class', [
    'service/class_service',
    "view/class/info",
    "template"
], function (require, exports, module) {


    var classService = require("service/class_service");
    var classInfo = require("view/class/info");
    var _classTabId = null;
    var _courseClassTabId = null;
    var _courseId = null;
    var utils = iMethod.utils;

    var classTab = function (pageMaker) {
        var classTab = $("#" + _classTabId);
        pageMaker = pageMaker || {};
        pageMaker['items'] = pageMaker['items'] || [];
        classTab.iMethodTable({
            dataList: pageMaker['items'],
            titles: [{
                key: "classId",
                name: "班次id"
            }, {
                key: "className",
                name: "班次名称"
            }, {
                key: "courseName",
                name: "课程名称"
            }, {
                key: "tenantName",
                name: "租户"
            }, {
                key: "classStartTime",
                name: "班次开始时间",
                render: function (obj, el) {
                    return utils.parseDate(el)
                }
            }, {
                key: "classEndTime",
                name: "班次结束时间",
                render: function (obj, el) {
                    return utils.parseDate(el)
                }
            }, {
                key: "isWeight",
                name: "是否开启权重",
                render: function (org, isWeight) {
                    return isWeight == 1 ? "是" : "否";
                }
            }, {
                key: "finishStatusName",
                name: "使用状态"
            }],
            page: {
                pageIndex: pageMaker['pageIndex'],
                pageSize: pageMaker['pageSize'],
                totalPage: pageMaker['pageMax'],
                pages: pageMaker['pageArr'],
                rowCount: pageMaker['items'].length,
                pageClick: function (index, size) {
                    queryClass(index, size);
                }
            }
        })
    };

    var queryClass = function (index, size) {
        classService.queryClass(function (dataMap) {
            var pageMaker = dataMap['pageMaker'];
            classTab(pageMaker);
        }, {
            pageIndex: index,
            pageSize: size,
            currentStatus: null,
            currentStage: null
        });
    };

    exports.classTab = function (classTabId, courseList, tenantList, finishStatus) {
        _classTabId = classTabId;
        queryClass();
        finishStatus = [{
            code: "",
            codeName: "请选择"
        }].concat(finishStatus);
        $(".iMethod-currentStatus").iMethodSelect({
            id: "code",
            text: "codeName",
            dataList: finishStatus || [],
            unSelected: {
                code: "",
                codeName: "请选择"
            }
        });
        courseList = [{
            courseId: "",
            courseName: "请选择"
        }].concat(courseList);

        $(".iMethod-course").iMethodSelect({
            id: "courseId",
            text: "courseName",
            dataList: courseList || [],
            unSelected: {
                courseId: "",
                courseName: "请选择"
            }
        });

        tenantList = [{
            tenantId: "",
            tenantName: "请选择"
        }].concat(tenantList);
        $(".iMethod-tenant").iMethodSelect({
            id: "tenantId",
            text: "tenantName",
            dataList: tenantList || [],
            unSelected: {
                tenantId: "",
                tenantName: "请选择"
            }
        })
    };

    var courseClassTab = function (pageMaker) {
        var courseClassTab = $("#" + _courseClassTabId);
        pageMaker = pageMaker || {};
        pageMaker['items'] = pageMaker['items'] || [];
        courseClassTab.iMethodTable({
            dataList: pageMaker['items'],
            titles: [{
                key: "classId",
                name: "班次id"
            }, {
                key: "className",
                name: "班次名称"
            }, {
                key: "courseName",
                name: "课程名称"
            }, {
                key: "tenantName",
                name: "租户"
            }, {
                key: "classStartTime",
                name: "班次开始时间",
                render: function (obj, el) {
                    return utils.parseDate(el)
                }
            }, {
                key: "classEndTime",
                name: "班次结束时间",
                render: function (obj, el) {
                    return utils.parseDate(el)
                }
            }, {
                key: "isWeight",
                name: "是否开启权重",
                render: function (org, isWeight) {
                    return isWeight == 1 ? "是" : "否";
                }
            }, {
                key: "finishStatusName",
                name: "使用状态"
            }],
            page: {
                pageIndex: pageMaker['pageIndex'],
                pageSize: pageMaker['pageSize'],
                totalPage: pageMaker['pageMax'],
                pages: pageMaker['pageArr'],
                rowCount: pageMaker['items'].length,
                pageClick: function (index, size) {
                    queryCourseClass(index, size);
                }
            }
        })
    };

    var queryCourseClass = function (index, size) {
        classService.queryCourseClass(_courseId, function (dataMap) {
            var pageMaker = dataMap['pageMaker'];
            courseClassTab(pageMaker);
        }, {
            pageIndex: index,
            pageSize: size,
            currentStatus: null,
            currentStage: null
        });
    };

    exports.courseClassTab = function (courseClassTabId, courseId, finishStatus) {
        _courseClassTabId = courseClassTabId;
        _courseId = courseId;
        queryCourseClass();
        finishStatus = [{
            code: "",
            codeName: "请选择"
        }].concat(finishStatus);
        $(".iMethod-currentStatus").iMethodSelect({
            id: "code",
            text: "codeName",
            dataList: finishStatus || [],
            unSelected: {
                code: "",
                codeName: "请选择"
            }
        })
        $(".iMethod-courseClassAdd").on("click", function () {
            dialogClass();
        })
    };

    var dialogClass = function () {
        var addDialog = iMethod.dialog({
            className: "iMethod-dialog-addClass",
            title: "添加班次",
            content: classInfo(),
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
        var scoreRadio = iMethod.radio({
            root: addDialog.target,
            item: ".score-class"
        })
        var templateRadio = iMethod.radio({
            root: addDialog.target,
            item: ".template-class"
        })
        addDialog.target.on("click.iMethod-sure", ".iMethod-sure", function () {
            var classes = {};
            classes['openTime'] = new Date(addDialog.target.find(".iMethod-openTime").val()).Format("yyyy-MM-dd 00:00:00");
            classes['endTime'] = new Date(addDialog.target.find(".iMethod-endTime").val()).Format("yyyy-MM-dd 00:00:00");;
            classes['template'] = templateRadio.getChecked("data-template");
            classes['score'] = scoreRadio.getChecked("data-score");
            classes['video'] = addDialog.target.find(".iMethod-video").val();
            classes['topic'] = addDialog.target.find(".iMethod-topic").val();
            classes['quiz'] = addDialog.target.find(".iMethod-quiz").val();
            classes['task'] = addDialog.target.find(".iMethod-task").val();
            classes['className'] = addDialog.target.find(".className").val();
            classes['exam'] = addDialog.target.find(".iMethod-exam").val();
            classService.saveClass(_courseId, classes, function (res) {
                if (res.status == 1) {
                    addDialog.close();
                    queryCourseClass();
                } else {
                    alert(res['msg'])
                }

            })
        });
    };

    iMethod.controller.class = module.exports;
});