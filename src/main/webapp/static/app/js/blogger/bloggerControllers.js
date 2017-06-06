var blog = angular.module('bloggerControllers', []);

blog.controller('bloggerController', function($rootScope, $scope, bloggerService, $routeParams, postService, $location){
	
	function init(){
		var path = $location.$$path;
		if(path == '/bloggers' && 
				(!$rootScope.currentBlogger || 
						$rootScope.currentBlogger.systemRole != 'ADMIN')) {
			$location.path('/home');
		}else if($routeParams.id && 
				(!$rootScope.currentBlogger || 
						($rootScope.currentBlogger.systemRole != 'ADMIN' && 
								$rootScope.currentBlogger.id != $routeParams.id))){
			$location.path('/home');
		}
	};
	
	$scope.getPage = function(){
		bloggerService.getPage($scope.page, $scope.size)
			.then(function success(response){
				$scope.bloggers = response.data.content;
				$scope.totalItems = response.data.totalElements;
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
				$scope.totalItems = response.data.totalElements;
			}, function error(response){
				
			});
	};
	
	$scope.deletePost = function(id){
		postService.delete(id)
			.then(function success(response){
				$scope.getPostsByBloggerId();
			}, function error(response){
				
			});
	};
	
	$scope.editPost = function(id){
		$location.path('/addEditPost/' + id);
	}
	
	$scope.showBlogger = function(id){
		$location.path('/bloggers/' + id);
	}
	
	//pagination
	
	$scope.pageChanged = function() {
		$log.log('Page changed to: ' + $scope.currentPage);
	};

	$scope.maxSize = 5;
	$scope.totalItems = 175;
	$scope.currentPage = $scope.page + 1;
		
	init();
});