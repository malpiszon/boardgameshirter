package net.malpiszon.boardgameshirter.dtos;

public class CardDto {

    private Integer height;
    private Integer width;

    public CardDto(Integer height, Integer width) {
        this.height = height;
        this.width = width;
    }

    public CardDto() {
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWidth() {
        return width;
    }
}
