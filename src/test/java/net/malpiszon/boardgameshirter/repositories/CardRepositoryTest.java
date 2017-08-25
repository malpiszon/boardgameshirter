package net.malpiszon.boardgameshirter.repositories;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import net.malpiszon.boardgameshirter.models.Card;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CardRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CardRepository cardRepository;

    @Test
    public void givenValidData_whenFindByHeightAndWidth_thenReturnCard() {
        // given
        Card card = new Card(20, 10);
        entityManager.persist(card);
        entityManager.flush();

        // when
        Card found = cardRepository.findByHeightAndWidth(card.getHeight(), card.getWidth());

        // then
        assertThat(found.getHeight(), is(card.getHeight()));
        assertThat(found.getWidth(), is(card.getWidth()));
    }
}