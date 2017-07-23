ALTER TABLE gamecards RENAME TO games_cards;
ALTER TABLE games_cards RENAME COLUMN gameid TO game_id;
ALTER TABLE games_cards RENAME COLUMN cardid TO card_id;