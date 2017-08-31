package net.malpiszon.boardgameshirter.dtos;

public class ShirtDto {
    private String name;
    private Integer height;
    private Integer width;

    public ShirtDto(String name, Integer height, Integer width) {
        this.name = name;
        this.height = height;
        this.width = width;
    }

    public ShirtDto() {
    }

    public String getName() {
        return name;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }
}
