package net.malpiszon.boardgameshirter.services;

import java.util.List;

import net.malpiszon.boardgameshirter.dtos.ShirtDto;
import net.malpiszon.boardgameshirter.exceptions.EntityAlreadyExistsException;

public interface IShirtService {

    void save(final ShirtDto shirt) throws EntityAlreadyExistsException;
    List<ShirtDto> findAll();
}
