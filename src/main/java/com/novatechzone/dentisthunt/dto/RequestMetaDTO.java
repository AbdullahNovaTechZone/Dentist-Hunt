package com.novatechzone.dentisthunt.dto;

import com.novatechzone.dentisthunt.domain.user.GenderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestMetaDTO {
    private Long id;
    private String name;
    private String email;
    private LocalDate dateOfBirth;
    private String mobileNumber;
    private GenderType gender;
}
