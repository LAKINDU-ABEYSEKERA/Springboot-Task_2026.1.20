package edu.icet.ecom.service.strategy;

import edu.icet.ecom.model.dto.PricingResponse;
import edu.icet.ecom.model.entity.Event;
import java.math.BigDecimal;

public class VipPricingStrategy implements PricingStrategy {
    @Override
    public PricingResponse calculate(Event event) {
        // Rule: VIP gets 10% off UNLESS High Demand [cite: 35]
        if (event.isHighDemand()) {
            return new PricingResponse(event.getBasePrice(), "VIP (High Demand: No Discount)");
        } else {
            BigDecimal discounted = event.getBasePrice().multiply(new BigDecimal("0.90"));
            return new PricingResponse(discounted, "VIP Discount (10% Off)");
        }
    }
}