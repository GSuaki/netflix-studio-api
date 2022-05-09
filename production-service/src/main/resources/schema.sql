CREATE TABLE IF NOT EXISTS productions (
    id BIGINT(20) PRIMARY KEY,
    version INT(5) NOT NULL,
    name VARCHAR(255) NOT NULL,
    movie_id BIGINT(20) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL
);