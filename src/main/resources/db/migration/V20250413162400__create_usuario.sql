CREATE TABLE usuario (
	id bigserial NOT NULL,
	nome varchar(120) NOT NULL,
	email varchar(120) NOT NULL,
	senha varchar NOT NULL,
	CONSTRAINT usuario_pkey PRIMARY KEY (id)
);	