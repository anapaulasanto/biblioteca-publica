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

CREATE TABLE access_info (
                             id BIGSERIAL PRIMARY KEY,
                             pdf_id BIGINT,
                             FOREIGN KEY (pdf_id) REFERENCES pdf(id)
);

CREATE TABLE pdf (
                     id BIGSERIAL PRIMARY KEY,
                     is_available BOOLEAN NOT NULL,
                     acs_token_link VARCHAR(255)
);

CREATE TABLE items (
    id VARCHAR(255) PRIMARY KEY,
    volume_info_id BIGINT,
    access_info_id BIGINT,
    google_response_id BIGINT,
    FOREIGN KEY (volume_info_id) REFERENCES volume_info(id),
    FOREIGN KEY (access_info_id) REFERENCES access_info(id),
    FOREIGN KEY (google_response_id) REFERENCES google_response(id)
);

CREATE TABLE google_response (
    id BIGSERIAL PRIMARY KEY
);

-- Adicionar coluna de referência na tabela items
ALTER TABLE items
ADD COLUMN google_response_id BIGINT,
ADD CONSTRAINT fk_google_response
    FOREIGN KEY (google_response_id)
    REFERENCES google_response(id);