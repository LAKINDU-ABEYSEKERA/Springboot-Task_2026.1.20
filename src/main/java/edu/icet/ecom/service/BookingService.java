package edu.icet.ecom.service;

import edu.icet.ecom.model.entity.Booking;

public interface BookingService {
    Booking createBooking(Long userId, Long seatId);
    void cancelBooking(Long bookingId);
}