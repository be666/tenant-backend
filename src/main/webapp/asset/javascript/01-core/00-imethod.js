/**
 * auth : iMethod
 * create_at: 15/10/13.
 * desc:
 * note:
 *  1.
 */
(function ($w, $) {
  $w._window = {};
  $w.imethod = $w.imethod || {
      _: {},
      ui: {},
      service: {},
      controller: {},
      contextPath: "/",
      extend: $.extend,
      _seq: {},
      seq: function () {
        var _id;
        do {
          _id = Math.random() + "00000000";
          _id = _id.substr(_id.indexOf(".") + 1, 6);
        } while (!!$w.imethod._seq[_id]);
        $w.imethod._seq[_id] = !0;
        return _id;
      }
    };
})(window, jQuery);


