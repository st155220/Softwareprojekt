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

    public Person get_Person_By_Id(String id){return personRepository.getOne(id);}
    public List<Person> find_All_Persons(){return personRepository.findAll();}
    public Person save_Person(Person person){return personRepository.save(person);}
}
