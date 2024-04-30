package com.novatechzone.dentisthunt.domain.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRegisterDTO {
    @NotBlank(message = "Name not Found")
    private String name;
    @NotBlank(message = "Email not Found")
    @Email
    private String email;
    @NotBlank(message = "Password not Found")
    private String password;
}
