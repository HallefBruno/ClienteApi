DROP TABLE IF EXISTS cliente;  
    CREATE TABLE cliente (  
    id INT AUTO_INCREMENT  PRIMARY KEY,  
    codigo VARCHAR(50),  
    nome VARCHAR(255) NOT NULL,
    cnpj VARCHAR(18) NOT NULL,
    logradouro VARCHAR(255) NOT NULL,
    cep VARCHAR(20),
    geolocalizacao VARCHAR(100) NOT NULL
);  


INSERT INTO cliente (id, codigo, nome, cnpj, logradouro, cep, geolocalizacao) VALUES (1, 110, 'Marco Antonio','34947562000169', 'R. 12-C - St. Garavelo Res. Park, Aparecida de Goiania - GO, 74930220, Brasil', '74930220', '-16.7666458,-49.3441011');
INSERT INTO cliente (id, codigo, nome, cnpj, logradouro, cep, geolocalizacao) VALUES (2, 220, 'Jorge Lucas','34947562000111', 'Av. Lago dos Marrecos - Jardim Tropical, Aparecida de Goiania - GO, 74946520, Brasil', '74946520', '-16.7729867,-49.3440473');
INSERT INTO cliente (id, codigo, nome, cnpj, logradouro, cep, geolocalizacao) VALUES (3, 330, 'Judith Guedes','34947562000161', 'Av. Independencia - Goiania, GO, Brasil', null, '-16.6629557,-49.2566925');