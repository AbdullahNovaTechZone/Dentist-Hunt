package com.novatechzone.dentisthunt.domain.user;

import com.novatechzone.dentisthunt.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Column(name = "password", nullable = false, length = 20)
    private String password;
    @Column(name = "date_of_birth", length = 2)
    private LocalDate dateOfBirth;
    @Column(name = "mobile_number", length = 10)
    private String mobileNumber;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "gender")
    private GenderType gender;
    @Column(name = "status")
    private Boolean status;
}
