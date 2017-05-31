var blog = angular.module('commentControllers', []);

blog.controller('commentController', function($scope, commentService, $routeParams){
	
	$scope.likeClass = ['glyphicon', 'glyphicon-thumbs-up', 'sb-pointer'];
	
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
	
	$scope.updateLike = function(comment, index, choice){
		if(choice == 1){
			comment.positive = comment.positive + 1;
		}else{
			comment.negative = comment.negative + 1;
		}
		
		commentService.save(comment, $routeParams.id)
			.then(function success(response){
				$scope.comments[index] = response.data;
			}, function error(response){
				
			});
	};
	
	$scope.delete = function(id){
		commentService.delete(id)
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
	
});