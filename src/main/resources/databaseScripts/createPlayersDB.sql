CREATE TABLE players (
    id SERIAL PRIMARY KEY,
    age INT,
    name VARCHAR(255) UNIQUE NOT NULL,
    PASSWORD VARCHAR(255) NOT NULL,
    highscore INT
);