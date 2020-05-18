package de.hohenheim.realdemocracy.service;

import de.hohenheim.realdemocracy.entity.User;
import de.hohenheim.realdemocracy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save_User(User user) {
        return userRepository.save(user);
    }

    public List<User> find_All_Users() {
        return userRepository.findAll();
    }
}