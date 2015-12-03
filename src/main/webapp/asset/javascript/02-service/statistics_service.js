/**
    * auth : iMethod
    * create_at:  15/12/2.
    * desc:
    * note:
    *  1.
    */
define('service/statistics_service', function (require, exports, module) {

    exports.queryStatisticsList = function (callback, query) {
        var type = query['type'];
        var startDate = query['startDate'];
        var endDate = query['endDate'];
        var pageIndex = query['pageIndex'];
        var pageSize = query['pageSize'];
        var _query = query['query'];
        iMethod._.ajax({
            url: "/statistics.ajax",
            data: {
                type:type,
                query: _query,
                startDate: startDate,
                endDate: endDate,
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
    iMethod.service.statistics_service = module.exports
});