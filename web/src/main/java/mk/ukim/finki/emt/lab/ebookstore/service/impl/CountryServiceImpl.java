package mk.ukim.finki.emt.lab.ebookstore.service.impl;


import mk.ukim.finki.emt.lab.ebookstore.model.Country;
import mk.ukim.finki.emt.lab.ebookstore.model.dto.CountryDto;
import mk.ukim.finki.emt.lab.ebookstore.model.exceptions.CountryNotFoundException;
import mk.ukim.finki.emt.lab.ebookstore.repository.CountryRepository;
import mk.ukim.finki.emt.lab.ebookstore.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return this.countryRepository.findById(id);
    }

    @Override
    public Optional<Country> create(String name, String continent) {
        Country country = new Country(name, continent);

        return Optional.of(this.countryRepository.save(country));
    }

    @Override
    public Optional<Country> create(CountryDto countryDto) {
        Country country = new Country(countryDto.getName(), countryDto.getContinent());

        return Optional.of(this.countryRepository.save(country));
    }

    @Override
    public Optional<Country> edit(Long id, String name, String continent) {
        Country country = this.findById(id)
                .orElseThrow(() -> new CountryNotFoundException(id));

        country.setName(name);
        country.setContinent(continent);

        return Optional.of(this.countryRepository.save(country));
    }

    @Override
    public Optional<Country> edit(Long id, CountryDto countryDto) {
        Country country = this.findById(id)
                .orElseThrow(() -> new CountryNotFoundException(id));

        country.setName(countryDto.getName());
        country.setContinent(countryDto.getContinent());

        return Optional.of(this.countryRepository.save(country));
    }

    @Override
    public void deleteById(Long id) {
        this.countryRepository.deleteById(id);
    }
}
