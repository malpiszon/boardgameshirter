package net.malpiszon.boardgameshirter.models;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users_games")
public class UserGame implements Serializable {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccount userAccount;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @OneToMany(mappedBy = "userGame")
    private Set<UserShirtUserGame> userShirtUserGames;

    @Column(name = "quantity", nullable = false)
    @NotNull
    private Integer quantity;

    public UserGame() {}

    public String getUserName() {
        return userAccount.getName();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Game getGame() {
        return game;
    }

    public Set<UserShirtUserGame> getShirtedWith() {
        return userShirtUserGames;
    }
}
