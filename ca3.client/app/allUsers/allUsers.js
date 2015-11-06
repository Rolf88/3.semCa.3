'use strict';

var app = angular.module('myApp.allUsers', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/allUsers', {
                    templateUrl: 'allUsers/allUsersView.html',
                    controller: 'allUsersCtrl'
                });
            }])

        .controller('allUsersCtrl', function ($http, userFactory) {
            var self = this;
            self.removeUser = function (user) {
                userFactory.removeUser(user.id).then(function (response) {
                    var indexOfUser = self.users.indexOf(user);
                    self.users.splice(indexOfUser, 1);
                })
            }

            userFactory.getAllUsers().then(function (response) {
                self.users = response.data;
            })

        });

app.factory("userFactory", ["$http", function ($http) {
        return{
            getAllUsers: function () {
                return $http.get("api/admin/users");
            }
            , removeUser: function (id) {
                return $http.delete("api/admin/user/" + id);
            }
        }
    }]);






            