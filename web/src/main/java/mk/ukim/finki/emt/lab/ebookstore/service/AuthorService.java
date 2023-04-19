package mk.ukim.finki.emt.lab.ebookstore.service;


import mk.ukim.finki.emt.lab.ebookstore.model.Author;
import mk.ukim.finki.emt.lab.ebookstore.model.dto.AuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();

    Optional<Author> findById(Long id);

    Optional<Author> create(String name, String surname, Long countryId);

    Optional<Author> create(AuthorDto authorDto);

    Optional<Author> edit(Long id, String name, String surname, Long countryId);

    Optional<Author> edit(Long id, AuthorDto authorDto);

    void deleteById(Long id);
}
