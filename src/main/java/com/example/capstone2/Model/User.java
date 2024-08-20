package com.example.capstone2.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name can't be empty")
    @Size(min = 3, max = 10)
    @Column(columnDefinition = "varchar(10) not null")
    private String name;

    @NotNull(message = "age can't be empty")
    @Min(value = 21, message = "age can't be less the 21")
    @Column(columnDefinition = "int not null")
    private int age;

    @NotNull(message = "phoneNumber can't be empty")
    @Pattern(regexp = "^(05)(\\d){8}", message = "phoneNumber has to be a valid phone number")
    private String phoneNumber;


    @Email(message = "email has to be a valid email format")
    @Column(unique = true, nullable = false)
    private String email;

    @NotNull(message = "balance can't be empty")
    @Positive(message = "balance has to be a positive number")
    @Column(nullable = false)
    private int balance;
}
