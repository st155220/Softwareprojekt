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
        Politician test_Politician = new Politician();
        test_Politician.set_Sektor("Ministerium für Kultur und Freizeit");
        test_Politician.set_Nachname("Schäfer");
        test_Politician.set_Vorname("Anja");
        test_Politician.set_E_Mail("anja.schaefer@bundesregierung.de");
        test_Politician.set_Passwort("Marlene04.08.");
        userService.save_User(test_Politician);

        Citizen test_Citizen = new Citizen();
        test_Citizen.set_Ausweisnummer("F123456789");
        test_Citizen.set_Bundesland(Bundesland.Baden_Wuerttemberg);
        test_Citizen.set_E_Mail("jackie.violin@gmx.de");
        test_Citizen.set_Passwort("Pfeil123Bogen!");
        userService.save_User(test_Citizen);

        Debatte test_Debatte = new Debatte(Kategorie.ALLE, Thema.ALLE, test_Politician,
                Bundesland.Bayern, "T1", "P1", "L1",
                "12.04.2020 12:00");
        debatteService.save_Debatte(test_Debatte);
    }
}
