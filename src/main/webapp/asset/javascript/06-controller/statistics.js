/**
 * Created by bcaring on 15/12/2.
 */
define('controller/statistics', ['service/statistics_service', "template"], function (require, exports, module) {

    exports.layout = function (codeLayout, codes, currentStatus, serviceType, orgType, schoolType, courseType) {

    };


    exports.query = function (pId) {
        iMethod._.ajax({
            url: "",
            success: function () {

            }
        })
    }
    iMethod.controller.statistics = module.exports;
});