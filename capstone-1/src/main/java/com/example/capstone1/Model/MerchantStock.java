package com.example.capstone1.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {
    @NotEmpty(message = "Enter the Merchant Stock id")
    private String id;
    private String merchantId;
    @NotEmpty(message = "Enter the Product Stock id")
    private String productId;
    @NotNull(message = "Enter the stock")
    @Positive
    @Min(10)
    private int stock;
}
