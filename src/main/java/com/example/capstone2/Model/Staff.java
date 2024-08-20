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
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name can't be empty")
    @Size(min = 3, max = 10)
    @Column(columnDefinition = "varchar(10) not null")
    private String name;

    @NotNull(message = "salary can't be empty")
    @Positive(message = "salary must be a positive value")
    @Column(columnDefinition = "double not null")
    private double salary;

    @Pattern(regexp = "(receptionist|Accountant|HR)")
    private String role;


    @Positive(message = "budget must be a positive value")
    @Column(columnDefinition = "double")
    private double budget;
}
