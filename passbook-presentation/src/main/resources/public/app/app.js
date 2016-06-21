var app = angular.module("passbookApp", []);

app
		.controller(
				"passbookCtrl",
				function($scope, $http, $location, $document, $window,
						$interval) {

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

					function init() {

						$http
								.get("/golfDetails")
								.then(
										function(response) {
											$scope.data_properties = response.data;

											$scope.data_properties.hole_numbers_array = [];
											$scope.data_properties.hole_numbers_array = createHoleNumberList(parseInt($scope.data_properties.hole_type_list[0].holes));

											console.log(response.data);
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
								+ $scope.user.score + "&gameId="
								+ $scope.user.gameId;

						$http.get(pushUrl).then(function(response) {
							$scope.update = "Pust Notification Successful";
						});

					}

					$scope.clearPassbook = function() {
						$scope.user.name = "";
						$scope.user.age = "";
						$scope.user.gender = "";
					}

					$scope.createPassbook = function() {

						$scope.urlPath = "/createPassbook?name="
								+ $scope.user.name + "&age=" + $scope.user.age
								+ "&gender=" + $scope.user.gender
								+ "&golf_course=" + $scope.user.golf_course
								+ "&hole_type=" + $scope.user.hole_type
								+ "&tee_type=" + $scope.user.tee_type
								+ "&handicap=" + $scope.user.handicap;

						return $scope.urlPath;

					}

					init();
				});
