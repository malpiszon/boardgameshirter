CREATE TABLE games (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE cards (
  id BIGSERIAL PRIMARY KEY,
  height SMALLINT NOT NULL,
  width SMALLINT NOT NULL,
  CONSTRAINT UN_cards UNIQUE (height, width)
);

CREATE TABLE gamecards (
  gameid BIGSERIAL,
  cardid BIGSERIAL,
  quantity SMALLINT NOT NULL,
  PRIMARY KEY (gameid, cardid),
  CONSTRAINT FK_games_id FOREIGN KEY (gameid) REFERENCES games(id),
  CONSTRAINT FK_cards_id FOREIGN KEY (cardid) REFERENCES cards(id)
);

CREATE TABLE shirts (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  quantity SMALLINT NOT NULL,
  height SMALLINT NOT NULL,
  width SMALLINT NOT NULL,
  CONSTRAINT UN_shirts UNIQUE (height, width)
);