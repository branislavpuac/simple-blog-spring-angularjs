var blog = angular.module('categoryControllers', []);

blog.controller('categoryController', function($rootScope, $scope, $routeParams, categoryService, $location){
	
	function init(){
		var path = $location.$$path;
		if(path == '/categories' || 
				path.startsWith('/addEditCategory') && 
				(!$rootScope.currentBlogger || 
						$rootScope.currentBlogger != 'ADMIN')){
			$location.path('/home');
		}
	}
	
	$scope.getPage = function(){
		categoryService.getPage($scope.page, $scope.size)
			.then(function success(response){
				$scope.categories = response.data.content;
				$scope.totalItems = response.data.totalElements;
			}, function error(response){
				
			});
	};
	
	$scope.getOne = function(){
		if($routeParams.id){
			categoryService.getOne($routeParams.id)
			.then(function success(response){
				$scope.category = response.data;
			}, function error(response){
				
			});
		}else{
			$scope.category = {};
		};
		
	};
	
	$scope.save = function(){
		categoryService.save($scope.category)
			.then(function success(response){
				
			}, function error(response){
				
			});
	};
	
	$scope.delete = function(id){
		categroyService.delete(id)
			.then(function success(response){
				$scope.getPage();
			}, function error(response){
				
			});
			
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