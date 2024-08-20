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
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "doctorNote can't be empty")
    @Size(min = 10, max = 50)
    @Column(columnDefinition = "varchar(50) not null")
    private String doctorNote;

    @NotNull(message = "userID can't be empty")
    @Positive(message = "userID should be positive number")
    @Column(columnDefinition = "int not null")
    private int userID;

    @NotNull(message = "doctorID can't be empty")
    @Positive(message = "doctorID should be positive number")
    @Column(columnDefinition = "int not null")
    private int doctorID;

    @NotNull(message = "medicineID can't be empty")
    @Positive(message = "medicineID should be positive number")
    @Column(columnDefinition = "int not null")
    private int medicineID;

    @AssertFalse
    private boolean isRedeemed;
}
