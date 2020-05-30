package de.hohenheim.realdemocracy.service;

import de.hohenheim.realdemocracy.entity.Role;
import de.hohenheim.realdemocracy.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role saveRole(Role role){return roleRepository.save(role);}
}
