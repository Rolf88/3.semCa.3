'use strict';

angular.module('myApp.documentation', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/documentation', {
                    templateUrl: 'documentation/documentationView.html',
                    controller: 'documentationCtrl'
                });
            }])

        .controller('documentationCtrl', function ($http, $scope) {   
        });