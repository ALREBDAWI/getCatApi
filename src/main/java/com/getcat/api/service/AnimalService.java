package com.getcat.api.service;

import com.getcat.api.model.Animal;
import com.getcat.api.repo.AnimalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public class AnimalService {
    @Autowired
    private AnimalRepo animalRepo;

    public List<Animal> getAllAnimals(){
        return animalRepo.findAll();
    }

    public Animal getAnimalById(Integer id){
        return animalRepo.findById(id).orElseThrow(()->new RuntimeException("Animal not found"));
    }

    public Animal saveAnimal(Animal animal){
        return animalRepo.save(animal);
    }

    public void deleteAnimal(Integer id){
        animalRepo.deleteById(id);
    }
}
