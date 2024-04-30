package com.novatechzone.dentisthunt.domain.security;

import com.novatechzone.dentisthunt.domain.user.User;
import com.novatechzone.dentisthunt.domain.user.UserRepository;
import com.novatechzone.dentisthunt.dto.ApplicationResponseDTO;
import com.novatechzone.dentisthunt.exception.ApplicationCustomException;
import com.novatechzone.dentisthunt.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

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

    public Map<String, Object> signIn(UserLogInDTO userLogInDTO) {
        if (userRepository.findByEmail(userLogInDTO.getEmail()).isEmpty()) {
            throw new ApplicationCustomException(HttpStatus.NOT_FOUND, "EMAIL_NOT_FOUND", "Email Not Found");
        } else {
            Optional<User> optionalUser = userRepository.findByEmailAndPassword(userLogInDTO.getEmail(), userLogInDTO.getPassword());
            if (optionalUser.isEmpty()) {
                throw new ApplicationCustomException(HttpStatus.BAD_REQUEST, "INVALID_CREDENTIALS", "Invalid Credentials");
            } else {
                User user = optionalUser.get();
                String accessToken = jwtUtils.generateAccessToken(user);
                Map<String, Object> response = new HashMap<>();
                response.put("accessToken", accessToken);
                response.put("status", HttpStatus.OK);
                response.put("code", "USER_SIGNED_IN_SUCCESSFULLY");
                response.put("message", "User Signed In Successfully");

                return response;
            }
        }
    }
}
