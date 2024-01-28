package com.novatechzone.dentisthunt.domain.security;

import com.novatechzone.dentisthunt.domain.user.User;
import com.novatechzone.dentisthunt.domain.user.UserRepository;
import com.novatechzone.dentisthunt.dto.ApplicationResponseDTO;
import com.novatechzone.dentisthunt.exception.ApplicationCustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    public ApplicationResponseDTO signUp(UserRegisterDTO userRegisterDTO) {
        if (userRepository.findByEmail(userRegisterDTO.getEmail()).isPresent()) {
            throw new ApplicationCustomException(HttpStatus.BAD_REQUEST, "EMAIL_ALREADY_EXIST", "Email Already Exist");
        } else {
            User user = new User();
            user.setName(userRegisterDTO.getName());
            user.setEmail(userRegisterDTO.getEmail());
            user.setPassword(userRegisterDTO.getPassword());
            user.setStatus(true);
            userRepository.save(user);

            return new ApplicationResponseDTO(HttpStatus.CREATED, "USER_REGISTERED_SUCCESSFULLY", "User Registered Successfully");
        }
    }

    public ApplicationResponseDTO signIn(UserLogInDTO userLogInDTO) {
        if (userRepository.findByEmail(userLogInDTO.getEmail()).isEmpty()) {
            throw new ApplicationCustomException(HttpStatus.NOT_FOUND, "EMAIL_NOT_FOUND", "Email Not Found");
        } else if (userRepository.findByEmailAndPassword(userLogInDTO.getEmail(), userLogInDTO.getPassword()).isEmpty()) {
            throw new ApplicationCustomException(HttpStatus.BAD_REQUEST, "INVALID_CREDENTIALS", "Invalid Credentials");
        } else {
            return new ApplicationResponseDTO(HttpStatus.OK, "USER_SIGNED_IN_SUCCESSFULLY", "User Signed In Successfully");
        }
    }
}
