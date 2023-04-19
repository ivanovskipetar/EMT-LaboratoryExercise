package mk.ukim.finki.emt.lab.ebookstore.service;


import mk.ukim.finki.emt.lab.ebookstore.model.Country;
import mk.ukim.finki.emt.lab.ebookstore.model.dto.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();

    Optional<Country> findById(Long id);

    Optional<Country> create(String name, String continent);

    Optional<Country> create(CountryDto countryDto);

    Optional<Country> edit(Long id, String name, String continent);

    Optional<Country> edit(Long id, CountryDto countryDto);

    void deleteById(Long id);
}
