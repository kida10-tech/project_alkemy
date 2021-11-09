package com.alkemy.icons.icons.controller;

import com.alkemy.icons.icons.dto.IconDTO;
import com.alkemy.icons.icons.service.IconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("icons")
public class IconController {

    @Autowired
    private IconService iconService;

    @Autowired
    public IconController(IconService iconService) {
        this.iconService = iconService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<IconDTO>> getAllIcons() {
        List<IconDTO> dtoList = this.iconService.getAll();
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IconDTO> getDetailsById(@PathVariable Long id) {
        IconDTO iconDTO = this.iconService.getDetailById(id);
        return ResponseEntity.ok(iconDTO);
    }

    @GetMapping
    public ResponseEntity<List<IconDTO>> getDetailsByFilter(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String date,
            @RequestParam(required = false) Set<Long> cities,
            @RequestParam(required = false, defaultValue = "ASC") String order) {

        List<IconDTO> dtoList = this.iconService.getByFilters(name, date, cities, order);
        return ResponseEntity.ok(dtoList);
    }

    @PostMapping
    public ResponseEntity<IconDTO> save(@RequestBody IconDTO iconDTO) {
        IconDTO result = this.iconService.save(iconDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IconDTO> update(@PathVariable Long id, @RequestBody IconDTO iconDTO) {
        IconDTO result = this.iconService.modify(id, iconDTO);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<IconDTO> delete(@PathVariable Long id) {
        this.iconService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/{id}/country/{idCountry}")
    public ResponseEntity<Void> addCountry(@PathVariable Long id, @PathVariable Long idCountry) {
        this.iconService.addCountry(id, idCountry);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}/country/{idCountry}")
    public ResponseEntity<Void> removeCountry(@PathVariable Long id, @PathVariable Long idCountry) {
        this.iconService.removeCountry(id, idCountry);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
