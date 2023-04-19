package mk.ukim.finki.emt.lab.ebookstore.service.impl;


import mk.ukim.finki.emt.lab.ebookstore.model.User;
import mk.ukim.finki.emt.lab.ebookstore.model.enumerations.Role;
import mk.ukim.finki.emt.lab.ebookstore.repository.UserRepository;
import mk.ukim.finki.emt.lab.ebookstore.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> create(String username, String name, String surname, Role role) {
        User user = new User(username,name,surname,role);
        this.userRepository.save(user);
        return Optional.of(user);    }

    @Override
    public List<User> listAll() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return Optional.of(this.userRepository.findByUsername(username));
    }
}
