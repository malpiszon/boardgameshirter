package net.malpiszon.boardgameshirter.models;

import java.util.Collection;
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

    public Game(String name) {
        this.name = name;
    }

    public Game() {}

    public String getName() {
        return name;
    }
}
