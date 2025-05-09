CREATE TABLE items (
    id VARCHAR(255) PRIMARY KEY,
    volume_info_id BIGINT,
    access_info_id BIGINT,
    google_response_id BIGINT,
    FOREIGN KEY (volume_info_id) REFERENCES volume_info(id),
    FOREIGN KEY (access_info_id) REFERENCES access_info(id),
    FOREIGN KEY (google_response_id) REFERENCES google_response(id)
);