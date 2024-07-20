CREATE DATABASE caixa_rapido;
DROP DATABASE caixa_rapido;
USE caixa_rapido;

SELECT * FROM categoria_produto;
SELECT * FROM cliente;
SELECT * FROM compra;
SELECT * FROM produto_compra;
SELECT * FROM forma_pagamento;
SELECT * FROM parcela;
DELETE FROM compra;
DELETE FROM produto_compra;
DELETE FROM parcela;

SHOW TABLES;
INSERT INTO categoria_produto (id_categoria_produto, nome, desconto) VALUES (UUID_TO_BIN(UUID()), 'Alimentos não Perecíveis', 0);
INSERT INTO categoria_produto (id_categoria_produto, nome, desconto) VALUES (UUID_TO_BIN(UUID()), 'Alimentos Perecíveis', 0);
INSERT INTO categoria_produto (id_categoria_produto, nome, desconto) VALUES (UUID_TO_BIN(UUID()), 'Bebidas', 0);
INSERT INTO categoria_produto (id_categoria_produto, nome, desconto) VALUES (UUID_TO_BIN(UUID()), 'Alimentos Congelados', 20);
INSERT INTO categoria_produto (id_categoria_produto, nome, desconto) VALUES (UUID_TO_BIN(UUID()), 'Limpeza', 0);
