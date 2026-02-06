-- Inserir Artistas
INSERT INTO artista (nome) VALUES
                               ('Serj Tankian'),
                               ('Mike Shinoda'),
                               ('Michel Teló'),
                               ('Guns N'' Roses')
    ON CONFLICT DO NOTHING;

-- Inserir Álbuns de Serj Tankian
INSERT INTO album (nome) VALUES
                             ('Harakiri'),
                             ('Black Blooms'),
                             ('The Rough Dog')
    ON CONFLICT DO NOTHING;

-- Inserir Álbuns de Mike Shinoda
INSERT INTO album (nome) VALUES
                             ('The Rising Tied'),
                             ('Post Traumatic'),
                             ('Post Traumatic EP'),
                             ('Where''d You Go')
    ON CONFLICT DO NOTHING;

-- Inserir Álbuns de Michel Teló
INSERT INTO album (nome) VALUES
                             ('Bem Sertanejo'),
                             ('Bem Sertanejo - O Show (Ao Vivo)'),
                             ('Bem Sertanejo - (1ª Temporada) - EP')
    ON CONFLICT DO NOTHING;

-- Inserir Álbuns de Guns N' Roses
INSERT INTO album (nome) VALUES
                             ('Use Your Illusion I'),
                             ('Use Your Illusion II'),
                             ('Greatest Hits')
    ON CONFLICT DO NOTHING;

-- Relacionar Artistas com Álbuns
-- Serj Tankian (id=1) com seus álbuns
INSERT INTO artista_album (artista_id, album_id)
SELECT a.id, al.id
FROM artista a, album al
WHERE a.nome = 'Serj Tankian'
  AND al.nome IN ('Harakiri', 'Black Blooms', 'The Rough Dog')
    ON CONFLICT DO NOTHING;

-- Mike Shinoda (id=2) com seus álbuns
INSERT INTO artista_album (artista_id, album_id)
SELECT a.id, al.id
FROM artista a, album al
WHERE a.nome = 'Mike Shinoda'
  AND al.nome IN ('The Rising Tied', 'Post Traumatic', 'Post Traumatic EP', 'Where''d You Go')
    ON CONFLICT DO NOTHING;

-- Michel Teló (id=3) com seus álbuns
INSERT INTO artista_album (artista_id, album_id)
SELECT a.id, al.id
FROM artista a, album al
WHERE a.nome = 'Michel Teló'
  AND al.nome IN ('Bem Sertanejo', 'Bem Sertanejo - O Show (Ao Vivo)', 'Bem Sertanejo - (1ª Temporada) - EP')
    ON CONFLICT DO NOTHING;

-- Guns N' Roses (id=4) com seus álbuns
INSERT INTO artista_album (artista_id, album_id)
SELECT a.id, al.id
FROM artista a, album al
WHERE a.nome = 'Guns N'' Roses'
  AND al.nome IN ('Use Your Illusion I', 'Use Your Illusion II', 'Greatest Hits')
    ON CONFLICT DO NOTHING;