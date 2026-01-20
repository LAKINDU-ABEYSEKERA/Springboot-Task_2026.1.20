package edu.icet.ecom.controller;

import edu.icet.ecom.model.dto.BookingRequest;
import edu.icet.ecom.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seats")
@RequiredArgsConstructor
public class SeatController {

    private final SeatService seatService;


    @PostMapping("/{id}/hold")
    public ResponseEntity<String> holdSeat(@PathVariable Long id, @RequestBody BookingRequest request) {

        seatService.holdSeat(id, request.getUserId());
        return ResponseEntity.ok("Seat held successfully for 10 minutes.");
    }
}