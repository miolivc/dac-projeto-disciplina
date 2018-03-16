app.controller('horarioLabsCtrl', function($scope, $state, $stateParams,$window, $http, $ionicPopup, $ionicLoading, $ionicLoading){
	//variaveis
	$scope.lab = [];
	
	$ionicLoading.show({
		template: 'Carregando...',
	}).then(function(){
	});

	//montando objeto
	var req = {
		method: 'GET',
		url:'https://localhost:8082/core/api/laboratorio'
	}

	$http(req).then(function(resp){
		
		//pegando dados 
		$scope.lab = resp.data;
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