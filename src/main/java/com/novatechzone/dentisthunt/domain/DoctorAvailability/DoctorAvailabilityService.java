package com.novatechzone.dentisthunt.domain.DoctorAvailability;

import com.novatechzone.dentisthunt.domain.Doctor.DoctorRepository;
import com.novatechzone.dentisthunt.exception.ApplicationCustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class DoctorAvailabilityService {
    private final DoctorAvailabilityRepository doctorAvailabilityRepository;
    private final DoctorRepository doctorRepository;
    private final TimeSlotRepository timeSlotRepository;

    public List<DoctorAvailabilityResponse> getAllDoctorAvailability(Long doctorId) {
        if (doctorRepository.findById(doctorId).isEmpty()) {
            throw new ApplicationCustomException(HttpStatus.NOT_FOUND, "INVALID_DOCTOR_ID", "Invalid Doctor Id");
        } else {
            Optional<List<DoctorAvailability>> optionalDoctorAvailabilityList = doctorAvailabilityRepository.findByDoctorId(doctorId);
            if (optionalDoctorAvailabilityList.isEmpty()) {
                throw new ApplicationCustomException(HttpStatus.NOT_FOUND, "NO_AVAILABILITY", "No Availability");
            } else {
                List<DoctorAvailability> doctorAvailabilityList = optionalDoctorAvailabilityList.get();
                List<DoctorAvailabilityResponse> doctorAvailabilityResponses = new ArrayList<>();

                doctorAvailabilityList.forEach(availability -> {
                    if (availability.getDate().isEqual(LocalDate.now()) || availability.getDate().isAfter(LocalDate.now())) {
                        List<LocalTime> slots = new ArrayList<>();
                        timeSlotRepository.findAllByDoctorAvailabilityId(availability.getId()).forEach(timeSlot -> slots.add(timeSlot.getSlot()));

                        DoctorAvailabilityResponse response = new DoctorAvailabilityResponse(availability, slots);
                        doctorAvailabilityResponses.add(response);
                    }
                });

                return doctorAvailabilityResponses;
            }
        }
    }
}
