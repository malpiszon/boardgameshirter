package net.malpiszon.boardgameshirter.repositories;

import net.malpiszon.boardgameshirter.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
