package de.hohenheim.realdemocracy.service;

import de.hohenheim.realdemocracy.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

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
    @Autowired
    private RoleService roleService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");
        Role politicianRole = new Role("ROLE_Politician");
        roleService.saveRole(adminRole);
        roleService.saveRole(userRole);
        roleService.saveRole(politicianRole);

        Person p1 = new Person();
        p1.setAusweisnummer("D12345");
        p1.setNachname("Larche");
        p1.setBundesland(Bundesland.Bayern);
        p1.setIstWahlbereichtigt(true);
        personService.savePerson(p1);

        Person p2 = new Person();
        p2.setAusweisnummer("P123456789");
        p2.setNachname("Schäfer");
        p2.setBundesland(Bundesland.Bayern);
        p2.setIstWahlbereichtigt(true);
        personService.savePerson(p2);

        Person p3 = new Person();
        p3.setAusweisnummer("B123456789");
        p3.setNachname("Violin");
        p3.setBundesland(Bundesland.Baden_Wuerttemberg);
        p3.setIstWahlbereichtigt(true);
        personService.savePerson(p3);

        Politician test_Politician = new Politician();
        test_Politician.setSektor(Sektor.Ministerium_für_Kultur_und_Freizeit);
        test_Politician.setNachname("Schäfer");
        test_Politician.setVorname("Anja");
        test_Politician.setAusweisnummer("P123456789");
        test_Politician.setUsername("anja.schaefer@bundesregierung.de");
        test_Politician.setPassword(passwordEncoder.encode("Marlene04.08."));
        test_Politician.setEnabled(true);
        test_Politician.setBundesland(Bundesland.ALLE);
        Set<Role> test_Politician_roles = new HashSet<>();
        test_Politician_roles.add(politicianRole);
        test_Politician.setRoles(test_Politician_roles);
        userService.saveUser(test_Politician);

        User test_User = new User();
        test_User.setAusweisnummer("B123456789");
        test_User.setBundesland(Bundesland.Baden_Wuerttemberg);
        test_User.setUsername("jackie.violin@gmx.de");
        test_User.setPassword(passwordEncoder.encode("Pfeil123Bogen!"));
        test_User.setEnabled(true);
        Set<Role> test_User_roles = new HashSet<>();
        test_User_roles.add(userRole);
        test_User.setRoles(test_User_roles);
        userService.saveUser(test_User);

        Debatte test_Debatte = new Debatte();
        test_Debatte.setErsteller(test_Politician);
        test_Debatte.setBundesland(Bundesland.Baden_Wuerttemberg);
        test_Debatte.setTitel("Bau eines neuen Kulturparks in Baden-Württemberg");
        test_Debatte.setProblemstellung("Laut einer neuen Studie ist Stress eines der häufigsten Krankheitsursachen in unserer Bundesrepublik.");
        test_Debatte.setLoesungsstrategie("Wir nutzen die freie Fläche am Mühlheimer-Tor und bauen dort mit staatlichen Ressourcen einen neuen Kulturpark zur Entspannung und Erholung der Bürgerinnen und Bürger.");
        test_Debatte.setStichtag("01.06.2021T12:00");
        debatteService.saveDebatte(test_Debatte);

        Debatte d2 = new Debatte();
        d2.setErsteller(test_Politician);
        d2.setBundesland(Bundesland.Bayern);
        d2.setTitel("Investitionen in die Automobilindustrie in Bayern");
        d2.setProblemstellung("Zu wenig Geld bei Daimler und Audi");
        d2.setLoesungsstrategie("Siehe Titel.");
        d2.setStichtag("01.07.2021T12:00");
        debatteService.saveDebatte(d2);

        Debatte d3 = new Debatte();
        d3.setErsteller(test_Politician);
        d3.setBundesland(Bundesland.ALLE);
        d3.setTitel("Ausgliederung unserer Abgeordneten");
        d3.setProblemstellung("Zu wenig Abgeordnete im Bundesland");
        d3.setLoesungsstrategie("Wir haben vor die neue Abgeordnete in den Bundestag aufzunehmen.");
        d3.setStichtag("01.08.2021T12:00");
        debatteService.saveDebatte(d3);
    }
}
