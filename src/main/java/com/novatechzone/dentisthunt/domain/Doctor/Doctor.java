package com.novatechzone.dentisthunt.domain.Doctor;

import com.novatechzone.dentisthunt.domain.Category.Category;
import com.novatechzone.dentisthunt.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "doctors")
public class Doctor extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Column(name = "category_id", nullable = false)
    private Long categoryId;
    @Column(name = "rating")
    private Double rating;
    @Column(name = "views")
    private Long views;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Category category;
}