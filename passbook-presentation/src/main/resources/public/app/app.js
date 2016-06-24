var app = angular.module("passbookApp", []);

app
		.controller(
				"passbookCtrl",
				function($scope, $http, $location, $document, $window,
						$interval) {
					$scope.passbookUrlPath = passbookUrlPath;
					$scope.update = "";
					$scope.error = "Please fill all the fields.";
				
					$scope.user = {
						id : 0,
						name : userName,
						age : "",
						gender : "",
						golf_course : "",
						hole_type : "",
						tee_type : "",
						handicap : "",
						par : "",
						score : ""
					};

					$scope.data_properties = {};
					
					$scope.checkForEmptyPassbook = function(){
						
						console.log($scope.passbookUrlPath);
						
						if($scope.passbookUrlPath == null){
							return true;
						}else{
							return false;
						}
					}

					function init() {

						$http
								.get("/golfDetails")
								.then(
										function(response) {
											$scope.data_properties = response.data;

											console.log(response.data);
											$scope.data_properties.hole_numbers_array = [];
											$scope.data_properties.hole_numbers_array = createHoleNumberList(parseInt($scope.data_properties.hole_type_list[0].holes));

											$scope.user.age = $scope.data_properties.golf_user[0].age;
											$scope.user.name = $scope.data_properties.golf_user[0].name;
											$scope.user.gender = $scope.data_properties.golf_user[0].gender;

										});
					}

					function createHoleNumberList(totalHoleNumber) {
						var numbers_array = [];
						console.log(totalHoleNumber);
						for (var count = 1; count <= totalHoleNumber; count++) {
							numbers_array.push(count);
						}

						return numbers_array;
					}

					$scope.updatePassbook = function() {

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
						});

					}

					$scope.clearPassbook = function() {
						$scope.user.name = "";
						$scope.user.age = "";
						$scope.user.gender = "";
					}
					
					$scope.redirectToUpdate = function() {

						$scope.urlPath = "/update?name="
								+ $scope.user.name + "&age=" + $scope.user.age
								+ "&gender=" + $scope.user.gender
								+ "&golf_course=" + $scope.user.golf_course
								+ "&hole_type=" + $scope.user.hole_type
								+ "&tee_type=" + $scope.user.tee_type
								+ "&handicap=" + $scope.user.handicap;

						return $scope.urlPath;

					}

					

					$scope.createPassbook = function() {
			
						$scope.urlPath = $scope.passbookUrlPath;

						return $scope.urlPath;

					}

					init();
				});
