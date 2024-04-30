package com.novatechzone.dentisthunt.domain.DoctorAvailability;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/doctor-availability")
public class DoctorAvailabilityResource {
    private final DoctorAvailabilityService doctorAvailabilityService;

    @GetMapping("availability/{doctor-id}")
    public ResponseEntity<List<DoctorAvailabilityResponse>> getAllDoctorAvailability(@PathVariable("doctor-id") Long doctorId) {
        return ResponseEntity.ok(doctorAvailabilityService.getAllDoctorAvailability(doctorId));
    }
}
