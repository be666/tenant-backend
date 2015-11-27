/**
 * Created by iMethod on 15/11/18.
 */
define('controller/code', ['service/code_service', "template"], function (require, exports, module) {

    exports.layout = function (codeLayout, codes, currentStatus, serviceType, orgType, schoolType, courseType) {

    };


    exports.query = function (pId) {
        iMethod._.ajax({
            url: "",
            success: function () {

            }
        })
    }
    iMethod.controller.code = module.exports;
});