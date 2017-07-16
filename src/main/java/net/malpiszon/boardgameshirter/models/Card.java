package net.malpiszon.boardgameshirter.models;

import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "height", nullable = false)
    @NotNull
    private Integer height;

    @Column(name = "width", nullable = false)
    @NotNull
    private Integer width;

    @OneToMany(mappedBy = "card")
    private Set<GameCard> gameCards;

    public Card(Integer height, Integer width) {
        this.height = height;
        this.width = width;
    }

    public Card() {}

    public Integer getHeight() {
        return height;
    }

    public Integer getWidth() {
        return width;
    }
}
