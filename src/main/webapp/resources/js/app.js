var routerApp = angular.module('routerApp', ['ui.router', 'ngStorage']);
routerApp.config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/');

    $stateProvider
//////////////States
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
        
        .state('app.passengerHome',{
        	url: 'passengerHome',
        	views: {
        		'header@':{
        			templateUrl: 'resources/html/header.html'
        		},
        		'content@':{
        			templateUrl: 'resources/html/passenger/passengerHome.html',
        			controller: 'rideController'
        		}
        	}
        }) 
        
        .state('app.requested',{
        	url: 'requested',
        	views: {
        		'header@':{
        			templateUrl: 'resources/html/header.html'
        		},
        		'content@':{
        			templateUrl: 'resources/html/passenger/passengerRequestinprogress.html',
        			controller: 'Ctrl1'
        		}
        	}
        }) 
       
         .state('app.passengerHistory',{
        	url: 'passengerHistory',
        	views: {
        		'header@':{
        			templateUrl: 'resources/html/header.html'
        		},
        		'content@':{
        			templateUrl: 'resources/html/passenger/passengerRequestinprogress.html',
        			controller: 'Ctrl1'
        		}
        	}
        }) 
        
           .state('app.driver',{
        	url: 'driver',
        	views: {
        		'header@':{
        			templateUrl: 'resources/html/header.html'
        		},
        		'content@':{
        			templateUrl: 'resources/html/driver/driver.html',
        			controller: 'getRidesController'
        		}
        	}
        })
        
        .state('app.rideStatus',{
        	url: 'rideStatus',
        	views: {
        		'header@':{
        			templateUrl: 'resources/html/header.html'
        		},
        		'content@':{
        			templateUrl: 'resources/html/driver/status.html',
        			controller: 'rideStatusController'
        		}
        	}
        })
        
        .state('app.rating',{
        	url: 'rating',
        	views: {
        		'header@':{
        			templateUrl: 'resources/html/header.html'
        		},
        		'content@':{
        			templateUrl: 'resources/html/driver/rating.html',
        			controller: 'passengerRatingController'
        		}
        	}
        })
               
        
});
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

///Controllers

//Login Functionality
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
		    		   $state.transitionTo("app.driver");
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
		    		   $state.transitionTo("app.passengerHome");
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
     
//Home Redirection     
     
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
     
 //Autohide and show driver and passenger form
     routerApp.controller('MyController', function ($scope) {
         //This will hide the DIV by default.
         $scope.IsHidden = true;
         $scope.ShowHide = function () {
             //If DIV is hidden it will be visible and vice versa.
             $scope.IsHidden = $scope.IsHidden ? false : true;
         }
     });
     
