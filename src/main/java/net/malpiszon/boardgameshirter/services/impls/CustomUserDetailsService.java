package net.malpiszon.boardgameshirter.services.impls;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.transaction.Transactional;

import net.malpiszon.boardgameshirter.models.Privilege;
import net.malpiszon.boardgameshirter.models.Role;
import net.malpiszon.boardgameshirter.models.UserAccount;
import net.malpiszon.boardgameshirter.repositories.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findByName(username);
        if (userAccount == null) {
            throw new UsernameNotFoundException(String.format("Could not find the userAccount '%s'", username));
        }

        return new User(userAccount.getName(), userAccount.getPassword(), true, true, true, true,
                getAuthorities(userAccount.getRoles()));
    }

    private final Collection<? extends GrantedAuthority> getAuthorities(final Collection<Role> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private final List<String> getPrivileges(final Collection<Role> roles) {
        final List<String> privilegeNames = new ArrayList<String>();
        final List<Privilege> privileges = new ArrayList<Privilege>();
        for (final Role role : roles) {
            privileges.addAll(role.getPrivileges());
        }
        for (final Privilege item : privileges) {
            privilegeNames.add(item.getName());
        }

        return privilegeNames;
    }

    private final List<GrantedAuthority> getGrantedAuthorities(final List<String> privileges) {
        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (final String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}
