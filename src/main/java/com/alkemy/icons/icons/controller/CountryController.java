package com.alkemy.icons.icons.controller;

import com.alkemy.icons.icons.dto.CountryDTO;
import com.alkemy.icons.icons.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    //Creacion de los endpoints GET = obtener info / POST = crear info.
    @GetMapping
    public ResponseEntity<List<CountryDTO>> getAll() {
        List<CountryDTO> countries = countryService.getAllCountries();
        return ResponseEntity.ok().body(countries);
    }

    @PostMapping
    public ResponseEntity<CountryDTO> save(@RequestBody CountryDTO country) { //We define the endpoint to save the country
        CountryDTO savedCountry = countryService.save(country); //Country to be save
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCountry); //Saved country response = 201
    }
}
