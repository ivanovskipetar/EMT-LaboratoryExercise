package mk.ukim.finki.emt.lab.ebookstore.model;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.emt.lab.ebookstore.model.enumerations.Role;


@Entity
@Data
@Table(name = "library_user")
public class User {
    @Id
    private String username;
    private String name;
    private String surname;
    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String username, String name, String surname, Role role) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

    public User() {
    }
}
