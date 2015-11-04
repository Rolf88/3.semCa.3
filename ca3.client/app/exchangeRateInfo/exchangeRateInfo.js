'use strict';

var CurrencyModule = angular.module('myApp.exchangeRateInfo', ['ngRoute']);

CurrencyModule.config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/exchangeRateInfo', {
            templateUrl: 'exchangeRateInfo/exchangeRateInfoView.html'
        });
    }]);

CurrencyModule.factory("CurrencyFactory", ["$http", function ($http) {
        return {
            getDailyRates: function () {
                return $http.get("api/currency/dailyrates");
            }
        };
    }]);


CurrencyModule.controller("DailyRatesController", ["CurrencyFactory", function (CurrencyFactory) {
        var self = this;
        self.rate = [];
        
        CurrencyFactory.getDailyRates().then(function (response) {
            self.rate = response.data;
        });
    }]);