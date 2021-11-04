package com.alkemy.icons.icons.controller;

import com.alkemy.icons.icons.dto.ContinentDTO;
import com.alkemy.icons.icons.service.ContinentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("continents")
public class ContinentController {

    @Autowired //Se coloca esta anotacion para que spring inicialice el servicio
    private ContinentService continentService;

    //Creacion de los endpoints GET = obtener info / POST = crear info.
    @GetMapping
    public ResponseEntity<List<ContinentDTO>> getAll() {

        List<ContinentDTO> continents = continentService.getAllContinents();
        return ResponseEntity.ok().body(continents);
    }
    @PostMapping
    public ResponseEntity<ContinentDTO> save(@RequestBody ContinentDTO continent) { //We define the endpoints to save continents
        ContinentDTO savedContinent = continentService.save(continent); //save continent
        return ResponseEntity.status(HttpStatus.CREATED).body(savedContinent);// Response saved continent: 201
    }
}
