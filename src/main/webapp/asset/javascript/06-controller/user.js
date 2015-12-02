/**
 * auth : iMethod
 * create_at: 15/11/14.
 * desc:
 * note:
 *  1.
 */
define('controller/user', [
    'controller/org',
    'service/user_service',
    'view/user/list',
    'view/user/org_user',
    'view/user/list_head',
    'view/user/list_body',
    'view/user/info',
    "template"
], function (require, exports, module) {
    var userList = require("view/user/list");
    var userListHead = require("view/user/list_head");
    var userListBody = require("view/user/list_body");
    var userInfo = require("view/user/info");
    var orgUser = require("view/user/org_user");
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
        });
        selectUserDialog.target.on("click.iMethod-userSelect", ".iMethod-userSelect", function () {
            var user = {};
            selectUserCallback(user)
        })
    };

    var userTable = function (index, size) {
        userService.queryUserList(function (pageMarker) {
            var dateList = pageMarker['items'] || [];
            var pageIndex = pageMarker['pageIndex'];
            var pageSize = pageMarker['pageSize'];
            var totalPage = pageMarker['pageMax'];
            var pages = pageMarker['pageArr'];
            selectUserDialog.target.find(".iMethod-userTable").iMethodTable({
                dataList: dateList,
                titles: null,
                page: {
                    pageIndex: pageIndex,
                    pageSize: pageSize,
                    totalPage: totalPage,
                    pages: pages,
                    rowCount: dateList.length || 0,
                    pageClick: function (index, size) {
                        userTable(index, size)
                    }
                }
            });
            selectUserDialog.position();
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
            var user = {};
            userService.saveUser(user, function (user) {
                selectUserCallback(user);
            })
        })
    };

    var _orgUserPanel;
    var _orgId;
    var _org;
    var orgUserDialog;
    exports.orgUserTable = function (orgUserPanel, orgId, org) {
        _orgUserPanel = orgUserPanel;
        _orgId = orgId;
        _org = org;
        queryOrgUser();
        var $orgUserTab = $("#" + _orgUserPanel);
        $orgUserTab.on("click.iMethod-orgUserAdd", ".iMethod-orgUserAdd", function () {
            dialogOrgUser()
        });

        $orgUserTab.on("click.iMethod-user-edit", ".user-edit", function () {
            var $this = $(this);
            var pk = $this.closest("tr").attr("data-pk");
            dialogOrgUserEdit(pk)
        });
    };

    var dialogOrgUserEdit = function (userId) {
        userService.queryUser(userId, function (dateMap) {
            var user = dateMap['user'];
            orgUserDialog = iMethod.dialog({
                className: "iMethod-dialog-addOrg",
                title: "添加人员",
                content: orgUser({
                    org: _org,
                    user: user
                }),
                buttons: [{
                    className: "iMethod-sure",
                    text: "添加"
                }, {
                    className: "iMethod-cancel",
                    text: "取消",
                    click: function () {
                        orgUserDialog.close();
                    }
                }]
            });

            var genderRadio = iMethod.radio({
                root: orgUserDialog.target,
                item: ".iMethod-radio"
            });

            if (user['gender'] == 1) {
                $(".man").addClass("check");
            } else {
                $(".woman").addClass("check");
            }
            orgUserDialog.target.on("click.iMethod-sure", ".iMethod-sure", function () {
                var _user = {};
                _user['userId'] = user['userId'];
                _user['orgId'] = _orgId;
                _user['userCode'] = orgUserDialog.target.find(".userCode").val();
                _user['userName'] = orgUserDialog.target.find(".userName").val();
                _user['gender'] = genderRadio.getChecked().is(".man") ? 1 : 0;
                _user['mobile'] = orgUserDialog.target.find(".mobile").val();
                _user['email'] = orgUserDialog.target.find(".email").val();
                userService.updateUser(_user, function (user) {
                    orgUserDialog.close();
                    queryOrgUser();
                })
            })
        })
    };

    var dialogOrgUser = function () {
        orgUserDialog = iMethod.dialog({
            className: "iMethod-dialog-addOrg",
            title: "添加人员",
            content: orgUser({
                org: _org
            }),
            buttons: [{
                className: "iMethod-sure",
                text: "添加"
            }, {
                className: "iMethod-cancel",
                text: "取消",
                click: function () {
                    orgUserDialog.close();
                }
            }]
        });

        var genderRadio = iMethod.radio({
            root: orgUserDialog.target,
            item: ".iMethod-radio"
        });

        orgUserDialog.target.on("click.iMethod-sure", ".iMethod-sure", function () {
            var user = {};
            user['orgId'] = _orgId;
            user['userCode'] = orgUserDialog.target.find(".userCode").val();
            user['userName'] = orgUserDialog.target.find(".userName").val();
            user['gender'] = genderRadio.getChecked().is(".man") ? 1 : 0;
            user['mobile'] = orgUserDialog.target.find(".mobile").val();
            user['email'] = orgUserDialog.target.find(".email").val();
            userService.saveUser(user, function (user) {
                orgUserDialog.close();
                queryOrgUser();
            })
        })
    };
    var queryOrgUser = function (index, size) {
        var $orgUserTab = $("#" + _orgUserPanel);
        userService.queryOrgUserList(_orgId, function (dataMap) {
            var pageMaker = dataMap['pageMaker'];
            var dateList = pageMaker['items'] || [];
            var pageIndex = pageMaker['pageIndex'];
            var pageSize = pageMaker['pageSize'];
            var totalPage = pageMaker['pageMax'];
            var rowCount = pageMaker['rowCount'];
            var pages = pageMaker['pageArr'];
            $orgUserTab.find(".iMethod-orgUserTable").iMethodTable({
                pk: "userId",
                pageCols: "8",
                templateHead: userListHead,
                templateBody: userListBody,
                dataList: dateList,
                titles: [{
                    key: "userCode",
                    name: '用户编号'
                }, {
                    key: "userName",
                    name: '用户名'
                }, {
                    key: "orgId",
                    name: '机构名称',
                    render: function (user, orgId) {
                        return _org['orgName'];
                    }
                }, {
                    key: "mobile",
                    name: '电话'
                }, {
                    key: "email",
                    name: '电子邮件'
                }, {
                    key: "gender",
                    name: '性别',
                    render: function (obj, gender) {
                        return gender == 1 ? "男" : "女";
                    }
                }],
                page: {
                    pageIndex: pageIndex,
                    pageSize: pageSize,
                    totalPage: totalPage,
                    pages: pages,
                    rowCount: dateList.length || 0,
                    pageClick: function (index, size) {
                        queryOrgUser(index, size)
                    }
                }
            });
        }, {
            pageIndex: index,
            pageSize: size
        });
        iMethod.checkBox({
            root: $orgUserTab,
            all: ".select-all",
            item: ".select-item"
        })
    };
    iMethod.controller.user = module.exports;
});