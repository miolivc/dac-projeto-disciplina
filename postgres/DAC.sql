CREATE TABLE curso(
	codigo_curso int primary key,
	abreviacao varchar(40) not null,
	descricao varchar(50) not null,
	periodo int not null,
	unidade varchar(50) not null
);
CREATE TABLE disciplina (
	codigo_disc int primary key,
	abreviacao varchar(40) not null,
	aulas_semana int not null,
	carga_horaria int not null,
	descricao varchar(50) not null,
	codigo_curso int,
	FOREIGN KEY (codigo_curso) REFERENCES curso(codigo_curso) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE laboratorio(
	codigo_lab int primary key,
	abreviacao varchar(40) not null,
	descricao varchar(50) not null
);
CREATE TABLE sala (
	codigo_sala int primary key,
	abreviacao varchar(40) not null,
	descricao varchar(50) not null
);
CREATE TABLE horario (
	codigo_hora int primary key,
	descricao varchar(50) not null,
	inicio TIME not null,
	fim TIME not null
);
CREATE TABLE professor(
	codigo_prof int primary key,
	email varchar(50) not null,
	nome varchar(50) not null,
	regime varchar(3) not null,
	unidade varchar(10) not null,
	vinculo varchar(12) not null,
        senha varchar(20) not null,
        logado boolean
);
CREATE TABLE turma(
	codigo_turma int primary key,
	identificacao char not null,
	disciplina varchar(50) not null,
	codigo_curso int,
	codigo_prof int,
	foreign key (codigo_curso) REFERENCES curso(codigo_curso),
	foreign key (codigo_prof) REFERENCES professor(codigo_prof)
);
CREATE TABLE aula (
	cod_aula int primary key,
        abrev_dia int,
	dia varchar(15) not null,
	codigo_curso int,
	codigo_disc int,
	codigo_hora int,
	codigo_prof int,
	codigo_sala int,
	codigo_lab int,
	codigo_turma int,
	foreign key (codigo_curso) REFERENCES curso(codigo_curso),
	foreign key (codigo_disc) REFERENCES disciplina(codigo_disc),
	foreign key (codigo_hora) REFERENCES horario(codigo_hora),
	foreign key (codigo_sala) REFERENCES sala(codigo_sala),
	foreign key (codigo_lab) REFERENCES laboratorio(codigo_lab),
	foreign key (codigo_turma) REFERENCES turma(codigo_turma),
	foreign key (codigo_prof) REFERENCES professor(codigo_prof)
);
CREATE TABLE usuario (
        id int primary key,
        nome varchar(50) not null,
        email varchar(50) not null,
        senha varchar(20) not null,
        tipo varchar(15) not null,
        logado boolean
);
CREATE TABLE pedido(
        id int primary key,
        nome varchar(50) not null,
        email varchar(50) not null,
        senha varchar(20) not null,
        tipo varchar(15) not null,
        prioridade int
);

INSERT INTO usuario VALUES (1, 'admin', 'admin@gmail.com', 'admin123', 'Administrador', true);
INSERT INTO laboratorio (codigo_lab, abreviacao, descricao) VALUES(36, 'X', 'X');
INSERT INTO sala(codigo_sala, abreviacao, descricao) VALUES(37, 'X', 'X');


/*DROP TABLE disciplina, sala, laboratorio, horario, aula, turma, professor, curso, usuario, pedido;*/
