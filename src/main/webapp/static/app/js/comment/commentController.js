var blog = angular.module('commentControllers', []);

blog.controller('commentController', function($scope, commentService, $routeParams){
	
	$scope.likeClass = ['glyphicon', 'glyphicon-thumbs-up', 'sb-pointer']
	
	$scope.getPage = function(){
		console.log($routeParams.id)
		commentService.getPage($scope.page, $scope.size, $routeParams.id)
			.success(function(data){
				$scope.comments = data.content;
				$scope.totalComments = data.totalElements;
			})
			.error(function(){
				
			})
	};
	
	$scope.updateLike = function(comment, index, choice){
		console.log(event);
		if(choice == 1){
			comment.positive = comment.positive + 1;
		}else{
			comment.negative = comment.negative + 1;
		}
		
		commentService.save(comment, $routeParams.id)
			.success(function(data){
				$scope.comments[index] = data;
			})
			.error(function(data){
				
			});
	};
	
});