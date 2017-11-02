package net.malpiszon.boardgameshirter.services;

import java.util.List;

import net.malpiszon.boardgameshirter.dtos.CardDto;
import net.malpiszon.boardgameshirter.exceptions.EntityAlreadyExistsException;
import net.malpiszon.boardgameshirter.models.Card;

public interface ICardService {

    void save(final Card card) throws EntityAlreadyExistsException;
    List<CardDto> findAll();
    CardDto createCardDto(Card card);
}
