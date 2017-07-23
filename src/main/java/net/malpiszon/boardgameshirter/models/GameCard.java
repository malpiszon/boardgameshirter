package net.malpiszon.boardgameshirter.models;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "games_cards")
public class GameCard implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @Id
    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    @Column(name = "quantity", nullable = false)
    @NotNull
    private Integer quantity;

    public GameCard() {}

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getCardWidth() {
        return card.getWidth();
    }

    public Integer getCardHeight() {
        return card.getHeight();
    }
}
