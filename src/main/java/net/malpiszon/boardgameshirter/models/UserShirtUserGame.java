package net.malpiszon.boardgameshirter.models;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users_shirts_users_games")
public class UserShirtUserGame implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_shirt_id")
    private UserShirt userShirt;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_game_id")
    private UserGame userGame;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;


    @Column(name = "quantity", nullable = false)
    @NotNull
    private Integer quantity;

    public UserShirtUserGame() {}

    public Integer getQuantity() {
        return quantity;
    }

    public String getShirtName() {
        return userShirt.getShirt().getName();
    }

    public Card getCard() {
        return card;
    }
}
