'use strict';

/**
 * @ngdoc directive
 * @name izzyposWebApp.directive:adminPosHeader
 * @description
 * # adminPosHeader
 */
angular.module('app')
	.directive('headerNotification',function(){
		return {
        templateUrl:'/public/app-services/directives/header/header-notification/header-notification.html',
        restrict: 'E',
        replace: true
    	}
	});


