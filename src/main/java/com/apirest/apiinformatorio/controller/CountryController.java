package com.apirest.apiinformatorio.controller;

import com.apirest.apiinformatorio.model.Country;
import com.apirest.apiinformatorio.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/country")
@RestController
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping
    public ResponseEntity<List<Country>> getCountries() {
        return countryService.getCountries();
    }

    @RequestMapping(value = "{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable("id") Long id) {
        return countryService.getCountryById(id);
    }

    @PostMapping
    public ResponseEntity<Country> addCountry(@RequestBody Country country) {
        return countryService.addCountry(country);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<String> deleteCountry (@PathVariable("id") Long id) {
        return countryService.deleteCountry(id);
    }


    @PutMapping
    public ResponseEntity<Country> updateCountry(@RequestBody Country country) {
        return countryService.updateCountry(country);
    }

}
