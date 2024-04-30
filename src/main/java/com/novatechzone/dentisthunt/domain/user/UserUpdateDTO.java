package com.novatechzone.dentisthunt.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserUpdateDTO {
    @NotBlank(message = "Patient Name Required")
    private String name;
    @NotNull(message = "Date of Birth Required")
    private LocalDate dateOfBirth;
    @NotNull(message = "Gender Required")
    private GenderType gender;
    @NotBlank(message = "Mobile Number Required")
    @Size(min = 10, max = 10, message = "Mobile Number must be 10 digits")
    @Pattern(regexp = "^07[01245678]\\d{7}$")
    private String mobileNumber;
    @NotBlank(message = "Email Required")
    @Email(message = "Invalid Email")
    private String email;
}
