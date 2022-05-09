CREATE TABLE IF NOT EXISTS movies (
    id BIGINT(20) PRIMARY KEY,
    version INT(5) NOT NULL,
    title VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL
);

CREATE TABLE IF NOT EXISTS movies_talents (
    talent_id BIGINT(20) NOT NULL,
    version INT(5) NOT NULL,
    movie_id BIGINT(20) NOT NULL,
    INDEX idx_talent_id (talent_id),
    INDEX idx_movie_id (movie_id)
);