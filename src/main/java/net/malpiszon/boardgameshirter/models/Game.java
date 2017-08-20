package net.malpiszon.boardgameshirter.models;

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

    @OneToMany(mappedBy = "addonTo", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Game> parentTo;

    @ManyToOne
    @JoinColumn(name = "addon_to", updatable = false)
    private Game addonTo;

    public Game(String name, Set<GameCard> gameCards) {
        this.name = name;
        this.gameCards = gameCards;
        this.addonTo = null;
    }

    public Game(String name, Set<GameCard> gameCards, Game addonTo) {
        this.name = name;
        this.gameCards = gameCards;
        this.addonTo = addonTo;
    }

    public Game() {}

    public String getName() {
        return name;
    }

    public Set<GameCard> getGameCards() {
        return gameCards;
    }

    public Game getAddonTo() {
        return addonTo;
    }
}
