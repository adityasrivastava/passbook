app.controller("RegisterFormCtrl",['$scope', function($scope){
	$scope.registerForm = registerForm;


	$scope.genders = [
	                  {
	                	  value: "Male"
	                  },
	                  {
	                	  value: "Female"
	                  }];

}])