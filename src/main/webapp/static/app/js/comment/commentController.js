var blog = angular.module('blogControllers', []);

blog.controller('commentController', function($scope){
	
	$scope.getAll = function(){
		commentService.getAll()
			.success(function(data){
				
			})
			.error(function(){
				
			})
	}
	
});