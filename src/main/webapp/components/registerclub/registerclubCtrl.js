angular.module('controllers.registerclubCtrl', []).
  controller('registerclubCtrl', function($scope, AuthService) {

	  $scope.authenticate = function () {
          $scope.error = null;
          $scope.success = null;

          if ($scope.password === $scope.retypePassword) {
              $scope.signup();
          } else {
              $scope.error = "Password doesnot match";
          }
      };
      
      $scope.signup = function () {
          var parameters = {isClub: true};
          var object = {
        		  firstName : $scope.firstName,
        		  lastName : $scope.lastName,
        		  username : $scope.username,
        		  password : $scope.password
          }

          AuthService.signup(object.firstName, object.lastName, object.username, object.password)
              .then(
              function (response) {
            	  object.isClub = true;
                  AuthService.update(response.userId, object)
                  .then(function (res) {
                	  $scope.success = 'Signup Success !';                      
                  }, function (err) {
                	  $scope.error = err.data.error_description;
                  })

              }, function(error) {
            	  $scope.error = error.data.error_description;
			}
          );
      };

  });