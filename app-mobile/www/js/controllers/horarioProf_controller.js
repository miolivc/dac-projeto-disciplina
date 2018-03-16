//Abrindo e respondendo enquetes
app.controller('horarioProfCtrl', function($scope, $state, $stateParams,$window, $http, $ionicPopup, $ionicLoading, $ionicLoading){
	//variaveis
	$scope.prof = [];
	
	$ionicLoading.show({
		template: 'Carregando...',
	}).then(function(){
	});

	//montando objeto
	var req = {
		method: 'GET',
		url:'https://localhost:8082/core/api/professor'
	}

	$http(req).then(function(resp){
		
		//pegando dados da enquete
		$scope.prof = resp.data;
		//parando de carregar
		$ionicLoading.hide();

	}, function(err){
		//parando de carregar
		$ionicLoading.hide()
		
			//alerta de erro
			var alertPopup = $ionicPopup.alert({
				title: 'Erro!',
				template: 'Não foi possível carregar os professores!'
			});
		});
})