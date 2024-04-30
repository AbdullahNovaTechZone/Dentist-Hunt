package com.novatechzone.dentisthunt.domain.user;

import com.novatechzone.dentisthunt.dto.ApplicationResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserResource {
    private final UserService userService;

    @PutMapping("/update")
    public ResponseEntity<ApplicationResponseDTO> updateUser(@Valid @RequestBody UserUpdateDTO userUpdateDTO){
        return ResponseEntity.ok(userService.updateUser(userUpdateDTO));
    }
}
