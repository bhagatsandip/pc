angular
		.module('controllers.resetpasswordCtrl', [])
		.controller(
				'resetpasswordCtrl',
				function($scope, $location,$routeParams) {

					$scope.token = $routeParams.token;
					
					$scope.submit = function() {
						  alert("Your password has been reset successfully!");
						  $location.path('/');
					  }	


				});