package com.getcat.api.controller;

import com.getcat.api.model.Animal;
import com.getcat.api.repo.AnimalRepo;
import com.getcat.api.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {
    @Autowired
    private AnimalService animalService;

    @GetMapping
    public List<Animal> getAll(){
        return animalService.getAllAnimals();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> getAnimalById(@PathVariable Integer id){
        return ResponseEntity.ok(animalService.getAnimalById(id));
    }

    @PostMapping
    public Animal CreateAnimal(@RequestBody Animal animal){
        return animalService.saveAnimal(animal);
    }
}
