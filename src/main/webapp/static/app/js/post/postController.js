var blog = angular.module('blog.controllers', [])

blog.controller('postController', function($scope, postService, $routeParams){
	
	$scope.getPage = function(){
		postService.getPage($scope.page, $scope.size)
			.success(function(data){
				$scope.posts = data.content;
			})
			.error(function(){
				
			});
	};

	$scope.getOne = function(){
		postService.getOne($routeParams.id)
			.success(function(data){
				$scope.post = data;
			})
			.error(function(){

			});
	};
	
});