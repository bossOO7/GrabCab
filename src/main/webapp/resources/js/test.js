var routerApp = angular.module('routerApp', ['ui.router', 'ngStorage']);
routerApp.config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/');

    $stateProvider

    // route for the home page
        .state('app', {
            url: '/',
            views: {
                'header':{
                    templateUrl: 'resources/html/header.html'
                },
                'content':{
                	templateUrl: 'resources/html/content.html',
                	controller: 'loginController'
                }

            }
        })
        .state('app.mainStateTest',{
        	url: 'mainTest',
        	views: {
        		'header@':{
        			templateUrl: 'resources/html/header.html'
        		},
        		'content@':{
        			templateUrl: 'resources/html/mainStateTestContent.html',
        			controller: 'mainStateController'
        		}
        	}
        })
});
        
     routerApp.controller('loginController',['$scope','$http','$state','$window',function($scope,$http,$state,$window){
	   $scope.login=function(){
	       $http({
	           method:'post',
	           url:'/grabCab/',
	           headers: {"Content-Type":"application/x-www-form-urlencoded"},
	           transformRequest: function(obj) {
	               var str = [];
	               for(var p in obj)
	               str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
	               return str.join("&");
	           },
	           data:{
	               username:"raghu.kolisetty@gmail.com",
	               password:"raghu"
	           }
	
	       }).then(function(data){
	           console.log(data);
	    	   if(data.status==200){
	               console.log(data.token);
	    		   $state.transitionTo("app.mainStateTest");
	            }
	           else{
	               
	
	           }
	       })
	   }
     }])
     
       routerApp.controller('mainStateController',['$scope','$http','$state','$window',function($scope,$http,$state,$window){
    	   $http({
    		   method:'post',
    		   url:'/grabCab/test2'
    	   }).then(function(data){
    		   console.log(data);
    	   }, function errorCallback(response) {
    		  console.log("error occured re!"); 
    	   });
     }])
