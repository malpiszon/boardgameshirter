package net.malpiszon.boardgameshirter.services;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import com.google.common.collect.Lists;
import net.malpiszon.boardgameshirter.models.Card;
import net.malpiszon.boardgameshirter.models.Privilege;
import net.malpiszon.boardgameshirter.models.Role;
import net.malpiszon.boardgameshirter.models.UserAccount;
import net.malpiszon.boardgameshirter.repositories.UserAccountRepository;
import net.malpiszon.boardgameshirter.services.impls.CustomUserDetailsService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CustomUserDetailsServiceTest {

    @TestConfiguration
    static class CustomUserDetailsServiceTestContextConfiguration {

        @Bean
        public UserDetailsService userDetailsService() {
            return new CustomUserDetailsService();
        }
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Autowired
    private UserDetailsService userDetailsService;

    @MockBean
    private UserAccountRepository userAccountRepository;

    private final String existingUsername = "Tom";
    private final String existingUserPassword = "TomPass";

    @Mock
    UserAccount existingUserAccount;

    @Before
    public void setUp() {
        Mockito.when(existingUserAccount.getName()).thenReturn(existingUsername);
        Mockito.when(existingUserAccount.getPassword()).thenReturn(existingUserPassword);
        Mockito.when(existingUserAccount.getRoles()).thenReturn(null);
        Mockito.when(userAccountRepository.findByName(existingUsername)).thenReturn(existingUserAccount);
    }

    @Test(expected=UsernameNotFoundException.class)
    public void whenLoadUserByUsername_givenNull_thenUsernameNotFoundBeThrown() {
        // when
        userDetailsService.loadUserByUsername(null);
    }

    @Test(expected=UsernameNotFoundException.class)
    public void whenLoadUserByUsername_givenNonExistingUser_thenUsernameNotFoundBeThrown() {
        // given
        String nonExistingUsername = "Jerry";

        // when
        userDetailsService.loadUserByUsername(nonExistingUsername);
    }

    @Test
    public void whenLoadUserByUsername_givenExistingUserWithoutPrivileges_thenUserBeReturned() {
        // when
        UserDetails userDetails = userDetailsService.loadUserByUsername(existingUsername);

        // then
        assertThat(userDetails.getUsername(), is(existingUsername));
        assertThat(userDetails.getPassword(), is(existingUserPassword));
    }

    @Test
    public void whenLoadUserByUsername_givenExistingUserWithPrivileges_thenUserBeReturned() {
        // given
        Role role = Mockito.mock(Role.class);
        Privilege privilege = Mockito.mock(Privilege.class);
        String privilegeName = "privilegeName";
        Mockito.when(privilege.getName()).thenReturn(privilegeName);
        Mockito.when(role.getPrivileges()).thenReturn(Lists.newArrayList(privilege));
        Mockito.when(existingUserAccount.getRoles()).thenReturn(Lists.newArrayList(role));

        // when
        UserDetails userDetails = userDetailsService.loadUserByUsername(existingUsername);

        // then
        assertThat(userDetails.getUsername(), is(existingUsername));
        assertThat(userDetails.getPassword(), is(existingUserPassword));
        assertThat(userDetails.getAuthorities().size(), is(1));
        assertThat(((GrantedAuthority)userDetails.getAuthorities().toArray()[0]).getAuthority(), is(privilegeName));
    }
}