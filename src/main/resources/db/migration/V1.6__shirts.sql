ALTER TABLE shirts DROP CONSTRAINT FK_shirts_user_id;
ALTER TABLE shirts DROP COLUMN user_id;

ALTER TABLE users_shirts_users_games ADD COLUMN card_id BIGSERIAL;
ALTER TABLE users_shirts_users_games ADD CONSTRAINT FK_users_shirts_users_games_cards_id FOREIGN KEY (card_id) REFERENCES cards(id);