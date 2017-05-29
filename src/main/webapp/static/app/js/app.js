var blog = angular.module('blog', ['ngRoute',
									'blog.services',
									'blog.controllers',
									'commentControllers',
									'commentServices',
									'categoryControllers',
									'categoryServices',
									'bloggerControllers',
									'bloggerServices',
									'sharedControllers',
									'sharedDirectives']);

blog.config(function($routeProvider, $httpProvider) {
	
	$routeProvider
	.when('/home', {
		templateUrl: '/static/app/html/partial/post/postList.html',
		controller: 'postController'
	})
	.when('/post/:id', {
		templateUrl: '/static/app/html/partial/post/postItem.html',
		controller: 'postController'
	})
	.when('/addEditPost', {
		templateUrl: '/static/app/html/partial/post/addEditPost.html',
		controller: 'postController'
	})
	.when('/addEditPost/:id', {
		templateUrl: '/static/app/html/partial/post/addEditPost.html',
		controller: 'postController'
	})
	.when('/categories', {
		templateUrl: '/static/app/html/partial/category/categoryList.html',
		controller: 'categoryController'
	})
	.when('/addEditCategory', {
		templateUrl: '/static/app/html/partial/category/addEditCategory.html',
		controller: 'categoryController'
	})
	.when('/addEditCategory/:id', {
		templateUrl: '/static/app/html/partial/category/addEditCategory.html',
		controller: 'categoryController'
	})
	.when('/comments/:id', {
		templateUrl: '/static/app/html/partial/comment/commentList.html',
		controller: 'commentController'
	})
	.when('/bloggers', {
		templateUrl: '/static/app/html/partial/blogger/bloggerList.html',
		controller: 'bloggerController'
	})
	.when('/bloggers/:id', {
		templateUrl: '/static/app/html/partial/blogger/blogerProfile.html',
		controller: 'bloggerController'
	})
	.when('/login', {
		templateUrl: '/static/app/html/login.html',
		controller: 'navigationController'
	})
	.when('/register', {
		templateUrl: '/static/app/html/register.html',
		controller: 'navigationController'
	})
	.otherwise({
		redirectTo: '/home'
	});
	
	$httpProvider.defaults.headers.common['x-Requested-With'] = 'XMLHttpRequest';
	
});