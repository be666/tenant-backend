/**
 * auth : bqxu
 * create_at: 15/11/5.
 * desc:
 * note:
 *  1.
 */
define('controller/play/uedit', [], function (require, exports, module) {

    var _init = null;
    var utils = imethod.utils;
    imethod.script.insert([
        imethod.contextPath + "/js/uedit/ueditor.config.js",
        imethod.contextPath + "/js/uedit/ueditor.all.js"
    ], function () {

        _init && _init();
    });

    exports.getEditor = function (a, b) {
        return UE.getEditor(a, b);
    };

    exports.init = function (callback) {
        var UE = UE || null;
        if (utils.isNull(UE) || utils.isUndef(UE.getEditor)) {
            _init = callback;
        }
        else {
            callback && callback();
        }
    };

    imethod.controller.play = imethod.controller.play || {};
    imethod.controller.play.uedit = module.exports;
});