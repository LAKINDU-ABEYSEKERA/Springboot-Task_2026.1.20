package edu.icet.ecom.service.strategy;

import edu.icet.ecom.model.dto.PricingResponse;
import edu.icet.ecom.model.entity.Event;

public class RegularPricingStrategy implements PricingStrategy {
    @Override
    public PricingResponse calculate(Event event) {
        return new PricingResponse(event.getBasePrice(), "Standard Rate");
    }
}