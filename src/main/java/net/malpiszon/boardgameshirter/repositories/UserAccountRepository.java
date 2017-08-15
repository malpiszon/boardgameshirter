package net.malpiszon.boardgameshirter.repositories;

import net.malpiszon.boardgameshirter.models.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    UserAccount findByName(String name);
}
