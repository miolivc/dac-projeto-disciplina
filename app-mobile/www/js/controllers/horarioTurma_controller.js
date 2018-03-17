app.controller('horarioTurmasCtrl', function($scope, $state, $stateParams,$window, $http, $ionicPopup, $ionicLoading, $ionicLoading){
	//variaveis
	$scope.turma = [];
	
	$ionicLoading.show({
		template: 'Carregando...',
	}).then(function(){
	});

	//montando objeto
	localStorage.getItem("Authorization");
	var req = {
		method: 'GET',
		url:'https://localhost:8082/core/api/turma'
	};

	$http(req).then(function(resp){
		
		//pegando dados 
		$scope.turma = resp.data;
		//parando de carregar
		$ionicLoading.hide();

	}, function(err){
		//parando de carregar
		$ionicLoading.hide()
		
			//alerta de erro
			var alertPopup = $ionicPopup.alert({
				title: 'Erro!',
				template: 'Não foi possível carregar as turmas!'
			});
		});
})