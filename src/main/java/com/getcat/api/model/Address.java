package com.getcat.api.model;

import jakarta.persistence.*;
import lombok.*;

@Data // doing the work of getters and setters
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue
    @Column(name = "address_id")
    private Integer addressId;

    @Column(name = "building_num", length = 10)
    private String buildingNum;

    @Column(name = "street", length = 150)
    private String street;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "postal_code", length = 20)
    private String postalCode;
}