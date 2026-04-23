package com.getcat.api.service;

import com.getcat.api.model.User;
import com.getcat.api.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    // we need to think og a logic to get users who are not soft deleted
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public User getUserById(Integer id){
        return userRepo.findById(id).orElseThrow(()->new RuntimeException("user not found with id "+id));
    }

    public User createUser(User user){
        // password hash logic to be added here
        return userRepo.save(user);
    }

    public User updateUser(Integer id,User user){
        User existingUser = getUserById(id);
        // by checking if not null we protect data from being lost
        // we can partially update (patch)
        if(existingUser.getFirstname() != null){
            existingUser.setFirstname(user.getFirstname());
        }
        if(existingUser.getLastname() != null){
            existingUser.setLastname(user.getLastname());
        }
        if(existingUser.getEmail() != null){
            existingUser.setEmail(user.getEmail());
        }
        if(existingUser.getPhone() != null){
            existingUser.setPhone(user.getPhone());
        }
        if(user.getAddress() != null){
            existingUser.setAddress(user.getAddress());
        }
        if(existingUser.getUserPhoto() != null){
            existingUser.setUserPhoto(user.getUserPhoto());
        }

        return userRepo.save(existingUser);
    }

    public void softDeleteUser(Integer id){
        User user = getUserById(id);

        // to prevent unnecessary operations
        if (Boolean.TRUE.equals(user.getIsDeleted())) {
            throw new RuntimeException("User already deleted");
        }

        user.setIsDeleted(true);
        userRepo.save(user);
    }

    public void hardDeleteUser(Integer id){
        User User = getUserById(id);
        userRepo.delete(User);
    }
}
