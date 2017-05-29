var blog = angular.module('bloggerControllers', []);

blog.controller('bloggerController', function($scope, bloggerService, $routeParams, postService){
	
	$scope.getPage = function(){
		bloggerService.getPage($scope.page, $scope.size)
			.then(function success(response){
				$scope.bloggers = response.data.content;
			}, function error(response){
				
			});
	};
	
	$scope.getOne = function(){
		bloggerService.getOne($routeParams.id)
			.then(function success(response){
				$scope.blogger = response.data;
			}, function error(response){
				
			});
	};
	
	$scope.getPostsByBloggerId = function(){
		postService.getPostsByBloggerId($routeParams.id, $scope.page, $scope.size)
			.then(function success(response){
				$scope.posts = response.data.content
			}, function error(response){
				
			});
	};
	
});