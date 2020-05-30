package de.hohenheim.realdemocracy.service;

import de.hohenheim.realdemocracy.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelpService {

    @Autowired
    private UserService userService;
    @Autowired
    private DebatteService debatteService;
    @Autowired
    private AbstimmungService abstimmungService;
    @Autowired
    private PersonService personService;

    public List<Debatte> getDebattes(Bundesland bundesland, Sektor sektor) {
        return debatteService.findAllDebattes();
    }

    public Bundesland getBundesland(String bundesland) {
        switch (bundesland) {
            case "alle":
                return Bundesland.ALLE;
            case "baden_wuerttemberg":
                return Bundesland.Baden_Wuerttemberg;
            case "bayern":
                return Bundesland.Bayern;
            case "berlin":
                return Bundesland.Berlin;
            case "brandenburg":
                return Bundesland.Brandenburg;
            case "bremen":
                return Bundesland.Bremen;
            case "hamburg":
                return Bundesland.Hamburg;
            case "hessen":
                return Bundesland.Hessen;
            case "mecklenburg_vorpommen":
                return Bundesland.Mecklenburg_Vorpommen;
            case "niedersachsen":
                return Bundesland.Niedersachsen;
            case "nordrhein_westfalen":
                return Bundesland.Nordrhein_Westfalen;
            case "rheinland_pfalz":
                return Bundesland.Rheinland_Pfalz;
            case "saarland":
                return Bundesland.Saarland;
            case "sachsen_anhalt":
                return Bundesland.Sachsen_Anhalt;
            case "sachsen":
                return Bundesland.Sachsen;
            case "schleswig-holstein":
                return Bundesland.Schleswig_Holstein;
            case "thueringen":
                return Bundesland.Thueringen;
        }
        return null;
    }

    public boolean passwortFormatPasst(String passwort){
        boolean sonderzeichen = false;
        boolean zahl = false;
        boolean grossbuchstabe = false;
        boolean kleinbuchstabe = false;

        for (char c: passwort.toCharArray()){
            if(c == '!' || c == '$' ||  c == '%' ||  c == '&' ||  c == '/' ||  c == '?' ||  c == '+' ||  c == '-' ||  c == '*' ||  c == '#' || c == '=' || c == '.'){
                sonderzeichen = true;
            }
            if(Character.isDigit(c)){
                zahl = true;
            }
            if(Character.isLetter(c) && Character.isUpperCase(c)){
                grossbuchstabe = true;
            }
            if(Character.isLetter(c) && Character.isLowerCase(c)){
                kleinbuchstabe = true;
            }
        }
        return passwort.length() >= 10 && sonderzeichen && zahl && grossbuchstabe && kleinbuchstabe;
    }

    public boolean schonAbgestimmt(Debatte debatte) {
        for (Abstimmung abstimmung : abstimmungService.findAllAbstimmungen()) {
            if (abstimmung.getDebatteId() == debatte.getDebatteId()
                    && abstimmung.getUserId() == userService.getCurrentUser().getUserId()) {
                return true;
            }
        }
        return false;
    }

    public boolean nichtWahlberechtigt(Bundesland bundesland) {
        Person person = personService.findByAusweisnummer(userService.getCurrentUser().getAusweisnummer());
        return person == null || !person.istWahlbereichtigt() ||
                (person.getBundesland() != bundesland && bundesland != Bundesland.ALLE);
    }
}
