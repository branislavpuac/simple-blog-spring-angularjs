var blog = angular.module('blog.controllers', [])

blog.controller('postController', function($scope, postService, $routeParams){
	
	$scope.post = {};
	
	$scope.file = [];
	
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
	
	$scope.saveWithFile = function(){
		var file = $scope.file;
		var fd = new FormData();
		fd.append('file', file);
		fd.append('post',angular.toJson($scope.post,true));
		postService.saveWithFile(fd)
			.success(function(data){
				
			})
			.error(function(){
				
			});
	};
	
});