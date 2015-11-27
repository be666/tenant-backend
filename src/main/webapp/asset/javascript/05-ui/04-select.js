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
        template: template("select"),
        dataList: null,
        text: "text",
        id: "id",
        beforeSelect: null,
        onChange: null,
        onClick: null,
        selected: null,
        ulHeight:160,
        unSelected: {
            id: "",
            text: "请选择"
        }
    };
    var iMethodSelect = function (targetId, args) {
        var params = utils.extend(def, args);
        var $target = $("#" + targetId);
        if (!$target.hasClass("iMethod-select")) {
            $target.addClass("iMethod-select");
        }
        var select = function () {
            var dataList = params["dataList"];
            var template = params["template"];
            var id = params['id'];
            var text = params['text'];
            var selected = params['selected'] || {};
            var unSelected = params['unSelected'] || {};
            var selectEl = utils.arrFind(dataList, function (el, i, arr) {
                    if (el[id] == selected[id]) {
                        return el;
                    }
                }) || [];
            selected = selectEl[0] || unSelected;
            $target.html(template({
                id: id,
                text: text,
                dataList: dataList,
                selected: selected
            }));
            params['selected'] = selected;
            $(".")
        };
        var pub = {
            setConfig: function (opts) {
                params = utils.extend(params, opts);
                select();
            },
            destroy: function () {

            }
        };

        $target.on("click.pageClick", "[data-id]", function () {
            var $this = $(this);
            var dataId = $this.attr("data-id");
            var id = params['id'];
            var text = params['text'];
            var beforeSelect = params['beforeSelect'];
            var onChange = params['onChange'];
            var onClick = params['onClick'];
            if (beforeSelect && !!beforeSelect(id)) {
                return;
            }
            var selected = {};
            selected[id] = dataId;
            params['selected'] = selected;
            select();
            selected = params['selected'];
            onChange && onChange(selected);
        });

        $target.on("click.pageClick", "[data-select-id]", function () {
            $target.find("ul").addClass("active");
        });

        $target.on("mouseleave", function () {
            $target.find("ul").removeClass("active");
        });

        select();
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
            if (typeof $w.iMethod.select[$TargetId] != "undefined") {
                $w.iMethod.select[$TargetId].destroy()
            }
            $w.iMethod.select[$TargetId] = null
        }
        if (typeof $w.iMethod.select[$TargetId] == "undefined" || $w.iMethod.select[$TargetId] == null) {
            $w.iMethod.select[$TargetId] = new iMethodSelect($TargetId, args)
        } else {
            $w.iMethod.select[$TargetId].setConfig(args)
        }
        return $w.iMethod.select[$TargetId];
    }

})(window, jQuery);