ALTER TABLE shirts ADD COLUMN user_id BIGSERIAL NOT NULL;
ALTER TABLE shirts ADD CONSTRAINT FK_shirts_user_id FOREIGN KEY (user_id) REFERENCES users(id);
ALTER TABLE shirts DROP COLUMN quantity;

CREATE TABLE users_games (
  id BIGSERIAL PRIMARY KEY,
  user_id BIGINT,
  game_id BIGINT,
  quantity SMALLINT NOT NULL DEFAULT 1,
  CONSTRAINT UN_users_games_user_id_game_id UNIQUE (user_id, game_id),
  CONSTRAINT FK_users_games_users_id FOREIGN KEY (user_id) REFERENCES users(id),
  CONSTRAINT FK_users_games_games_id FOREIGN KEY (game_id) REFERENCES games(id)
);

CREATE TABLE users_shirts (
  id BIGSERIAL PRIMARY KEY,
  user_id BIGINT,
  shirt_id BIGINT,
  quantity SMALLINT NOT NULL,
  CONSTRAINT UN_users_shirts_user_id_shirt_id UNIQUE (user_id, shirt_id),
  CONSTRAINT FK_users_shirts_user_id FOREIGN KEY (user_id) REFERENCES users(id),
  CONSTRAINT FK_users_shirts_shirts_id FOREIGN KEY (shirt_id) REFERENCES shirts(id)
);

CREATE TABLE users_shirts_users_games (
  user_shirt_id BIGINT,
  user_game_id BIGINT,
  quantity SMALLINT NOT NULL,
  destroyed BOOL NOT NULL DEFAULT FALSE,
  PRIMARY KEY (user_shirt_id, user_game_id),
  CONSTRAINT FK_users_shirts_users_games_user_shirt_id FOREIGN KEY (user_shirt_id) REFERENCES users_shirts(id),
  CONSTRAINT FK_users_shirts_users_games_user_game_id FOREIGN KEY (user_game_id) REFERENCES users_games(id)
);

UPDATE privileges SET name = 'ADMIN_PRIVILEGE' WHERE name = 'MODIFY_PRIVILEGE';