package com.apirest.apiinformatorio.controller;

import com.apirest.apiinformatorio.model.Country;
import com.apirest.apiinformatorio.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/pais")
@RestController
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping
    public ResponseEntity<List<Country>> getCountries() {
        return ResponseEntity.ok().body(countryService.getCountries());
    }

    @RequestMapping("{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(countryService.getCountryById(id));
    }

    @PostMapping("/agregar")
    public ResponseEntity<Country> addCountry(@RequestBody Country country) {
        return ResponseEntity.status(HttpStatus.CREATED).body(countryService.addCountry(country));
    }

    @DeleteMapping("/borrar/{id}")
    public HttpStatus deleteCountry (@PathVariable("id") Long id) {
        countryService.deleteCountry(id);
        return HttpStatus.NO_CONTENT;
    }


    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable("id") Long id, @RequestBody Country country) {
        country.setId(id);
        return ResponseEntity.ok().body(countryService.updateCountry(country));
    }

}
