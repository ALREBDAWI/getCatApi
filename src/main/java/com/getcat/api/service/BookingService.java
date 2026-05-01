package com.getcat.api.service;

import com.getcat.api.model.Booking;
import com.getcat.api.model.BookingStatus;
import com.getcat.api.model.Post;
import com.getcat.api.model.User;
import com.getcat.api.repo.BookingRepo;
import com.getcat.api.repo.PostRepo;
import com.getcat.api.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {
    final BookingRepo bookingRepo;
    final UserRepo userRepo;
    final PostRepo postRepo;

    public List<Booking> getAllBookings(){
        return bookingRepo.findAll();
    }

    public Booking getBookingById(Integer bookingId){
        return bookingRepo.findById(bookingId).orElseThrow(()-> new RuntimeException("bookingId not found with id "+bookingId));
    }

    public Booking createBooking(Integer userId, Integer postId){
        User user = userRepo.findById(userId).orElseThrow(()-> new RuntimeException("user not found with id "+userId));
        Post post = postRepo.findById(postId).orElseThrow(()-> new RuntimeException("post not found with id "+postId));

        // we check that this booking does not exist already
        bookingRepo.findByUserAndPost(user, post).ifPresent(booking -> {
           throw  new RuntimeException("booking already exists");
        });

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setPost(post);
        booking.setBookingStatus(BookingStatus.pending);

        return bookingRepo.save(booking);

    }
}