//Registration Functionality     
     routerApp.controller('registerController',['$scope','$http','$state','$window',function($scope,$http,$state,$window){
  	   $scope.register=function(){
	       console.log($scope.passengerEmail);
	       console.log("inside register function");
  		   if(!($scope.passengerEmail)){
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
		               phone:$scope.phone,
		               password:$scope.password,
		               licensenumber:$scope.licensenumber,
		               type:'driver'
		               
		       
		           }
		
		       }).then(function(data){
		           console.log(data);
		           console.log("inside success");
		           console.log($scope.email);
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
		               email:$scope.passengerEmail,
		               password:$scope.password,
		               phone:$scope.phone,
		               type:'passenger'
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

//Requesting ride from passenger home     
     routerApp.controller('rideController',['$scope','$http','$state','$window',function($scope,$http,$state,$window){
  	   $scope.request=function(){  
	  		   $http({
		           method:'post',
		           url:'/grabCab/ride',
		           headers: {"Content-Type":"application/x-www-form-urlencoded"},
		           transformRequest: function(obj) {
		               var str = [];
		               for(var p in obj)
		               str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		               return str.join("&");
		           },
		           data:{
		        	   pickupLocation:$scope.pickupLocation,
		        	   dropOffLocation:$scope.dropOffLocation,
		        	   carType:$scope.carType
		           }
		
		       }).then(function(data){
		     //  }).then(function(){
		           console.log(data);
		    	   if(data.status==200){
		               console.log(data.token);          
		    		   $state.transitionTo("requested");
		            }
		           else{
		               
		
		           }
		       })
	       
	   }
     }])
     
     
//polling and presenting driver details   on on progress page in passenger's aplication page  
     routerApp.controller('Ctrl1', function($scope,$timeout){
    	 $scope.Status=response.Status;
    	 //$scope.Status='A';
    	 var poll=function(){
    		 $timeout(function(){
    	         if($scope.Status== 'R'){
    	        	 console.log("in progress");
    			   	poll();
    	         }
    	         else if($scope.Status=='A')
    	        	 {
    	        	 routerApp.controller('requestController',['$scope','$http','$state','$window',function($scope,$http,$state,$window){
    	        	  	   $scope.requestControl=function(){  
    	        		  		   $http({
    	        			           method:'GET',
    	        			           url:'/grabCab/isRideAccepted',
    	        			           headers: {"Content-Type":"application/x-www-form-urlencoded"}
    	        			       }).then(function (response){ 
    	        			    		   $scope.RideID=response.RideID;
    	        			        	   $scope.driverName=response.driverName;
    	        			        	   $scope.driverPhoneNumber=response.driverPhoneNumber;
    	        			        	   $scope.carType=response.carType;
    	        			    	   
    	        			    	  /* $scope.RideID=0123;
	        			        	   $scope.driverName='xyz';
	        			        	   $scope.driverPhoneNumber=902345676859;
	        			        	   $scope.carType='SUV';*/
    	        			        	   
    	        			        	   case1.parent().parent().css({'display': 'none'});
    	        			       })       
    	        		   }
    	        	     }])
    	        	 }}, 1000);
    	    };     
    	   poll();
    	 
     })
         
 //Ride Cancellation  by passenger
     routerApp.controller('requestController',['$scope','$http','$state','$window',function($scope,$http,$state,$window){
  	   $scope.Cancel=function(){  
	  		   $http({
		           method:'PUT',
		           url:'/grabCab/ride',
		           headers: {"Content-Type":"application/x-www-form-urlencoded"},
		           transformRequest: function(obj) {
		               var str = [];
		               for(var p in obj)
		               str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		               return str.join("&");
		           },
		           data:{
		        	   RideID:$scope.RideID,
		        	   Reason:$scope.Reason,
		        	   Status:'C'
		           }
		
		       }).then(function(data){
		           console.log(data);
		    	   if(data.status==200){
		               console.log(data.token);          
		    		   $state.transitionTo("passengerHome");
		            }
		           else{
		               
		
		           }
		       })
	       
	   }
     }])
     
 //Driver Homepage    
    routerApp.controller('getRidesController',['$scope','$http','$state','$window',function($scope,$http,$state,$window){
    	   	$http({
             method:'get',
             url:'/grabCab/driver',
             headers: {"Content-Type":"application/x-www-form-urlencoded"},
             
             data:{
            	 
             }

         }).success(function(data){
             if(data.statusCode==200){
             $scope.rideID=data.rideID;
        	 $scope.pickupLocation=data.ride_ID;
             }
    	       
    	            else{


    	            }
    	        }).error(function(error){

    	        })
    	        
     }])
     
     
     routerApp.controller('getRidesController',['$scope','$http','$state','$window',function($scope,$http,$state,$window){
  	   $scope.accept=function(){  
	  		   $http({
		           method:'PUT',
		           url:'/grabCab/ride',
		           headers: {"Content-Type":"application/x-www-form-urlencoded"},
		           transformRequest: function(obj) {
		               var str = [];
		               for(var p in obj)
		               str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		               return str.join("&");
		           },
		           data:{
		        	   RideId:$scope.RideId,
		        	   rideStatus:'Accepted'
		           }
		
		       }).then(function(data){
		           console.log(data);
		    	   if(data.status==200){
		               console.log(data.token);          
		    		   $state.transitionTo("app.rideStatus");
		            }
		           else{
		               
		
		           }
		       })
	       
	   }
     }])
     
     
 //    Passenger History--- not done yet on frontend and need to decide what to print
       routerApp.controller('rideController',['$scope','$http','$state','$window',function($scope,$http,$state,$window){
  	   $scope.history=function(){  
	  		   $http({
		           method:'post',
		           url:'/grabCab/ride',
		           headers: {"Content-Type":"application/x-www-form-urlencoded"},
		           transformRequest: function(obj) {
		               var str = [];
		               for(var p in obj)
		               str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		               return str.join("&");
		           },
		           data:{
		        	   
		           }
		
		       }).then(function(data){
		     //  }).then(function(){
		           console.log(data);
		    	   if(data.status==200){
		               console.log(data.token);          
		    		   $state.transitionTo("passengerHistory");
		            }
		           else{
		               
		
		           }
		       })
	       
	   }
     }])
     
     
     
     //
     
      routerApp.controller('rideStatusController',['$scope','$http','$state','$window',function($scope,$http,$state,$window){
  	   $scope.endRide=function(){  
	  		   $http({
		           method:'PUT',
		           url:'/grabCab/ride',
		           headers: {"Content-Type":"application/x-www-form-urlencoded"},
		           transformRequest: function(obj) {
		               var str = [];
		               for(var p in obj)
		               str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		               return str.join("&");
		           },
		           data:{
		        	   RideId:$scope.RideId,
		        	   rideStatus:'Ended'
		           }
		
		       }).then(function(data){
		           console.log(data);
		    	   if(data.status==200){
		               console.log(data.token);          
		    		   $state.transitionTo("app.rating");
		            }
		           else{
		               
		
		           }
		       })
	       
	   }
     }])
     
     //
     
      routerApp.controller('passengerRatingController',['$scope','$http','$state','$window',function($scope,$http,$state,$window){
  	   $scope.rate=function(){  
	  		   $http({
		           method:'PUT',
		           url:'/grabCab/ride',
		           headers: {"Content-Type":"application/x-www-form-urlencoded"},
		           transformRequest: function(obj) {
		               var str = [];
		               for(var p in obj)
		               str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		               return str.join("&");
		           },
		           data:{
		        	   RideId:$scope.RideId,
		        	   UserRating:$scope.UserRating
		           }
		
		       }).then(function(data){
		           console.log(data);
		    	   if(data.status==200){
		               console.log(data.token);          
		    		   $state.transitionTo("app.driver");
		            }
		           else{
		               
		
		           }
		       })
	   }
     }])
