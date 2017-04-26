var blog = angular.module('blog', ['ngRoute',
									'blog.services',
									'blog.controllers',
									'commentControllers',
									'commentServices']);

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
	.otherwise({
		redirectTo: '/home'
	});
	
});