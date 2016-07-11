app.controller("UpdateFormCtrl",['$scope','$location', '$http', function($scope,$location, $http){
	$scope.registerForm = registerForm;
  console.log(registerForm);
  $scope.user = {};

  $scope.user.error = {};

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
      if(registerFormValidation()){
        return null;
      }


  		if(updateRedirect == "false"){
  			$http.post('/signup', $scope.registerForm, config).then(function(response){

//          if(response.data === 'false'){
//            $scope.user.error.emailexist = true;
//            return;
//          }
          
          if(response.data === 'false'){
              $scope.user.error.usernameexist = true;
              return;
            }

  			window.location = response.data;
  		}, function(response){
  			console.log("Error Whoops!!!");
  		});
  		}else{
  			$http.put('/signup', $scope.registerForm, config).then(function(response){

//          if(response.data === 'false'){
//            $scope.user.error.emailexist = true;
//            return;
//          }
  				
  				 if(response.data === 'false'){
  		              $scope.user.error.usernameexist = true;
  		              return;
  		            }

  			window.location = response.data;
  		}, function(response){
  			console.log("Error Whoops!!!");
  		});
  		}
  		
  }
  // Validates email address of course.
function validEmail(e) {
    var filter = /^\s*[\w\-\+_]+(\.[\w\-\+_]+)*\@[\w\-\+_]+\.[\w\-\+_]+(\.[\w\-\+_]+)*\s*$/;
    return String(e).search (filter) != -1;
}


          function registerFormValidation(){

            var error_flag = false;
            $scope.user.error = {};

            if($scope.registerForm.firstName === "" || $scope.registerForm.firstName === undefined || $scope.registerForm.firstName === null){
              $scope.user.error.firstName = true;
              error_flag = true;
            }

            if($scope.registerForm.lastName === "" || $scope.registerForm.lastName === undefined || $scope.registerForm.lastName == null){
              $scope.user.error.lastName = true;
              error_flag = true;
            }

            if($scope.registerForm.email === "" || $scope.registerForm.email === undefined || $scope.registerForm.email === null || validEmail($scope.registerForm.email) === false){
              $scope.user.error.email = true;
              error_flag = true;
            }

            if(angular.isNumber($scope.registerForm.age) != true){
              $scope.user.error.age = true;
              error_flag = true;
            }

            if($scope.registerForm.gender === "" || $scope.registerForm.gender === undefined || $scope.registerForm.gender === null){
              $scope.user.error.gender = true;
              error_flag = true;
            }

            if($scope.registerForm.password === "" || $scope.registerForm.password === undefined || $scope.registerForm.password === null){
              $scope.user.error.password = true;
              error_flag = true;
            }

            return error_flag;
          }

 


}]);