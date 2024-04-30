package com.novatechzone.dentisthunt.domain.user;

import com.novatechzone.dentisthunt.dto.ApplicationResponseDTO;
import com.novatechzone.dentisthunt.dto.RequestMetaDTO;
import com.novatechzone.dentisthunt.exception.ApplicationCustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final RequestMetaDTO requestMetaDTO;

    public ApplicationResponseDTO updateUser(UserUpdateDTO userUpdateDTO) {
        User user = userRepository.findById(requestMetaDTO.getId())
                .orElseThrow(() -> new ApplicationCustomException(HttpStatus.BAD_REQUEST, "USER_NOT_FOUND", "User Not Found"));

        user.setName(userUpdateDTO.getName());
        user.setDateOfBirth(userUpdateDTO.getDateOfBirth());
        user.setGender(userUpdateDTO.getGender());
        user.setMobileNumber(userUpdateDTO.getMobileNumber());

        userRepository.findByEmail(userUpdateDTO.getEmail()).ifPresent(existingUser -> {
            if (!user.getEmail().equals(userUpdateDTO.getEmail())) {
                throw new ApplicationCustomException(HttpStatus.BAD_REQUEST, "EMAIL_ALREADY_EXISTS", "Email Already Exists");
            }
        });

        user.setEmail(userUpdateDTO.getEmail());
        userRepository.save(user);

        return new ApplicationResponseDTO(HttpStatus.OK, "USER_UPDATED_SUCCESSFULLY!", "User Updated Successfully!");
    }

}