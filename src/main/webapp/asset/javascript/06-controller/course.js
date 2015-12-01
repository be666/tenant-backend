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
    'view/course/buy',
    'view/course/list_body',
    'view/course/list_head',
    'view/course/buy_body',
    "template"
], function (require, exports, module) {

    var courseService = require("service/course_service");
    var courseInfo = require("view/course/info");
    var courseBuy = require("view/course/buy");
    var courseListHead = require('view/course/list_head');
    var courseListBody = require('view/course/list_body');
    var courseBuyBody = require('view/course/buy_body');
    var _tenantTabId = null;
    var _tenantId = null;
    var _courseType = null;
    var _serviceType = null;

    var utils = iMethod.utils;
    var courseTab = function (pageMaker) {
        var tenantTab = $("#" + _tenantTabId);
        pageMaker = pageMaker || {};
        pageMaker['items'] = pageMaker['items'] || [];
        tenantTab.iMethodTable({
            dataList: pageMaker['items'],
            templateHead: courseListHead,
            templateBody: courseListBody,
            pk: "courseId",
            pageCols: "9",
            titles: [{
                key: "courseId",
                name: "课程id"
            }, {
                key: "courseName",
                name: "课程名称"
            }, {
                key: "tenantName",
                name: "机构名称"
            }, {
                key: "courseTypeName",
                name: "课程类型"
            }, {
                key: "startTime",
                name: "服务开始时间",
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
                key: "expireStatusName",
                name: "当前状态"
            }],
            page: {
                pageIndex: pageMaker['pageIndex'],
                pageSize: pageMaker['pageSize'],
                totalPage: pageMaker['pageMax'],
                pages: pageMaker['pageArr'],
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
            pageCols: "8",
            templateHead: courseListHead,
            templateBody: courseListBody,
            pk: "courseId",
            dataList: pageMaker['items'],
            titles: [{
                key: 'courseName',
                name: "课程名称"
            }, {
                key: 'tenantName',
                name: "租户名称"
            }, {
                key: 'courseTypeName',
                name: "课程类型"
            }, {
                key: 'startTime',
                name: "开始时间",
                render: function (obj, el) {
                    return utils.parseDate(el)
                }
            }, {
                key: 'endTime',
                name: "结束时间",
                render: function (obj, el) {
                    return utils.parseDate(el)
                }
            }, {
                key: 'expireStatusName',
                name: "使用状态"
            }],
            page: {
                pageIndex: pageMaker['pageIndex'],
                pageSize: pageMaker['pageSize'],
                totalPage: pageMaker['pageMax'],
                pages: pageMaker['pageArr'],
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
            courseTab(pageMaker);
        }, {
            pageIndex: index,
            pageSize: size,
            query:$(".iMethod-queryCourse").val()||"",
            courseId: $(".iMethod-courseList").iMethodSelect().getSelected()['courseId'],
            tenantId: $(".iMethod-tenantList").iMethodSelect().getSelected()['tenantId'],
            courseType: $(".iMethod-courseType").iMethodSelect().getSelected()['code']

        });
    };


    var queryOrgCourse = function (index, size) {
        courseService.queryOrgCourse(_tenantId, function (dataMap) {
            var pageMaker = dataMap['pageMaker'];
            orgCourseTable(pageMaker);
        }, {
            pageIndex: index,
            pageSize: size,
            query:$(".iMethod-queryCourse").val()||"",
            courseId: $(".iMethod-courseList").iMethodSelect().getSelected()['courseId'],
            tenantId: $(".iMethod-tenantList").iMethodSelect().getSelected()['tenantId'],
            courseType: $(".iMethod-courseType").iMethodSelect().getSelected()['code']
        });
    };

    /**
     * 课程管理
     * @param tenantTabId
     */
    exports.courseTab = function (tenantTabId, tenantList, courseList, courseType, currentStatus) {
        _tenantTabId = tenantTabId;

        var tenantTab = $("#" + _tenantTabId);
        tenantTab.on("click.class-manager", ".class-manager", function () {
            var $this = $(this);
            var pk = $this.closest("tr").attr("data-pk");
            window.location.href = iMethod.contextPath + "/course/" + pk + "/class";
        });
        //$(".iMethod-currentStatus").iMethodSelect({
        //    id: "code",
        //    text: "codeName",
        //    dataList: currentStatus || [],
        //    unSelected: {
        //        code: "",
        //        codeName: "请选择"
        //    },
        //    onChange:function(){
        //        queryCourse();
        //    }
        //});
        $(".iMethod-courseType").iMethodSelect({
            id: "code",
            text: "codeName",
            dataList: [{
                code: "",
                codeName: "请选择"
            }].concat(courseType),
            unSelected: {
                code: "",
                codeName: "请选择"
            },
            onChange: function () {
                queryCourse();
            }
        });
        $(".iMethod-courseList").iMethodSelect({
            id: "courseId",
            text: "courseName",
            dataList: [{
                courseId: "",
                courseName: "请选择"
            }].concat(courseList),
            unSelected: {
                courseId: "",
                courseName: "请选择"
            },
            onChange: function () {
                queryCourse();
            }
        });
        $(".iMethod-tenantList").iMethodSelect({
            id: "tenantId",
            text: "tenantName",
            dataList: [{
                tenantId: "",
                tenantName: "请选择"
            }].concat(tenantList),
            unSelected: {
                tenantId: "",
                tenantName: "请选择"
            },
            onChange: function () {
                queryCourse();
            }
        });
        var keyTimer;
        $(".iMethod-queryCourse").on("keyup.queryCourse", function () {
            var $this = $(this);
            if (event.keyCode == 13) {
                if (keyTimer != null) {
                    clearTimeout(keyTimer);
                }
                keyTimer = setTimeout(function () {
                    queryCourse();
                    clearTimeout(keyTimer);
                }, 500);
            }
        });
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
            course['serviceTime'] = new Date(addDialog.target.find(".iMethod-serviceTime").val()).Format("yyyy-MM-dd 00:00:00");
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


    var dialogCourseBuy = function () {
        var queryCourseCanBuy = function (index, size) {
            courseService.queryCourseCanBuy(_tenantId, function (dataMap) {
                var pageMaker = dataMap['pageMaker'];
                var dateList = pageMaker['items'] || [];
                var pageIndex = pageMaker['pageIndex'];
                var pageSize = pageMaker['pageSize'];
                var totalPage = pageMaker['pageMax'];
                var rowCount = pageMaker['rowCount'];
                var pages = pageMaker['pageArr'];
                addDialog.target.find(".iMethod-courseTable").iMethodTable({
                    pageCols: "8",
                    templateHead: courseListHead,
                    templateBody: courseBuyBody,
                    pk: "courseId",
                    dataList: dateList,
                    titles: [{
                        key: 'courseName',
                        name: "课程名称"
                    }, {
                        key: 'tenantName',
                        name: "租户名称"
                    }, {
                        key: 'courseTypeName',
                        name: "课程类型"
                    }, {
                        key: 'startTime',
                        name: "开始时间",
                        render: function (obj, el) {
                            return utils.parseDate(el)
                        }
                    }, {
                        key: 'endTime',
                        name: "结束时间",
                        render: function (obj, el) {
                            return utils.parseDate(el)
                        }
                    }, {
                        key: 'expireStatusName',
                        name: "使用状态"
                    }],
                    page: {
                        pageIndex: pageIndex,
                        pageSize: pageSize,
                        totalPage: totalPage,
                        pages: pages,
                        rowCount: dateList.length || 0,
                        pageClick: function (index, size) {
                            queryCourseCanBuy(index, size)
                        }
                    }
                });
                addDialog.position();
            }, {
                pageIndex: index,
                pageSize: size,
                currentStatus: null,
                currentStage: null
            });

        };
        var addDialog = iMethod.dialog({
            className: "iMethod-dialog-addCourse",
            title: "购买课程",
            content: courseBuy(),
            buttons: [{
                className: "iMethod-cancel",
                text: "取消",
                click: function () {
                    addDialog.close();
                }
            }]
        });
        addDialog.target.on("click.course-buy", ".course-buy", function () {
            var $this = $(this);
            var courseId = $this.closest("tr").attr("data-pk");
            if (!utils.isEmptyStr(courseId)) {
                courseService.courseBuy(_tenantId, courseId, function (res) {
                    if (res.status == 1) {
                        addDialog.close();
                        queryOrgCourse();
                    } else if (res['msg']) {
                        iMethod.alert(res['msg']);
                    }
                });
            }
        });
        queryCourseCanBuy();
    };
    /**
     * 租户下课程管理
     * @param tenantTabId
     * @param tenantId
     */
    exports.orgCourseTab = function (tenantTabId, tenantId, courseType, serviceType, tenantList, courseList, currentStatus) {
        _tenantTabId = tenantTabId;
        _tenantId = tenantId;
        _courseType = courseType;
        _serviceType = serviceType;

        $(".iMethod-courseAdd").on("click", function () {
            //window.location.href = iMethod.contextPath + "/tenant/" + tenantId + "/course/new";
            dialogCourse();
        });
        $(".iMethod-courseBuy").on("click", function () {
            //window.location.href = iMethod.contextPath + "/tenant/" + tenantId + "/course/new";
            dialogCourseBuy();
        });
        $("#" + _tenantTabId).on("click.class-manager", ".class-manager", function () {
            var $this = $(this);
            var pk = $this.closest("tr").attr("data-pk");
            window.location.href = iMethod.contextPath + "/course/" + pk + "/class";
        })
        $(".iMethod-courseType").iMethodSelect({
            id: "code",
            text: "codeName",
            dataList: [{
                code: "",
                codeName: "请选择"
            }].concat(courseType),
            unSelected: {
                code: "",
                codeName: "请选择"
            },
            onChange: function () {
                queryOrgCourse();
            }
        });
        $(".iMethod-courseList").iMethodSelect({
            id: "courseId",
            text: "courseName",
            dataList: [{
                courseId: "",
                courseName: "请选择"
            }].concat(courseList),
            unSelected: {
                courseId: "",
                courseName: "请选择"
            },
            onChange: function () {
                queryOrgCourse();
            }
        });
        $(".iMethod-tenantList").iMethodSelect({
            id: "tenantId",
            text: "tenantName",
            dataList: [{
                tenantId: "",
                tenantName: "请选择"
            }].concat(tenantList),
            unSelected: {
                tenantId: "",
                tenantName: "请选择"
            },
            onChange: function () {
                queryOrgCourse();
            }
        });

        var keyTimer;
        $(".iMethod-queryCourse").on("keyup.queryCourse", function () {
            var $this = $(this);
            if (event.keyCode == 13) {
                if (keyTimer != null) {
                    clearTimeout(keyTimer);
                }
                keyTimer = setTimeout(function () {
                    queryOrgCourse();
                    clearTimeout(keyTimer);
                }, 500);
            }
        });
        queryOrgCourse();
    };

    exports.courseInfo = function () {

    };

    iMethod.controller.tenant = module.exports;
});