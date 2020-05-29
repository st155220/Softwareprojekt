package de.hohenheim.realdemocracy.service;

import de.hohenheim.realdemocracy.entity.Bundesland;
import de.hohenheim.realdemocracy.entity.Role;
import de.hohenheim.realdemocracy.entity.User;
import de.hohenheim.realdemocracy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> find_User_By_Id(int id) { return userRepository.findById(id);}
    public void delete_By_Id(int id) { userRepository.deleteById(id);}
    public boolean change_email(Integer userId, String newUsername) {
        User user = userRepository.getOne(userId);
        user.setUsername(newUsername);
        userRepository.save(user);
        return true;
    }
    public boolean change_passwort(Integer userId, String new_passwort) {
        User user = userRepository.getOne(userId);
        user.setPassword(new_passwort);
        userRepository.save(user);
        return true;
    }
    public boolean change_Bundesland(Integer userId, Bundesland new_bundesland) {
        User user = userRepository.getOne(userId);
        user.set_Bundesland(new_bundesland);
        userRepository.save(user);
        return true;
    }

    public User save_User(User user) {
        return userRepository.save(user);
    }
    public List<User> find_All_Users() {
        return userRepository.findAll();
    }

    /**
     * Sucht nach einem User mit einem bestimmten Usernamen.
     *
     * @param username der username.
     * @return User-Objekt.
     */
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Spring Security Authentication Methoden
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Gibt den aktuell eingeloggten User zurück.
     *
     * @return User.
     */
    public User getCurrentUser() {
        return getUserByUsername(((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUsername());
    }

    /**
     * Gibt das UserDetails Objekt des aktuell eingeloggten Users zurück. Wird u.U. benötigt um
     * Rollen-Authentifizierungschecks durchzuführen.
     *
     * @return UserDetails Objekt der Spring Security.
     */
    public org.springframework.security.core.userdetails.User getCurrentUserDetails() {
        return (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
    }

    /**
     * Überschreibt die Methode, welche für den Login der Spring Security benötigt wird..
     *
     * @param username the username des Benutzers.
     * @return UserDetails Objekt des Spring Security Frameworks.
     * @throws UsernameNotFoundException exception.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("Could not find the user for username " + username);
        }
        List<GrantedAuthority> grantedAuthorities = getUserAuthorities(user.getRoles());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                user.isEnabled(), true, true, user.isEnabled(), grantedAuthorities);
    }

    private List<GrantedAuthority> getUserAuthorities(Set<Role> roleSet) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Role role : roleSet) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRolename()));
        }
        return grantedAuthorities;
    }

}