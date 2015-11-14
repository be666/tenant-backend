/**
 * auth : bqxu
 * create_at: 15/11/7.
 * desc:
 * note:
 *  1.
 */
(function ($w, $) {
    var utils = imethod.utils;
    $w.imethod._.resize = [];
    var resize = {
        push: function (name, func) {
            if (utils.isFunc(name)) {
                func = name;
                name = name.name;
            }
            resize.pop(name, func);
            $w.imethod._.resize.push({
                name: name,
                func: func
            })
        },
        pop: function (name, func) {
            if (utils.isFunc(name)) {
                func = name;
                name = name.name;
            }
            if (name != null || utils.isFunc(func)) {
                var resize = $w.imethod._.resize;
                var fl = resize.length;
                while (fl > 0) {
                    fl--;
                    var _resize = resize[fl] || {};
                    if (name == _resize['name'] ||
                        func == _resize['func']) {
                        delete _resize;
                    }
                }
            }
        }


    };
    $w.imethod.resize = resize;
    $(window).resize(function () {
        var resize = $w.imethod._.resize;
        var fl = resize.length;
        while (fl > 0) {
            fl--;
            var func = resize[fl]['func'];
            if (utils.isFunc(func)) {
                func();
            }
        }
    });
})(window, jQuery);
