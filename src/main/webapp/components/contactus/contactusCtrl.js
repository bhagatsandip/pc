angular.module('controllers.contactusCtrl', []).
  controller('contactusCtrl', function($scope, $route) {

	  $scope.name = "";
	  $scope.email = "";
	  $scope.category = "";
	  $scope.message = "";
	  
	  $scope.submitFeedback = function(){
		console.log($scope.name);
		console.log($scope.email);
		console.log($scope.category);
		console.log($scope.message);
	  }
	  
	  $scope.submit = function() {
		  alert("Thank you for getting in touch!");
		  $route.reload();
	  }	  
	  
  });