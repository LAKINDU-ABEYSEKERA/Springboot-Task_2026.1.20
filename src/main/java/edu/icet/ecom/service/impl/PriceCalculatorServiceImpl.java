package edu.icet.ecom.service.impl;

import edu.icet.ecom.model.dto.PricingResponse;
import edu.icet.ecom.model.entity.Event;
import edu.icet.ecom.model.entity.User;
import edu.icet.ecom.repository.EventRepository;
import edu.icet.ecom.repository.UserRepository;
import edu.icet.ecom.service.PriceCalculatorService;
import edu.icet.ecom.service.strategy.PricingStrategy;
import edu.icet.ecom.service.strategy.PricingStrategyFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceCalculatorServiceImpl implements PriceCalculatorService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final PricingStrategyFactory strategyFactory;

    @Override
    public PricingResponse calculatePrice(Long userId, Long eventId) {
        // 1. Fetch User and Event
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        // 2. Get the correct Strategy based on User Tier (Factory Pattern)
        PricingStrategy strategy = strategyFactory.getStrategy(user.getTier());

        // 3. Calculate and Return
        return strategy.calculate(event);
    }
}