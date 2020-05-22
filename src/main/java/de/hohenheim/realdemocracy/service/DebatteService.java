package de.hohenheim.realdemocracy.service;

import de.hohenheim.realdemocracy.entity.Debatte;
import de.hohenheim.realdemocracy.entity.User;
import de.hohenheim.realdemocracy.repository.DebatteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DebatteService {

    @Autowired
    private DebatteRepository debatteRepository;

    public Debatte get_Debatte_By_Id(int id) { return debatteRepository.getOne(id);}
    public Debatte save_Debatte(Debatte debatte) {return debatteRepository.save(debatte);}
    public List<Debatte> find_All_Debates(){ return debatteRepository.findAll(); }

}
