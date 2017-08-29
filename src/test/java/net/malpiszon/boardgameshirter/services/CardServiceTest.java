package net.malpiszon.boardgameshirter.services;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import com.google.common.collect.Lists;
import net.malpiszon.boardgameshirter.dtos.CardDto;
import net.malpiszon.boardgameshirter.exceptions.EntityAlreadyExistsException;
import net.malpiszon.boardgameshirter.models.Card;
import net.malpiszon.boardgameshirter.repositories.CardRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CardServiceTest {

    @TestConfiguration
    static class CardServiceTestContextConfiguration {

        @Bean
        public CardService cardService() {
            return new CardService();
        }
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Autowired
    private CardService cardService;

    @MockBean
    private CardRepository cardRepository;

    private final Integer height = 20;
    private final Integer width = 10;

    @Before
    public void setUp() {
        Card card = new Card(height, width);

        Mockito.when(cardRepository.findAll()).thenReturn(Lists.newArrayList(card));
    }

    @Test
    public void whenFindAll_thenCardShouldBeFound() {
        // when
        List<CardDto> found = cardService.findAll();

        // then
        assertThat(found.size(), is(1));
        assertThat(found.get(0).getHeight(), is(height));
        assertThat(found.get(0).getWidth(), is(width));
    }

    @Test(expected=EntityAlreadyExistsException.class)
    public void givenExistingData_whenSave_thenEntityAlreadyExistsExceptionBeThrown() throws EntityAlreadyExistsException {
        // given
        Card existingCard = new Card(height, width);
        List<CardDto> found = cardService.findAll();

        // when
        cardService.save(existingCard);
    }

    @Test
    public void givenValidData_whenSave_thenCardShouldBeSaved() throws EntityAlreadyExistsException {
        // given
        Integer height = 25;
        Integer width = 10;
        Card card = new Card(height, width);

        // when
        cardService.save(card);

        // then
        Mockito.verify(cardRepository, Mockito.times(1)).save(card);
    }
}