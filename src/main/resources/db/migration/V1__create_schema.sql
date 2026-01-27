CREATE TABLE album (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(200) NOT NULL
);

CREATE TABLE artista (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(200) NOT NULL
);

CREATE TABLE artista_album (
    artista_id BIGINT NOT NULL REFERENCES artista(id),
    album_id BIGINT NOT NULL REFERENCES album(id),
    PRIMARY KEY (artista_id, album_id)
);