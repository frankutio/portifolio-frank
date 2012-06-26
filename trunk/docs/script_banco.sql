CREATE TABLE Image (
  id_image INT(3) NOT NULL AUTO_INCREMENT,
  Lixeira_id_lixeira INT(3) NULL,
  Portifolio_id_portifolio INT(3) NOT NULL,
  alt VARCHAR(100) NULL,
  title VARCHAR(100) NULL,
  descricao VARCHAR(500) NULL,
  ordem INT(2) NULL,
  lixo ENUM('true','false') NULL,
  PRIMARY KEY(id_image),
  INDEX Image_FKIndex1(Portifolio_id_portifolio),
  INDEX Image_FKIndex2(Lixeira_id_lixeira)
)
TYPE=InnoDB;

CREATE TABLE Lixeira (
  id_lixeira INT(3) NOT NULL AUTO_INCREMENT,
  data_exclusao DATE NOT NULL,
  flag_lixo BOOL NULL,
  PRIMARY KEY(id_lixeira)
)
TYPE=InnoDB;

CREATE TABLE Portifolio (
  id_portifolio INT(3) NOT NULL AUTO_INCREMENT,
  Lixeira_id_lixeira INT(3) NULL,
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
  INDEX Portifilio_FKIndex1(Usuario_id_user),
  INDEX Portifolio_FKIndex2(Lixeira_id_lixeira)
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
  Lixeira_id_lixeira INT(3) NULL,
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
  INDEX Usuario_FKIndex1(Tipo_id),
  INDEX Usuario_FKIndex2(Lixeira_id_lixeira)
)
TYPE=InnoDB;


