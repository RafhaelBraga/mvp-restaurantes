CREATE TABLE restaurantes (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(20) NOT NULL,
	bairro VARCHAR(20),
	logradouro VARCHAR(20),
	numero BIGINT(10),
	telefone BIGINT(10)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;