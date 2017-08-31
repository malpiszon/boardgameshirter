package net.malpiszon.boardgameshirter.services.impls;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import net.malpiszon.boardgameshirter.dtos.UserGameDto;
import net.malpiszon.boardgameshirter.dtos.UserShirtUserGameDto;
import net.malpiszon.boardgameshirter.repositories.UserGameRepository;
import net.malpiszon.boardgameshirter.services.IGameService;
import net.malpiszon.boardgameshirter.services.IUserGameService;
import net.malpiszon.boardgameshirter.services.impls.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserGameService implements IUserGameService {

    @Autowired
    private UserGameRepository repository;

    @Autowired
    private IGameService gameService;
    @Autowired
    private CardService cardService;

    public List<UserGameDto> findAll() {
        List<UserGameDto> userGames = Lists.newArrayList();
        userGames.addAll(repository.findAll().stream().map(g -> new UserGameDto(gameService.createGameDto(g.getGame()),
                g.getQuantity(), g.getShirtedWith().stream().map(
                        s -> new UserShirtUserGameDto(cardService.createCardDto(s.getCard()), s.getShirtName(), s.getQuantity())
        ).collect(Collectors.toSet()), g.getUserName())).collect(Collectors.toList()));

        return userGames;
    }
}
