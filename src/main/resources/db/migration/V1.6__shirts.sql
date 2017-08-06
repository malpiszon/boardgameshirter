ALTER TABLE shirts DROP CONSTRAINT FK_user_id RESTRICT;
ALTER TABLE shirts DROP COLUMN user_id RESTRICT;

ALTER TABLE users_shirts_users_games ADD COLUMN card_id BIGSERIAL;
ALTER TABLE users_shirts_users_games ADD CONSTRAINT FK_cards_id FOREIGN KEY (card_id) REFERENCES cards(id);