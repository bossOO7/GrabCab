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
                	templateUrl: 'resources/html/login.html',
                	controller: 'loginController'
                }

            }
        })
        .state('app.register',{
        	url: 'register',
        	views: {
        		'header@':{
        			templateUrl: 'resources/html/header.html'
        		},
        		'content@':{
        			templateUrl: 'resources/html/register.html',
        			controller: 'registerController'
        		}
        	}
        })
        .state('app.home',{
        	url: 'mainTest',
        	views: {
        		'header@':{
        			templateUrl: 'resources/html/header.html'
        		},
        		'content@':{
        			templateUrl: 'resources/html/home.html',
        			controller: 'homeController'
        		}
        	}
        })
});
        
     routerApp.controller('loginController',['$scope','$http','$state','$window',function($scope,$http,$state,$window){
	   $scope.login=function(){
		   if($scope.driverOrPassenger=="driver"){
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
		               username:$scope.email,
		               password:$scope.password,
		               type:"driver"
		           }
		
		       }).then(function(data){
		           console.log(data);
		    	   if(data.status==200){
		               console.log(data.token);
		    		   $state.transitionTo("app.home");
		            }
		           else{
		               
		
		           }
		       })
		   }
		   else{
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
		               username:$scope.email,
		               password:$scope.password,
		               type:"passenger"
		           }
		
		       }).then(function(data){
		           console.log(data);
		    	   if(data.status==200){
		               console.log(data.token);
		    		   $state.transitionTo("app.home");
		            }
		           else{
		               
		
		           }
		       })
		   }
	   }
	   $scope.switchToRegister=function(){
		   $state.transitionTo("app.register");
	   }
     }])
     
       routerApp.controller('homeController',['$scope','$http','$state','$window',function($scope,$http,$state,$window){
    	   $http({
    		   method:'post',
    		   url:'/grabCab/testing'
    	   }).then(function(data){
    		   console.log(data);
    	   }, function errorCallback(response) {
    		  console.log("error occured re!"); 
    	   });
     }])
     
     routerApp.controller('registerController',['$scope','$http','$state','$window',function($scope,$http,$state,$window){
  	   $scope.register=function(){
	       console.log($scope.driverOrPassenger);
  		   if($scope.driverOrPassenger=="driver"){
	  		   $http({
		           method:'post',
		           url:'/grabCab/driver',
		           headers: {"Content-Type":"application/x-www-form-urlencoded"},
		           transformRequest: function(obj) {
		               var str = [];
		               for(var p in obj)
		               str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		               return str.join("&");
		           },
		           data:{
		               username:$scope.username,
		               email:$scope.email,
		               password:$scope.password,
		               type:$scope.driverOrPassenger
		           }
		
		       }).then(function(data){
		           console.log(data);
		    	   if(data.status==200){
		               console.log(data.token);
		    		   $state.transitionTo("app");
		            }
		           else{
		               
		
		           }
		       })
	       }
	       else{
	  		   $http({
		           method:'post',
		           url:'/grabCab/passenger',
		           headers: {"Content-Type":"application/x-www-form-urlencoded"},
		           transformRequest: function(obj) {
		               var str = [];
		               for(var p in obj)
		               str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		               return str.join("&");
		           },
		           data:{
		               username:$scope.username,
		               email:$scope.email,
		               password:$scope.password,
		               type:$scope.driverOrPassenger
		           }
		
		       }).then(function(data){
		           console.log(data);
		    	   if(data.status==200){
		               console.log(data.token);
		    		   $state.transitionTo("app");
		            }
		           else{
		               
		
		           }
		       })
	       }
	   }
  	   $scope.switchToLogin=function(){
  		   $state.transitionTo("app");
  	   }
     }])
