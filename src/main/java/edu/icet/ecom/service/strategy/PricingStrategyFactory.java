package edu.icet.ecom.service.strategy;

import edu.icet.ecom.model.enums.UserTier;
import org.springframework.stereotype.Component;

@Component
public class PricingStrategyFactory {

    public PricingStrategy getStrategy(UserTier tier) {
        return switch (tier) {
            case VIP -> new VipPricingStrategy();
            case PLATINUM -> new PlatinumPricingStrategy();
            default -> new RegularPricingStrategy();
        };
    }
}