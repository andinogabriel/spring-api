package com.apirest.apiinformatorio.controller;

import com.apirest.apiinformatorio.model.City;
import com.apirest.apiinformatorio.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/city")
@RestController
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    public ResponseEntity<List<City>> getCities() {
        return cityService.getCities();
    }

    @RequestMapping("{id}")
    public ResponseEntity<City> getCityById(@PathVariable("id") Long id) {
        return cityService.getCityById(id);
    }

    @PostMapping
    public ResponseEntity<City> addCity(@RequestBody City city) {
        return cityService.addCity(city);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<String> deleteCityById (@PathVariable("id") Long id) {
        return cityService.deleteCityById(id);
    }

    @PutMapping
    public ResponseEntity<City> updateCity (@RequestBody City city) {
        return cityService.updateCity(city);
    }

}
