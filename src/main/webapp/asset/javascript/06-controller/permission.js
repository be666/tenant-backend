/**
 * Created by bcaring on 15/12/1.
 */
define('controller/permission', [
    'service/permission_service',
    'view/permission/osuser',
    'view/permission/list_body',
    'view/permission/list_head',
    'view/permission/dialog_list_body',
    'view/permission/dialog_list_head',
    'view/permission/menu_list_body',
    'view/permission/menu_list_head',
    "template"
], function (require, exports, module) {

    var permissionService = require("service/permission_service");
    var _osUserPanel;
    var osUser = require("view/permission/osuser");
    var listBody = require('view/permission/list_body');
    var listHead = require('view/permission/list_head');
    var dialogListBody = require('view/permission/dialog_list_body');
    var dialogListHead = require('view/permission/dialog_list_head');
    var menuListBody = require('view/permission/menu_list_body');
    var menuListHead = require('view/permission/menu_list_head');
    var utils = iMethod.utils;
    exports.osUserTable = function (OsUserPanel) {
        _osUserPanel = OsUserPanel;
        osUserTable();

        $("#" + _osUserPanel).on("click.iMethod-osUserAdd", ".iMethod-osUserAdd", function () {
            addOsUserDialog();
        });

        $("#" + _osUserPanel).on("click.permission-edit", ".permission-edit", function () {
            var $this = $(this);
            var userId = $this.closest("tr").attr("data-pk");
            userMenu(userId);
        });
    };

    var userMenu = function (userId) {
        var menuDialog = iMethod.dialog({
            className: "iMethod-dialog-addCourse",
            title: "权限设置",
            content: "<div class='menu_table'></div>",
            buttons: [{
                className: "iMethod-sure",
                text: "保存",
            }, {
                className: "iMethod-cancel",
                text: "取消",
                click: function () {
                    menuDialog.close();
                }
            }]
        });

        var menuTable = function () {
            permissionService.queryMenu(userId, function (dataMap) {
                var menuList = dataMap['menuList'];
                var ruleList = dataMap['ruleList'];
                menuDialog.target.find(".menu_table").iMethodTable({
                    templateHead: menuListHead,
                    templateBody: menuListBody,
                    pk: "menuId",
                    dataList: menuList,
                    titles: [{
                        key: 'menuName',
                        name: "名称"
                    }, {
                        key: 'menuType',
                        name: "权限类型",
                        render: function (menu, menuType) {
                            if (menuType == 1) {
                                return "url"
                            }
                        }
                    }]
                });
                var rl = ruleList.length;
                for (var i = 0; i < rl; i++) {
                    var rule = ruleList[i];
                    menuDialog.target.find('[data-pk="' + rule['menuId'] + '"]').addClass("check");
                }
            });
        };
        menuTable();
        var menuCheck = iMethod.checkBox({
            root: menuDialog.target,
            item: ".select-item",
            all: ".select-all"
        })
        menuDialog.target.on("click.iMethod-sure", ".iMethod-sure", function () {
            var menuId = menuCheck.getChecked("data-pk");
            permissionService.usermenu(userId, menuId, function (res) {
                if (res.status == 1) {
                    menuDialog.close();
                    osUserTable();
                } else if (res['msg']) {
                    iMethod.alert(res['msg']);
                }
            })
        })
    };


    var addOsUserDialog = function () {
        var addDialog = iMethod.dialog({
            className: "iMethod-dialog-addCourse",
            title: "添加管理员",
            content: osUser(),
            buttons: [{
                className: "iMethod-cancel",
                text: "取消",
                click: function () {
                    addDialog.close();
                }
            }]
        });
        var queryUnOsUser = function (index, size) {
            permissionService.queryUnOsUserList(function (dataMap) {
                var pageMaker = dataMap['pageMaker'];
                var pageIndex = pageMaker['pageIndex'];
                var pageSize = pageMaker['pageSize'];
                var totalPage = pageMaker['pageMax'];
                var rowCount = pageMaker['rowCount'];
                var pages = pageMaker['pageArr'];
                var dateList = pageMaker['items'] || [];
                addDialog.target.find(".iMethod-unOsUserTable").iMethodTable({
                    templateHead: dialogListHead,
                    templateBody: dialogListBody,
                    pk: "userId",
                    dataList: dateList,
                    pageCols:"5",
                    titles: [{
                        key: 'userCode',
                        name: "用户编号"
                    }, {
                        key: 'userName',
                        name: "用户名"
                    }, {
                        key: 'orgName',
                        name: "机构名称"
                    }],
                    page: {
                        pageIndex: pageIndex,
                        pageSize: pageSize,
                        totalPage: totalPage,
                        pages: pages,
                        rowCount: dateList.length || 0,
                        pageClick: function (index, size) {
                            queryUnOsUser(index, size)
                        }
                    }
                });
            }, {
                pageIndex: index,
                pageSize: size
            })
        };
        queryUnOsUser();
        addDialog.target.on("click.un-os-add", '.un-os-add', function () {
            var $this = $(this);
            var userId = $this.closest("tr").attr("data-pk");
            if (!utils.isEmptyStr(userId)) {
                permissionService.addUser(userId, function (res) {
                    if (res.status == 1) {
                        addDialog.close();
                        osUserTable();
                    } else if (res['msg']) {
                        iMethod.alert(res['msg']);
                    }
                })
            }
        })
    }


    var osUserTable = function (index, size) {
        permissionService.queryOsUserList(function (dataMap) {
            var pageMaker = dataMap['pageMaker'];
            var pageIndex = pageMaker['pageIndex'];
            var pageSize = pageMaker['pageSize'];
            var totalPage = pageMaker['pageMax'];
            var rowCount = pageMaker['rowCount'];
            var pages = pageMaker['pageArr'];
            var dateList = pageMaker['items'] || [];
            $("#" + _osUserPanel).find(".iMethod-OsUserTable").iMethodTable({
                templateHead: listHead,
                templateBody: listBody,
                pk: "userId",
                pageCols:"5",
                dataList: dateList,
                titles: [{
                    key: 'userCode',
                    name: "用户编号"
                }, {
                    key: 'userName',
                    name: "用户名"
                }, {
                    key: 'orgName',
                    name: "机构名称"
                }],
                page: {
                    pageIndex: pageIndex,
                    pageSize: pageSize,
                    totalPage: totalPage,
                    pages: pages,
                    rowCount: dateList.length || 0,
                    pageClick: function (index, size) {
                        osUserTable(index, size)
                    }
                }
            });
        }, {
            pageIndex: index,
            pageSize: size
        });


    };

    iMethod.controller.permission = module.exports;
});