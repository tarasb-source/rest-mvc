-- Flyway migration: create category table (if missing) and insert initial rows idempotently
-- Works on MySQL and H2 (basic SQL compatible for both)

-- Create table if it does not exist
CREATE TABLE IF NOT EXISTS category (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL UNIQUE
);

