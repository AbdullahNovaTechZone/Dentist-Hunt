package com.novatechzone.dentisthunt.domain.Doctor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public List<Doctor> getAllDoctorsByName(String name) {
        return doctorRepository.findAllByNameContaining(name);
    }

    public List<Doctor> getAllPopularDoctors() {
        return doctorRepository.findAllByOrderByViewsDesc();
    }

    public List<Doctor> getAllFeatureDoctors() {
        return doctorRepository.findAllByOrderByRatingDesc();
    }
}