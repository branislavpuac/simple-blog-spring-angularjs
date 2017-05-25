var sharedControllers = angular.module('sharedControllers', []);

sharedControllers.controller('navigationController', function($scope, $http, $rootScope, $location){
	
	$scope.credentials = {};
	
	var authenticate = function(credentials, callback) {
		
		var headers = credentials ? {
			authorization : "Basic "
				+ btoa(credentials.username + ":" + credentials.password)
		} : {};
		
		$http.get('/user', { headers: headers})
			.then(function success(response){
				if(response.data.name){
					$rootScope.currentBloger = response.data.principal;
				}else{
					$rootScope.burrentBloger = null;
				}
				callback && callback($rootScope.currentBloger, response.data);
			}, function error(response){
				$scope.currentBloger = null;
				callback && callback(false, response.data)
			});
	};
	
	authenticate();
	
	$scope.login = function(){
		authenticate($scope.credentials, function(currentBloger){
			if(currentBloger){
				console.log('Login succeeded');
				$location.path('/');
			}else{
				console.log('Login failed');
				$location.path('/login');
				$rootScope.currentBloger = null;
			}
		});
	};
	
	$scope.logout = function(){
		$http.post('/logout', {})
			.then(function success(){
				$rootScope.currentbloger = null
				$location.path('/')
			}, function error(){
				$console.log('Logout failed');
				$rootScope.currentBloger = null;
				$location.path('/');
			});
	};
	
	$scope.file = [];
	
	$scope.register = function(){
		var file = $scope.file
		var fd = new FormData();
		fd.append('file', file);
		fd.append('bloger',angular.toJson($scope.bloger,true));
		$http.post('api/users/file', fd, {
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