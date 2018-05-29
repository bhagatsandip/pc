angular.module('controllers.partneryourclubCtrl', []).
  controller('partneryourclubCtrl', function($scope, $route) {

	  $scope.name = "";
	  $scope.email = "";
	  $scope.clubType = "";
	  $scope.pincode = "";
	  $scope.telephone = "";
	  
	  $scope.submitFeedback = function(){
		console.log($scope.name);
		console.log($scope.email);
		console.log($scope.category);
		console.log($scope.message);
	  }
	  
	  $scope.submit = function() {

		  if (window.confirm('Thank you for enrollment request'))
		  {
			  $route.reload();
		  }
		  else
		  {
			  $route.reload();
		  }
	  }	  
	  
  });