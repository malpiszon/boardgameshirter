package net.malpiszon.boardgameshirter.dtos;

import net.malpiszon.boardgameshirter.models.Card;
import net.malpiszon.boardgameshirter.models.Game;
import net.malpiszon.boardgameshirter.models.Shirt;

public class SuggestionDto {

    private Game game;
    private Card card;
    private Shirt shirt;
    private int quantity;

    public SuggestionDto(Game game, Card card, Shirt shirt, int quantity) {
        this.game = game;
        this.card = card;
        this.shirt = shirt;
        this.quantity = quantity;
    }

    public String getGameName() {
        return game.getName();
    }

    public Shirt getShirt() {
        return shirt;
    }

    public int getQuantity() {
        return quantity;
    }

    public Card getCard() {
        return card;
    }
}
