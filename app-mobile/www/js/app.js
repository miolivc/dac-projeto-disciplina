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
    //controller: 'loginCtrl'
  })

  //horarios
  .state('app.horarios', {
    url: '/horarios',
    views: {
      'menuContent': {
        templateUrl: 'templates/horarios.html',
        controller: 'HorariosCtrl'
      }
    }
  })

  //horarios
  .state('app.horario', {
    url: '/verhorario/:id',
    views: {
      'menuContent': {
        templateUrl: 'templates/verHorario.html',
        controller: 'VerHorarioCtrl'
      }
    }
  })

  // rota iniciar
  $urlRouterProvider.otherwise('/login');
});
