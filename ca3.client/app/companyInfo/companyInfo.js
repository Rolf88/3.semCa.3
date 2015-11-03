'use strict';

angular.module('myApp.companyInfo', ['ngRoute', 'ui.bootstrap'])
        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/companyInfo', {
                    templateUrl: 'companyInfo/companyInfoView.html',
                    controller: 'companyInfoCtrl'
                });
            }])
        .controller('companyInfoCtrl', ['$http', '$scope', 'companyInfoFactory', function ($http, $scope, companyInfoFactory) {
                $scope.company = {};
                $scope.companyFound = false;
                $scope.searchText = "";
                $scope.errorMessage = "";

                $scope.getCompany = function () {
                    $scope.companyFound = false;
                    companyInfoFactory.searchCompany($scope.searchText).then(function (data) {
                        var data = data.data;
                        if (data.length <= 0) {
                            $scope.errorMessage = "Could not find any results";
                        } else {
                            $scope.errorMessage = "";
                            $scope.companyFound = true;
                            $scope.company = data;
                        }
                    }, function (error) {
                        if ($scope.searchText.length <= 0) {
                            $scope.errorMessage = "Please provide a search input";
                        } else {
                            $scope.errorMessage = "An error has orcured - See console";
                            console.log(error);
                        }
                    });
                };
            }])
        .factory('companyInfoFactory', ['$http', function ($http) {
                var urlBase = 'api/company';
                var companyInfoFactory = {};

                companyInfoFactory.searchCompany = function (search) {
                    return $http.get(urlBase + '/' + search);
                };

                return companyInfoFactory;
            }]);