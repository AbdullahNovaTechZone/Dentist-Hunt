package com.novatechzone.dentisthunt.domain.record;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordImageRepository extends JpaRepository<RecordImage, Long> {
}
