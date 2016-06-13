'use strict';

angular.module('app')
	.directive('header',function(){
		return {
        templateUrl:'/public/directives/header/header.html',
        restrict: 'E',
        replace: true
    	}
	});


