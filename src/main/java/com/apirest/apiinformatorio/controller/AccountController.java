package com.apirest.apiinformatorio.controller;

import com.apirest.apiinformatorio.model.City;
import com.apirest.apiinformatorio.model.Country;
import com.apirest.apiinformatorio.model.State;
import com.apirest.apiinformatorio.model.User;
import com.apirest.apiinformatorio.objects.UserDTO;
import com.apirest.apiinformatorio.service.CityService;
import com.apirest.apiinformatorio.service.CountryService;
import com.apirest.apiinformatorio.service.StateService;
import com.apirest.apiinformatorio.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AccountController {

    @Autowired
    private CountryService countryService;
    @Autowired
    private StateService stateService;
    @Autowired
    private CityService cityService;
    @Autowired
    private UserService userService;

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }


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
    public String save(@Valid User user, BindingResult bindingResult){

        if(bindingResult.hasErrors()) {
            return "register_form";
        }
        System.out.println(user);
        log.info(">> user: {}", user.toString());
        return "register_success";
    }
}
