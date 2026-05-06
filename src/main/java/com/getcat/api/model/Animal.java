package com.getcat.api.model;

import jakarta.persistence.*;
import lombok.*;

@Data // doing the work of getters and setters
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ref_animals")
public class Animal {

    @Id
    @GeneratedValue
    @Column(name = "animal_id")
    private Integer animalId;

    @Column(name = "species", length = 50,unique = true, nullable = false)
    private String species;
}
