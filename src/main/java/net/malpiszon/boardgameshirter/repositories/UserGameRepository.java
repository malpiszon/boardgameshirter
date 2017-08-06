package net.malpiszon.boardgameshirter.repositories;

import java.util.List;

import net.malpiszon.boardgameshirter.models.UserGame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGameRepository extends JpaRepository<UserGame, Long> {
    List<UserGame> findByUserAccountName(String userName);
}
