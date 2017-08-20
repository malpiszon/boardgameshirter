package net.malpiszon.boardgameshirter.dtos;

import java.util.Set;

public class UserGameDto {

    private GameDto game;
    private Integer quantity;
    private Set<UserShirtUserGameDto> shirtedWith;
    private String userName;

    public UserGameDto(GameDto game, Integer quantity, Set<UserShirtUserGameDto> shirtedWith, String userName) {
        this.game = game;
        this.quantity = quantity;
        this.shirtedWith = shirtedWith;
        this.userName = userName;
    }

    public GameDto getGame() {
        return game;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Set<UserShirtUserGameDto> getShirtedWith() {
        return shirtedWith;
    }

    public String getUserName() {
        return userName;
    }
}
