package com.getcat.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private Integer userId;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String userPhoto;
    private AddressResponseDTO address;
}
