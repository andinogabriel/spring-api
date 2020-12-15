package com.apirest.apiinformatorio.repository;

import com.apirest.apiinformatorio.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {

}
