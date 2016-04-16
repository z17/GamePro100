"use strict";

var gulp = require("gulp"),
	sass = require("gulp-sass"),
	autoprefixer = require('gulp-autoprefixer'),
	browserSync = require('browser-sync'),
	reload = browserSync.reload;

gulp.task('default', ['server', 'watch']);

// Запуск сервера
gulp.task('server', ['sass'], function () {
	browserSync({
		notify: false,
		port: 9000,
		server: {
			baseDir: 'web'
		}
	});	
});


//Слежка
gulp.task('watch', function () {
	gulp.watch('src/sass/**/*.scss', ['sass']);
	gulp.watch([
		'web/js/**/*.js'
	]).on('change', reload);
});

// Компилируем SCSS
gulp.task('sass', function () {
	gulp.src('./src/sass/main.scss')
		.pipe(sass.sync().on('error', sass.logError))
		.pipe(autoprefixer({
			browsers: ['> 1%']
		}))
		.pipe(gulp.dest('./web/css/'))
		.pipe(reload({stream: true}));
});