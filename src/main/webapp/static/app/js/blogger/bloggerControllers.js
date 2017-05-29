var blog = angular.module('bloggerControllers', []);

blog.controller('bloggerController', function($scope, bloggerService){
	
	$scope.getPage = function(){
		bloggerService.getPage($scope.page, $scope.size)
			.then(function success(response){
				$scope.bloggers = response.data.content;
			}, function error(response){
				
			});
	};
	
});