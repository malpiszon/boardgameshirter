package net.malpiszon.boardgameshirter.repositories;

import net.malpiszon.boardgameshirter.models.Shirt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShirtRepository extends JpaRepository<Shirt, Long> {
}
