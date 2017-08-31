package net.malpiszon.boardgameshirter.services;

import java.util.Optional;

public interface ILoggedUserService {

    Optional<String> getLoggedUserName();
}
