package com.getcat.api.model;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Integer postId;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "animal_photo", length = 500)
    private String animalPhoto;

    @Column(name = "start_date") // animal setting suggested start date
    private LocalDate startDate;

    @Column(name = "end_date") // animal setting suggested end date
    private LocalDate endDate;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // post creation time and date

    @Column(name = "is_deleted")
    private Boolean isDeleted = false; // false by default

    // FK
    @ManyToOne(fetch = FetchType.LAZY) // with LAZY fetch we have faster query and less resource consumption
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    private Service service;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id")
    private Animal animal;
}

