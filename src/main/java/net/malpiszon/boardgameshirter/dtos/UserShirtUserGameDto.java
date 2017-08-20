package net.malpiszon.boardgameshirter.dtos;

public class UserShirtUserGameDto {
    private CardDto card;
    private String shirtName;
    private Integer quantity;

    public UserShirtUserGameDto(CardDto card, String shirtName, Integer quantity) {
        this.card = card;
        this.shirtName = shirtName;
        this.quantity = quantity;
    }

    public CardDto getCard() {
        return card;
    }

    public String getShirtName() {
        return shirtName;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
