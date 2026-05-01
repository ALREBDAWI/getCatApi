package com.getcat.api.repo;

import com.getcat.api.model.Booking;
import com.getcat.api.model.Post;
import com.getcat.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepo extends JpaRepository<Booking, Integer> {
    //spring jpa is analysing the name of this method and writes the query needed
    //find = select || by = where
    // optional is to protect from NullPointException
    Optional<Booking> findByUserAndPost(User user, Post post);
}
