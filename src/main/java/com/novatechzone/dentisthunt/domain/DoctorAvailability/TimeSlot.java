package com.novatechzone.dentisthunt.domain.DoctorAvailability;

import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class TimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalTime slot;
    @Column(name = "doctor_availability_id")
    private Long doctorAvailabilityId;
    @ManyToOne
    @JoinColumn(name = "doctor_availability_id", referencedColumnName = "id", insertable = false, updatable = false)
    private DoctorAvailability doctorAvailability;
}
