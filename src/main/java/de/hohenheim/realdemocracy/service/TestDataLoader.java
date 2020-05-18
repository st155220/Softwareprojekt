package de.hohenheim.realdemocracy.service;

import de.hohenheim.realdemocracy.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class TestDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    //private static final Logger logger = LoggerFactory.getLogger(TestDataLoader.class);

    @Autowired
    private UserService userService;

    @Autowired
    private DebatteService debatteService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Politician test_Politician = new Politician(
                "Ministerium für Kultur und Freizeit", "Schäfer", "Anja");
        test_Politician.set_E_Mail("Anja.Schaefer@bundesregierung.de");
        test_Politician.set_Password("Marlene04.08.");
        userService.save_User(test_Politician);

        Citizen test_Citizen = new Citizen("F123456789");
        test_Citizen.set_Bundesland(Bundesland.Baden_Wuerttemberg);
        test_Citizen.set_E_Mail("Jackie.Violin@gmx.de");
        test_Citizen.set_Password("Pfeil123Bogen!");
        userService.save_User(test_Citizen);

        Debatte test_Debatte = new Debatte(Kategorie.ALLE, Thema.ALLE, test_Politician,
                Bundesland.Bayern, "T1", "P1", "L1",
                new Date(120, 04, 10, 12, 0));
        debatteService.safe_Debatte(test_Debatte);
    }
}
