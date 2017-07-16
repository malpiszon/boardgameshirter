package net.malpiszon.boardgameshirter.models;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotNull
    @Size(max = 128)
    private String name;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GameCard> gameCards;

    public Game(String name) {
        this.name = name;
        this.gameCards = new HashSet<>();
    }

    public Game() {}

    public String getName() {
        return name;
    }

    public Set<GameCard> getGameCards() {
        return gameCards;
    }
}
