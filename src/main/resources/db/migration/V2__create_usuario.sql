CREATE TABLE usuario (
                         id BIGSERIAL PRIMARY KEY,
                         username VARCHAR(100) NOT NULL UNIQUE,
                         password VARCHAR(255) NOT NULL,
                         ativo BOOLEAN NOT NULL DEFAULT TRUE
);

INSERT INTO usuario (username, password, ativo)
VALUES (
           'admin',
           '$2a$10$DOWSDbP5tR4tDgG6Y5FzOeKX7vHcYpE3ZQw3tRzq3Z9tRZJz6x5lS',
           true
       );
