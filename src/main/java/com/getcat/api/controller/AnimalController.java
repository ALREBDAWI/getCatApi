package com.getcat.api.controller;

import com.getcat.api.model.Animal;
import com.getcat.api.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animals")
@RequiredArgsConstructor
public class AnimalController {
    private final AnimalService animalService;

    @GetMapping
    public List<Animal> getAll(){
        return animalService.getAllAnimals();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> getAnimalById(@PathVariable Integer id){
        return ResponseEntity.ok(animalService.getAnimalById(id));
    }

    // create animal deleted because it should be fixed in DB.
    //new animals could be added directly in DB
    /*@PostMapping
    public Animal CreateAnimal(@RequestBody Animal animal){
        return animalService.createAnimal(animal);
    }*/
}
