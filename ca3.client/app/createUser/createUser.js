'use strict';

angular.module('myApp.createUser', ['ngRoute'])
        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/createUser', {
                    templateUrl: 'createUser/createUserView.html',
                    controller: 'createUserCtrl'
                });
            }])
        .controller('createUserCtrl', function ($http, $scope, $location) {
            $scope.error = null;
            $scope.save = function () {
                $http.post("http://localhost:8084/ca3.server/api/create", $scope.newuser)
                        .success(function (data, status, headers, config) {
                            $scope.error = null;
                            $location.path("#/home");
                        })
                        .error(function (data, status, headers, config) {
                            $scope.error = "user allready exist";
                        });
                ;
            };
        });