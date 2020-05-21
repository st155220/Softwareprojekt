package de.hohenheim.realdemocracy.service;

import de.hohenheim.realdemocracy.entity.Bundesland;
import de.hohenheim.realdemocracy.entity.Citizen;
import de.hohenheim.realdemocracy.entity.User;
import de.hohenheim.realdemocracy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> find_User_By_Id(int id) { return userRepository.findById(id);}

    public void delete_By_Id(int id) { userRepository.deleteById(id);}

    public boolean change_email(Integer userId, String new_email) {
            User user = userRepository.getOne(userId);
            user.set_E_Mail(new_email);
            userRepository.save(user);
            return true;
    }

    public boolean change_passwort(Integer userId, String new_passwort) {
        User user = userRepository.getOne(userId);
        user.set_Passwort(new_passwort);
        userRepository.save(user);
        return true;
    }

    public boolean change_Bundesland(Integer userId, Bundesland new_bundesland) {
        Citizen user = (Citizen) userRepository.getOne(userId);
        user.set_Bundesland(new_bundesland);
        userRepository.save(user);
        return true;
    }

    public User save_User(User user) {
        return userRepository.save(user);
    }

    public List<User> find_All_Users() {
        return userRepository.findAll();
    }
}