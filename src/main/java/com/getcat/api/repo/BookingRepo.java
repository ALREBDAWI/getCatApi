package com.getcat.api.repo;

import com.getcat.api.model.Booking;
import com.getcat.api.model.Post;
import com.getcat.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer> {
    Optional<Object> findByUserAndPost(User user, Post post);
}
