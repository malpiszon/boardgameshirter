package net.malpiszon.boardgameshirter.services;

import java.util.List;

import net.malpiszon.boardgameshirter.dtos.GameDto;
import net.malpiszon.boardgameshirter.exceptions.EntityAlreadyExistsException;
import net.malpiszon.boardgameshirter.exceptions.NoSuchEntityException;
import net.malpiszon.boardgameshirter.models.Game;

public interface IGameService {

    void save(final GameDto game) throws EntityAlreadyExistsException, NoSuchEntityException;
    List<GameDto> findAll();
    GameDto createGameDto(Game game);
}
