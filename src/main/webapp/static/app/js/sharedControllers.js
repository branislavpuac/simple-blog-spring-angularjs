var sharedControllers = angular.module('sharedControllers', []);

sharedControllers.controller('navigationController', function($scope, $http, $rootScope, $location){
	
	$scope.credentials = {};

	$scope.incorectPassword = false;
	
	var authenticate = function(credentials, callback) {
		
		var headers = credentials ? {
			authorization : "Basic "
				+ btoa(credentials.username + ":" + credentials.password)
		} : {};
		
		$http.get('/user', { headers: headers})
			.then(function success(response){
				if(response.data.name){
					$rootScope.currentBlogger = response.data.principal;
				}else{
					$rootScope.burrentBlogger = null;
				}
				callback && callback($rootScope.currentBlogger, response.data);
			}, function error(response){
				$scope.currentBlogger = null;
				$scope.incorectPassword = true;
				callback && callback(false, response.data)
			});
	};
	
	authenticate();
	
	$scope.login = function(){
		authenticate($scope.credentials, function(currentBlogger){
			if(currentBlogger){
				console.log('Login succeeded');
				$location.path('/');
			}else{
				console.log('Login failed');
				$location.path('/login');
				$rootScope.currentBlogger = null;
			}
		});
	};
	
	$scope.logout = function(){
		$http.post('/logout', {})
			.then(function success(){
				$rootScope.currentBlogger = null
				$location.path('/')
			}, function error(){
				console.log('Logout failed');
				$rootScope.currentBlogger = null;
				$location.path('/');
			});
	};
	
	$scope.file = [];
	
	$scope.register = function(){
		var file = $scope.file
		var fd = new FormData();
		fd.append('file', file);
		fd.append('blogger',angular.toJson($scope.blogger,true));
		$http.post('/api/bloggers/file', fd, {
			transformRequest : angular.identity,
			headers : {
			'Content-Type' : undefined
			}
		})
		.then(function success(response){
			$location.path('/home')
		}, function error(response){
			
		});			
	};
	
});