package com.apirest.apiinformatorio.service;

import com.apirest.apiinformatorio.exception.ResourceNotFoundException;
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

    public Country addCountry(Country country) {
        return countryRepository.save(country);
    }

    public List<Country> getCountries() {
        return countryRepository.findAll();
    }

    public Country getCountryById(Long id){
        Optional<Country> optionalCountry = countryRepository.findById(id);
        if(optionalCountry.isPresent()) {
            return optionalCountry.get();
        } else {
            throw new ResourceNotFoundException("Pais con id: "+id+" no encontrado.");
        }
    }

    public void deleteCountry(Long id) {
        Optional<Country> optionalCountry = countryRepository.findById(id);
        if(optionalCountry.isPresent()) {
            countryRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Pais con id: "+id+" no encontrado.");
        }
    }

    public Country updateCountry(Country country) {
        Optional<Country> optionalCountry = countryRepository.findById(country.getId());
        if(optionalCountry.isPresent()) {
            Country updateCountry = optionalCountry.get();
            updateCountry.setName(country.getName());
            countryRepository.save(updateCountry);
            return updateCountry;
        } else {
            throw new ResourceNotFoundException("Pais con id: "+country.getId()+" no encontrado.");
        }
    }


}
