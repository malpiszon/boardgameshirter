package net.malpiszon.boardgameshirter.repositories;

import net.malpiszon.boardgameshirter.models.UserGame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGameRepository extends JpaRepository<UserGame, Long> {
}
