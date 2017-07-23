package net.malpiszon.boardgameshirter.models;

import java.util.Collection;
import javax.persistence.*;

@Entity
@Table(name = "priviledges")
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;

    public String getName() {
        return name;
    }

    public Collection<Role> getRoles() {
        return roles;
    }
}
