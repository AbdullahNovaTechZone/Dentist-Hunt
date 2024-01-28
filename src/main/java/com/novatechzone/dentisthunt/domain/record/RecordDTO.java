package com.novatechzone.dentisthunt.domain.record;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RecordDTO {
    @NotBlank(message = "Record for is required")
    private String recordFor;
    @NotNull(message = "Record type is required")
    private RecordType recordType;
}
