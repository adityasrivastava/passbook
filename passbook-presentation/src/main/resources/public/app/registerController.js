app.controller("RegisterFormCtrl",['$scope', '$http', function($scope, $http){
	$scope.registerForm = registerForm;
	// $scope.registerForm = {};
	$scope.genders = [
	                  {
	                  		id: 0,
	                	  value: "Male"
	                  },
	                  {		id: 1,
	                	  value: "Female"
	                  }];

  $scope.submitForm = function(){
  		console.log($scope.registerForm );
  		console.log(config);
  		$http.post('/signup', $scope.registerForm, config).then(function(response){
  			window.location = response.data;
  		}, function(response){
  			console.log("Error Whoops!!!");
  		});
  }

}]);