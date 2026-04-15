package com.getcat.api.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private  Integer roleId;

    @Column(name = "role_label", length = 20, unique = true, nullable = false)
    private String roleLabel;
}
