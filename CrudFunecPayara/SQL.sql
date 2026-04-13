
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