package edu.icet.ecom.service.impl;

import edu.icet.ecom.model.entity.Booking;
import edu.icet.ecom.model.entity.Seat;
import edu.icet.ecom.model.enums.BookingStatus;
import edu.icet.ecom.model.enums.SeatStatus;
import edu.icet.ecom.repository.BookingRepository;
import edu.icet.ecom.repository.SeatRepository;
import edu.icet.ecom.service.BookingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final SeatRepository seatRepository;
    private final BookingRepository bookingRepository;

    @Override
    @Transactional
    public Booking createBooking(Long userId, Long seatId) {

        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        if (seat.getStatus() != SeatStatus.HELD) {
            throw new RuntimeException("Cannot book. Seat is not currently held.");
        }
        if (!seat.getHeldByUserId().equals(userId)) {
            throw new RuntimeException("Cannot book. You do not hold this seat.");
        }

        seat.setStatus(SeatStatus.SOLD);
        seatRepository.save(seat);

        Booking booking = new Booking();
        booking.setUserId(userId);
        booking.setSeatId(seatId);
        booking.setStatus(BookingStatus.CONFIRMED);
        booking.setAmountPaid(seat.getEvent().getBasePrice());

        return bookingRepository.save(booking);
    }

    @Override
    @Transactional
    public void cancelBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));


        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new RuntimeException("Booking is already cancelled.");
        }

        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);

        Seat seat = seatRepository.findById(booking.getSeatId())
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        seat.setStatus(SeatStatus.AVAILABLE);
        seat.setHeldByUserId(null);
        seat.setHoldExpiry(null);
        seatRepository.save(seat);
    }
}