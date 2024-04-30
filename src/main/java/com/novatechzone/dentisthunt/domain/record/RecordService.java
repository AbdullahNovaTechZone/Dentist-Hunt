package com.novatechzone.dentisthunt.domain.record;

import com.novatechzone.dentisthunt.dto.ApplicationResponseDTO;
import com.novatechzone.dentisthunt.dto.RequestMetaDTO;
import com.novatechzone.dentisthunt.exception.ApplicationCustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RecordService {
    private final RecordRepository recordRepository;
    private final RecordImageRepository recordImageRepository;
    private final RequestMetaDTO requestMetaDTO;

    public Map<String, Object> addRecord(RecordDTO recordDTO) {
        Record recordDetails = recordRepository.save(new Record.RecordBuilder().recordFor(recordDTO.getRecordFor()).userId(requestMetaDTO.getId()).recordType(recordDTO.getRecordType()).build());
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.CREATED);
        response.put("code", "RECORD_CREATED_SUCCESSFULLY");
        response.put("message", "Record created successfully!");
        response.put("data", recordDetails);
        return response;
    }

    public ApplicationResponseDTO addRecordImage(Long recordId, MultipartFile[] file) {
        if (recordRepository.findById(recordId).isEmpty()) {
            throw new ApplicationCustomException(HttpStatus.NOT_FOUND, "RECORD_ID_NOT_FOUND", "Record Id Not Found");
        } else if (file.length > 0) {
            processFile(recordId, file);
            return new ApplicationResponseDTO(HttpStatus.CREATED, "RECORD_IMAGE_UPLOADED", "Record Image Uploaded");
        } else {
            throw new ApplicationCustomException(HttpStatus.NOT_FOUND, "IMAGE_NOT_FOUND", "Image Not Found");
        }
    }

    private void processFile(Long recordId, MultipartFile[] file) {
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
                    recordImageRepository.save(RecordImage.builder().path(newFileName).recordId(recordId).build());
                } else {
                    throw new ApplicationCustomException(HttpStatus.NOT_FOUND, "ORIGINAL_FILE_NAME_NOT_FOUND", "Original File Name Not Found");
                }
            } catch (IOException e) {
                throw new ApplicationCustomException(HttpStatus.BAD_REQUEST, "IO_EXCEPTION", e.getMessage());
            }
        }
    }
}