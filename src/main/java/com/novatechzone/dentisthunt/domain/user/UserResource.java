package com.novatechzone.dentisthunt.domain.user;

import com.novatechzone.dentisthunt.dto.ApplicationResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserResource {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApplicationResponseDTO> updateUser(@Valid @RequestBody UserUpdateDTO userUpdateDTO){
        return ResponseEntity.ok(userService.updateUser(userUpdateDTO));
    }
}
