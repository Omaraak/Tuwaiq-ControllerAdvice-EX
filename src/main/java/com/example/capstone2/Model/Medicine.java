package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name can't be empty")
    @Size(min = 3, max = 10)
    @Column(columnDefinition = "varchar(10) not null")
    private String name;

    @NotNull(message = "price can't be empty")
    @Positive(message = "price should be positive number")
    @Column(columnDefinition = "int not null")
    private int price;

    @NotNull(message = "quantity can't be empty")
    @Positive(message = "quantity should be positive number")
    @Column(columnDefinition = "int not null")
    private int quantity;

    @NotNull(message = "price can't be empty")
    @Positive(message = "price should be positive number")
    @Column(columnDefinition = "int not null")
    private int pharmacyID;
}
