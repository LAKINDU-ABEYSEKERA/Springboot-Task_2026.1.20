package edu.icet.ecom.controller;

import edu.icet.ecom.model.dto.PricingResponse;
import edu.icet.ecom.service.PriceCalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pricing")
@RequiredArgsConstructor
public class PricingController {

    private final PriceCalculatorService priceCalculatorService;

    // Endpoint: GET /pricing/calculate?userId=1&eventId=1
    @GetMapping("/calculate")
    public ResponseEntity<PricingResponse> calculate(@RequestParam Long userId, @RequestParam Long eventId) {
        PricingResponse response = priceCalculatorService.calculatePrice(userId, eventId);
        return ResponseEntity.ok(response);
    }
}