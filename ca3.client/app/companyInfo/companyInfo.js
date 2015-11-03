'use strict';

angular.module('myApp.companyInfo', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/companyInfo', {
    templateUrl: 'companyInfo/companyInfoView.html',
    controller: 'companyInfoCtrl'
  });
}])

.controller('companyInfoCtrl', function($http,$scope) {
  $http.get('api/demoadmin')
            .success(function (data, status, headers, config) {
              $scope.data = data;
            })
            .error(function (data, status, headers, config) {
              
             });
 
});