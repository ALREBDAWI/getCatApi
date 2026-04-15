package com.getcat.api.repo;

import com.getcat.api.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepo extends JpaRepository<Animal, Integer> {
}
