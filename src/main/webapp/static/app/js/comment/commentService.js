var blog = angular.module('commentServices', []);

blog.service('commentService', function($http){
	
	this.url = 'api/comments';
	
	this.getPage = function(page, size, postId){
		return $http.get(this.url, { params: {'page': page, 'size': size, 'postId': postId }});
	};
	
	this.findAllByPostId = function(postId){
		return $http.get(this.url, { params: { 'postId': postId }});
	};
	
	this.save = function(comment, postId){
		if(comment.id){
			return $http.put(this.url + '/' + comment.id, comment, { params: { 'postId': postId }});
		}else{
			return $http.post(this.url, comment, { params: { 'postId': postId }});
		}
	};
	
});