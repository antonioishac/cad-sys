CREATE TABLE TB_PERMISSAO (
	ID INTEGER PRIMARY KEY,
	DESCRICAO VARCHAR(50) NOT NULL
);

INSERT INTO TB_PERMISSAO(ID, DESCRICAO) VALUES(1, 'ROLE_ADMIN');
INSERT INTO TB_PERMISSAO(ID, DESCRICAO) VALUES(2, 'ROLE_USER');