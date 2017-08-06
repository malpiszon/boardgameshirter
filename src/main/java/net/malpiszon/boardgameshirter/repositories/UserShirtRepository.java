package net.malpiszon.boardgameshirter.repositories;

import java.util.List;

import net.malpiszon.boardgameshirter.models.UserShirt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserShirtRepository extends JpaRepository<UserShirt, Long> {
    List<UserShirt> findByUserAccountName(String userName);
}
