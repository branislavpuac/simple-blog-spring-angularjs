var blog = angular.module('commentControllers', []);

blog.controller('commentController', function($scope, commentService){
	
	$scope.getPage = function(){
		commentService.getPage($scope.page, $scope.size)
			.success(function(data){
				$scope.comments = data.content;
				$scope.totalComments = data.totalElements;
			})
			.error(function(){
				
			})
	}
	
});