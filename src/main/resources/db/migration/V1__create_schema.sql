CREATE TABLE artista (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(200) NOT NULL
);

CREATE TABLE album (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(200) NOT NULL
);

CREATE TABLE artista_album (
    artista_id INT NOT NULL REFERENCES artista(id),
    album_id INT NOT NULL REFERENCES album(id),
    PRIMARY KEY (artista_id, album_id)
);
