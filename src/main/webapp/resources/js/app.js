var routerApp = angular.module('routerApp', ['ui.router', 'ngStorage']);
routerApp.config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/');

    $stateProvider
//States start here-----------------------------------------------------------------
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
        
//-------------------------------------------------------------------------------       
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
  //--------------------------------------------------------------------------      
        
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
 //--------------------------------------------------------------------------------       
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
//-----------------------------------------------------------------------------------        
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
//----------------------------------------------------------------------------------------      
         .state('app.passengerHistory',{
        	url: 'passengerHistory',
        	views: {
        		'header@':{
        			templateUrl: 'resources/html/header.html'
        		},
        		'content@':{
        			templateUrl: 'resources/html/passenger/history.html',
        			controller: 'historyController'
        		}
        	}
        }) 
 //--------------------------------------------------------------------------------------------       
        .state('app.historyDriver',{
        	url: 'historyDriver',
        	views: {
        		'header@':{
        			templateUrl: 'resources/html/header.html'
        		},
        		'content@':{
        			templateUrl: 'resources/html/driver/historyDriver.html',
        			controller: 'historyDriverController'
        		}
        	}
        }) 
 //--------------------------------------------------------------------------------------------
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
//----------------------------------------------------------------------------------------------        
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
//---------------------------------------------------------------------------------------------------        
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
//-----------------------------------------------------------------------------------------------------        
        .state('app.passengerRating',{
        	url: 'rating1',
        	views: {
        		'header@':{
        			templateUrl: 'resources/html/header.html'
        		},
        		'content@':{
        			templateUrl: 'resources/html/passenger/passengerRating.html',
        			controller: 'dRatingController'
        		}
        	}
        })
 
 //----------------------------------------------------------------------------------------------------
        .state('app.adminHome',{
        	url: 'admin',
        	views: {
        		'header@':{
        			templateUrl: 'resources/html/header.html'
        		},
        		'content@':{
        			templateUrl: 'resources/html/admin/admin.html',
        			controller: 'adminHomeController'
        		}
        	}
        })
        
   //----------------------------------------------------------------------------------------------------
        
        .state('app.pendingRequests',{
        	url: 'admin/pendingRequests',
        	views: {
        		'header@':{
        			templateUrl: 'resources/html/header.html'
        		},
        		'content@':{
        			templateUrl: 'resources/html/admin/pendingRequests.html',
        			controller: 'pendingRequestsController'
        		}
        	}
        })
        
    //----------------------------------------------------------------------------------------------------
        
        .state('app.promoCodes',{
        	url: 'admin/promoCodes',
        	views: {
        		'header@':{
        			templateUrl: 'resources/html/header.html'
        		},
        		'content@':{
        			templateUrl: 'resources/html/admin/promoCodes.html',
        			controller: 'promoCodesController'
        		}
        	}
        })
        
       //----------------------------------------------------------------------------------------------------
        
         .state('app.bonusCodes',{
        	url: 'admin/bonusCodes',
        	views: {
        		'header@':{
        			templateUrl: 'resources/html/header.html'
        		},
        		'content@':{
        			templateUrl: 'resources/html/admin/bonusCodes.html',
        			controller: 'bonusCodesController'
        		}
        	}
        })
        
        
});

//States end here----------------------------------------------------------------------------------------------
///Controllers Start here--------------------------------------------------------------------------------------

