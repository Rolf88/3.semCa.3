'use strict';

describe('myApp.allUsers allUsersCtrl', function () {
    var scope, httpBackendMock, ctrl;
    var testResponse = {message: "Test Message Mock"};

    beforeEach(module('myApp.allUsers'));

    beforeEach(inject(function ($httpBackend, $rootScope, $controller) {
        httpBackendMock = $httpBackend;
        httpBackendMock.expectGET('api/admin/users').respond(testResponse);
        scope = $rootScope.$new();
        ctrl = $controller('allUsersCtrl', {$scope: scope});
    }));

    it('Should fetch a test message', function () {
        expect(ctrl.users).toBeUndefined();
        httpBackendMock.flush();
        expect(ctrl.users.message).toEqual("Test Message Mock");
    });
});