package com.getcat.api.service;

import com.getcat.api.model.Animal;
import com.getcat.api.repo.AnimalRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalService {
    private final AnimalRepo animalRepo;

    public List<Animal> getAllAnimals(){
        return animalRepo.findAll();
    }

    public Animal getAnimalById(Integer id){
        return animalRepo.findById(id).orElseThrow(()->new RuntimeException("Animal not found with id: "+id));
    }

    public Animal createAnimal(Animal animal) {
        return null;
    }
    // we deleted create and delete animals because animals should be fixed in db
    //enum could be used here, but it will not give us flexibility we need to deploy every time we add a new one
}
