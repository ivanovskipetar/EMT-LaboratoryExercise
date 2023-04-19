package mk.ukim.finki.emt.lab.ebookstore.service.impl;

import mk.ukim.finki.emt.lab.ebookstore.model.exceptions.CountryNotFoundException;

import mk.ukim.finki.emt.lab.ebookstore.model.Author;
import mk.ukim.finki.emt.lab.ebookstore.model.Country;
import mk.ukim.finki.emt.lab.ebookstore.model.dto.AuthorDto;
import mk.ukim.finki.emt.lab.ebookstore.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emt.lab.ebookstore.repository.AuthorRepository;
import mk.ukim.finki.emt.lab.ebookstore.repository.CountryRepository;
import mk.ukim.finki.emt.lab.ebookstore.service.AuthorService;
import mk.ukim.finki.emt.lab.ebookstore.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository,
                             CountryService countryService,
                             CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public Optional<Author> create(String name, String surname, Long countryId) {
        Country country = this.countryRepository.findById(countryId).orElseThrow(() -> new CountryNotFoundException(countryId));

        Author author = new Author(name, surname, country);

        return Optional.of(this.authorRepository.save(author));
    }

    @Override
    public Optional<Author> create(AuthorDto authorDto) {
        Country country = this.countryRepository.findById(authorDto.getCountry())
                .orElseThrow(() -> new CountryNotFoundException(authorDto.getCountry()));

        Author author = new Author(authorDto.getName(), authorDto.getUsername(), country);

        return Optional.of(this.authorRepository.save(author));
    }

    @Override
    public Optional<Author> edit(Long id, String name, String username, Long countryId) {
        Author author = this.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));

        author.setName(name);
        author.setUsername(username);

        Country country = this.countryRepository.findById(countryId).orElseThrow(() -> new CountryNotFoundException(countryId));
        author.setCountry(country);

        return Optional.of(this.authorRepository.save(author));
    }

    @Override
    public Optional<Author> edit(Long id, AuthorDto authorDto) {
        Author author = this.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));

        author.setName(authorDto.getName());
        author.setUsername(authorDto.getUsername());

        Country country = this.countryRepository.findById(authorDto.getCountry())
                .orElseThrow(() -> new CountryNotFoundException(authorDto.getCountry()));
        author.setCountry(country);

        return Optional.of(this.authorRepository.save(author));
    }

    @Override
    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);
    }
}
