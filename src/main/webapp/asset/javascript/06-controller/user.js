/**
 * auth : bqxu
 * create_at: 15/11/14.
 * desc:
 * note:
 *  1.
 */
define('controller/user', [
    'service/user_service',
    'view/user/list',
    "template"
], function (require, exports, module) {
    var usrList = require("view/user/list");

    var selectUserCallback;
    exports.selectUser = function (callback) {
        selectUserCallback = callback;
        var selectUserDialog = iMethod.dialog({
            className: "iMethod-dialog-user",
            title: "选择用户",
            content: usrList(),
            buttons: []
        });
        selectUserDialog.target().on("")
    };
    iMethod.controller.user = module.exports;
});