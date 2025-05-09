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