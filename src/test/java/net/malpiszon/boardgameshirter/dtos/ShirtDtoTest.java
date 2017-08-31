package net.malpiszon.boardgameshirter.dtos;

import static org.junit.Assert.*;

import net.malpiszon.boardgameshirter.models.Shirt;
import org.junit.Test;
import org.modelmapper.ModelMapper;

public class ShirtDtoTest {

    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void whenConvertShirtToShirtDto_thenCorrect() {
        Shirt shirt = new Shirt();
        shirt.setName("Test name");
        shirt.setHeight(20);
        shirt.setWidth(10);

        ShirtDto shirtDto = modelMapper.map(shirt, ShirtDto.class);
        assertEquals("Invalid height", shirt.getHeight(), shirtDto.getHeight());
        assertEquals("Invalid width", shirt.getWidth(), shirtDto.getWidth());
    }

    @Test
    public void whenConvertShirtDtoToShirt_thenCorrect() {
        ShirtDto shirtDto = new ShirtDto("Test name", 20, 10);

        Shirt shirt = modelMapper.map(shirtDto, Shirt.class);
        assertEquals("Invalid name", shirtDto.getName(), shirt.getName());
        assertEquals("Invalid height", shirtDto.getHeight(), shirt.getHeight());
        assertEquals("Invalid width", shirtDto.getWidth(), shirt.getWidth());
    }

}