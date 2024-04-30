package com.novatechzone.dentisthunt.domain.booking;

import com.novatechzone.dentisthunt.domain.Doctor.Doctor;
import com.novatechzone.dentisthunt.domain.DoctorAvailability.TimeSlot;
import com.novatechzone.dentisthunt.domain.user.User;
import com.novatechzone.dentisthunt.model.BaseEntity;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Booking extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "slot_id")
    private Long slotId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "patient_name")
    private String patientName;
    @Column(name = "patient_contact_number")
    private String patientContactNumber;
    @ManyToOne
    @JoinColumn(name = "slot_id", referencedColumnName = "id", insertable = false, updatable = false)
    private TimeSlot timeSlot;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;
}
