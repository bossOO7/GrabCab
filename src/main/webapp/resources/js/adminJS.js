routerApp.controller('adminHomeController',['$scope','$http','$state','$window',function($scope,$http,$state,$window){
	console.log("reached admin login");
	$scope.requests = function(){
		console.log("reaching fun");
		$state.transitionTo("app.pendingRequests");
	}
	$scope.promos = function(){
		console.log("reaching fun");
		$state.transitionTo("app.promoCodes");
	}
}]);

routerApp.controller('promoCodesController',['$scope','$http','$state','$window',function($scope,$http,$state,$window){
	$scope.toggleSelection = function(item){
	    item.isRowSelected = !item.isRowSelected;
	}
	$scope.createPromo = function(){
		console.log("cretepromo called");   
		$http({
	           method:'post',
	           url:'/grabCab/promo',
	           headers: {"Content-Type":"application/x-www-form-urlencoded"},
	           transformRequest: function(obj) {
	               var str = [];
	               for(var p in obj)
	               str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
	               return str.join("&");
	           },
	           data:{
	        	   promocode:$scope.promocode,
	        	   discountprice:$scope.discountprice
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
	
	$scope.loadPassengers = function(){
		console.log("loadPassenger called");   
		$http({
	           method:'get',
	           url:'/grabCab/passenger',
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
	           console.log(data);
	           $scope.data = data.data;
	       })
	}
	$scope.isAnythingSelected = function () {
	    var list = [];
		for(var i = 0; i < $scope.data.length; i++){
	        if($scope.data[i].isRowSelected === true){
	            console.log("selected row"+$scope.data[i]);
	            var jsonObj = $scope.data[i];
	            list.push(jsonObj['email']);
	            
	        }
		    else{
		      	//console.log("selected row"+$scope.data[i]);
		    }
	    }
		console.log(list);

	    return false;
	};
}]);

//-------------------------------------------------------------------------------------------------------------------

routerApp.controller('pendingRequestsController',['$scope','$http','$state','$window',function($scope,$http,$state,$window){
	   $http({
           method:'get',
           url:'/grabCab/driver/pending',
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
           console.log(data);
           $scope.data = data;
       })
       
       $scope.approve = function(e){
		   console.log(e);
  		   $http({
	           method:'post',
	           url:'/grabCab/driver/approve',
	           headers: {"Content-Type":"application/x-www-form-urlencoded"},
	           transformRequest: function(obj) {
	               var str = [];
	               for(var p in obj)
	               str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
	               return str.join("&");
	           },
	           data:{
	        	   status:"approved",
	        	   email: e
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
	
}]);


//-------------------------------------------------------------------------------------------------------------------

