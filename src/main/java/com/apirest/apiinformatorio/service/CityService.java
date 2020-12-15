package com.apirest.apiinformatorio.service;

import com.apirest.apiinformatorio.model.City;
import com.apirest.apiinformatorio.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public ResponseEntity<City> addCity(City city) {
        City newCity = cityRepository.save(city);
        return ResponseEntity.ok(newCity);
    }

    public ResponseEntity<List<City>> getCities() {
        List<City> states = cityRepository.findAll();
        return ResponseEntity.ok(states);
    }

    public ResponseEntity<City> getCityById(Long id){
        Optional<City> optionalCity = cityRepository.findById(id);
        if(optionalCity.isPresent()){
            return ResponseEntity.ok(optionalCity.get());
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    public ResponseEntity<String> deleteCityById(Long id) {
        cityRepository.deleteById(id);
        String message = "Se ha borrado la ciudad con el ID: " + id;
        return ResponseEntity.ok(message);
    }

    public ResponseEntity<City> updateCity(City city) {
        Optional<City> optionalCity = cityRepository.findById(city.getId());
        if(optionalCity.isPresent()) {
            City cityToUpdate = optionalCity.get();
            cityToUpdate.setName(city.getName());
            cityRepository.save(cityToUpdate);
            return ResponseEntity.ok(cityToUpdate);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
