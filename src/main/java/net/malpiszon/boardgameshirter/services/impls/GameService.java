package net.malpiszon.boardgameshirter.services.impls;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import net.malpiszon.boardgameshirter.dtos.CardDto;
import net.malpiszon.boardgameshirter.dtos.GameCardDto;
import net.malpiszon.boardgameshirter.dtos.GameDto;
import net.malpiszon.boardgameshirter.exceptions.EntityAlreadyExistsException;
import net.malpiszon.boardgameshirter.exceptions.NoSuchEntityException;
import net.malpiszon.boardgameshirter.models.Card;
import net.malpiszon.boardgameshirter.models.Game;
import net.malpiszon.boardgameshirter.models.GameCard;
import net.malpiszon.boardgameshirter.repositories.CardRepository;
import net.malpiszon.boardgameshirter.repositories.GameRepository;
import net.malpiszon.boardgameshirter.services.IGameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameService implements IGameService {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ModelMapper modelMapper;

    private static Predicate<Game> gameExists(String name) {
        return g -> g.getName().equals(name);
    }

    @Transactional
    public void save(final GameDto game) throws EntityAlreadyExistsException, NoSuchEntityException {
        List<Game> existing = gameRepository.findAll();

        if (existing.stream().filter(gameExists(game.getName())).count() > 0) {
            throw new EntityAlreadyExistsException(String.format("There already exists a game with name '%s'", game.getName()));
        }

        Game parentGame = null;
        if (game.getAddonTo() != null) {
            parentGame = gameRepository.findByName(game.getAddonTo());
            if (parentGame == null) {
                throw new NoSuchEntityException(String.format("There is no game with name '%s'", game.getAddonTo()));
            }
        }

        Set<GameCard> gameCardsToAdd = Optional.ofNullable(game.getGameCards()).orElse(Collections.emptySet()).stream().map(g -> {
            Card cardToAdd = cardRepository.findByHeightAndWidth(g.getCard().getHeight(), g.getCard().getWidth());
            if (cardToAdd == null) {
                cardToAdd = cardRepository.save(new Card(g.getCard().getHeight(), g.getCard().getWidth()));
            }
            return new GameCard(cardToAdd, g.getQuantity());
        }).collect(Collectors.toSet());
        Game gameToAdd = new Game(game.getName(), gameCardsToAdd, parentGame);
        Optional.ofNullable(gameToAdd.getGameCards()).orElse(Collections.emptySet()).stream().forEach(g -> g.setGame(gameToAdd));

        gameRepository.save(gameToAdd);
    }

    public List<GameDto> findAll() {
        List<GameDto> games = Lists.newArrayList();
        games.addAll(gameRepository.findAll().stream().map(
                g -> createGameDto(g)
        ).collect(Collectors.toList()));

        return games;
    }

    public GameDto createGameDto(Game game) {
        return new GameDto(game.getName(), Optional.ofNullable(game.getGameCards()).orElse(Collections.emptySet()).stream().map(
                c -> new GameCardDto(modelMapper.map(c.getCard(), CardDto.class), c.getQuantity())
        ).collect(Collectors.toSet()), game.getAddonTo() != null ? game.getAddonTo().getName() : "");
    }
}
