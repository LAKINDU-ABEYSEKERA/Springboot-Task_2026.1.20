package edu.icet.ecom.service.strategy;

import edu.icet.ecom.model.dto.PricingResponse;
import edu.icet.ecom.model.entity.Event;

public class PlatinumPricingStrategy implements PricingStrategy {
    @Override
    public PricingResponse calculate(Event event) {
        // Rule: Base Price + Priority Access Flag [cite: 36]
        return new PricingResponse(event.getBasePrice(), "Platinum Priority Access Granted");
    }
}