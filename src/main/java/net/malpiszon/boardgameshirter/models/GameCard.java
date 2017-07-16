package net.malpiszon.boardgameshirter.models;

import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "gamecards")
public class GameCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity", nullable = false)
    @NotNull
    private Integer quantity;

    public GameCard(Integer quantity) {
        this.quantity = quantity;
    }

    public GameCard() {}

    public Integer getQuantity() {
        return quantity;
    }
}
