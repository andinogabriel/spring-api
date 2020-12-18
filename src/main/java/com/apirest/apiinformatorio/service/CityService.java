package com.apirest.apiinformatorio.service;

import com.apirest.apiinformatorio.exception.ResourceNotFoundException;
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

    public City addCity(City city) {
        return cityRepository.save(city);
    }

    public List<City> getCities() {
        return cityRepository.findAll();
    }

    public City getCityById(Long id){
        Optional<City> optionalCity = cityRepository.findById(id);
        if(optionalCity.isPresent()){
            return optionalCity.get();
        } else {
            throw new ResourceNotFoundException("Ciudad con id: "+id+" no encontrada.");
        }
    }

    public String deleteCityById(Long id) {
        Optional<City> optionalCity = cityRepository.findById(id);
        if (optionalCity.isPresent()) {
            String message = "Se ha borrado la ciudad con el ID: " + id;
            cityRepository.deleteById(id);
            return message;
        }
        throw new ResourceNotFoundException("Ciudad con id: "+id+" no encontrada.");
    }

    public City updateCity(City city) {
        Optional<City> optionalCity = cityRepository.findById(city.getId());
        if(optionalCity.isPresent()) {
            City cityToUpdate = optionalCity.get();
            cityToUpdate.setName(city.getName());
            cityRepository.save(cityToUpdate);
            return cityToUpdate;
        } else {
            throw new ResourceNotFoundException("Ciudad con id: "+city.getId()+" no encontrada.");
        }
    }

}
