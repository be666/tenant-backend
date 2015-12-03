/**
 * Created by bcaring on 15/12/3.
 */
define('service/serve_service', function (require, exports, module) {

    exports.queryService = function (contextId, contextType, callback) {
        iMethod._.ajax({
            url: "/serve/" + contextType + "/" + contextId + ".ajax",
            success: function (res) {
                if (res.status == 1) {
                    callback && callback(res['dataMap'] || {});
                } else if (res['msg']) {
                    iMethod.alert(res['msg']);
                }
            }
        })
    };

    exports.updateService = function (serve, callback) {
        iMethod._.ajax({
            url: "/serve/" + serve["serviceId"] + ".post",
            data: serve,
            type: "post",
            success: function (res) {
                callback && callback(res);

            }
        })
    }
    iMethod.service.statistics_service = module.exports
});