var blog = angular.module('blog.services', []);

blog.service('postService', function($http){
	
	this.url = '/api/posts';
	
	this.getPage = function(page, size) {
		return $http.get(this.url, { params: { 'page': page, 'size': size} });
	}

	this.getOne = function(id) {
		return $http.get(this.url + '/' + id);
	}

	this.save = function(post) {
		if(post.id){
			return $http.put(this.url + '/' + post.id, post);
		}else{
			return $http.post(this.url, post);
		}
	}

	this.delete = function(id) {
		return $http.delete(this.url + '/' + id);
	}
	
});