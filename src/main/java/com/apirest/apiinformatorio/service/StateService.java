package com.apirest.apiinformatorio.service;

import com.apirest.apiinformatorio.exception.ResourceNotFoundException;
import com.apirest.apiinformatorio.model.State;
import com.apirest.apiinformatorio.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    public State addState(State state) {
        return stateRepository.save(state);
    }

    public List<State> getStates() {
        return stateRepository.findAll();
    }

    public State getStateById(Long id){
        Optional<State> optionalState = stateRepository.findById(id);
        if(optionalState.isPresent()){
            return optionalState.get();
        } else {
            throw new ResourceNotFoundException("Provincia con id: "+id+" no encontrado.");
        }
    }

    public String deleteStateById(Long id) {
        Optional<State> optionalState = stateRepository.findById(id);
        if(optionalState.isPresent()) {
            stateRepository.deleteById(id);
            String message = "Se ha borrado la provincia con el ID: " + id;
            return message;
        } else {
            throw new ResourceNotFoundException("Provincia con id: "+id+" no encontrado.");
        }
    }

    public State updateState(State state) {
        Optional<State> optionalState = stateRepository.findById(state.getId());
        if(optionalState.isPresent()) {
            State stateToUpdate = optionalState.get();
            stateToUpdate.setName(state.getName());
            stateRepository.save(stateToUpdate);
            return stateToUpdate;
        } else {
            throw new ResourceNotFoundException("Provincia con id: "+state.getId()+" no encontrado.");
        }
    }

}
