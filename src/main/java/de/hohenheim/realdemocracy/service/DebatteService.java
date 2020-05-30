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

    public Debatte saveDebatte(Debatte debatte) {return debatteRepository.save(debatte);}
    public List<Debatte> findAllDebattes(){ return debatteRepository.findAll(); }
    public Debatte getDebatteById(int id) { return debatteRepository.getOne(id);}
    public void deleteDebatteById(int id){debatteRepository.deleteById(id);}

    public boolean addZustimmung(Integer debatteId) {
        Debatte debatte = debatteRepository.getOne(debatteId);
        debatte.addZustimmung();
        debatteRepository.save(debatte);
        return true;
    }

    public boolean addAblehnung(Integer debatteId) {
        Debatte debatte = debatteRepository.getOne(debatteId);
        debatte.addAblehnung();
        debatteRepository.save(debatte);
        return true;
    }
}