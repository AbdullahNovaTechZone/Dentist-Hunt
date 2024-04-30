package com.novatechzone.dentisthunt.domain.Doctor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findAllByNameContaining(String name);

    List<Doctor> findAllByOrderByViewsDesc();

    List<Doctor> findAllByOrderByRatingDesc();
}
