package com.novatechzone.dentisthunt.domain.DoctorAvailability;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorAvailabilityRepository extends JpaRepository<DoctorAvailability, Long> {
    Optional<List<DoctorAvailability>> findByDoctorId(Long doctorId);
}
