package net.malpiszon.boardgameshirter.services;

import java.util.List;

import net.malpiszon.boardgameshirter.dtos.UserGameDto;

public interface IUserGameService {

    List<UserGameDto> findAll();
}
