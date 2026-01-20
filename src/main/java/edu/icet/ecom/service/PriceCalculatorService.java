package edu.icet.ecom.service;

import edu.icet.ecom.model.dto.PricingResponse;

public interface PriceCalculatorService {
    PricingResponse calculatePrice(Long userId, Long eventId);
}