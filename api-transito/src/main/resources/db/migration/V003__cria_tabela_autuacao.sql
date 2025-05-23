
CREATE TABLE autuacao(

  id_autuacao BIGINT PRIMARY KEY AUTO_GENERATE;
  descricao TEXT(300) NOT NULL,
  valor_multa DECIMAL(10,2) NOT NULL,
  data_ocorrencia DATETIME NOT NULL,
  id_veiculo BIGINT,

  ADD CONSTRAINT fk_autuacao_veiculo
  FOREIGN KEY(id_veiculo) REFERENCES veiculo(id_veiculo)
);
