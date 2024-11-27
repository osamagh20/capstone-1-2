package com.example.capstone1.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Merchant {
    @NotEmpty(message = "Enter the id")
    private String id;
    @NotEmpty(message = "Enter the name")
    @Size(min = 4,message = "Enter the name with 3 characters or above")
    private String name;
}
