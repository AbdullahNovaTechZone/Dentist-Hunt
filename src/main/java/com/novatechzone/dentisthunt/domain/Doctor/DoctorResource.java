package com.novatechzone.dentisthunt.domain.Doctor;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/doctor")
public class DoctorResource {
    private final DoctorService doctorService;

    @GetMapping("/all-doctors")
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @GetMapping("/all-doctors-by-name/{name}")
    public ResponseEntity<List<Doctor>> getAllDoctorsByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(doctorService.getAllDoctorsByName(name));
    }

    @GetMapping("/all-popular-doctors")
    public ResponseEntity<List<Doctor>> getAllPopularDoctors() {
        return ResponseEntity.ok(doctorService.getAllPopularDoctors());
    }

    @GetMapping("/all-feature-doctors")
    public ResponseEntity<List<Doctor>> getAllFeatureDoctors() {
        return ResponseEntity.ok(doctorService.getAllFeatureDoctors());
    }
}
