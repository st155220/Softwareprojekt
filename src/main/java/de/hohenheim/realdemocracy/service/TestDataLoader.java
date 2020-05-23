package de.hohenheim.realdemocracy.service;

import de.hohenheim.realdemocracy.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class TestDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserService userService;
    @Autowired
    private DebatteService debatteService;
    @Autowired
    private AbstimmungService abstimmungService;
    @Autowired
    private PersonService personService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Person p1 = new Person();
        p1.setAusweisnummer("D12345");
        p1.setNachname("Larche");
        p1.setBundesland(Bundesland.Bayern);
        p1.setIst_Wahlbereichtigt(true);
        personService.save_Person(p1);

        Person p2 = new Person();
        p2.setAusweisnummer("P123456789");
        p2.setNachname("Schäfer");
        p2.setBundesland(Bundesland.Bayern);
        p2.setIst_Wahlbereichtigt(true);
        personService.save_Person(p2);

        Person p3 = new Person();
        p3.setAusweisnummer("B123456789");
        p3.setNachname("Violin");
        p3.setBundesland(Bundesland.Baden_Wuerttemberg);
        p3.setIst_Wahlbereichtigt(true);
        personService.save_Person(p3);

        Politician test_Politician = new Politician();
        test_Politician.set_Sektor("Ministerium für Kultur und Freizeit");
        test_Politician.set_Nachname("Schäfer");
        test_Politician.set_Vorname("Anja");
        test_Politician.set_Ausweisnummer("P123456789");
        test_Politician.set_E_Mail("anja.schaefer@bundesregierung.de");
        test_Politician.set_Passwort("Marlene04.08.");
        test_Politician.set_Bundesland(Bundesland.ALLE);
        userService.save_User(test_Politician);

        Debatte test_Debatte = new Debatte();
        test_Debatte.setKategorie(Kategorie.ALLE);
        test_Debatte.setThema(Thema.ALLE);
        test_Debatte.setErsteller(test_Politician);
        test_Debatte.setBundesland(Bundesland.Baden_Wuerttemberg);
        test_Debatte.setTitel("Bau eines neuen Kulturparks in Baden-Württemberg");
        test_Debatte.setProblemstellung("Laut einer neuen Studie ist Stress eines der häufigsten Krankheitsursachen in unserer Bundesrepublik.");
        test_Debatte.setLoesungsstrategie("Wir nutzen die freie Fläche am Mühlheimer-Tor und bauen dort mit staatlichen Ressourcen einen neuen Kulturpark zur Entspannung und Erholung der Bürgerinnen und Bürger.");
        test_Debatte.setStichtag("01.06.2021T12:00");
        debatteService.save_Debatte(test_Debatte);

        Debatte d2 = new Debatte();
        d2.setKategorie(Kategorie.ALLE);
        d2.setThema(Thema.ALLE);
        d2.setErsteller(test_Politician);
        d2.setBundesland(Bundesland.Bayern);
        d2.setTitel("Investitionen in die Automobilindustrie in Bayern");
        d2.setProblemstellung("Zu wenig Geld bei Daimler und Audi");
        d2.setLoesungsstrategie("Siehe Titel.");
        d2.setStichtag("01.07.2021T12:00");
        debatteService.save_Debatte(d2);

        Debatte d3 = new Debatte();
        d3.setKategorie(Kategorie.ALLE);
        d3.setThema(Thema.ALLE);
        d3.setErsteller(test_Politician);
        d3.setBundesland(Bundesland.ALLE);
        d3.setTitel("Ausgliederung unserer Abgeordneten");
        d3.setProblemstellung("Zu wenig Abgeordnete im Bundesland");
        d3.setLoesungsstrategie("Wir haben vor die neue Abgeordnete in den Bundestag aufzunehmen.");
        d3.setStichtag("01.08.2021T12:00");
        debatteService.save_Debatte(d3);

        User test_User = new User();
        test_User.set_Ausweisnummer("B123456789");
        test_User.set_Bundesland(Bundesland.Baden_Wuerttemberg);
        test_User.set_E_Mail("jackie.violin@gmx.de");
        test_User.set_Passwort("Pfeil123Bogen!");
        userService.save_User(test_User);
    }
}
