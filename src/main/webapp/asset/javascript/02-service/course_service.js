/**
 * auth : iMethod
 * create_at: 15/10/23.
 * desc:
 * note:
 *  1.
 */
define('service/course_service', function (require, exports, module) {

    exports.queryCourse = function (callback, query) {
        iMethod._.ajax({
            url: "/course.ajax",
            data: {
                pageIndex: query['pageIndex'],
                pageSize: query['pageSize'],
                currentStatus: query['currentStatus'],
                currentStage: query['currentStage']
            },
            type: "get",
            success: function (res) {
                if (res.status == 1) {
                    callback && callback(res['dataMap'] || {});
                } else if (res['msg']) {
                    iMethod.alert(res['msg']);
                }
            }
        })
    };

    exports.queryOrgCourse = function (tenantId, callback, query) {
        iMethod._.ajax({
            url: "/tenant/" + tenantId + "/course.ajax",
            data: {
                pageIndex: query['pageIndex'],
                pageSize: query['pageSize'],
                currentStatus: query['currentStatus'],
                currentStage: query['currentStage']
            },
            type: "get",
            success: function (res) {
                if (res.status == 1) {
                    callback && callback(res['dataMap'] || {});
                } else if (res['msg']) {
                    iMethod.alert(res['msg']);
                }
            }
        })
    };

    exports.saveCourse = function (_tenantId, course, callback) {
        var name = course['name'];
        var courseType = course['courseType'];
        var serviceType = course['serviceType'];
        var serviceTime = course['serviceTime'];
        var courseMoney = course['courseMoney'];
        var videoTime = course['videoTime'];
        var courseScore = course['courseScore'];
        var chapterMoney = course['chapterMoney'];
        var chapterNum = course['chapterNum'];
        var chapterAll = course['chapterAll'];
        var peopleMoney = course['peopleMoney'];
        var peopleNum = course['peopleNum'];
        var peopleAll = course['peopleAll'];
        iMethod._.ajax({
            url: "/tenant/"+_tenantId+"/course/save",
            data: {
                name: name,
                courseType: courseType,
                serviceType: serviceType,
                serviceTime: serviceTime,
                courseMoney: courseMoney,
                videoTime: videoTime,
                courseScore: courseScore,
                chapterMoney: chapterMoney,
                chapterNum: chapterNum,
                chapterAll: chapterAll,
                peopleMoney: peopleMoney,
                peopleNum: peopleNum,
                peopleAll: peopleAll
            },
            type: "post",
            success: function (res) {
                if (res.status == 1) {
                    callback && callback(res['dataMap'] || {});
                } else if (res['msg']) {
                    iMethod.alert(res['msg']);
                }
            }
        })
    };

    iMethod.service.course_service = module.exports
});