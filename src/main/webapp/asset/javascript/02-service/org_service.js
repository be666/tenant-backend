/**
 * auth : bqxu
 * create_at: 15/11/20.
 * desc:
 * note:
 *  1.
 */
define('service/org_service', function (require, exports, module) {

    exports.queryOrgList = function (callback, obj) {
        iMethod._.ajax({
            url: "/org/query",
            data: {
                query: obj['query'],
                pageIndex: obj['pageIndex'],
                pageSize: obj['pageSize']
            },
            success: function (res) {
                callback && callback(res);
            }
        })
    };


    exports.saveOrg = function (org, callback) {
        var orgName = org['orgName'];
        var orgType = org['orgType'];
        var orgPid = org['orgPid'];
        var schoolType = org['schoolType'];
        var city = org['city'];
        var province = org['province'];
        iMethod._.ajax({
            url: "/org",
            type: "post",
            data: {
                schoolType: schoolType,
                province: province,
                orgType: orgType,
                orgName: orgName,
                orgPid: orgPid,
                city: city
            },
            success: function (res) {
                callback && callback(res);
            }
        })
    }
    iMethod.service.user_service = module.exports
});