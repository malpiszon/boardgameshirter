package net.malpiszon.boardgameshirter.dtos;

public class GameCardDto {

    private CardDto card;
    private Integer quantity;

    public GameCardDto(CardDto card, Integer quantity) {
        this.card = card;
        this.quantity = quantity;
    }

    public GameCardDto() {
    }

    public CardDto getCard() {
        return card;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