//Raghu:Login Functionality
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
		   else if($scope.driverOrPassenger=="passenger"){
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
		               type:"admin"
		           }
		
		       }).then(function(data){
		           console.log(data);
		    	   if(data.status==200){
		               console.log(data.token);
		    		   $state.transitionTo("app.adminHome");
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
     
//Raghu:Home Redirection----------------------------------------------------------------------------------------------    
     
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
     
 //Swati:Autohide and show driver and passenger form------------------------------------------------------------------
     routerApp.controller('MyController', function ($scope) {
         //This will hide the DIV by default.
         $scope.IsHidden = true;
         $scope.ShowHide = function () {
             //If DIV is hidden it will be visible and vice versa.
             $scope.IsHidden = $scope.IsHidden ? false : true;
         }
     });
     
//Swati:Registration Functionality------------------------------------------------------------------------------------
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
		               licenseNumber:$scope.licenseNumber,
		               cardnumber:$scope.cardnumber,
		               cardname:$scope.cardname,
		               expirydate:$scope.expirydate,
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
		               cardnumber:$scope.cardnumber,
		               cardname:$scope.cardname,
		               expirydate:$scope.expirydate,
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

//Swati:Requesting ride from passenger home -------------------------------------------------------------------------   
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
	    		   $state.transitionTo("app.requested");
	    		 //  $scope.poll();
		           }
		           else{
		               
		
		           }
		       })
	       
	   }
  	   
  /*	 $scope.poll = function(){
		 console.log('Inside poll');
		 $http({
	           method:'GET',
	           url:'/grabCab/isRideAccepted',
	           headers: {"Content-Type":"application/x-www-form-urlencoded"}
	       }).then(function (response){ 
	    	   console.log(response);
	    	   
	    	   $scope.value = response.data;
	    	   console.log("Scope value in response***",$scope.value);
	    	   console.log("Ride Status in response***",$scope.value.rideStatus );
	    	   while($scope.value.rideStatus == null )
	    		{
	    		  console.log("Ride Status in request ride***",$scope.value.rideStatus );
	    		  $scope.poll(); 
	    		}
	    	   
	    	       if($scope.value.rideStatus =='A')
	    	       { 
	    	    	 console.log("Ride Accepted*****",response );
	    	    	 while($scope.value.rideStatus != 'C')
		    		 {
	    	    		 $scope.poll();
		    		 }
	    	    	 
	    	       }

	    	    	 if($scope.value.rideStatus=='C'){
			    	       console.log('Complete');
			    	 }
	    	       
	        })
	 } */
  	  //Swati:Redirection to history -----------------------------------------------------------------------
  	 $scope.history=function(){  	         
	    		   $state.transitionTo("app.passengerHistory");

 }
  	   
     }])
     
   
