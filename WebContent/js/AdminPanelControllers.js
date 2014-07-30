var panelControllers = angular.module('AdminPanelControllers',['ngTable','ui.bootstrap']);

panelControllers.controller('DashboardCtrl',function($scope){
    
    
});

panelControllers.controller('InvManController', function($scope,$http,ngTableParams,$log,$modal) {
	  $scope.open = function (size) {
		    var modalInstance = $modal.open({
		      templateUrl: 'myModalContent.html',
		      controller: ModalInstanceCtrl,
		      size: size,
		      resolve: {
		      }
		    });

		    modalInstance.result.then(function (submitItem) {
		      var item = {};
		      item.name = submitItem.name;
		      item.cost = submitItem.cost;
		      item.price = submitItem.price;
		      
		      $http({
		      
		    	  url:"/SimpleREST/api/payment/addItem",
		    	  method: "POST",
		    	  data: item,
		    	  headers: {'Content-Type': "application/json"}
		    		 
		    		  
		      }).success(function(data){
		    	  $log.info("Data Added");
		      });
		    }, function () {
		      $log.info('Modal dismissed at: ' + new Date());
		    });
		  };
		  
	$http({ method: 'GET',
        url: 'http://localhost:8080/SimpleREST/api/payment/offering'
     }).success(function(data){
    	 	$scope.data = angular.fromJson(data);

    	    $scope.tableParams = new ngTableParams({
    	        page: 1,            // show first page
    	        count: 15           // count per page
    	    }, {
    	        total: $scope.data.length, // length of data
    	        getData: function($defer, params) {
    	            $defer.resolve($scope.data.slice((params.page() - 1) * params.count(), params.page() * params.count()));
    	        }
    	    });
    }).error(function(){
        alert("error");
    });
});

var ModalInstanceCtrl = function ($scope, $modalInstance) {

	  $scope.itemForm = {}
	  $scope.ok = function () {
		var item = {};
		item.name =$scope.itemForm.name;
		item.price =$scope.itemForm.price;
		item.cost = $scope.itemForm.cost;
		
	    $modalInstance.close(item);
	  };

	  $scope.cancel = function () {
	    $modalInstance.dismiss('cancel');
	  };
	};