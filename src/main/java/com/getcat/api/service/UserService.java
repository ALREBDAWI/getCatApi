package com.getcat.api.service;

import com.getcat.api.dto.AddressResponseDTO;
import com.getcat.api.dto.UserRequestDTO;
import com.getcat.api.dto.UserResponseDTO;
import com.getcat.api.model.Address;
import com.getcat.api.model.User;
import com.getcat.api.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    // a method to transform the user object into user dto
    private UserResponseDTO mapToResponseDTO(User user){
        AddressResponseDTO addressResponseDTO = null;
        //if the address is null we fill the address dto with null values to avoid NullPointerException
        if(user.getAddress() != null){
            addressResponseDTO = AddressResponseDTO.builder()
                            .addressId(user.getAddress().getAddressId())
                    .buildingNum(user.getAddress().getBuildingNum())
                    .street(user.getAddress().getStreet())
                    .city(user.getAddress().getCity())
                    .postalCode(user.getAddress().getPostalCode())
                    .build();
        }
        return UserResponseDTO.builder()
                .userId(user.getUserId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .phone(user.getPhone())
                .userPhoto(user.getUserPhoto())
                .address(addressResponseDTO)
                .build();
    }

    // we need to think og a logic to get users who are not soft deleted
    public List<UserResponseDTO> getAllUsers(){
        return userRepo.findAll().stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    public UserResponseDTO getUserById(Integer id){
         User user = userRepo.findById(id).orElseThrow(()->new RuntimeException("user not found with id "+id));
         return mapToResponseDTO(user);
    }

    public UserResponseDTO createUser(UserRequestDTO request){
        // password hash logic to be added here
        User user = new User();
                user.setFirstname(request.getFirstname());
                user.setLastname(request.getLastname());
                user.setEmail(request.getEmail());
                user.setPhone(request.getPhone());
                user.setUserPhoto(request.getUserPhoto());

                // we also check if address does not exist
                if(request.getAddress() != null){
                    Address address = new Address();
                    address.setBuildingNum(request.getAddress().getBuildingNum());
                    address.setStreet(request.getAddress().getStreet());
                    address.setCity(request.getAddress().getCity());
                    address.setPostalCode(request.getAddress().getPostalCode());
                    user.setAddress(address);
                }

        return mapToResponseDTO(userRepo.save(user));
    }

    public UserResponseDTO updateUser(Integer id,UserRequestDTO request){
        User existingUser = userRepo.findById(id)
                .orElseThrow(()->new RuntimeException("user not found with id "+id));
        // by checking if not null we protect data from being lost
        // we can partially update (patch)
        if(request.getFirstname() != null){
            existingUser.setFirstname(request.getFirstname());
        }
        if(request.getLastname() != null){
            existingUser.setLastname(request.getLastname());
        }
        if(request.getEmail() != null){
            existingUser.setEmail(request.getEmail());
        }
        if(request.getPhone() != null){
            existingUser.setPhone(request.getPhone());
        }
        if(request.getUserPhoto() != null){
            existingUser.setUserPhoto(request.getUserPhoto());
        }

        //update address too
        if(request.getAddress() != null){
            Address address = existingUser.getAddress() !=null ? existingUser.getAddress() : new Address();
            if(request.getAddress().getBuildingNum() != null) address.setBuildingNum(request.getAddress().getBuildingNum());
            if(request.getAddress().getStreet() != null) address.setStreet(request.getAddress().getStreet());
            if(request.getAddress().getCity() != null) address.setCity(request.getAddress().getCity());
            if(request.getAddress().getPostalCode() != null) address.setPostalCode(request.getAddress().getPostalCode());

            existingUser.setAddress(address);
        }


        return mapToResponseDTO(userRepo.save(existingUser));
    }

    private User findUserById(Integer id){
        return userRepo.findById(id).orElseThrow(()->new RuntimeException("user not found with id "+id));
    }

    public void softDeleteUser(Integer id){
            // to prevent unnecessary operations
        User user = findUserById(id);
        if (Boolean.TRUE.equals(user.getIsDeleted())) {
            throw new RuntimeException("User already deleted");
        }

        user.setIsDeleted(true);
        userRepo.save(user);
    }

    public void hardDeleteUser(Integer id){
        User User = findUserById(id);
        userRepo.delete(User);
    }
}
