package com.example.capstone1.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    @NotEmpty(message = "Enter the id")
    private String id;
    @NotEmpty(message = "Enter the name")
    @Size(min = 4,message = "Enter the name with 3 characters or above")
    private String name;
    @NotNull(message = "Enter the price")
    @Positive
    private double price;
    @NotEmpty(message = "Enter the category id")
    private String categoryId;
    private String comments;
}
