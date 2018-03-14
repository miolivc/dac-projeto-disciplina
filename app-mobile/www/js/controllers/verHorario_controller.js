app.controller('VerHorarioCtrl', function($scope) {
  $scope.horario = [
    { title: 'Horario Salas', id: 1, descricao:"Horario das salas do IFPB" },
    { title: 'Horario Laboratório', id: 2, descricao:"Horario dos laboratório do IFPB" },
    { title: 'Horario Professores', id: 3, descricao:"Horario dos professores do IFPB" },
    { title: 'Horario Cursos', id: 4, descricao:"Horario dos cursos do IFPB" },
    { title: 'Horario Turma', id: 5, descricao:"Horario das turmas do IFPB" }
  ];
})