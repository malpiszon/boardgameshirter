package net.malpiszon.boardgameshirter.dtos;

import static org.junit.Assert.*;

import net.malpiszon.boardgameshirter.models.Card;
import org.junit.Test;
import org.modelmapper.ModelMapper;

public class CardDtoTest {

    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void whenConvertCardToCardDto_thenCorrect() {
        Card card = new Card();
        card.setHeight(20);
        card.setWidth(10);

        CardDto cardDto = modelMapper.map(card, CardDto.class);
        assertEquals("Invalid height", card.getHeight(), cardDto.getHeight());
        assertEquals("Invalid width", card.getWidth(), cardDto.getWidth());
    }

    @Test
    public void whenConvertCardDtoToCard_thenCorrect() {
        CardDto cardDto = new CardDto(20, 10);

        Card card = modelMapper.map(cardDto, Card.class);
        assertEquals("Invalid height", cardDto.getHeight(), card.getHeight());
        assertEquals("Invalid width", cardDto.getWidth(), card.getWidth());
    }
}