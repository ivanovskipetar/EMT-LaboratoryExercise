package mk.ukim.finki.emt.lab.ebookstore.service;

import mk.ukim.finki.emt.lab.ebookstore.model.User;
import mk.ukim.finki.emt.lab.ebookstore.model.enumerations.Role;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> create(String username, String name, String surname, Role role);
    List<User> listAll();
    Optional<User> findUserByUsername(String username);
}
