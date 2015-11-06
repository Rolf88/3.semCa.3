'use strict';

describe('myApp.createUser createUserCtrl', function () {
    var scope, httpBackendMock, ctrl;
    var testResponse = {message: "Test Message Mock"};

    beforeEach(module('myApp.createUser'));

    beforeEach(inject(function ($httpBackend, $rootScope, $controller) {
        httpBackendMock = $httpBackend;
        httpBackendMock.expectPOST('api/create').respond(testResponse);
        scope = $rootScope.$new();
        ctrl = $controller('createUserCtrl', {$scope: scope});
    }));

    it('Should not fetch an error message', function () {
        expect(scope.error).toEqual(null);
    });
});