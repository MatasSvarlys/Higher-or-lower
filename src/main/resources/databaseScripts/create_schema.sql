CREATE DATABASE IF NOT EXISTS `higherOrLower`;
USE `higherOrLower`;

DROP TABLE IF EXISTS `users`;
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS `players`;
CREATE TABLE players (
    id SERIAL PRIMARY KEY,
    age INT,
    name VARCHAR(255) UNIQUE NOT NULL,
    highscore INT
);

DROP TABLE IF EXISTS `celebrities`;
CREATE TABLE celebrities (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL,
    country VARCHAR(255),
    googleSearchCount INT
);