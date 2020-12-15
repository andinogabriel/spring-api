package com.apirest.apiinformatorio.service;

import com.apirest.apiinformatorio.model.Country;
import com.apirest.apiinformatorio.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public ResponseEntity<Country> addCountry(Country country) {
        Country newCountry = countryRepository.save(country);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<List<Country>> getCountries() {
        List<Country> countries = countryRepository.findAll();
        return ResponseEntity.ok(countries);
    }

    public ResponseEntity<Country> getCountryById(Long id){
        Optional<Country> optionalCountry = countryRepository.findById(id);
        if(optionalCountry.isPresent()) {
            return ResponseEntity.ok(optionalCountry.get());
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    public ResponseEntity<String> deleteCountry(Long id) {
        countryRepository.deleteById(id);
        String message = "Se ha borrado el pais con :" + id;
        return ResponseEntity.ok(message);
    }

    public ResponseEntity<Country> updateCountry(Country country) {
        Optional<Country> optionalCountry = countryRepository.findById(country.getId());
        if(optionalCountry.isPresent()) {
            Country updateCountry = optionalCountry.get();
            updateCountry.setName(country.getName());
            countryRepository.save(updateCountry);
            return ResponseEntity.ok(updateCountry);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
