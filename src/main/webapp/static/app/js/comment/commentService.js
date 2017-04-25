var blog = angular.module('blogServices', []);

blog.service('commentService', function($http){
	
	this.url = 'api/comments';
	
	this.getall = function(){
		return $http.get(this.url);
	}
	
});