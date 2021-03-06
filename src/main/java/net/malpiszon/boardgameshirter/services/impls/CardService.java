package net.malpiszon.boardgameshirter.services.impls;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import net.malpiszon.boardgameshirter.dtos.CardDto;
import net.malpiszon.boardgameshirter.exceptions.EntityAlreadyExistsException;
import net.malpiszon.boardgameshirter.models.Card;
import net.malpiszon.boardgameshirter.repositories.CardRepository;
import net.malpiszon.boardgameshirter.services.ICardService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CardService implements ICardService {

    @Autowired
    private CardRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    private static Predicate<Card> cardExists(Integer height, Integer width) {
        return g -> g.getHeight().equals(height) && g.getWidth().equals(width);
    }

    @Transactional
    public void save(final Card card) throws EntityAlreadyExistsException {
        List<Card> existing = repository.findAll();

        if (existing.stream().filter(cardExists(card.getHeight(), card.getWidth())).count() > 0) {
            throw new EntityAlreadyExistsException(String.format("There already exists a game card with dimensions '%d x %d'", card.getHeight(), card.getWidth()));
        }

        repository.save(card);
    }

    public List<CardDto> findAll() {
        List<CardDto> cards = Lists.newArrayList();
        cards.addAll(repository.findAll().stream().map(c -> createCardDto(c)).collect(Collectors.toList()));

        return cards;
    }

    public CardDto createCardDto(Card card) {
        return modelMapper.map(card, CardDto.class);
    }
}
