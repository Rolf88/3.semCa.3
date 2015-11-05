'use strict';

angular.module('myApp.createUser', ['ngRoute', 'ui.bootstrap'])
        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/createUser', {
                    templateUrl: 'createUser/createUserView.html',
                    controller: 'createUserCtrl'
                });
            }])
        .controller('createUserCtrl', function ($http, $scope) {

            $scope.save = function () {
                $http.post("http://localhost:8084/ca3.server/api/create", $scope.newuser);
            };
        });