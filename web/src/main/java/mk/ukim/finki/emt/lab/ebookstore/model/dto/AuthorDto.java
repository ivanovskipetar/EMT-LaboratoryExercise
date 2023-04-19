package mk.ukim.finki.emt.lab.ebookstore.model.dto;

import lombok.Data;

@Data
public class AuthorDto {

    private String name;

    private String username;

    private Long country;

    public AuthorDto() {
    }

    public AuthorDto(String name, String surname, Long country) {
        this.name = name;
        this.username = surname;
        this.country = country;
    }
}
