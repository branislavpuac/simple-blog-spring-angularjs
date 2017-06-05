var blog = angular.module('commentControllers', []);

blog.controller('commentController', function($rootScope, $scope, commentService, $routeParams, $location){
	
	function init(){
		var path = $location.$$path;
		if((path == '/commentsAdmin' || 
				path.startsWith('/editComment')) && 
				(!$rootScope.currentBlogger || 
						$rootScope.currentBlogger.systemRole != 'ADMIN')){
			$location.path('/home');
		}
	};
	
	$scope.getPage = function(){
		commentService.getPage($scope.page, $scope.size, $routeParams.id)
			.then(function success(response){
				$scope.comments = response.data.content;
				$scope.totalItems = response.data.totalElements;
			}, function error(response){
				
			});
	};
	
	$scope.findAllByPostId = function(){
		commentService.findAllByPostId($routeParams.id)
			.then(function success(response){
				$scope.comments = response.data;
				$scope.totalComments = response.data.length;
			}, function error(response){
				
			});
	};
	
	$scope.findAll = function(){
		commentService.findAll($scope.page, $scope.size)
			.then(function success(response){
				$scope.comments = response.data.content
				$scope.totalItems = response.data.totalElements;
			}, function error(response){
				
			});
	}
	
	$scope.updateLike = function(id, index, choice){
		commentService.updateLike(id, choice)
			.then(function success(response){
				if(response.data){
					$scope.comments[index] = response.data;
				}
			}, function error(response){
				
			});
		$scope.comments[index].like = true;
	};
	
	$scope.delete = function(id){
		commentService.delete(id)
			.then(function success(response){
				$scope.getPage();
			}, function error(response){
				
			});
	};
	
	$scope.showAllComments = function(){
		$location.path('/comments/' + $routeParams.id);
	};
	
	//pagination
	
	$scope.pageChanged = function() {
		$log.log('Page changed to: ' + $scope.currentPage);
	};

	$scope.maxSize = 5;
	$scope.totalItems = 175;
	$scope.currentPage = $scope.page + 1;
	
	init();
	
});