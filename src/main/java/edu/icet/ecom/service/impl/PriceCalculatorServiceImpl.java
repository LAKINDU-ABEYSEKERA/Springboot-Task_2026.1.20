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
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        PricingStrategy strategy = strategyFactory.getStrategy(user.getTier());

        return strategy.calculate(event);
    }
}