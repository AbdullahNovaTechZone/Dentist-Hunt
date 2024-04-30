package com.novatechzone.dentisthunt.domain.Favourite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavouriteRepository extends JpaRepository<Favourite, Long> {
    Optional<Favourite> findByUserIdAndDoctorId(Long id, Long doctorId);

    List<Favourite> findAllByUserId(Long userId);
}
