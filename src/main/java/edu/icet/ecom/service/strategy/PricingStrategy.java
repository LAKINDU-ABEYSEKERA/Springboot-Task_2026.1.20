package edu.icet.ecom.service.strategy;

import edu.icet.ecom.model.dto.PricingResponse;
import edu.icet.ecom.model.entity.Event;

public interface PricingStrategy {
    PricingResponse calculate(Event event);
}