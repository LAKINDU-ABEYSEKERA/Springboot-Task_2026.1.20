package edu.icet.ecom.service.impl;

import edu.icet.ecom.model.entity.Seat;
import edu.icet.ecom.model.enums.SeatStatus;
import edu.icet.ecom.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatCleanupScheduler {

    private final SeatRepository seatRepository;

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void releaseExpiredSeats() {

        List<Seat> expiredSeats = seatRepository.findAll().stream()
                .filter(seat -> seat.getStatus() == SeatStatus.HELD)
                .filter(seat -> seat.getHoldExpiry() != null && seat.getHoldExpiry().isBefore(LocalDateTime.now()))
                .toList();

        if (expiredSeats.isEmpty()) {
            return;
        }

        System.out.println("SCHEDULER: Found " + expiredSeats.size() + " expired seats. Releasing them...");

        for (Seat seat : expiredSeats) {
            seat.setStatus(SeatStatus.AVAILABLE);
            seat.setHeldByUserId(null);
            seat.setHoldExpiry(null);
        }

        seatRepository.saveAll(expiredSeats);
        System.out.println("SCHEDULER: Seats released successfully.");
    }
}