package edu.icet.ecom.controller;

import edu.icet.ecom.model.dto.BookingRequest;
import edu.icet.ecom.model.entity.Booking;
import edu.icet.ecom.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/confirm")
    public ResponseEntity<Booking> confirmBooking(@RequestBody BookingRequest request) {
        Booking booking = bookingService.createBooking(request.getUserId(), request.getSeatId());
        return ResponseEntity.ok(booking);
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<String> cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return ResponseEntity.ok("Booking cancelled successfully. Seat is now available.");
    }
}