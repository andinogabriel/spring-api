package com.apirest.apiinformatorio.controller;

import com.apirest.apiinformatorio.model.Country;
import com.apirest.apiinformatorio.model.State;
import com.apirest.apiinformatorio.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/state")
@RestController
public class StateController {

    @Autowired
    private StateService stateService;

    @GetMapping
    public ResponseEntity<List<State>> getStates() {
        return stateService.getStates();
    }

    @RequestMapping("{id}")
    public ResponseEntity<State> getStateById(@PathVariable("id") Long id) {
        return stateService.getStateById(id);
    }

    @PostMapping
    public ResponseEntity<State> addState(@RequestBody State state) {
        return stateService.addState(state);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<String> deleteStateById (@PathVariable("id") Long id) {
        return stateService.deleteStateById(id);
    }

    @PutMapping
    public ResponseEntity<State> updateState (@RequestBody State state) {
        return stateService.updateState(state);
    }

}
