'use strict';

describe('myApp.exchangeRateInfo module', function () {

    beforeEach(module('myApp.exchangeRateInfo'));

// //Mocks for the test
//  beforeEach(module({
//    InfoFactory: { getInfo: function() {return  "Factory"; }},
//    InfoService: { getInfo: function() {return  "Service"; }}
//  }));

    describe('CurrencyFactory', function () {

        var testResponse = {
            "date": "Nov 5, 2015 10:11:16 AM",
            "rates": [
                {
                    "currencyCode": "HRK",
                    "rate": 98.74
                },
                {
                    "currencyCode": "CHF",
                    "rate": 688.56
                },
                {
                    "currencyCode": "MXN",
                    "rate": 41.45
                },
                {
                    "currencyCode": "ZAR",
                    "rate": 49.36
                },
                {
                    "currencyCode": "INR",
                    "rate": 10.4
                },
                {
                    "currencyCode": "CNY",
                    "rate": 108.0
                },
                {
                    "currencyCode": "THB",
                    "rate": 19.27
                },
                {
                    "currencyCode": "AUD",
                    "rate": 489.67
                },
                {
                    "currencyCode": "KRW",
                    "rate": 0.6012
                },
                {
                    "currencyCode": "ILS",
                    "rate": 176.2
                },
                {
                    "currencyCode": "JPY",
                    "rate": 5.6232
                },
                {
                    "currencyCode": "PLN",
                    "rate": 175.96
                },
                {
                    "currencyCode": "GBP",
                    "rate": 1046.76
                },
                {
                    "currencyCode": "IDR",
                    "rate": 0.0506
                },
                {
                    "currencyCode": "HUF",
                    "rate": 2.376
                },
                {
                    "currencyCode": "PHP",
                    "rate": 14.61
                },
                {
                    "currencyCode": "TRY",
                    "rate": 238.96
                },
                {
                    "currencyCode": "RUB",
                    "rate": 10.83
                },
                {
                    "currencyCode": "HKD",
                    "rate": 88.43
                },
                {
                    "currencyCode": "EUR",
                    "rate": 745.92
                },
                {
                    "currencyCode": "XDR",
                    "rate": 952.12
                },
                {
                    "currencyCode": "MYR",
                    "rate": 159.45
                },
                {
                    "currencyCode": "CAD",
                    "rate": 520.89
                },
                {
                    "currencyCode": "USD",
                    "rate": 685.4
                },
                {
                    "currencyCode": "BGN",
                    "rate": 381.39
                },
                {
                    "currencyCode": "NOK",
                    "rate": 80.15
                },
                {
                    "currencyCode": "RON",
                    "rate": 167.68
                },
                {
                    "currencyCode": "SGD",
                    "rate": 487.21
                },
                {
                    "currencyCode": "CZK",
                    "rate": 27.58
                },
                {
                    "currencyCode": "SEK",
                    "rate": 79.42
                },
                {
                    "currencyCode": "NZD",
                    "rate": 453.45
                },
                {
                    "currencyCode": "BRL",
                    "rate": 180.33
                }
            ]
        };

        beforeEach(module('myApp.exchangeRateInfo'));

        it('should be able to fetch from the API', inject(function ($httpBackend, CurrencyFactory) {
            $httpBackend.expectGET('api/currency/dailyrates').respond(testResponse);

            var dailyrates = CurrencyFactory.getDailyRates();
            $httpBackend.flush();

            dailyrates.then(function (response) {
                expect(response.data).toBe(testResponse);
            });
        }));

        it('should be able to convert from the API', inject(function ($httpBackend, CurrencyFactory) {
            $httpBackend.expectGET('api/currency/calculator/100/eur/nok').respond(103.23);

            var dailyrates = CurrencyFactory.convert(100, {currencyCode: "eur"}, {currencyCode: "nok"});
            $httpBackend.flush();

            dailyrates.then(function (response) {
                expect(response.data).toBe(103.23);
            });
        }));

    });

    describe('DailyRatesController', function () {

        var testResponse = {
            "date": "Nov 5, 2015 10:11:16 AM",
            "rates": [
                {
                    "currencyCode": "HRK",
                    "rate": 98.74
                },
                {
                    "currencyCode": "CHF",
                    "rate": 688.56
                },
                {
                    "currencyCode": "MXN",
                    "rate": 41.45
                },
                {
                    "currencyCode": "ZAR",
                    "rate": 49.36
                },
                {
                    "currencyCode": "INR",
                    "rate": 10.4
                },
                {
                    "currencyCode": "CNY",
                    "rate": 108.0
                },
                {
                    "currencyCode": "THB",
                    "rate": 19.27
                },
                {
                    "currencyCode": "AUD",
                    "rate": 489.67
                },
                {
                    "currencyCode": "KRW",
                    "rate": 0.6012
                },
                {
                    "currencyCode": "ILS",
                    "rate": 176.2
                },
                {
                    "currencyCode": "JPY",
                    "rate": 5.6232
                },
                {
                    "currencyCode": "PLN",
                    "rate": 175.96
                },
                {
                    "currencyCode": "GBP",
                    "rate": 1046.76
                },
                {
                    "currencyCode": "IDR",
                    "rate": 0.0506
                },
                {
                    "currencyCode": "HUF",
                    "rate": 2.376
                },
                {
                    "currencyCode": "PHP",
                    "rate": 14.61
                },
                {
                    "currencyCode": "TRY",
                    "rate": 238.96
                },
                {
                    "currencyCode": "RUB",
                    "rate": 10.83
                },
                {
                    "currencyCode": "HKD",
                    "rate": 88.43
                },
                {
                    "currencyCode": "EUR",
                    "rate": 745.92
                },
                {
                    "currencyCode": "XDR",
                    "rate": 952.12
                },
                {
                    "currencyCode": "MYR",
                    "rate": 159.45
                },
                {
                    "currencyCode": "CAD",
                    "rate": 520.89
                },
                {
                    "currencyCode": "USD",
                    "rate": 685.4
                },
                {
                    "currencyCode": "BGN",
                    "rate": 381.39
                },
                {
                    "currencyCode": "NOK",
                    "rate": 80.15
                },
                {
                    "currencyCode": "RON",
                    "rate": 167.68
                },
                {
                    "currencyCode": "SGD",
                    "rate": 487.21
                },
                {
                    "currencyCode": "CZK",
                    "rate": 27.58
                },
                {
                    "currencyCode": "SEK",
                    "rate": 79.42
                },
                {
                    "currencyCode": "NZD",
                    "rate": 453.45
                },
                {
                    "currencyCode": "BRL",
                    "rate": 180.33
                }
            ]
        };

        beforeEach(module('myApp.exchangeRateInfo'));

        beforeEach(inject(function (CurrencyFactory, $q) {
            CurrencyFactory.getDailyRates = function () {
                return {
                    then: function (func) {
                        func({data: testResponse});
                    }
                };
            };
        }));

        it('should be defined', inject(function ($controller) {
            var ctrl = $controller("DailyRatesController");

            expect(ctrl).toBeDefined();
        }));

        it('should be defined rate', inject(function ($controller) {
            var ctrl = $controller("DailyRatesController");

            expect(ctrl.rate).toBeDefined();
        }));
    });
});