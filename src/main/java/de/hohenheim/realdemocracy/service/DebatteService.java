package de.hohenheim.realdemocracy.service;

import de.hohenheim.realdemocracy.entity.Debatte;
import de.hohenheim.realdemocracy.repository.DebatteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DebatteService {

    @Autowired
    private DebatteRepository debatteRepository;

    public Debatte safe_Debatte(Debatte debatte) {return debatteRepository.save(debatte);}

    public List<Debatte> find_All_Debates(){ return debatteRepository.findAll(); }

}
