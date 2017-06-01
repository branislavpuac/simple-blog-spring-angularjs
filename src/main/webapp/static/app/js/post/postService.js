var blog = angular.module('blog.services', []);

blog.service('postService', function($http){
	
	this.url = '/api/posts';
	
	this.getPage = function(page, size) {
		return $http.get(this.url, { params: { 'page': page, 'size': size} });
	};
	
	this.findAll = function(page, size) {
		return $http.get(this.url + '/admin', { params: { 'page': page, 'size': size }});
	};
	
	this.getPostsByBloggerId = function(id, page, size){
		return $http.get(this.url, {params: { 'id': id, 'page': page, 'size': size }});
	};

	this.getOne = function(id) {
		return $http.get(this.url + '/' + id);
	};

	this.save = function(post) {
		if(post.id){
			return $http.put(this.url + '/' + post.id, post);
		}else{
			return $http.post(this.url, post);
		}
	};
	
	this.saveWithFile = function(fd){
		return $http.post(this.url + '/file', fd, {
			transformRequest : angular.identity,
			headers : {
			'Content-Type' : undefined
			}
		});
	};

	this.delete = function(id) {
		return $http.delete(this.url + '/' + id);
	};
	
});