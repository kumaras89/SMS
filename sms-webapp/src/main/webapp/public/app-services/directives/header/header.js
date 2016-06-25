'use strict';

angular.module('app')
	.directive('header',function(){
		return {
        templateUrl:'/public/app-services/directives/header/header.html',
        restrict: 'E',
        replace: true
    	}
	});


