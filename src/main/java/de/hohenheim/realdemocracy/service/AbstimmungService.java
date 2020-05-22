package de.hohenheim.realdemocracy.service;

import de.hohenheim.realdemocracy.entity.Abstimmung;
import de.hohenheim.realdemocracy.repository.AbstimmungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbstimmungService {

    @Autowired
    private AbstimmungRepository abstimmungRepository;

    public List<Abstimmung> find_All_Abstimmungen(){return abstimmungRepository.findAll();}
    public Abstimmung save_Abstimmung(Abstimmung abstimmung){return abstimmungRepository.save(abstimmung);}

}
