
CREATE TABLE veiculo (

  id_veiculo BIGINT PRIMARY KEY AUTO_INCREMENT,
  marca VARCHAR(20) NOT NULL,
  modelo VARCHAR(50) NOT NULL,
  placa VARCHAR(10) NOT NULL,
  status_veiculo VARCHAR(60) NOT NULL,
  data_cadastro DATETIME NOT NULL,
  data_apreensao DATETIME,
  id_proprietario BIGINT,

  CONSTRAINT UNIQUE(placa),
  ADD CONSTRAINT fk_proprietario
  FOREIGN KEY(id_proprietario) REFERENCES proprietario(id_proprietario)
);