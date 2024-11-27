package com.example.capstone1.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class User {
    @NotEmpty(message = "Enter the id")
    private String id;
    @NotEmpty(message = "Enter the name")
    @Size(min = 10,message = "Enter the name with 10 characters or above")
    private String username;
    @NotEmpty(message = "Enter the password")
    @Size(min = 6,message = "Enter password 6 characters or above ")
    @Pattern(regexp = "^[0-9a-zA-Z]+$",message = "Enter with digits and characters only.")
    private String password;
    @NotEmpty(message = "Enter the email")
    @Email
    private String email;
    @NotEmpty(message = "Enter the Role")
    @Pattern(regexp = "^(Admin|Customer)$",message = "Please chose Admin or Customer")
    private String role;
    @NotNull(message = "Enter the balance")
    @Positive
    private double balance;
    private int item;
    private int visiting;
    @NotEmpty(message = "Enter your home")
    private String homeAddress;
    private String subscribe;
    private ArrayList<Product> orederList = new ArrayList<>();



}
