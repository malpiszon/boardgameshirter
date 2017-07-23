package net.malpiszon.boardgameshirter.models;

import java.util.Collection;
import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<UserAccount> userAccounts;

    @ManyToMany
    @JoinTable(name = "roles_privileges", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
    private Collection<Privilege> privileges;

    public String getName() {
        return name;
    }

    public Collection<UserAccount> getUserAccounts() {
        return userAccounts;
    }

    public Collection<Privilege> getPrivileges() {
        return privileges;
    }
}
