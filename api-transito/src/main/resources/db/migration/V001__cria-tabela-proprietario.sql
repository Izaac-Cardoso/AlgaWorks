
CREATE TABLE proprietario (

  id_proprietario BIGINT PRIMARY KEY AUTO_INCREMENT,
  email VARCHAR(60) NOT NULL,
  nome VARCHAR(200)NOT NULL,
  telefone VARCHAR(20) NOT NULL,

  ADD CONSTRAINT UNIQUE(email)
);