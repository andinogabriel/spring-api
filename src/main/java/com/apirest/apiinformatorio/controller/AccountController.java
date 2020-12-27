package com.apirest.apiinformatorio.controller;

import com.apirest.apiinformatorio.model.City;
import com.apirest.apiinformatorio.model.Country;
import com.apirest.apiinformatorio.model.State;
import com.apirest.apiinformatorio.model.User;
import com.apirest.apiinformatorio.service.CityService;
import com.apirest.apiinformatorio.service.CountryService;
import com.apirest.apiinformatorio.service.StateService;
import com.apirest.apiinformatorio.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Controller
public class AcountController {

    @Autowired
    private CountryService countryService;
    @Autowired
    private StateService stateService;
    @Autowired
    private CityService cityService;
    @Autowired
    private UserService userService;

    private static final Logger log = LoggerFactory.getLogger()


    @GetMapping("/register")
    public String register(@ModelAttribute User user, Model model){
        model.addAttribute("user", user);

        List<Country> countries = countryService.getCountries();
        model.addAttribute("countries", countries);

        List<State> states = stateService.getStates();
        model.addAttribute("states", states);

        List<City> cities = cityService.getCities();
        model.addAttribute("cities", cities);

        return "register_form";
    }


    @PostMapping("/register")
    public String save(@ModelAttribute("user") User user){
        System.out.println(user);
        userService.addUser(user);
        return "register_success";
    }
}
