CREATE TABLE books (
id serial primary key,
title VARCHAR(100) NOT NULL,
author VARCHAR(100) NOT NULL,
publishedDate DATE NOT NULL,
isbn VARCHAR(100) NOT NULL UNIQUE
);