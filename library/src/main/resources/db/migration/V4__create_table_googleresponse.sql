CREATE TABLE google_response (
    id BIGSERIAL PRIMARY KEY
);

-- Adicionar coluna de referência na tabela items
ALTER TABLE items
ADD COLUMN google_response_id BIGINT,
ADD CONSTRAINT fk_google_response
    FOREIGN KEY (google_response_id)
    REFERENCES google_response(id);