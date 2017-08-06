package net.malpiszon.boardgameshirter.models;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users_shirts")
public class UserShirt implements Serializable {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccount userAccount;

    @ManyToOne
    @JoinColumn(name = "shirt_id")
    private Shirt shirt;

    @OneToMany(mappedBy = "userShirt")
    private Set<UserShirtUserGame> userShirtUserGames;

    @Column(name = "quantity", nullable = false)
    @NotNull
    private Integer quantity;

    public UserShirt() {}

    public Integer getQuantity() {
        return quantity;
    }

    public Shirt getShirt() {
        return shirt;
    }
}
