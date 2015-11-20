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
    'view/user/info',
    "template"
], function (require, exports, module) {
    var userList = require("view/user/list");
    var userInfo = require("view/user/info");
    var userService = require("service/user_service");

    var selectUserCallback;
    var selectUserDialog;
    var addUserDialog;
    exports.selectUser = function (callback) {
        selectUserCallback = callback;
        selectUserDialog = iMethod.dialog({
            className: "iMethod-dialog-user",
            title: "选择用户",
            content: userList(),
            buttons: []
        });
        userTable();
        selectUserDialog.target.on("click.iMethod-userAdd", ".iMethod-userAdd", function () {
            addUser()
        })
        selectUserDialog.target.on("click.iMethod-userSelect", ".iMethod-userSelect", function () {
            var user = {};
            selectUserCallback(user)
        })
    };

    var userTable = function (index, size) {
        userService.queryUserList(function (pageMarker) {
            var dateList = pageMarker['items'] || [];
            var curPage = pageMarker['curPage'];
            var pageSize = pageMarker['pageSize'];
            var totalPage = pageMarker['totalPage'];
            selectUserDialog.target.find(".iMethod-userTable").iMethodTable({
                dataList: dateList,
                titles: null,
                page: {
                    curPage: curPage,
                    pageSize: pageSize,
                    totalPage: totalPage,
                    rowCount: dateList.length || 0,
                    pageClick: function (index, size) {
                        userTable(index, size)
                    }
                }
            });
        }, {
            pageIndex: index,
            pageSize: size
        });
    };

    var addUser = function () {
        addUserDialog = iMethod.dialog({
            className: "iMethod-dialog-addUser",
            title: "添加用户",
            content: userInfo(),
            buttons: [{
                className: "iMethod-sure",
                text: "添加"
            }, {
                className: "iMethod-cancel",
                text: "取消",
                click: function () {
                    addUserDialog.close();
                }
            }]
        });
        addUserDialog.target.on("click.iMethod-sure", ".iMethod-sure", function () {
            addUserDialog.target.find("");
            var user = {};
            userService.saveUser(user, function (user) {
                selectUserCallback(user);
            })
        })
    };
    iMethod.controller.user = module.exports;
});