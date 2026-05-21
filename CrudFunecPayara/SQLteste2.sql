
CREATE TABLE estado
(
    codigo serial,
    nome character varying(50),
    sigla character varying(2),
    CONSTRAINT estado_pkey PRIMARY KEY (codigo)
);

CREATE TABLE cidade(
    codigo serial,
    nome character varying(100),
    codigo_estado integer NOT NULL,
    CONSTRAINT cidade_pkey PRIMARY KEY (codigo),
    CONSTRAINT fk_cidade_estado FOREIGN KEY (codigo)
	references estado
       
);



create table cliente(
	codigo serial primary key,
	nome varchar(50),
	sexo VARCHAR(30),
	telefone varchar(15),
		cpf varchar(15),
	codigo_cidade integer references cidade(codigo)
);


CREATE TABLE produtos
(
    id serial,
    descricao character varying(120),
    valor_compra numeric(10,2),
    valor_venda numeric(10,2),
    marca character varying(60),
    quantidade integer,
    CONSTRAINT produtos_pkey PRIMARY KEY (id)
);

CREATE TABLE venda (
    codigo SERIAL PRIMARY KEY,
    codigo_cliente INTEGER NOT NULL,

    valor_compra NUMERIC(10,2) NOT NULL DEFAULT 0,
    valor_desconto NUMERIC(10,2) NOT NULL DEFAULT 0,
    valor_total NUMERIC(10,2) NOT NULL DEFAULT 0,

    CONSTRAINT fk_venda_cliente
        FOREIGN KEY (codigo_cliente)
        REFERENCES cliente(codigo)
        ON DELETE RESTRICT
);


/*
=====================================================
 TABELA ITENS DA VENDA
=====================================================
*/

CREATE TABLE itens_venda_produto (
    codigo SERIAL PRIMARY KEY,
    codigo_venda INTEGER NOT NULL,
    codigo_produto INTEGER NOT NULL,
    quantidade INTEGER NOT NULL,
    valor_unitario NUMERIC(10,2) NOT NULL DEFAULT 0,

    CONSTRAINT fk_item_venda
        FOREIGN KEY (codigo_venda)
        REFERENCES venda(codigo)
        ON DELETE CASCADE,

    CONSTRAINT fk_item_produto
        FOREIGN KEY (codigo_produto)
        REFERENCES produtos(id)
        ON DELETE RESTRICT
);
