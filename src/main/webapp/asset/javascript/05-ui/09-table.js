/**
 * auth : iMethod
 * create_at: 15/11/15.
 * desc:
 * note:
 *  1.根据数据 生成 table
 *  data[] <-> {key:value} <-> key : title <-> key :name
 *  2.table   支持分页
 *
 */
(function ($w, $) {
    var utils = $w.iMethod.utils;

    $w.iMethod.table = [];

    var def = {
        templateTitle: null,
        templateBody: null,
        data: null,
        column: null,
        page: null
    };
    var iMethodTable = function (targetId, args) {
        var params = utils.extend(def, args);
        var $target = $("#" + targetId);

        var table = function () {
            $target.html();
        };

        var pub = {
            setConfig: function (opts) {
                params = utils.extend(def, opts);
            },
            destroy: function () {

            },
            page: function (index, size) {

            }
        };
        return pub;

    };
    $.fn.iMethodTable = function (args) {
        var $Target = $(this);
        var $TargetId = $Target.attr("id");
        if (typeof $TargetId == "undefined") {
            $TargetId = "iMethod_table_" + iMethod.seq();
            $Target.attr("id", $TargetId)
        }
        if ($Target.data("iMethodTable") !== "complete") {
            if (typeof $w.iMethod.table[$TargetId] != "undefined") {
                $w.iMethod.table[$TargetId].destroy()
            }
            $w.iMethod.table[$TargetId] = null
        }
        if (typeof $w.iMethod.table[$TargetId] == "undefined" || $w.iMethod.btn[$TargetId] == null) {
            $w.iMethod.table[$TargetId] = new iMethodTable($TargetId, args)
        } else {
            $w.iMethod.table[$TargetId].setConfig(args)
        }
        return $w.iMethod.table[$TargetId];
    }

})(window, jQuery);