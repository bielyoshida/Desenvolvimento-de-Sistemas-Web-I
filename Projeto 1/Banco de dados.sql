CREATE DATABASE lojabd;

CREATE TABLE produtos (
  id SERIAL PRIMARY KEY,
  descricao VARCHAR(120) NOT NULL,
  valor_compra NUMERIC(10,2) NOT NULL,
  valor_venda NUMERIC(10,2) NOT NULL,
  marca VARCHAR(60) NOT NULL,
  quantidade INT NOT NULL
);
