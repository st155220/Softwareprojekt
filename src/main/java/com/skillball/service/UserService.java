package com.skillball.service;

import com.skillball.entity.Ticket;
import com.skillball.repository.UserRepository;
import com.skillball.entity.Game;
import com.skillball.entity.Role;
import com.skillball.entity.User;
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
    @Autowired
    private GameService gameService;
    @Autowired
    private TicketService ticketService;
    private String emailConfirmationCode;
    private User tempUser;

    public boolean changeEmail(Integer userId, String newEmail) {
        User user = userRepository.getOne(userId);
        user.setEmail(newEmail);
        user.setEmailConfirmed(false);
        userRepository.save(user);
        return true;
    }

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

    public void setRole(User user, Role role) {
        user.setRole(role);
        userRepository.save(user);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(User user) {
        for (Game game : gameService.findAllGame()) {
            if (game.getUser() == user) {
                gameService.deleteGame(game);
            }
        }
        for (Ticket ticket : ticketService.findAllTicket()) {
            if (ticket.getRequester() == user) {
                ticketService.deleteTicket(ticket);
            }
        }
        userRepository.delete(user);
    }

    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
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

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
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

    public String getEmailConfirmationCode() {
        return emailConfirmationCode;
    }

    public void setEmailConfirmationCode(String emailConfirmationCode) {
        this.emailConfirmationCode = emailConfirmationCode;
    }

    public User getTempUser() {
        return tempUser;
    }

    public void setTempUser(User tempUser) {
        this.tempUser = tempUser;
    }

    public boolean isCorrectEmail(String email) {
        boolean atContained = false;
        for (int i = 1; i < email.length(); i++) {
            if (atContained && email.charAt(i) == '.' && i + 1 < email.length()) {
                return true;
            }
            if (email.charAt(i) == '@') {
                if (atContained) {
                    return false;
                }
                atContained = true;
                i++;
            }
        }
        return false;
    }

    public List<User> listUsers() {
        List<User> list = findAllUser();
        list.sort(new UserComparator());
        return list;
    }

    private class UserComparator implements Comparator<User> {
        @Override
        public int compare(User user1, User user2) {
            return user1.getUsername().compareTo(user2.getUsername());
        }
    }
}