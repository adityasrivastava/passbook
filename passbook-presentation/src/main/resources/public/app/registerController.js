app.controller("RegisterFormCtrl",['$scope', '$http', function($scope, $http){
	$scope.registerForm = registerForm;
	// $scope.registerForm = {};

	function setGendersFromResponse(){

		var gender = registerForm.gender.toLowerCase();

		for(gender_index in $scope.genders){
			var temp_gender_ = $scope.genders[gender_index].toLowerCase();
			if(temp_gender_ === gender){
				$scope.registerForm.gender = $scope.genders[gender_index];
				break;
			}
		}

	}



	$scope.genders = ["Male", "Female"];
	                 
  $scope.registerFormStatus = function(){

  	if(updateRedirect == "false"){
  		return false;
  	}else{
  		  setGendersFromResponse();
  		return true;
  	}

  }

  $scope.submitForm = function(){
  		if(updateRedirect == "false"){
  			$http.post('/signup', $scope.registerForm, config).then(function(response){
  			window.location = response.data;
  		}, function(response){
  			console.log("Error Whoops!!!");
  		});
  		}else{
  			$http.put('/signup', $scope.registerForm, config).then(function(response){
  			window.location = response.data;
  		}, function(response){
  			console.log("Error Whoops!!!");
  		});
  		}
  		
  }

}]);