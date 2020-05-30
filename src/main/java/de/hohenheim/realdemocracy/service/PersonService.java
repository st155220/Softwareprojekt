package de.hohenheim.realdemocracy.service;

import de.hohenheim.realdemocracy.entity.Person;
import de.hohenheim.realdemocracy.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person savePerson(Person person){return personRepository.save(person);}
    public List<Person> findAllPersons(){return personRepository.findAll();}
    public Person findByAusweisnummer(String ausweisnummer){return personRepository.findByAusweisnummer(ausweisnummer);}
}
