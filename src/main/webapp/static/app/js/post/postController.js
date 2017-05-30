var blog = angular.module('blog.controllers', [])

blog.controller('postController', function($scope, postService, $routeParams, categoryService){
	
	$scope.post = {};
	
	$scope.file = [];
	
	$scope.getPage = function(){
		postService.getPage($scope.page, $scope.size)
			.then(function success(response){
				$scope.posts = response.data.content;
			}, function error(response){
				
			});
	};

	$scope.getOne = function(){
		if ($routeParams.id) {
			postService.getOne($routeParams.id).then(
				function success(response) {
					$scope.post = response.data;
				}, function error() {

				});
		}
	};
	
	$scope.saveWithFile = function(){
		var file = $scope.file;
		var fd = new FormData();
		fd.append('file', file);
		fd.append('post',angular.toJson($scope.post,true));
		postService.saveWithFile(fd)
			.then(function success(){
				
			}, function error(){
				
			});
	};
	
	$scope.getCategories = function(){
		categoryService.getAll()
			.then(function success(response){
				$scope.categories = response.data;
			}, function error(response){
				
			})
	}
	
});