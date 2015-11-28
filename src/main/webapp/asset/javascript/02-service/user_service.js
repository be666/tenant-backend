/**
 * auth : iMethod
 * create_at: 15/10/23.
 * desc:
 * note:
 *  1.
 */
define('service/user_service', function (require, exports, module) {

    exports.queryUserList = function (callback, obj) {
        iMethod._.ajax({
            url: "/user.ajax",
            data: {
                query: obj['query'],
                pageIndex: obj['pageIndex'],
                pageSize: obj['pageSize']
            },
            success: function (res) {
                if (res.status == 1) {
                    callback && callback(res['dataMap'] || {});
                } else if (res['msg']) {
                    iMethod.alert(res['msg']);
                }
            }
        })
    };


    exports.saveUser = function (user, callback) {
        var userName = user['userName'];
        var userCode = user['userCode'];
        var orgId = user['orgId'];
        var mobile = user['mobile'];
        var email = user['email'];
        var gender = user['gender'];
        iMethod._.ajax({
            url: "/user",
            type: "post",
            data: {
                userCode: userCode,
                userName: userName,
                orgId: orgId,
                mobile: mobile,
                email: email,
                gender: gender
            },
            success: function (res) {
                callback && callback(res);
            }
        })
    };

    exports.queryOrgUserList = function (orgId, callback, obj) {
        iMethod._.ajax({
            url: "/org/" + orgId + "/user.ajax",
            data: {
                query: obj['query'],
                pageIndex: obj['pageIndex'],
                pageSize: obj['pageSize']
            },
            success: function (res) {
                if (res.status == 1) {
                    callback && callback(res['dataMap'] || {});
                } else if (res['msg']) {
                    iMethod.alert(res['msg']);
                }
            }
        })
    };
    iMethod.service.user_service = module.exports
});