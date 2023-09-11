package com.skillball.service;

import com.skillball.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class TestDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserService userService;
    @Autowired
    private VocabService vocabService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private GameService gameService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Role admin = new Role();
        admin.setRolename("ROLE_ADMIN");
        roleService.saveRole(admin);

        Role user = new Role();
        user.setRolename("ROLE_USER");
        roleService.saveRole(user);

        User alice = new User();
        alice.setEmail("st155220@stud.uni-stuttgart.de");
        alice.setUsername("alice");
        alice.setPassword(passwordEncoder.encode("alice"));
        alice.setLanguage("German");
        alice.setLevel("Basic");
        alice.setIndex(1);
        alice.setDifficulty("Normal");
        alice.setDurationQuarter(180);
        alice.setRsLength(7);
        alice.setRole(roleService.getRoleByRolename("ROLE_ADMIN"));
        alice.setEmailConfirmed(true);
        alice.setEnabled(true);
        userService.saveUser(alice);

        User bob = new User();
        bob.setEmail("dominik.larche@gmail.com");
        bob.setUsername("bob");
        bob.setPassword(passwordEncoder.encode("bob"));
        bob.setLanguage("German");
        bob.setLevel("Basic");
        bob.setIndex(1);
        bob.setDifficulty("Normal");
        bob.setDurationQuarter(180);
        bob.setRsLength(7);
        bob.setRole(roleService.getRoleByRolename("ROLE_USER"));
        bob.setEmailConfirmed(true);
        bob.setEnabled(true);
        userService.saveUser(bob);

        Vocab small = new Vocab();
        small.setLanguage("German");
        small.setLevel("Basic");
        small.setIndex(1);
        small.setEnglish("small");
        small.setTranslation("klein");
        small.setPosition(1);
        vocabService.saveVocab(small);

        Vocab rich = new Vocab();
        rich.setLanguage("German");
        rich.setLevel("Basic");
        rich.setIndex(1);
        rich.setEnglish("rich");
        rich.setTranslation("reich");
        rich.setPosition(2);
        vocabService.saveVocab(rich);

        Vocab seven = new Vocab();
        seven.setLanguage("German");
        seven.setLevel("Basic");
        seven.setIndex(1);
        seven.setEnglish("seven");
        seven.setTranslation("sieben");
        seven.setPosition(3);
        vocabService.saveVocab(seven);

        Vocab thanks = new Vocab();
        thanks.setLanguage("German");
        thanks.setLevel("Basic");
        thanks.setIndex(1);
        thanks.setEnglish("thanks");
        thanks.setTranslation("danke");
        thanks.setPosition(4);
        vocabService.saveVocab(thanks);

        Vocab beautiful = new Vocab();
        beautiful.setLanguage("German");
        beautiful.setLevel("Basic");
        beautiful.setIndex(1);
        beautiful.setEnglish("beautiful");
        beautiful.setTranslation("schön");
        beautiful.setPosition(5);
        vocabService.saveVocab(beautiful);

        Game game = new Game();
        game.setUser(bob);
        game.setTimeStamp();
        game.setGuest("Miami Dolphins");
        game.setHome("Buffalo Bills");
        game.setQuarter(4);
        game.setTime(30);
        game.setScoreGuest(10);
        game.setScoreHome(17);
        game.setKickoff(false);
        game.setHomePossession(true);
        game.setYard(37);
        game.setDown(2);
        game.setYellow(5);
        game.setHomeStarted(true);
        gameService.saveGame(game);

        Ticket login = new Ticket();
        login.setRequester(bob);
        login.setTimeStamp();
        login.setTitle("Login");
        login.setText("I am not able to login.");
        login.setDeletedByUser(false);
        login.setDeletedByAdmin(false);
        ticketService.saveTicket(login);

        Ticket register = new Ticket();
        register.setRequester(bob);
        register.setTimeStamp();
        register.setTitle("Register");
        register.setText("I am not able to register.");
        register.setDeletedByUser(false);
        register.setDeletedByAdmin(false);
        ticketService.saveTicket(register);
    }
}