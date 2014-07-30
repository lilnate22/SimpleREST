var ControlApp = angular.module("AdminPanel",['ui.bootstrap','ngRoute','AdminPanelControllers']);

ControlApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/Dashboard', {
        templateUrl: 'partials/dashboard.html',
        controller: 'DashboardCtrl'
      }).
      when('/InventoryManagement', {
        templateUrl: 'partials/InvMan.html',
        controller: 'InvManController'
      }).
      otherwise({
        redirectTo: '/Dashboard'
      });
  }]);

ControlApp.controller("TopUserPage",function($scope,$http,$rootScope){
	
    $scope.loggedIn = true;
	$http({
        method: 'GET',
        url: 'http://localhost:8080/SimpleREST/api/payment/nate'
     }).success(function(data){
        $scope.user = {name:"Nate",age:22};
        $rootScope.$broadcast("Update",$scope.user);
    }).error(function(){
        alert("error");
    });

});

ControlApp.controller("SideMenu",function($scope,$rootScope){
    $rootScope.$on("Update",function(event,message){
        $scope.user = message.name;
    });
});