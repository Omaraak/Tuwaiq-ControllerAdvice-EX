package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pharmacy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name can't be empty")
    @Size(min = 3, max = 10)
    @Column(columnDefinition = "varchar(10) not null")
    private String name;

    @NotEmpty(message = "name can't be empty")
    @Size(min = 10, max = 20)
    @Column(columnDefinition = "varchar(10) not null")
    private String address;

    @NotNull(message = "phoneNumber can't be empty")
    @Pattern(regexp = "^(05)(\\d){8}", message = "phoneNumber has to be a valid phone number")
    @Column(columnDefinition = "varchar(10) not null")
    private String phone;
}