//Swati:polling and presenting driver details   on on progress page in passenger's application page ----------------------
     routerApp.controller('Ctrl1',['$scope','$http','$state','$window','$interval',function($scope,$http,$state,$window,$interval){
    /*	 var poll = function(){
    		 
    		 $interval(function(){
    			 $http({
			           method:'GET',
			           url:'/grabCab/isRideAccepted',
			           headers: {"Content-Type":"application/x-www-form-urlencoded"}
			       }).then(function (response){ 
			    	   console.log(response);
			    	   $scope.value = response.data;
			    	   console.log("Scope value in response***",$scope.value);
			    	   console.log("Ride Status in response***",$scope.value.rideStatus );
			    	   if($scope.value.rideStatus == 'R'){
			    	        	// console.log("in progress");
			    			   	poll();
			        	   }
			    	       else if($scope.value.rideStatus =='A')
			    	       { 
			    	    	 console.log("Ride Accepted*****",response );
				    		   $scope.RideID=response.rideId;
				        	   $scope.driverName=response.driverName;
				        	   $scope.driverPhoneNumber=response.driverPhoneNumber;
				        	   $scope.carType=response.carType; 	          
				        	  // return;
			    	    	// $interval.cancel(poll);
			    	    	 //$state.transitionTo("app.passengerRating");
			    	       }
			    	   
			    	       else {
			    	    	 //if($scope.value.rideStatus=='C')
			    	    	   $state.transitionTo("app.passengerRating");
			    	       }
			        });
			        	   
			        	   //case1.parent().parent().css({'display': 'none'});
			       },10);
    		

    	    };     
    	  poll();
    	 */ 
    	  $scope.CheckRideStatus=function(){  
	  			 $http({
			           method:'GET',
			           url:'/grabCab/isRideAccepted',
			           headers: {"Content-Type":"application/x-www-form-urlencoded"}
			       }).then(function (response){ 
			    	   console.log(response);
			    	   $scope.value = response.data;
			    	   console.log("Scope value in response***",$scope.value);
			    	   console.log("Ride Status in response***",$scope.value.rideStatus );
			    	   if($scope.value.rideStatus == 'R' || $scope.value.rideStatus == 'A' ){
			    		   console.log("Ride Status inside ------------------>",$scope.value.rideStatus);
			    	   }
			    	   else if ($scope.value.rideStatus=='C'){
			    		   console.log("State transition for passengerRating ------------------>",$scope.value.rideStatus);
			    	    	   $state.transitionTo("app.passengerRating");
			    	       }
			        });
	       
	   }
  
    		 
    	 
    		 
    	   
    	 //Swati:Ride Cancellation  by passenger------------------------------------------------------------------------------
    	   $scope.Cancel=function(){  
    		   console.log("inside Cancel Ride ------------->")
      		 console.log("Cancellation Reason ", $scope.reason);
	  		   $http({
		           method:'POST',
		           url:'/grabCab/cancel',
		           headers: {"Content-Type":"application/x-www-form-urlencoded"},
		           transformRequest: function(obj) {
		               var str = [];
		               for(var p in obj)
		               str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		               return str.join("&");
		           },
		           data:{
		        	   Reason:$scope.reason,
		        	   rideStatus:'X'
		           }
		
		       }).then(function(data){
		           console.log(data);
		           $state.transitionTo("app.passengerHome");
		    	   if(data.status==200){
		               console.log(data.token);  
		               
		    		  // $state.transitionTo("app.passengerHome");
		            }
		           else{
		               
		
		           }
		       })
	       
	   }
     }])
         
 
 //Kanika:Driver Homepage-------------------------------------------------------------------------------------------     
     //Kanika: getting all rides in a radio and accepting one----------------------------------------------------------
     routerApp.controller('getRidesController',['$scope','$http','$state','$window',function($scope,$http,$state,$window){
    	 //get request for requested rides
    	 
    	 $http({
             method:'get',
             url:'/grabCab/driver',
             headers: {"Content-Type":"application/x-www-form-urlencoded"},
             
             data:{
            	 
             }

         }).success(function(data){
        	 console.log(data);
        	 console.log(data[0]);
        	 console.log(data[0].reason);
        	 $scope.data=data;
             if(data.statusCode==200){


             $scope.data=data;
        	
             }
    	       
    	            else{


    	            }
    	        }).error(function(error){
    	        	console.log(error);
    	        })
    	 
    	 //post request for accepting selected ride
  	   $scope.accept=function(){  
    		 //console.log("test"+x);
    		 console.log("Full scope id as " , $scope)
    		 console.log("Sending rider id as " + $scope.rideid);
	  		   $http({
		           method:'POST',
		           url:'/grabCab/ride1',
		           headers: {"Content-Type":"application/x-www-form-urlencoded"},
		           transformRequest: function(obj) {
		               var str = [];
		               for(var p in obj)
		               str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		               return str.join("&");
		           },
		           data:{
		        	   rideId:$scope.rideid,
		        	   rideStatus:'A'
		        		  
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
 //Redirecting to driver history page	 
    	 $scope.historyDriver=function(){  	         
  		   $state.transitionTo("app.historyDriver");

  }
     }])
     
     
 //Kanika:Driver Ride Status Information---------------------------------------------------------------------------------
     
      routerApp.controller('rideStatusController',['$scope','$http','$state','$window',function($scope,$http,$state,$window){
  	   $scope.endRide=function(){  
  		 console.log("in end ride Full scope id as " , $scope)
		 console.log("Sending rider id in endride as " + $scope.rideid);
	  		   $http({
		           method:'POST',
		           url:'/grabCab/ride2',
		           headers: {"Content-Type":"application/x-www-form-urlencoded"},
		           transformRequest: function(obj) {
		               var str = [];
		               for(var p in obj)
		               str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		               return str.join("&");
		           },
		           data:{
		        	   rideId:$scope.value.id,
		        	   rideStatus:'C'
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
  	   
  	 {
    	 //get request for ride id and status as in progress
    	 
    	 $http({
             method:'GET',
             url:'/grabCab/status',
             headers: {"Content-Type":"application/x-www-form-urlencoded"},
             
             data:{
            	 
             }

         }).success(function(response){
        	 console.log("*****: ",response);
        	console.log(response.id);
        	 $scope.value = response;
        	 console.log("After : ",$scope.value.id);
        	// $scope.rideStatus=response.rideStatus;
        /*	
    	 }).success(function(){
        	 console.log(data);
        	// console.log(response.rideid);
        	 $scope.data=data;
        	console.log(data[0]);
        	
        	 $scope.data=data;
             if(data.statusCode==200){


             $scope.data=data;
        	
             }
    	       
    	            else{


    	            }*/
    	        }).error(function(error){
    	        	console.log(error);
    	        })
  	 }
     }])
     
     //
  //Kanika:Rating a passenger  ------------------------------------------------------------------------------------------------
      routerApp.controller('passengerRatingController',['$scope','$http','$state','$window',function($scope,$http,$state,$window){
     
  	   $scope.rate=function(){  
  		   console.log("Rating passenger....",$scope.UserRating);
	  		   $http({
		           method:'POST',
		           url:'/grabCab/ratepassenger',
		           headers: {"Content-Type":"application/x-www-form-urlencoded"},
		           transformRequest: function(obj) {
		               var str = [];
		               for(var p in obj)
		               str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		               return str.join("&");
		           },
		           data:{
		        	   RideId:$scope.rideid,
		        	   UserRating:$scope.UserRating
		           }
		
		       }).then(function(data){
		           console.log("Rating Response data: ",data);
		           $state.transitionTo("app.driver");
		    	   if(data.status==200){
		               console.log(data.token);          
		    		   
		            }
		           else{
		               
		
		           }
		       })
	   }
     }])
     //Swati:Rating Driver---------------------------------------------------------------------------------------------
      routerApp.controller('dRatingController',['$scope','$http','$state','$window',function($scope,$http,$state,$window){
  	   $scope.rateDriver=function(){  
  		 console.log("Rating Driver....",$scope.rating);
	  		   $http({  
		           method:'POST',
		           url:'/grabCab/ride/ratedriver',
		           headers: {"Content-Type":"application/x-www-form-urlencoded"},
		           transformRequest: function(obj) {
		               var str = [];
		               for(var p in obj)
		               str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		               return str.join("&");
		           },
		           data:{//confirm raghu if we can do addition like this
		        	   
		        	   driverrating:$scope.rating
		        	   
		           }
		
		       }).then(function(data){
		           console.log(data);
		           console.log("Driver successfully rated");
		           $state.transitionTo("app.passengerHome");
		    	   if(data.status==200){
		               console.log(data.token);  
		               console.log("Driver successfully rated");
		    		   
		            }
		           else{
		               
		
		           }
		       })
	   }
     }])
     
 //Swati: Controller for passenger history get and going back to passenger homepage   : updated history scope variable 
     
  routerApp.controller('historyController',['$scope','$http','$state','$window',function($scope,$http,$state,$window){

	  	$http({
	           method:'GET',
	           url:'/grabCab/passenger/history',
	           headers: {"Content-Type":"application/x-www-form-urlencoded"},
	      
	           data:{
	        	 
	        	   
	           }
	
	       }).success(function(data){
	    	   console.log(data);
	    	   console.log(data[0]);
	        	 console.log(data[0].rideid);
	        	$scope.data=data;
	        	/* $scope.rideid=data.rideid;
	               $scope.username=data.username; 
	               $scope.pickuplocation=data.pickuplocation;
	               $scope.starttime=data.starttime;
	               $scope.dropofflocation=data.dropofflocation;
	               $scope.endtime=data.endtime;
	               $scope.cost=data.cost;
	               $scope.cartype=data.cartype;
	               $scope.cardnumber=data.cardnumber;
	    		   
	               $scope.rideid=data.rideid;
	               $scope.username=response.username; 
	               $scope.pickuplocation=response.pickuplocation;
	               $scope.starttime=response.starttime;
	               $scope.dropofflocation=response.dropofflocation;
	               $scope.endtime=response.endtime;
	               $scope.cost=response.cost;
	               $scope.cartype=response.cartype;
	               $scope.cardnumber=response.cardnumber;   */
	               if(data.status==200){     
	            }
	           else{
	               
	
	           }
	       }).error(function(error){
	    	   
	       }) 
	     
	 	  //-----------Go Back
	       $scope.GoBack=function(){  
	 		   $state.transitionTo("app.passengerHome");
	  	}
     }])
     
      //Kanika: Controller for driver history get and going back to driver homepage    
     
  routerApp.controller('historyDriverController',['$scope','$http','$state','$window',function($scope,$http,$state,$window){
	  $http({
          method:'GET',
          url:'/grabCab/driver/historyDriver',
          headers: {"Content-Type":"application/x-www-form-urlencoded"},
     
          data:{
       	 
       	   
          }

      }).success(function(data){
    	  
    	  console.log(data);
   	   console.log(data[0]);
       	 
   	   
              $scope.data=data;
           /*   $scope.username=response.username; 
              $scope.pickuplocation=response.pickuplocation;
              $scope.starttime=response.starttime;
              $scope.dropofflocation=response.dropofflocation;
              $scope.endtime=response.endtime;
              $scope.cost=response.cost;
              $scope.UserRating=response.UserRating;*/
              if(data.status==200){   
           }
          else{
              

          }
      }).error(function(error){
   	   
      }) 
    
	  //-----------Go Back
      $scope.GoBack_1=function(){  
		   $state.transitionTo("app.driver");
 	}
}])

        
     //Controllers end here---------------------------------------------------------------------------------------------------
