'use strict';

angular.module('myApp.allUsers', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/allUsers', {
    templateUrl: 'allUsers/allUsersView.html'
  });
}]);