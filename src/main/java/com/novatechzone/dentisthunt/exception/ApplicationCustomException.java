package com.novatechzone.dentisthunt.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ApplicationCustomException extends RuntimeException {
    private final HttpStatus status;
    private final String code;
    private final String message;
}