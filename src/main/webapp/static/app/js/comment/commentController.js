var blog = angular.module('commentControllers', []);

blog.controller('commentController', function($scope, commentService, $routeParams){
	
	$scope.likeClass = ['glyphicon', 'glyphicon-thumbs-up', 'sb-pointer'];
	
	$scope.getPage = function(){
		console.log($routeParams.id)
		commentService.getPage($scope.page, $scope.size, $routeParams.id)
			.then(function success(response){
				$scope.comments = response.data.content;
				$scope.totalComments = response.data.totalElements;
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
	
	$scope.updateLike = function(comment, index, choice){
		console.log(event);
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
	
});