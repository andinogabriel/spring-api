package com.apirest.apiinformatorio.controller;

import com.apirest.apiinformatorio.model.City;
import com.apirest.apiinformatorio.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/ciudad")
@RestController
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    public ResponseEntity<List<City>> getCities() {
        return ResponseEntity.ok().body(cityService.getCities());
    }

    @RequestMapping("{id}")
    public ResponseEntity<City> getCityById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(cityService.getCityById(id));
    }

    @PostMapping("/agregar")
    public ResponseEntity<City> addCity(@RequestBody City city) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cityService.addCity(city));
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> deleteCityById (@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(cityService.deleteCityById(id));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<City> updateCity (@PathVariable("id") Long id, @RequestBody City city) {
        city.setId(id);
        return ResponseEntity.ok().body(cityService.updateCity(city));
    }

}
