CREATE TABLE celebrities (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL,
    country VARCHAR(255),
    googleSearchCount INT
);