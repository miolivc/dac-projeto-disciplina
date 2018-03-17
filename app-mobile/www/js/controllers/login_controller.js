//Login de usuario
app.controller('loginCtrl'  , function($scope, $rootScope, $http, $state, $ionicPopup, $ionicLoading){

	$scope.loginUsuario = function(login){

		var req = {
			method: 'POST',
			url:'http://localhost:8082/core/api/aluno/authenticate',
			data: 'email=' + login.matricula + '&password='+ login.senha,
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/x-www-form-urlencoded'
			}
		}

		$http(req)
		.then(function(resp){

			console.log("Recebido: " + JSON.stringify(resp.data));

			localStorage.setItem("Authorization", JSON.stringify(resp.data));

			//indo pra pagina de enquetes
			$state.go('app.horarios');

		}, function(err){
			console.log(err.data);
				//alerta de erro
				var alertPopup = $ionicPopup.alert({
					title: 'Erro!',
					template: 'Não foi possível efetuar o login!'
				});
			});
		}
	}
);