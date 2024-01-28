package com.novatechzone.dentisthunt.domain.DoctorAvailability;

import com.novatechzone.dentisthunt.domain.Doctor.Doctor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "doctor_availability")
public class DoctorAvailability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "date", nullable = false)
    private LocalDate date;
    @Column(name = "time_slot_from", nullable = false)
    private LocalTime timeSlotFrom;
    @Column(name = "time_slot_to", nullable = false)
    private LocalTime timeSlotTo;
    @Column(name = "doctor_id", nullable = false)
    private Long doctorId;
    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Doctor doctor;
}
