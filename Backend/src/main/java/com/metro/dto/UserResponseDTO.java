package com.metro.dto;


import com.metro.model.Role;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private String id;
    private String name;
    private String email;
    private Double walletBalance;
    private Role role;
}
