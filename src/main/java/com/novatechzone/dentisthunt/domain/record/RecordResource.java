package com.novatechzone.dentisthunt.domain.record;

import com.novatechzone.dentisthunt.dto.ApplicationResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/record")
public class RecordResource {
    private final RecordService recordService;

    @PostMapping("/add-record")
    public ResponseEntity<Map<String, Object>> addRecord(@Valid @RequestBody RecordDTO recordDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(recordService.addRecord(recordDTO));
    }

    @PostMapping("/add-record-image/{record-id}")
    public ResponseEntity<ApplicationResponseDTO> addRecordImage(@PathVariable("record-id") Long recordId, @RequestBody MultipartFile[] file) {
        return ResponseEntity.status(HttpStatus.CREATED).body(recordService.addRecordImage(recordId, file));
    }
}
