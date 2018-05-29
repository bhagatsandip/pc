angular.module('controllers.corporatePartnerCtrl', []).
  controller('corporatePartnerCtrl', function($scope, $route) {

	  $scope.name = "";
	  $scope.email = "";
	  $scope.city = "";
	  $scope.category = "";
	  $scope.contact = "";
	  $scope.number = "";
	  //$scope.noOfEmployees = ["1 - 100", "100 - 1000", "1000 - 5,000","5,000 +"];
	  
	  $scope.submitFeedback = function(){ 
		console.log($scope.name);
		console.log($scope.email);
		console.log($scope.category);
		console.log($scope.message);
		console.log($scope.count);
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