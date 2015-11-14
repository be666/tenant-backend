var gulp = require('gulp');

var minifycss = require('gulp-minify-css'), // CSS压缩
    uglify = require('gulp-uglify'),        // js压缩
    concat = require('gulp-concat'),        // 合并文件
    rename = require('gulp-rename'),        // 重命名
    clean = require('gulp-clean'),
    less = require('gulp-less'),
    tmodjs = require('gulp-tmod'),
    mkdirp = require('mkdirp'),
    gulpSequence = require('gulp-sequence').use(gulp);

var cleanOff = true;
// Compile SASS & auto-inject into browsers
gulp.task('clean_bootstrap', function () {
    if (!cleanOff) {
        return gulp.src('dist/css/boot.min.css', {read: false})
            .pipe(clean({force: true}))
            .pipe(notify({message: 'clean_bootstrap task complete'}));
    }

});

gulp.task('less_bootstrap', function () {
    return gulp.src('javascript/00-lib/bootstrap/less/bootstrap.less')
        .pipe(less())
        .pipe(rename({suffix: '.min', basename: "boot"}))
        .pipe(gulp.dest('dist/css'))
});


gulp.task('copy_font', function () {
    return gulp.src('fonts/*')
        .pipe(gulp.dest('dist/fonts'))
});
gulp.task('copy_image', function () {
    return gulp.src('image/*.png')
        .pipe(gulp.dest('dist/image'))
});

var uiFile = "javascript/04-template/ui/*.ejs";
gulp.task('ui_template_pre', function () {
    return gulp.src(uiFile)
        .pipe(rename({extname: '.html'}))
        .pipe(gulp.dest('out/ui'))
});

gulp.task('ui_template_compile', function () {
    return gulp.src("out/ui/*.html")
        .pipe(tmodjs({
            base: 'out/ui',
            combo: true,
            "charset": "utf-8",
            //"syntax": "native",
            "helpers": "javascript/04-template/help.js",
            "escape": true,
            "compress": true,
            "type": "default",
            "runtime": "template.js",
            "minify": false,
            "cache": false,
            output: 'out/tpl/ui'
        }))
})


var viewFile = "javascript/04-template/view/**/*.ejs";

gulp.task('view_template_pre', function () {
    return gulp.src(viewFile)
        .pipe(rename({extname: '.html'}))
        .pipe(gulp.dest('out/vi/view'))

});


gulp.task('view_template_compile', function () {
    return gulp.src("out/vi/**/*.html")
        .pipe(tmodjs({
            base: 'out/vi/',
            combo: false,
            "charset": "utf-8",
            "syntax": "native",
            "helpers": "javascript/04-template/help.js",
            "escape": true,
            "compress": true,
            "type": "cmd",
            "alias": "template",
            "runtime": "template.js",
            "minify": false,
            "cache": false,
            output: 'out/tpl/vi'
        }))
})


gulp.task('clean_lib', function () {
    if (!cleanOff) {
        return gulp.src(['dist/js/lib.js'], {read: false})
            .pipe(clean({force: true}))
    }
});


gulp.task('make_lib', function () {
    return gulp.src([
        'javascript/00-lib/jquery/jquery.js',
        'javascript/00-lib/art/template-native.js',
        'javascript/00-lib/seajs/sea.js'
    ])
        .pipe(concat("lib.js"))
        .pipe(gulp.dest('out/js'))
        .pipe(rename({suffix: '.min'}))
        //.pipe(uglify())
        .pipe(gulp.dest('dist/js'))
});


gulp.task('clean_core', function () {
    if (!cleanOff) {
        return gulp.src(['dist/js/core.js'], {read: false})
            .pipe(clean({force: true}))
    }
});


var coreFile = [
    'javascript/01-core/00-imethod.js',
    'javascript/01-core/01-log.js',
    'javascript/01-core/02-json.js',
    'javascript/01-core/03-http.js',
    'javascript/01-core/04-dialog.js',
    'javascript/01-core/05-util.js',
    'javascript/01-core/06-heart.js',
    'javascript/01-core/07-user.js',
    'javascript/01-core/08-hash.js',
    'javascript/01-core/09-script.js',
    'javascript/01-core/10-resize.js',
    'javascript/01-core/99-debug.js'
];

gulp.task('make_core', function () {
    return gulp.src(coreFile)
        .pipe(concat("core.js"))
        .pipe(gulp.dest('out/js'))
        .pipe(rename({suffix: '.min'}))
        //.pipe(uglify())
        .pipe(gulp.dest('dist/js'))
});


