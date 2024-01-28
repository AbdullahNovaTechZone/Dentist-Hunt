package com.novatechzone.dentisthunt.domain.record;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "record_images")
public class RecordImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "path", nullable = false)
    private String path;
    @Column(name = "record_id", nullable = false)
    private Long recordId;
    @ManyToOne
    @JoinColumn(name = "record_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Record medicalRecord;
}