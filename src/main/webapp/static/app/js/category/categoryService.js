var blog = angular.module('categoryServices', []);

blog.service('categoryService', function($http){
	
	this.url = '/api/categories';
	
	this.getAll = function(){
		return $http.get(this.url);
	};
	
	this.getPage = function(page, size){
		return $http.get(this.url, { params: { 'page': page, 'size': size }});
	};
	
	this.getOne = function(id){
		return $http.get(this.url + '/' + id);
	};
	
	this.save = function(category){
		if(category.id){
			return $http.put(this.url + '/' + category.id, category);
		}else{
			return $http.post(this.url, category);
		}
	};
	
	this.delete = function(id){
		return $http.delete(this.url + '/' + id);
	};
	
});