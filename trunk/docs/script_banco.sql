CREATE TABLE Image (
  id_image INT(3) NOT NULL AUTO_INCREMENT,
  Portifolio_id_portifolio INT(3) NOT NULL,
  alt VARCHAR(100) NULL,
  title VARCHAR(100) NULL,
  descricao VARCHAR(500) NULL,
  ordem INT(2) NULL,
  lixo ENUM('true','false') NULL,
  PRIMARY KEY(id_image),
  INDEX Image_FKIndex1(Portifolio_id_portifolio)
)
TYPE=InnoDB;

CREATE TABLE Log_sistem (
  id_log INT(3) NOT NULL AUTO_INCREMENT,
  tipo VARCHAR(20) NULL,
  data_log DATE NULL,
  item INT(3) NULL,
  PRIMARY KEY(id_log)
)
TYPE=InnoDB;

CREATE TABLE Portifolio (
  id_portifolio INT(3) NOT NULL AUTO_INCREMENT,
  Usuario_id_user INT(3) NOT NULL,
  nome VARCHAR(100) NOT NULL,
  servico VARCHAR(100) NOT NULL,
  tecnologia VARCHAR(1000) NULL,
  descricao VARCHAR(2000) NULL,
  data_inicio DATE NULL,
  data_fim DATE NULL,
  link VARCHAR(100) NULL,
  img_capa VARCHAR(100) NULL,
  id_img_capa VARCHAR(20) NOT NULL,
  lixo ENUM('true','false') NULL,
  PRIMARY KEY(id_portifolio),
  INDEX Portifilio_FKIndex1(Usuario_id_user)
)
TYPE=InnoDB;

CREATE TABLE Tipo (
  id INT(3) NOT NULL AUTO_INCREMENT,
  tipo_user VARCHAR(3) NULL,
  PRIMARY KEY(id)
)
TYPE=InnoDB;

CREATE TABLE Usuario (
  id_user INT(3) NOT NULL AUTO_INCREMENT,
  Tipo_id INT(3) NOT NULL,
  login VARCHAR(20) NULL,
  nome VARCHAR(100) NULL,
  senha VARCHAR(8) NULL,
  email VARCHAR(100) NULL,
  foto VARCHAR(200) NULL,
  about VARCHAR(336) NULL,
  data_nascimento DATE NULL,
  lixo ENUM('true','false') NULL,
  PRIMARY KEY(id_user),
  INDEX Usuario_FKIndex1(Tipo_id)
)
TYPE=InnoDB;


