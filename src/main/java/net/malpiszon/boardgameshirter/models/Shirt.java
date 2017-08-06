package net.malpiszon.boardgameshirter.models;

import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "shirts")
public class Shirt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotNull
    private String name;

    @Column(name = "height", nullable = false)
    @NotNull
    private Integer height;

    @Column(name = "width", nullable = false)
    @NotNull
    private Integer width;

    @OneToMany(mappedBy = "shirt")
    private Set<UserShirt> userShirts;

    public Shirt(Integer height, Integer width) {
        this.height = height;
        this.width = width;
    }

    public Shirt() {}

    public String getName() {
        return name;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWidth() {
        return width;
    }
}
