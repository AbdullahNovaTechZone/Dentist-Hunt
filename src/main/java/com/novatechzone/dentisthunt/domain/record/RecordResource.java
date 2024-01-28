package com.novatechzone.dentisthunt.domain.record;

import com.novatechzone.dentisthunt.dto.ApplicationResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequiredArgsConstructor
@RequestMapping("/record")
public class RecordResource {
    private final RecordService recordService;

    @PostMapping("/add-record")
    public ResponseEntity<ApplicationResponseDTO> addRecord(@Valid @RequestBody RecordDTO recordDTO) {
        ApplicationResponseDTO applicationResponseDTO = recordService.addRecord(recordDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(applicationResponseDTO);
    }

    @PostMapping("/add-record-image")
    public ResponseEntity<ApplicationResponseDTO> addRecordImage(@RequestBody MultipartFile[] file) {
        ApplicationResponseDTO applicationResponseDTO = recordService.addRecordImage(file);
        return ResponseEntity.status(HttpStatus.CREATED).body(applicationResponseDTO);
    }
}
