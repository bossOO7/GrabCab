routerApp.controller('bonusCodesController',['$scope','$http','$state','$window',function($scope,$http,$state,$window){
	$scope.toggleSelection = function(item){
	    item.isRowSelected = !item.isRowSelected;
	}
	$scope.createbonus = function(){
		console.log("cretebonus called");   
		$http({
	           method:'post',
	           url:'/grabCab/bonus',
	           headers: {"Content-Type":"application/x-www-form-urlencoded"},
	           transformRequest: function(obj) {
	               var str = [];
	               for(var p in obj)
	               str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
	               return str.join("&");
	           },
	           data:{
	        	   bonuscode:$scope.bonuscode,
	        	   amount:$scope.amount
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
	
	$scope.getBonuses = function(){
		$http({
	           method:'get',
	           url:'/grabCab/bonus',
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
	           console.log("printing bonus"+data + " "+JSON.stringify(data));
	           var bonus = [];
	           for(var j = 0;j<data.data.length; j++){
	        	   bonus.push(data.data[j]['bonuscode']);
	           }
	           $scope.bonus = bonus;
	           console.log($scope.bonus);
	           for(var i=0;i<$scope.bonus.length;i++){
	        	   console.log($scope.bonus[i]['bonuscode']);
	           }
	       })
	}
	
	$scope.loadDrivers = function(){
		console.log("loadDriver called");   
		$http({
	           method:'get',
	           url:'/grabCab/allDrivers',
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
	    console.log("selected bcode"+$scope.bcode);
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
		$http({
	           method:'post',
	           url:'/grabCab/assignbonus',
	           headers: {"Content-Type":"application/x-www-form-urlencoded"},
	           transformRequest: function(obj) {
	               var str = [];
	               for(var p in obj)
	               str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
	               return str.join("&");
	           },
	           data:{	               
	        	   names : list,
	        	   bcode: $scope.bcode
	           }

	       }).then(function(data){
	           console.log(data);
	           $scope.data = data.data;
	       })
	    return false;
	};
}]);
