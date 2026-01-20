package edu.icet.ecom.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PricingResponse {
    private BigDecimal finalPrice;
    private String breakdown; // e.g., "VIP Discount Applied"
}