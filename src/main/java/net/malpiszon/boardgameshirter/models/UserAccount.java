package net.malpiszon.boardgameshirter.models;

import java.util.Collection;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotNull
    private String name;

    @Column(name = "password", length = 60, nullable = false)
    @NotNull
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    @OneToMany(mappedBy = "shirt")
    private Set<UserShirt> userShirts;

    @OneToMany(mappedBy = "game")
    private Set<UserGame> userGames;

    public UserAccount(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public UserAccount() {}

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }
}
