-- Criar tabela principal volume_info
CREATE TABLE volume_info (
                             id BIGSERIAL PRIMARY KEY,
                             title VARCHAR(255),
                             published_date VARCHAR(255),
                             description TEXT
);

-- Criar tabela para autores
CREATE TABLE volume_info_authors (
                                     volume_info_id BIGINT,
                                     author VARCHAR(255),
                                     FOREIGN KEY (volume_info_id) REFERENCES volume_info(id)
);

-- Criar tabela para categorias
CREATE TABLE volume_info_categories (
                                        volume_info_id BIGINT,
                                        category VARCHAR(255),
                                        FOREIGN KEY (volume_info_id) REFERENCES volume_info(id)
);