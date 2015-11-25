/**
 * auth : iMethod
 * create_at: 15/10/13.
 * desc:
 * note:
 *  1.
 */
(function ($w, $) {
    var utils = $w.iMethod.utils;

    $w.iMethod.select = [];

    var def = {
        dateList: null,
        title: "text",
        id: "id",
        beforeSelect: null,
        onChange: null,
        onClick: null,
        selected: null
    };
    var iMethodSelect = function (targetId, args) {
        var params = utils.extend(def, args);
        var $target = $("#" + targetId);

        var pub = {
            setConfig: function (opts) {
                params = utils.extend(def, opts);
                page();
            },
            destroy: function () {

            }
        };

        $target.on("click.pageClick", "[data-page]", function () {
            var $this = $(this);
            var page = $this.attr("data-page");
            var pageClick = params['pageClick'];
            var pageSize = params['pageSize'];
            pageClick && pageClick(page, pageSize);
        });
        $target.on("click.pageClick", "[data-size]", function () {
            var $this = $(this);
            var page = params['curPage'];
            var pageClick = params['pageClick'];
            var pageSize = $this.attr("data-size");
            pageClick && pageClick(page, pageSize);
        });

        $target.data("iMethodSelect", "complete");
        return pub;

    };
    $.fn.iMethodSelect = function (args) {
        var $Target = $(this);
        var $TargetId = $Target.attr("id");
        if (typeof $TargetId == "undefined") {
            $TargetId = "iMethod_select_" + iMethod.seq();
            $Target.attr("id", $TargetId)
        }
        if ($Target.data("iMethodSelect") !== "complete") {
            if (typeof $w.iMethod.page[$TargetId] != "undefined") {
                $w.iMethod.page[$TargetId].destroy()
            }
            $w.iMethod.page[$TargetId] = null
        }
        if (typeof $w.iMethod.page[$TargetId] == "undefined" || $w.iMethod.btn[$TargetId] == null) {
            $w.iMethod.page[$TargetId] = new iMethodSelect($TargetId, args)
        } else {
            $w.iMethod.page[$TargetId].setConfig(args)
        }
        return $w.iMethod.page[$TargetId];
    }

})(window, jQuery);