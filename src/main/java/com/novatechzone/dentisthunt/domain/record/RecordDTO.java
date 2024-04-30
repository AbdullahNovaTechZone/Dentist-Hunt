package com.novatechzone.dentisthunt.domain.record;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RecordDTO {
    @NotBlank(message = "Record for is required")
    private String recordFor;
    @NotNull(message = "Record type is required")
    private RecordType recordType;
}
