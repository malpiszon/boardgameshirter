ALTER TABLE games ADD COLUMN addon_to BIGINT;
ALTER TABLE games ADD CONSTRAINT FK_games_addon_to_id FOREIGN KEY (addon_to) REFERENCES games(id);