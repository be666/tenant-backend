/**
 * auth : iMethod
 * create_at: 15/11/15.
 * desc:
 * note:
 *  1.
 */
define('service/alarm_service', function (require, exports, module) {


    exports.queryAlarmList = function (callback, query) {
        var serviceType = query['serviceType'];
        var expireStatus = query['expireStatus'];
        var pageIndex = query['pageIndex'];
        var pageSize = query['pageSize'];
        var query = query['query'];
        iMethod._.ajax({
            url: "/alarm.ajax",
            data: {
                query: query,
                serviceType: serviceType,
                expireStatus: expireStatus,
                pageIndex: pageIndex,
                pageSize: pageSize
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
    iMethod.service.alarm_service = module.exports
});