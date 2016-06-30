var app = angular.module("passbookApp", []);

app
		.controller(
				"passbookCtrl",
				function($scope, $http, $location, $document, $window,
						$interval) {
					$scope.passbookUrlPath = passbookUrlPath;
					$scope.update = "";
					$scope.error = "Please fill all the fields.";

					$scope.modelSelected = false;

					$scope.updateGolfArray = new Array();
				
					$scope.user = {
						id : 0,
						name : "",
						age : "",
						gender : "",
						golf_course : "",
						hole_type : "",
						tee_type : "",
						handicap : "",
						par : "",
						score : ""
					};

					$scope.user.error = {};

					$scope.data_properties = {};
					
					$scope.checkForEmptyPassbook = function(){
						
						if($scope.passbookUrlPath == null){
							return true;
						}else{
							return false;
						}
					}

					$scope.selectGolfDetails = function(golf_course){
						$scope.user.golf_course = golf_course.golfCourseId;
						$scope.user.hole_type_selected = golf_course.holeId;
						$scope.user.hole_type = golf_course.holeId.holeTypeId;
						$scope.user.tee_type = golf_course.teeId.teeId;
						$scope.user.tee_type_selected = golf_course.teeId;

					}

					function init() {

						$http
								.get("/api/golfDetails")
								.then(
										function(response) {
											$scope.data_properties = response.data;

											console.log(response.data);

											$scope.user.age = $scope.data_properties.golf_user[0].age;
											$scope.user.name = $scope.data_properties.golf_user[0].name;
											$scope.user.gender = $scope.data_properties.golf_user[0].gender;

											setUpdateDetails();

										});


					}

					$scope.setSelectedGameId = function (game_selected){
						$scope.user.gameId = game_selected.id;
					}

					$scope.generateHoleNumbers = function (golf_game){
						var hole_array = [];

						if(golf_game == undefined){

							return hole_array;
						}

						var holeSize = golf_game.holeTypesId.holes;

						for(var number=0; number<holeSize; number++){
							hole_array.push(number+1);
						}

						return hole_array;
					}

					$scope.generateHoleDetails = function(selectedHole){

						for(index in $scope.user.gameId_selected.teeTypesId.teeDetails){
							if($scope.user.gameId_selected.teeTypesId.teeDetails[index].holeNumber == selectedHole){
								$scope.user.hole_selected_details = $scope.user.gameId_selected.teeTypesId.teeDetails[index];
								$scope.user.hole_selected_details.color = $scope.user.gameId_selected.teeTypesId.color;
								
							}
						}

					}


					function setUpdateDetails(){

						$scope.updateGolfArray.length = 0;

						if(passbookId != null){
							for(index in $scope.data_properties.golf){
								if($scope.data_properties.golf[index].id == passbookId){
									$scope.updateGolfArray.push($scope.data_properties.golf[index]);
									$scope.user.gameId_selected = $scope.data_properties.golf[index];

									console.log($scope.user.gameId_selected);
									break;
								}
							}
						}else{
							for(index in $scope.data_properties.golf){
								
									$scope.updateGolfArray.push($scope.data_properties.golf[index]);
								
							}
						}
					}

					function createHoleNumberList(totalHoleNumber) {
						var numbers_array = [];
	
						for (var count = 1; count <= totalHoleNumber; count++) {
							numbers_array.push(count);
						}

						return numbers_array;
					}

					

					$scope.updatePassbook = function() {

						if(updateFormValidation()){
							return null;
						}

						$scope.modelSelected = true;

						$scope.update = "";

						var pushUrl = "/pushNotifications?hole="
								+ $scope.user.hole + "&score="
								+ $scope.user.score + "&gameId=";
						
						if(passbookId == null){

							pushUrl += $scope.user.gameId;
						}else{

							pushUrl += passbookId;
						}
						

						$http.get(pushUrl).then(function(response) {
							$scope.update = "Pust Notification Successful";

							if($scope.user.hole + 1 <= $scope.user.gameId_selected.holeTypesId.holes){
								$scope.user.hole = $scope.user.hole + 1;
								$scope.generateHoleDetails($scope.user.hole);
							}
								
							
							$scope.user.score = undefined;
							$scope.modelSelected = false;
						});

					}

					$scope.clearPassbook = function() {
						$scope.user.name = "";
						$scope.user.age = "";
						$scope.user.gender = "";
					}
					
					$scope.redirectToUpdate = function() {

						if(golfGameValidation()){
							return null;
						}

						$scope.urlPath = "/createPass?name="
								+ $scope.user.name + "&age=" + $scope.user.age
								+ "&gender=" + $scope.user.gender
								+ "&golf_course=" + $scope.user.golf_course
								+ "&hole_type=" + $scope.user.hole_type
								+ "&tee_type=" + $scope.user.tee_type
								+ "&handicap=" + $scope.user.handicap;

						// return $scope.urlPath;

						window.location = $scope.urlPath;

					}

					

					$scope.createPassbook = function() {
			
						$scope.urlPath = $scope.passbookUrlPath;

						return $scope.urlPath;

					}

					 function updateFormValidation(){

			            var error_flag = false;
			            $scope.user.error = {};

			            if(angular.isNumber($scope.user.hole) != true){
			              $scope.user.error.hole = true;
			              error_flag = true;
			            }

			            if(angular.isNumber($scope.user.score) != true){
			              $scope.user.error.score = true;
			              error_flag = true;
			            }

			            return error_flag;
					}

					function golfGameValidation(){

						var error_flag = false;
						$scope.user.error = {};

						if(angular.isNumber($scope.user.handicap) != true){
							$scope.user.error.handicap = true;
							error_flag = true;
						}


						if($scope.user.golf_course == undefined || $scope.user.golf_course === ""){
							$scope.user.error.golf_course = true;
							error_flag = true;
						}

						return error_flag;
					}

					init();
				});
