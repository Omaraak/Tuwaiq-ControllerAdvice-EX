package com.example.capstone2.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "status can't be empty")
    @Pattern(regexp = "(conformed|in progress|canceled|done)")
    @Column(nullable = false)
    private String status;

    @NotEmpty(message = "type can't be empty")
    @Pattern(regexp = "(online|on premise)")
    @Column(nullable = false)
    private String type;

    @NotNull(message = "appointmentDate can't be empty")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate appointmentDate;

    @NotEmpty(message = "startTime can't be empty")
    @Pattern(regexp = "(\\d){2}(:)(\\d){2}", message = "startTime should be like this 10:01")
    @Column(columnDefinition = "varchar(5) not null")
    private String startTime;

    @NotEmpty(message = "endTime can't be empty")
    @Pattern(regexp = "(\\d){2}(:)(\\d){2}", message = "endTime should be like this 10:01")
    @Column(columnDefinition = "varchar(5) not null")
    private String endTime;

    @NotNull(message = "price can't be empty")
    @Positive(message = "price should be positive number")
    @Column(columnDefinition = "int not null")
    private int price;

    @PositiveOrZero(message = "userID should be positive number or zero")
    @Column(columnDefinition = "int")
    private int userID;

    @NotNull(message = "doctorID can't be empty")
    @Positive(message = "doctorID should be positive number")
    @Column(columnDefinition = "int not null")
    private int doctorID;
}
