package net.malpiszon.boardgameshirter.services.impls;

import java.util.Optional;

import net.malpiszon.boardgameshirter.services.ILoggedUserService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LoggedUserService implements ILoggedUserService {

    public Optional<String> getLoggedUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return Optional.of(currentUserName);
        }
        return Optional.empty();
    }
}
