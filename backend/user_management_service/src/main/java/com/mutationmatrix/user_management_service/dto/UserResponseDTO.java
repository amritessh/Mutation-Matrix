package com.mutationmatrix.user_management_service.dto;

import java.util.Set;

import com.mutationmatrix.user_management_service.model.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDTO {
    private long id;
    private String username;
    private String email;
    private Set<Role> roles;

}
