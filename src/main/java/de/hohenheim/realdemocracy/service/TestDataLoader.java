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

        Person personDominik = new Person();
        personDominik.setAusweisnummer("D123456789");
        personDominik.setNachname("Larche");
        personDominik.setBundesland(Bundesland.Bayern);
        personDominik.setIstWahlbereichtigt(true);
        personService.savePerson(personDominik);

        Person personAnja = new Person();
        personAnja.setAusweisnummer("A123456789");
        personAnja.setNachname("Schäfer");
        personAnja.setBundesland(Bundesland.Bayern);
        personAnja.setIstWahlbereichtigt(true);
        personService.savePerson(personAnja);

        Person personJackie = new Person();
        personJackie.setAusweisnummer("J123456789");
        personJackie.setNachname("Violin");
        personJackie.setBundesland(Bundesland.Baden_Wuerttemberg);
        personJackie.setIstWahlbereichtigt(true);
        personService.savePerson(personJackie);

        Person personMax = new Person();
        personMax.setAusweisnummer("M123456789");
        personMax.setNachname("Heidelberg");
        personMax.setBundesland(Bundesland.Baden_Wuerttemberg);
        personMax.setIstWahlbereichtigt(true);
        personService.savePerson(personMax);

        Politician anja = new Politician();
        anja.setSektor(Sektor.Ministerium_für_Kultur_und_Freizeit);
        anja.setNachname("Schäfer");
        anja.setVorname("Anja");
        anja.setAusweisnummer("A123456789");
        anja.setUsername("anja");
        anja.setPassword(passwordEncoder.encode("Aaabbbccc1*"));
        anja.setEnabled(true);
        anja.setBundesland(Bundesland.ALLE);
        Set<Role> anja_roles = new HashSet<>();
        anja_roles.add(politicianRole);
        anja.setRoles(anja_roles);
        userService.saveUser(anja);

        Politician max = new Politician();
        max.setSektor(Sektor.Ministerium_für_Arbeit_und_Soziales);
        max.setNachname("Heidelberg");
        max.setVorname("Max");
        max.setAusweisnummer("M123456789");
        max.setUsername("max");
        max.setPassword(passwordEncoder.encode("Aaabbbccc1*"));
        max.setEnabled(true);
        max.setBundesland(Bundesland.ALLE);
        Set<Role> max_roles = new HashSet<>();
        max_roles.add(politicianRole);
        max.setRoles(max_roles);
        userService.saveUser(max);

        User jackie = new User();
        jackie.setAusweisnummer("J123456789");
        jackie.setBundesland(Bundesland.Baden_Wuerttemberg);
        jackie.setUsername("jackie");
        jackie.setPassword(passwordEncoder.encode("Aaabbbccc1*"));
        jackie.setEnabled(true);
        Set<Role> jackie_roles = new HashSet<>();
        jackie_roles.add(userRole);
        jackie.setRoles(jackie_roles);
        userService.saveUser(jackie);

        Debatte test_Debatte = new Debatte();
        test_Debatte.setErsteller(anja);
        test_Debatte.setBundesland(Bundesland.Baden_Wuerttemberg);
        test_Debatte.setTitel("Bau eines neuen Kulturparks in Baden-Württemberg");
        test_Debatte.setProblemstellung("Laut einer neuen Studie ist Stress eines der häufigsten Krankheitsursachen in unserer Bundesrepublik.");
        test_Debatte.setLoesungsstrategie("Wir nutzen die freie Fläche am Mühlheimer-Tor und bauen dort mit staatlichen Ressourcen einen neuen Kulturpark zur Entspannung und Erholung der Bürgerinnen und Bürger.");
        test_Debatte.setStichtag("01.06.2021T12:00");
        debatteService.saveDebatte(test_Debatte);

        Debatte d2 = new Debatte();
        d2.setErsteller(max);
        d2.setBundesland(Bundesland.Bayern);
        d2.setTitel("Investitionen in die Automobilindustrie in Bayern");
        d2.setProblemstellung("Zu wenig Geld bei Daimler und Audi");
        d2.setLoesungsstrategie("Siehe Titel.");
        d2.setStichtag("01.07.2021T12:00");
        debatteService.saveDebatte(d2);

        Debatte d3 = new Debatte();
        d3.setErsteller(anja);
        d3.setBundesland(Bundesland.ALLE);
        d3.setTitel("Ausgliederung unserer Abgeordneten");
        d3.setProblemstellung("Zu wenig Abgeordnete im Bundesland");
        d3.setLoesungsstrategie("Wir haben vor die neue Abgeordnete in den Bundestag aufzunehmen.");
        d3.setStichtag("01.08.2021T12:00");
        debatteService.saveDebatte(d3);

        Debatte d4 = new Debatte();
        d4.setErsteller(max);
        d4.setBundesland(Bundesland.ALLE);
        d4.setTitel("Arbeitslosenversucherungspflicht");
        d4.setProblemstellung("Zu wenig Arbeit im Bundesland");
        d4.setLoesungsstrategie("Neues Gesetz zur Pflicht eine Arbeitslosenversucherung für jeden");
        d4.setStichtag("01.12.2021T12:00");
        debatteService.saveDebatte(d4);
    }
}
