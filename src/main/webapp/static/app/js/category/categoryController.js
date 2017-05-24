var blog = angular.module('categoryControllers', []);

blog.controller('categoryController', function($scope, $routeParams, categoryService){
	
	$scope.getPage = function(){
		categoryService.getPage($scope.page, $scope.size)
			.then(function success(response){
				$scope.categories = response.data;
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
	
});