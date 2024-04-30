package com.novatechzone.dentisthunt.domain.DoctorAvailability;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
    List<TimeSlot> findAllByDoctorAvailabilityId(Long doctorAvailabilityId);
}