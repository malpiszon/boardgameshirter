package net.malpiszon.boardgameshirter.services;

import java.util.List;
import java.util.function.Predicate;

import com.google.common.collect.Lists;
import net.malpiszon.boardgameshirter.exceptions.EntityAlreadyExistsException;
import net.malpiszon.boardgameshirter.models.Card;
import net.malpiszon.boardgameshirter.models.UserGame;
import net.malpiszon.boardgameshirter.repositories.CardRepository;
import net.malpiszon.boardgameshirter.repositories.UserGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserGameService {

    @Autowired
    private UserGameRepository repository;

    public List<UserGame> findAll() {
        List<UserGame> userGames = Lists.newArrayList();
        userGames.addAll(repository.findAll());

        return userGames;
    }
}
