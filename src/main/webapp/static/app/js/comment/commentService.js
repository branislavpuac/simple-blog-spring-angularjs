var blog = angular.module('commentServices', []);

blog.service('commentService', function($http){
	
	this.url = 'api/comments';
	
	this.getPage = function(page, size){
		return $http.get(this.url, { params: {'page': page, 'size': size } });
	}
	
});