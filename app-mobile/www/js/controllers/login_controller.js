//Login de usuario
app.controller('loginCtrl'  , function($scope, $rootScope, $http, $state, $ionicPopup, $ionicLoading){

	//dados dos alunos q recebe ao fazer o login
	$scope.aluno = [];
	$scope.loginUsuario = function(login){

		var req = {
			method: 'POST',
			url:'https://localhost:8082/core/api/aluno/autherticate',
			data: 'username=' + login.matricula + '&password='+ login.senha,
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded'
			}
		}

		$http(req).then(function(resp){

		//salvando no localStorage
		$scope.aluno = resp.data;
		var dados = angular.toJson($scope.aluno);
		localStorage.setItem("aluno", dados);

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
})