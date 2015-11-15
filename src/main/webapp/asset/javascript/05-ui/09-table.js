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
        template: template("table.layout"),
        templateHead: template("table.head"),
        templateBody: template("table.body"),
        templateFoot: template("table.foot"),
        dataList: null,
        titles: null,
        page: null
    };
    var iMethodTable = function (targetId, args) {
        var params = utils.extend(def, args);
        var $target = $("#" + targetId);

        var pub = {
            setConfig: function (opts) {
                params = utils.extend(params, opts);
                pub.buildHead(params['titles']);
                pub.buildBody(params['titles'], params['dataList']);
                pub.buildFoot(params['page']);
            },
            destroy: function () {

            },
            buildHead: function (titles) {
                var templateHead = params['templateHead'];
                $(".iMethod-head", $target).html(templateHead({
                    titles: titles
                }));
            },
            buildBody: function (titles, dataList) {
                var templateBody = params['templateBody'];
                $(".iMethod-body", $target).html(templateBody({
                    titles: titles,
                    dataList: dataList
                }));
            },
            buildFoot: function (page, dataList) {
                dataList = dataList || [];
                if (utils.nothing(page)) {
                    var templateFoot = params['templateFoot'];
                    $(".iMethod-foot", $target).html(templateFoot());
                } else {
                    $(".Method-foot td.page", $target).iMethodPage({
                        curPage: page['curPage'],
                        totalPage: page['totalPage'],
                        rowCount: dataList.length
                    });
                }
            }
        };
        var template = params['template'];
        $target.html(
            template()
        );
        if (utils.nothing(params['titles'])) {
            var titles = [];
            var dataList = params['dataList'];
            var dl = dataList.length;
            if (dl > 0) {
                var dt = dataList[0]
                for (var k in dt) {
                    titles[k] = k;
                }
            }
            params['titles'] = titles;
        }
        pub.setConfig();
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