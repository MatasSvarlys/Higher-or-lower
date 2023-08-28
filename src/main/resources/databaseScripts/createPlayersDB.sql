CREATE TABLE players (
    id SERIAL PRIMARY KEY,
    age INT,
    name VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    highscore INT
);