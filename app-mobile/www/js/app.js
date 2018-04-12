angular.module('starter', ['ionic', 'starter.controllers'])

.run(function($ionicPlatform) {
  $ionicPlatform.ready(function() {
    // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
    // for form inputs)
    if (window.cordova && window.cordova.plugins.Keyboard) {
      cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
      cordova.plugins.Keyboard.disableScroll(true);

    }
    if (window.StatusBar) {
      // org.apache.cordova.statusbar required
      StatusBar.styleDefault();
    }
  });
})

.config(function($stateProvider, $urlRouterProvider) {
  $stateProvider
  //menu
  .state('app', {
    url: '/app',
    abstract: true,
    templateUrl: 'templates/menu.html',
    //controller: 'AppCtrl'
  })

  .state('app.duvida', {
    url: '/duvida',
    views: {
      'menuContent': {
        templateUrl: 'templates/duvida.html'
      }
    }
  })

  //enviar duvidas
  .state('app.enviarDuvida', {
    url: '/enviarDuvida',
    views: {
      'menuContent': {
        templateUrl: 'templates/enviarDuvida.html'
      }
    }
  })

//todas as duvidas
.state('app.todasDuvidas', {
  url: '/todasDuvidas',
  views: {
    'menuContent': {
      templateUrl: 'templates/todasDuvidas.html'
    }
  }
})

//duvidas respondidas
.state('app.duvidasRespondidas', {
  url: '/duvidasRespondidas',
  views: {
    'menuContent': {
      templateUrl: 'templates/duvidasRespondidas.html'
    }
  }
})

//duvidas em aberto
.state('app.duvidasAberto', {
  url: '/duvidasAberto',
  views: {
    'menuContent': {
      templateUrl: 'templates/duvidasAberto.html'
    }
  }
})

.state('app.mensagem', {
  url: '/mensagem',
  views: {
    'menuContent': {
      templateUrl: 'templates/mensagem.html'
    }
  }
})

  //login
  .state('login', {
    url: '/login',
    templateUrl: 'templates/login.html',
    controller: 'loginCtrl'
  })

  //horarios
  .state('app.horarios', {
    url: '/horarios',
    views: {
      'menuContent': {
        templateUrl: 'templates/horarios.html',
        controller:  'horariosCtrl'
      }
    }
  })

  //horarios dos professores
  .state('app.horariosProf', {
    url: '/horariosProf',
    views: {
      'menuContent': {
        templateUrl: 'templates/horariosProf.html',
        controller: 'horarioProfCtrl'
      }
    }
  })

  //horarios das turmas
  .state('app.horariosTurmas', {
    url: '/horariosTurmas',
    views: {
      'menuContent': {
        templateUrl: 'templates/horariosTurmas.html',
        controller: 'horarioTurmasCtrl'
      }
    }
  })

//horarios das salas
.state('app.horariosSalas', {
  url: '/horariosSalas',
  views: {
    'menuContent': {
      templateUrl: 'templates/horariosSalas.html',
      controller: 'horarioSalasCtrl'
    }
  }
})

  //horarios dos lab
  .state('app.horariosLabs', {
    url: '/horariosLabs',
    views: {
      'menuContent': {
        templateUrl: 'templates/horariosLabs.html',
        controller: 'horarioLabsCtrl'
      }
    }
  })

  //horarios dos cursos
  .state('app.horariosCursos', {
    url: '/horariosCursos',
    views: {
      'menuContent': {
        templateUrl: 'templates/horariosCursos.html',
        controller: 'horarioCursosCtrl'
      }
    }
  })


  // rota iniciar
  $urlRouterProvider.otherwise('/login');
});
