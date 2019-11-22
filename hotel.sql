
-- Drop table

-- DROP TABLE categoria_item;

CREATE TABLE categoria_item (
	codigo serial NOT NULL,
	nome varchar(255) NULL,
	CONSTRAINT categoria_itens_pk PRIMARY KEY (codigo)
);

-- Drop table

-- DROP TABLE funcionario;

CREATE TABLE funcionario (
	codigo serial NOT NULL,
	nome varchar(255) NOT NULL,
	endereco varchar(255) NULL,
	cidade varchar(255) NULL,
	estado varchar(255) NULL,
	telefone varchar(255) NULL,
	data_nascimento date NULL,
	salario float8 NULL,
	deleted int4 NULL DEFAULT 0,
	CONSTRAINT funcionario_pk PRIMARY KEY (codigo)
);

-- Drop table

-- DROP TABLE hospede;

CREATE TABLE hospede (
	codigo serial NOT NULL,
	nome varchar(255) NOT NULL,
	endereco varchar(255) NULL,
	cidade varchar(255) NULL,
	estado varchar(255) NULL,
	pais varchar(255) NULL,
	telefone varchar(255) NULL,
	email varchar(255) NULL,
	data_nascimento date NULL,
	documento varchar(255) NULL,
	tipo_doc int4 NULL,
	deleted int4 NULL DEFAULT 0,
	CONSTRAINT hospede_pk PRIMARY KEY (codigo)
);

-- Drop table

-- DROP TABLE tipo_acomodacao;

CREATE TABLE tipo_acomodacao (
	codigo serial NOT NULL,
	descricao varchar(255) NOT NULL,
	valor_diaria float8 NOT NULL,
	qtde_adulto int4 NOT NULL,
	qtde_crianca int4 NOT NULL,
	CONSTRAINT tipo_acomodacao_pk PRIMARY KEY (codigo)
);

-- Drop table

-- DROP TABLE acomodacao;

CREATE TABLE acomodacao (
	codigo serial NOT NULL,
	codigo_tipo_acomodacao int4 NOT NULL,
	andar int4 NOT NULL,
	CONSTRAINT acomodacao_pk PRIMARY KEY (codigo),
	CONSTRAINT acomodacao_fk FOREIGN KEY (codigo_tipo_acomodacao) REFERENCES tipo_acomodacao(codigo)
);

-- Drop table

-- DROP TABLE dados_cartao;

CREATE TABLE dados_cartao (
	codigo serial NOT NULL,
	codigo_hospede int4 NOT NULL,
	numero varchar(255) NOT NULL,
	nome_cartao varchar(255) NOT NULL,
	codigo_seguranca varchar(3) NOT NULL,
	validade_mes varchar(2) NOT NULL,
	validade_ano varchar(2) NOT NULL,
	CONSTRAINT dados_cartao_pk PRIMARY KEY (codigo),
	CONSTRAINT dados_cartao_fk FOREIGN KEY (codigo_hospede) REFERENCES hospede(codigo)
);

-- Drop table

-- DROP TABLE item_consumo;

CREATE TABLE item_consumo (
	codigo serial NOT NULL,
	codigo_categoria int4 NOT NULL,
	item varchar(255) NULL,
	descricao varchar(255) NULL,
	preco float8 NULL,
	deleted int4 NULL DEFAULT 0,
	CONSTRAINT item_consumo_pk PRIMARY KEY (codigo),
	CONSTRAINT item_consumo_fk FOREIGN KEY (codigo_categoria) REFERENCES categoria_item(codigo)
);

-- Drop table

-- DROP TABLE reserva;

CREATE TABLE reserva (
	codigo serial NOT NULL,
	data_hora_chegada timestamp NOT NULL,
	data_hora_saida timestamp NOT NULL,
	codigo_hospede int4 NOT NULL,
	codigo_acomodacao int4 NOT NULL,
	codigo_cartao int4 NULL,
	valor_diaria float8 NULL,
	taxa_multa float8 NULL,
	desconto float8 NULL,
	qtde_adulto int4 NOT NULL,
	qtde_crianca int4 NULL,
	CONSTRAINT reserva_pk PRIMARY KEY (codigo),
	CONSTRAINT reserva_fk FOREIGN KEY (codigo_hospede) REFERENCES hospede(codigo),
	CONSTRAINT reserva_fk2 FOREIGN KEY (codigo_acomodacao) REFERENCES acomodacao(codigo),
	CONSTRAINT reserva_fk3 FOREIGN KEY (codigo_cartao) REFERENCES dados_cartao(codigo)
);

-- Drop table

-- DROP TABLE acompanhante;

CREATE TABLE acompanhante (
	codigo serial NOT NULL,
	codigo_reserva int4 NOT NULL,
	nome varchar(255) NOT NULL,
	idade int4 NOT NULL,
	CONSTRAINT acompanhante_pk PRIMARY KEY (codigo),
	CONSTRAINT acompanhante_fk FOREIGN KEY (codigo_reserva) REFERENCES reserva(codigo)
);

-- Drop table

-- DROP TABLE estadia;

CREATE TABLE estadia (
	codigo serial NOT NULL,
	data_hora_inicio timestamp NOT NULL,
	data_hora_termino timestamp NOT NULL,
	codigo_reserva int4 NOT NULL,
	codigo_hospede int4 NOT NULL,
	codigo_funcionario int4 NULL,
	codigo_acomodacao int4 NOT NULL,
	status int4 NULL DEFAULT 0,
	CONSTRAINT estadia_pk PRIMARY KEY (codigo),
	CONSTRAINT estadia_fk FOREIGN KEY (codigo_reserva) REFERENCES reserva(codigo),
	CONSTRAINT estadia_fk_1 FOREIGN KEY (codigo_hospede) REFERENCES hospede(codigo),
	CONSTRAINT estadia_fk_2 FOREIGN KEY (codigo_funcionario) REFERENCES funcionario(codigo),
	CONSTRAINT estadia_fk_3 FOREIGN KEY (codigo_acomodacao) REFERENCES acomodacao(codigo)
);

-- Drop table

-- DROP TABLE pagamento;

CREATE TABLE pagamento (
	codigo serial NOT NULL,
	codigo_estadia int4 NULL,
	data_hora timestamp NOT NULL,
	forma_pagamento int4 NOT NULL,
	data_vencimento date NULL,
	valor_total_diaria float8 NULL,
	valor_total_consumo float8 NULL,
	valor float8 NOT NULL,
	desconto int4 NULL,
	multa int4 NULL,
	status int4 NULL DEFAULT 0,
	CONSTRAINT pagamento_pk PRIMARY KEY (codigo),
	CONSTRAINT pagamento_fk FOREIGN KEY (codigo_estadia) REFERENCES estadia(codigo)
);

-- Drop table

-- DROP TABLE consumo;

CREATE TABLE consumo (
	codigo serial NOT NULL,
	data_hora timestamp NOT NULL,
	codigo_item int4 NOT NULL,
	codigo_estadia int4 NOT NULL,
	valor float8 NOT NULL,
	quantidade int4 NOT NULL,
	CONSTRAINT consumo_pk PRIMARY KEY (codigo),
	CONSTRAINT consumo_fk FOREIGN KEY (codigo_item) REFERENCES item_consumo(codigo),
	CONSTRAINT consumo_fk_1 FOREIGN KEY (codigo_estadia) REFERENCES estadia(codigo)
);
