package com.apirest.apiinformatorio.service;

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

    public ResponseEntity<State> addState(State state) {
        State newState = stateRepository.save(state);
        return ResponseEntity.ok(newState);
    }

    public ResponseEntity<List<State>> getStates() {
        List<State> states = stateRepository.findAll();
        return ResponseEntity.ok(states);
    }

    public ResponseEntity<State> getStateById(Long id){
        Optional<State> optionalState = stateRepository.findById(id);
        if(optionalState.isPresent()){
            return ResponseEntity.ok(optionalState.get());
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    public ResponseEntity<String> deleteStateById(Long id) {
        stateRepository.deleteById(id);
        String message = "Se ha borrado la provincia con el ID: " + id;
        return ResponseEntity.ok(message);
    }

    public ResponseEntity<State> updateState(State state) {
        Optional<State> optionalState = stateRepository.findById(state.getId());
        if(optionalState.isPresent()) {
            State stateToUpdate = optionalState.get();
            stateToUpdate.setName(state.getName());
            stateRepository.save(stateToUpdate);
            return ResponseEntity.ok(stateToUpdate);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
