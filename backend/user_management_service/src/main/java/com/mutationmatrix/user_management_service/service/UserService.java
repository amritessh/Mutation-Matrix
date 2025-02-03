package com.mutationmatrix.user_management_service.service;

import com.mutationmatrix.user_management_service.dto.UserRegistrationDTO;
import com.mutationmatrix.user_management_service.dto.UserResponseDTO;

public interface UserService {
    UserResponseDTO registerUser(UserRegistrationDTO userDTO);

    UserResponseDTO getUserByID(Long id);
}
