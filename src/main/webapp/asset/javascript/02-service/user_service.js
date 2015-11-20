/**
 * auth : bqxu
 * create_at: 15/10/23.
 * desc:
 * note:
 *  1.
 */
define('service/user_service', function (require, exports, module) {

    exports.queryUserList = function (callback, obj) {
        iMethod._.ajax({
            url: "/user",
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


    exports.saveUser = function (user, callback) {
        var userName = user['userName'];
        var orgId = user['orgId'];
        var mobile = user['mobile'];
        var email = user['email'];
        var gender = user['gender'];
        iMethod._.ajax({
            url: "/user",
            type: "post",
            data: {
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
    }
    iMethod.service.user_service = module.exports
});