package com.novatechzone.dentisthunt.domain.record;

import com.novatechzone.dentisthunt.dto.ApplicationResponseDTO;
import com.novatechzone.dentisthunt.exception.ApplicationCustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RecordService {
    private final RecordRepository recordRepository;
    private final RecordImageRepository recordImageRepository;

    public ApplicationResponseDTO addRecord(RecordDTO recordDTO) {
        recordRepository.save(new Record.RecordBuilder().recordFor(recordDTO.getRecordFor()).recordType(recordDTO.getRecordType()).build());
        return new ApplicationResponseDTO(HttpStatus.CREATED, "RECORD_CREATED_SUCCESSFULLY", "Record created successfully!");
    }

    public ApplicationResponseDTO addRecordImage(MultipartFile[] file) {
        if (file.length > 0) {
            processFile(file);
            return new ApplicationResponseDTO(HttpStatus.CREATED, "RECORD_IMAGE_UPLOADED", "Record Image Uploaded");
        } else {
            throw new ApplicationCustomException(HttpStatus.NOT_FOUND, "IMAGE_NOT_FOUND", "Image Not Found");
        }
    }

    private void processFile(MultipartFile[] file) {
        for (MultipartFile multipartFile : file) {
            try {
                String projectRoot = System.getProperty("user.dir");
                String originalFilename = multipartFile.getOriginalFilename();
                if (originalFilename != null) {
                    String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
                    String newFileName = UUID.randomUUID() + fileExtension;
                    String imagePath = "/uploads/" + newFileName;
                    Path path = Paths.get(projectRoot + imagePath);
                    File saveFile = new File(String.valueOf(path));
                    multipartFile.transferTo(saveFile);
                    Optional<Record> optionalRecord = recordRepository.findById(1L);
                    if (optionalRecord.isPresent()) {
                        Record medicalRecord = optionalRecord.get();
                        recordImageRepository.save(RecordImage.builder().path(newFileName).recordId(medicalRecord.getId()).build());
                    } else {
                        throw new ApplicationCustomException(HttpStatus.NOT_FOUND, "RECORD_ID_NOT_FOUND", "Record Id Not Found");
                    }
                } else {
                    throw new ApplicationCustomException(HttpStatus.NOT_FOUND, "ORIGINAL_FILE_NAME_NOT_FOUND", "Original File Name Not Found");
                }
            } catch (IOException e) {
                throw new ApplicationCustomException(HttpStatus.BAD_REQUEST, "IO_EXCEPTION", e.getMessage());
            }
        }
    }
}