package com.novatechzone.dentisthunt.domain.record;

import com.novatechzone.dentisthunt.model.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Record extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "record_for", nullable = false)
    private String recordFor;
    @Column(name = "record_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private RecordType recordType;
}