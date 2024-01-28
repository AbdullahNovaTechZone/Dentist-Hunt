package com.novatechzone.dentisthunt.domain.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLogInDTO {
    @NotBlank(message = "Email not Found")
    @Email
    private String email;
    @NotBlank(message = "Password not Found")
    private String password;
}
