package net.malpiszon.boardgameshirter.dtos;

import java.util.Set;

public class GameDto {

    private String name;
    private Set<GameCardDto> gameCards;
    private String addonTo;

    public GameDto(String name, Set<GameCardDto> gameCards) {
        this.name = name;
        this.gameCards = gameCards;
        this.addonTo = null;
    }

    public GameDto(String name, Set<GameCardDto> gameCards, String addonTo) {
        this.name = name;
        this.gameCards = gameCards;
        this.addonTo = addonTo;
    }

    public GameDto() {
    }

    public String getName() {
        return name;
    }

    public Set<GameCardDto> getGameCards() {
        return gameCards;
    }

    public String getAddonTo() {
        return addonTo;
    }
}
