package net.malpiszon.boardgameshirter.repositories;

import net.malpiszon.boardgameshirter.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
    Game findByName(String name);
}
