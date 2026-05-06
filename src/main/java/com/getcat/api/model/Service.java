package com.getcat.api.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ref_services")
public class Service {

    @Id
    @GeneratedValue
    @Column(name = "service_id")
    private String serviceId;

    @Column(name = "service_type", length = 50, unique = true, nullable = false)
    private String serviceType;
}
