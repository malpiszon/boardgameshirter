package net.malpiszon.boardgameshirter.models;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "games_cards")
public class GameCard implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false, updatable = false)
    private Game game;

    @Id
    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    @Column(name = "quantity", nullable = false, updatable = false)
    @NotNull
    private Integer quantity;

    public GameCard() {}

    public Integer getQuantity() {
        return quantity;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Card getCard() {
        return card;
    }
}
