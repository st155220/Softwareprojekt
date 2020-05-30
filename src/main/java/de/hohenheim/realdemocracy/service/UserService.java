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

    public boolean changeUsername(Integer userId, String newUsername) {
        User user = userRepository.getOne(userId);
        user.setUsername(newUsername);
        userRepository.save(user);
        return true;
    }

    public boolean changePassword(Integer userId, String newPassword) {
        User user = userRepository.getOne(userId);
        user.setPassword(newPassword);
        userRepository.save(user);
        return true;
    }

    public boolean changeBundesland(Integer userId, Bundesland newBundesland) {
        User user = userRepository.getOne(userId);
        user.setBundesland(newBundesland);
        userRepository.save(user);
        return true;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByAusweisnummer(String ausweisnummer) {
        return userRepository.existsByAusweisnummer(ausweisnummer);
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
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal()
                instanceof org.springframework.security.core.userdetails.User) {
            return getUserByUsername(((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal()).getUsername());
        }
        return getUserByUsername((String) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal());
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