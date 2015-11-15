/**
 * auth : bqxu
 * create_at: 15/10/23.
 * desc:
 * note:
 *  1.
 */
define('service/tenant_service', function (require, exports, module) {

    exports.queryTenant = function (callbacl, query) {

        iMethod._.ajax({
            url: "/tenant/query",
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

    iMethod.service.tenant_service = module.exports
});