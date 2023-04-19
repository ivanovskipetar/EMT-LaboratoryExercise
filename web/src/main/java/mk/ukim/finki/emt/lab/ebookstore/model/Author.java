package mk.ukim.finki.emt.lab.ebookstore.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String username;
    @ManyToOne
    private Country country;

    public Author() {
    }

    public Author(String name, String username, Country country) {
        this.name = name;
        this.username = username;
        this.country = country;
    }
}
