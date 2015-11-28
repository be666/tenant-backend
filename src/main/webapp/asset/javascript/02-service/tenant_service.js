/**
 * auth : iMethod
 * create_at: 15/10/23.
 * desc:
 * note:
 *  1.
 */
define('service/tenant_service', function (require, exports, module) {

    exports.queryTenant = function (callback, query) {
        iMethod._.ajax({
            url: "/tenant.ajax",
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


    exports.saveTenant = function (tenant, callback) {
        var shortName = tenant['shortName'];
        var serviceType = tenant['serviceType'];
        var schoolOrg = tenant['schoolOrg'];
        var schoolUser = tenant['schoolUser'];
        var sellOrg = tenant['sellOrg'];
        var sellUser = tenant['sellUser'];
        var managerOrg = tenant['managerOrg'];
        var managerUser = tenant['managerUser'];
        var managerSell = tenant['managerSell'];
        var serviceUser = tenant['serviceUser'];
        var resourceService = tenant['resourceService'];
        var scoreService = tenant['scoreService'];
        var tenantTime = tenant['tenantTime'];
        iMethod._.ajax({
            url: "/tenant/save",
            type: "post",
            data: {
                shortName: shortName,
                serviceType: serviceType,
                schoolOrg: schoolOrg,
                schoolUser: schoolUser,
                sellOrg: sellOrg,
                sellUser: sellUser,
                managerOrg: managerOrg,
                managerUser: managerUser,
                managerSell: managerSell,
                serviceUser: serviceUser,
                resourceService: resourceService,
                scoreService: scoreService,
                tenantTime: tenantTime
            },
            success: function (res) {
                callback && callback(res);
            }
        })
    };

    iMethod.service.tenant_service = module.exports
});