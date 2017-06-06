var blog = angular.module('blog.controllers', [])

blog.controller('postController', function($rootScope, $scope, postService, $routeParams, categoryService, $location){
	
	function init(){
		var path = $location.$$path;
		if(path == '/postsAdmin' && (!$rootScope.currentBlogger ||  ($rootScope.currentBlogger && $rootScope.currentBlogger.systemRole != 'ADMIN'))) {
			$location.path('/home');
		}else if(path.startsWith('/addEditPost') && !$rootScope.currentBlogger) {
			$location.path('/home');
		}
	};
	
	$scope.post = {};
	
	$scope.file = [];
	
	$scope.getPage = function(){
		postService.getPage($scope.page, $scope.size)
			.then(function success(response){
				$scope.posts = response.data.content;
				$scope.totalItems = response.data.totalElements;
			}, function error(response){
				
			});
	};
	
	$scope.findAll = function(){
		postService.findAll($scope.page, $scope.size)
			.then(function success(response){
				$scope.posts = response.data.content;
				$scope.totalItems = response.data.totalElements;
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
	
	$scope.delete = function(id){
		postService.delete(id)
			.then(function success(response){
				$scope.getPage();
			}, function error(response){
				
			});
	}
	
	$scope.getCategories = function(){
		categoryService.getAll()
			.then(function success(response){
				$scope.categories = response.data;
			}, function error(response){
				
			})
	}
	
	//pagination
	
	$scope.pageChanged = function() {
		$scope.page = $scope.currentPage -1;
		if($location.$$path.includes('Admin')){
			$scope.findAll();
		}else{
			$scope.getPage();
		}
		
	};

	$scope.maxSize = 5;
	$scope.totalItems = 175;
	$scope.currentPage = 1;
	
	
	init();
	
});