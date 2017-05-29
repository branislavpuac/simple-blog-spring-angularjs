var blog = angular.module('bloggerServices', []);

blog.service('bloggerService', function($http){
	
	this.url = '/api/bloggers';
	
	this.getPage = function(page, size){
		return $http.get(this.url, { params: { 'page': page, 'size': size }});
	}
	
});