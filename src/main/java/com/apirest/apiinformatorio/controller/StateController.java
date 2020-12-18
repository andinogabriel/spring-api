package com.apirest.apiinformatorio.controller;

import com.apirest.apiinformatorio.model.Country;
import com.apirest.apiinformatorio.model.State;
import com.apirest.apiinformatorio.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/provincia")
@RestController
public class StateController {

    @Autowired
    private StateService stateService;

    @GetMapping
    public ResponseEntity<List<State>> getStates() {
        return ResponseEntity.ok().body(stateService.getStates());
    }

    @RequestMapping("{id}")
    public ResponseEntity<State> getStateById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(stateService.getStateById(id));
    }

    @PostMapping("/agregar")
    public ResponseEntity<State> addState(@RequestBody State state) {
        return ResponseEntity.status(HttpStatus.CREATED).body(stateService.addState(state));
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> deleteStateById (@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(stateService.deleteStateById(id));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<State> updateState (@PathVariable("id") Long id, @RequestBody State state) {
        state.setId(id);
        return ResponseEntity.ok().body(stateService.updateState(state));
    }

}
