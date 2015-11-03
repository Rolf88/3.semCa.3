'use strict';

angular.module('myApp.documentation', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
            $routeProvider.when('/documentation', {
              templateUrl: 'documentation/documentationView.html',
              controller: 'documentationCtrl'
            });
          }])

        .controller('documentationCtrl', function ($http, $scope) {
          $http({
            method: 'GET',
            url: 'api/demouser'
          }).then(function successCallback(res) {
            $scope.data = res.data.message;
          }, function errorCallback(res) {
            $scope.error = res.status + ": "+ res.data.statusText;
          });

        });