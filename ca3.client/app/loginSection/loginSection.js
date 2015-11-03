'use strict';

angular.module('myApp.loginSection', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/loginSection', {
    templateUrl: 'loginSection/loginSectionView.html',
    controller: 'loginSectionCtrl'

        });
}])

.controller('loginSectionCtrl', function() {
});