gulp.task('clean_ui', function () {
    if (!cleanOff) {
        return gulp.src(['dist/js/ui.js'], {read: false})
            .pipe(clean({force: true}))
    }
});


gulp.task('make_ui', function () {
    return gulp.src([
        'javascript/04-template/help.js',
        'out/tpl/ui/.cache/*.js',
        'javascript/05-ui/01-dialog.impl.js',
        'javascript/05-ui/02-pagination.js',
        'javascript/05-ui/03-nav.js',
        'javascript/05-ui/03-http.js',
        'javascript/05-ui/04-select.js',
        'javascript/05-ui/05-page.js',
        'javascript/05-ui/06-tabs.js',
        'javascript/05-ui/06-pagination.js',
        'javascript/05-ui/07-tree.js',
        'javascript/05-ui/08-btn.js'
    ])
        .pipe(concat("ui.js"))
        .pipe(gulp.dest('out/js'))
        .pipe(rename({suffix: '.min'}))
        //.pipe(uglify())
        .pipe(gulp.dest('dist/js'))
});

gulp.task('module_less', function () {
    return gulp.src(['stylesheet/imethod.less'])
        .pipe(less())
        .pipe(rename({suffix: '.min'}))
        .pipe(minifycss())
        .pipe(gulp.dest('dist/css'))
});

var serviceFile = 'javascript/02-service/**/*.js';
gulp.task('build_service', function () {
    return gulp.src(serviceFile)
        .pipe(gulp.dest('out/js/service'))
        //.pipe(uglify())
        .pipe(gulp.dest('dist/js/service'))
});

gulp.task('build_view', function () {
    return gulp.src(['out/tpl/vi/**/*.js'])
        .pipe(gulp.dest('out/js'))
        //.pipe(uglify())
        .pipe(gulp.dest('dist/js'))
});


var controllerFile = 'javascript/06-controller/**/*.js'

gulp.task('build_controller', function () {
    return gulp.src(controllerFile)
        .pipe(gulp.dest('out/js/controller'))
        //.pipe(uglify())
        .pipe(gulp.dest('dist/js/controller'))
});


gulp.task('config', function () {
    return gulp.src('javascript/07-config/*.js')
        .pipe(concat('config.js'))
        .pipe(gulp.dest('out/js'))
        .pipe(rename({suffix: '.min'}))
        //.pipe(uglify())
        .pipe(gulp.dest('dist/js'))
});

var playerFile = [
    "javascript/00-lib/jwplayer-7.1.4/**/*.js",
    "javascript/00-lib/jwplayer-7.1.4/**/*.swf",
    "javascript/00-lib/jwplayer-7.1.4/**/*.css"
];
gulp.task("player", function () {
    return gulp.src(playerFile)
        .pipe(gulp.dest('dist/js/jwplayer'))
});


var uEditFile = [
    "javascript/00-lib/uedit/utf8-jsp/**"
];

gulp.task("uEdit", function () {
    return gulp.src(uEditFile)
        .pipe(gulp.dest('dist/js/uedit'))
});

gulp.task('bootstrap', gulpSequence('clean_bootstrap', 'less_bootstrap'));
gulp.task('ui', gulpSequence('clean_ui', 'ui_template_pre', 'ui_template_compile', 'make_ui'));
gulp.task('view', gulpSequence('view_template_pre', 'view_template_compile', 'build_view'));
gulp.task('lib', gulpSequence('clean_lib', 'make_lib'));
gulp.task('core', gulpSequence('clean_core', 'make_core'));
var outDir = [
    'out/js',
    'out/tpl',
    'out/ui',
    'out/vi'
];
var distDir = [
    'dist/css',
    'dist/fonts',
    'dist/image',
    'dist/js'
];
gulp.task("clean", function () {
    if (!cleanOff) {
        gulp.src(distDir, {read: false})
            .pipe(clean({force: true}));
        gulp.src(outDir, {read: false})
            .pipe(clean({force: true}))
        for (var k in outDir) {
            mkdirp(outDir[k])
        }
        for (var k in outDir) {
            mkdirp(distDir[k])
        }
    }
});
gulp.task("clean_out", function () {
    if (!cleanOff) {
        gulp.src(outDir, {read: false})
            .pipe(clean({force: true}))
    }

});
gulp.task('default', gulpSequence(
    'clean',
    'lib',
    "bootstrap",
    'player',
    'uEdit',
    'core',
    'ui',
    'view',
    'build_service',
    'build_controller',
    'config',
    'module_less',
    'copy_font',
    'copy_image',
    "clean_out"
));








