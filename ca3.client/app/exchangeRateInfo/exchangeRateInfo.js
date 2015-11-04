'use strict';

var CurrencyModule = angular.module('myApp.exchangeRateInfo', ['ngRoute']);

CurrencyModule.config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/exchangeRateInfo', {
            templateUrl: 'exchangeRateInfo/exchangeRateInfoView.html'
        });
    }]);

CurrencyModule.factory("CurrencyFactory", ["$http", function ($http) {
        var dailyrateCall = null;

        return {
            getDailyRates: function () {
                if (dailyrateCall === null) {
                    dailyrateCall = $http.get("api/currency/dailyrates");
                }

                return dailyrateCall;
            },
            convert: function (amount, currencyFrom, currencyTo) {
                return $http.get("api/currency/calculator/" + amount + "/" + currencyFrom.currencyCode + "/" + currencyTo.currencyCode).then(function (response) {
                    return response.data;
                });
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

CurrencyModule.controller("CurrencyConverterController", ["CurrencyFactory", function (CurrencyFactory) {
        var self = this;
        self.rate = [];
        self.amount = 100;
        self.result = undefined;

        self.convert = function () {
            CurrencyFactory.convert(self.amount, self.currencyFrom, self.currencyTo).then(function (result) {
                self.result = result;
            });
        };

        CurrencyFactory.getDailyRates().then(function (response) {
            self.rate = response.data;
            self.currencyFrom = self.rate.rates[0];
            self.currencyTo = self.rate.rates[0];
        });
    }]);