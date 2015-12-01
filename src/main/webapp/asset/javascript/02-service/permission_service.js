/**
 * auth : iMethod
 * create_at: 15/10/23.
 * desc:
 * note:
 *  1.
 */
define('service/permission_service', function (require, exports, module) {

    exports.loadMenu = function (callback) {
        iMethod._.ajax({
            url: "/permission/menu",
            type: "post",
            success: function (res) {
                if (res.status == 1) {
                    callback && callback(res['dataMap'] || {});
                } else if (res['msg']) {
                    iMethod.alert(res['msg']);
                }
            }
        });
    };


    exports.queryOsUserList = function (callback, obj) {
        iMethod._.ajax({
            url: "/permission/user.ajax",
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


    exports.queryUnOsUserList = function (callback, obj) {
        iMethod._.ajax({
            url: "/permission/unosuser.ajax",
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

    exports.addUser = function (userId, callback) {
        iMethod._.ajax({
            url: "/permission/adduser/" + userId,
            type: "post",
            success: function (res) {
                callback && callback(res);
            }
        })
    };

    exports.queryMenu = function (userId, callback) {
        iMethod._.ajax({
            url: "/permission/menu/" + userId,
            success: function (res) {
                if (res.status == 1) {
                    callback && callback(res['dataMap'] || {});
                } else if (res['msg']) {
                    iMethod.alert(res['msg']);
                }
            }
        })
    };

    exports.usermenu = function (userId, menuList, callback) {
        iMethod._.ajax({
            url: "/permission/usermenu/" + userId,
            data: {
                menus: menuList
            },
            type: "post",
            success: function (res) {
                callback && callback(res);
            }
        })
    };
    iMethod.service.user_service = module.exports
});