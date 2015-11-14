/**
 * auth : bqxu
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
                    callback && callback(res['dataMap']||{});
                } else if (res['msg']) {
                    iMethod.alert(res['msg']);
                }
            }
        });
    };

    iMethod.service.user_service = module.exports
});