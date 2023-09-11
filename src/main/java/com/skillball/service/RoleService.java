package com.skillball.service;

import com.skillball.entity.Role;
import com.skillball.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public void deleteRole(Role role) {
        roleRepository.delete(role);
    }

    public Role getRoleByRolename(String rolename) {
        List<Role> roleList = roleRepository.findAll();
        for (Role role : roleList) {
            if (role.getRolename().equals(rolename)) {
                return role;
            }
        }
        return null;
    }
}