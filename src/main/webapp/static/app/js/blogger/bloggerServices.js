var blog = angular.module('bloggerServices', []);

blog.service('bloggerService', function($http){
	
	this.url = '/api/bloggers';
	
	this.getPage = function(page, size){
		return $http.get(this.url, { params: { 'page': page, 'size': size }});
	};
	
	this.getOne = function(id){
		return $http.get(this.url + '/' + id);
	};
	
	this.saveWithFile = function(fd){
		return $http.post('/api/bloggers/file', fd, {
			trasformRequest: angular.identity,
			headers: {
				'Content-Type' : undefined
			}
		});
	};
	
});