package com.novatechzone.dentisthunt.domain.booking;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BookingDTO {
    @NotBlank
    private String patientName;
    @NotBlank
    @Pattern(regexp = "^07[01245678]\\d{7}$")
    private String patientContactNumber;
}
