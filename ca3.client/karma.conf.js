module.exports = function (config) {
    config.set({
        basePath: './',
        files: [
            'app/bower_components/angular/angular.js',
            'app/bower_components/angular-route/angular-route.js',
            'app/bower_components/angular-mocks/angular-mocks.js',
            'app/bower_components/angular-bootstrap/ui-bootstrap.js',
            'app/components/**/*.js',
            'app/components/*.js',
            'app/allUsers/**/*.js',
            'app/companyInfo/**/*.js',
            'app/createUser/**/*.js',
            'app/documentation/**/*.js',
            'app/exchangeRateInfo/**/*.js',
            'app/home/**/*.js',
            'app/app.js',
            'test/app/allUsers/*.js',
            'test/app/createUser/*.js',
            'test/app/exchangeRateInfo/**/*.js',
            'test/app/components/**/*.js'
        ],
        autoWatch: true,
        frameworks: ['jasmine'],
        browsers: ['Chrome'],
        plugins: [
            'karma-chrome-launcher',
            'karma-firefox-launcher',
            'karma-jasmine',
            'karma-junit-reporter'
        ],
        junitReporter: {
            outputFile: 'test_out/unit.xml',
            suite: 'unit'
        }

    });
};
