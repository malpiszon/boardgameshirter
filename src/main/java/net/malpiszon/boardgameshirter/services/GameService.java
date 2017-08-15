package net.malpiszon.boardgameshirter.services;

import java.util.List;
import java.util.function.Predicate;

import com.google.common.collect.Lists;
import net.malpiszon.boardgameshirter.exceptions.EntityAlreadyExistsException;
import net.malpiszon.boardgameshirter.models.Card;
import net.malpiszon.boardgameshirter.models.Game;
import net.malpiszon.boardgameshirter.models.GameCard;
import net.malpiszon.boardgameshirter.repositories.CardRepository;
import net.malpiszon.boardgameshirter.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private CardRepository cardRepository;

    private static Predicate<Game> gameExists(String name) {
        return g -> g.getName().equals(name);
    }

    @Transactional
    public void save(final Game game) throws EntityAlreadyExistsException {
        List<Game> existing = gameRepository.findAll();

        if (existing.stream().filter(gameExists(game.getName())).count() > 0) {
            throw new EntityAlreadyExistsException(String.format("There already exists a game with name '%s'", game.getName()));
        }

        for (GameCard gameCard : game.getGameCards()) {
            Card card = cardRepository.findByHeightAndWidth(gameCard.getCard().getHeight(), gameCard.getCard().getWidth());
            if (card == null) {
                card = cardRepository.save(new Card(gameCard.getCard().getHeight(), gameCard.getCard().getWidth()));
            }
            gameCard.setCard(card);
            gameCard.setGame(game);
        }

        gameRepository.save(game);
    }

    public List<Game> findAll() {
        List<Game> games = Lists.newArrayList();
        games.addAll(gameRepository.findAll());

        return games;
    }
}
