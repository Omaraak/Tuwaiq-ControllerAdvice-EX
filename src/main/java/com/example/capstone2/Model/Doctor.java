package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name can't be empty")
    @Size(min = 3, max = 10)
    @Column(columnDefinition = "varchar(10) not null")
    private String name;

    @NotNull(message = "position can't be empty")
    @Pattern(regexp = "(intern|fellow|residency)")
    @Column(columnDefinition = "varchar(9) not null")
    private String position;

    @Pattern(regexp = "(General|General Surgery|Dermatology|Neurosurgery)")
    @Column(columnDefinition = "varchar(11) not null")
    private String speciality;

    @NotNull(message = "phoneNumber can't be empty")
    @Pattern(regexp = "^(05)(\\d){8}", message = "phoneNumber has to be a valid phone number")
    @Column(columnDefinition = "varchar(10) not null")
    private String phone;

    @NotNull(message = "salary can't be empty")
    @Positive(message = "salary must be a positive value")
    @Column(columnDefinition = "double not null")
    private double salary;

    @NotNull(message = "age can't be empty")
    @Min(value = 21, message = "age can't be less the 21")
    @Column(columnDefinition = "int not null")
    private int age;

    @NotNull(message = "warning can't be empty")
    @PositiveOrZero(message = "warning must be a positive value")
    @Column(columnDefinition = "int not null")
    private int warning;
}
