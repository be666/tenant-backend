/**
 * auth : iMethod
 * create_at: 15/10/13.
 * desc:
 * note:
 *  1.
 */
(function ($w) {
  $w.imethod._.dialog = {
    ext: {
      alert: null,
      confirm: null,
      warn: null,
      error: null,
      dialog: null
    },
    history: [],
    alert: function (msg, callback) {
      $w.imethod._.dialog.history.push(msg);
      if ($w.imethod._.dialog.ext.alert) {
        return $w.imethod._.dialog.ext.alert(msg, callback);
      } else {
        $w.alert(msg);
        callback && callback();
        return {};
      }
    },
    confirm: function (msg, callback) {
      $w.imethod._.dialog.history.push(msg);
      if ($w.imethod._.dialog.ext.confirm) {
        return $w.imethod._.dialog.ext.confirm(msg, callback);
      } else {
        var flag = $w.confirm(msg);
        if (flag) {
          callback && callback(!0);
        } else {
          callback && callback(!!0);
        }
        return {};
      }
    },
    warn: function (msg, callback) {
      $w.imethod._.dialog.history.push(msg);
      if ($w.imethod._.dialog.ext.warn) {
        return $w.imethod._.dialog.ext.warn(msg, callback);
      } else {
        $w.alert(msg);
        callback && callback();
        return {};
      }
    },
    error: function (msg, callback) {
      $w.imethod._.dialog.history.push(msg);
      if ($w.imethod._.dialog.ext.error) {
        return $w.imethod._.dialog.ext.error(msg, callback);
      } else {
        $w.alert(msg);
        callback && callback();
        return {};
      }
    },
    prompt: function (msg, callback) {
      $w.imethod._.dialog.history.push(msg);
      if ($w.imethod._.dialog.ext.prompt) {
        return $w.imethod._.dialog.ext.prompt(msg, callback);
      } else {
        var msg = $w.prompt(msg);
        callback && callback(msg);
        return {};
      }
    },
    dialog: function (text, opts) {
      $w.imethod._.dialog.history.push(text);
      if ($w.imethod._.dialog.ext.dialog) {
        return $w.imethod._.dialog.ext.dialog(text, opts);
      } else {
        $w.alert(text);
        return {};
      }
    }
  };
  $w.imethod.alert = $w.imethod._.dialog.alert;
  $w.imethod.confirm = $w.imethod._.dialog.confirm;
  $w.imethod.success = $w.imethod._.dialog.alert;
  $w.imethod.error = $w.imethod._.dialog.error;
  $w.imethod.warn = $w.imethod._.dialog.warn;
  $w.imethod.dialog = $w.imethod._.dialog.dialog;
  $w.imethod.prompt = $w.imethod._.dialog.prompt;
})(window);