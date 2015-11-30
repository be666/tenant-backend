/**
 * auth : iMethod
 * create_at: 15/10/23.
 * desc:
 * note:
 *  1.
 */
define('service/class_service', function (require, exports, module) {

    exports.queryClass = function (callback, query) {

        iMethod._.ajax({
            url: "/class.ajax",
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

    exports.queryCourseClass = function (courseId, callback, query) {

        iMethod._.ajax({
            url: "/course/" + courseId + "/class.ajax",
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

    exports.saveClass = function (courseId, classes, callback) {
        var openTime = classes['openTime'];
        var endTime = classes['endTime'];
        var template = classes['template'];
        var score = classes['score'];
        var video = classes['video'];
        var topic = classes['topic'];
        var quiz = classes['quiz'];
        var task = classes['task'];
        var exam = classes['exam'];
        var className = classes['className'];
        iMethod._.ajax({
            url: "/course/" + courseId + "/class",
            data: {
                openTime: openTime,
                endTime: endTime,
                template: template,
                className: className,
                score: score,
                video: video,
                topic: topic,
                quiz: quiz,
                task: task,
                exam: exam
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