package com.novatechzone.dentisthunt.domain.security;

import com.novatechzone.dentisthunt.dto.ApplicationResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthResource {
    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<ApplicationResponseDTO> signUp(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
        ApplicationResponseDTO applicationResponseDTO = authService.signUp(userRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(applicationResponseDTO);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<Map<String, Object>> signIn(@Valid @RequestBody UserLogInDTO userLogInDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.signIn(userLogInDTO));
    }
}
