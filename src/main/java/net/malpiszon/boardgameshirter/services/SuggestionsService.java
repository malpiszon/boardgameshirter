package net.malpiszon.boardgameshirter.services;

import java.util.*;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.malpiszon.boardgameshirter.dtos.Suggestion;
import net.malpiszon.boardgameshirter.models.*;
import net.malpiszon.boardgameshirter.repositories.UserGameRepository;
import net.malpiszon.boardgameshirter.repositories.UserShirtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
public class SuggestionsService {

    private final static int MAX_SIZE_DIFF = 3;

    @Autowired
    private LoggedUserService loggedUserService;

    @Autowired
    private UserGameRepository userGameRepository;
    @Autowired
    private UserShirtRepository userShirtRepository;

    public List<Suggestion> getSuggestions() {
        List<Suggestion> suggestions = Lists.newArrayList();
        List<UserGame> userGames = userGameRepository.findByUserAccountName(loggedUserService.getLoggedUserName().orElseThrow(() -> new AccessDeniedException("Error during logged username retrieval")));
        List<UserShirt> userShirts = userShirtRepository.findByUserAccountName(loggedUserService.getLoggedUserName().orElseThrow(() -> new AccessDeniedException("Error during logged username retrieval")));

        for (UserGame userGame : userGames) {
            Set<UserShirtUserGame> userShirtUserGames = userGame.getShirtedWith();
            Map<Card, Integer> shirtedCards = Maps.newHashMap();
            for (UserShirtUserGame userShirtUserGame : userShirtUserGames) {
                shirtedCards.put(userShirtUserGame.getCard(), userShirtUserGame.getQuantity());
            }
            for (GameCard gameCard : userGame.getGame().getGameCards()) {
                Card card = gameCard.getCard();
                Optional<Integer> shirtedQuantity = Optional.ofNullable(shirtedCards.get(card));
                int notShirtedQuantity = gameCard.getQuantity() - shirtedQuantity.orElse(0);
                if (notShirtedQuantity > 0) {
                    for (UserShirt userShirt : userShirts) {
                        Shirt shirt = userShirt.getShirt();
                        if (shirt.getHeight() >= card.getHeight() && shirt.getHeight() <= (card.getHeight() + MAX_SIZE_DIFF)
                                && shirt.getWidth() >= card.getWidth() && shirt.getWidth() <= (card.getWidth() + MAX_SIZE_DIFF)
                                && gameCard.getQuantity() <= userShirt.getQuantity()) {
                            suggestions.add(new Suggestion(userGame.getGame(), card, shirt, notShirtedQuantity));
                        }
                    }
                }
            }
        }

        suggestions.sort(Comparator.comparingInt(Suggestion::getQuantity).reversed());
        return suggestions;
    }
}
