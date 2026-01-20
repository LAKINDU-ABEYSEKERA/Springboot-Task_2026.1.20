package edu.icet.ecom.service.impl;

import edu.icet.ecom.aop.AuditFailure; // We will create this in Phase 5
import edu.icet.ecom.model.entity.Seat;
import edu.icet.ecom.model.enums.SeatStatus;
import edu.icet.ecom.repository.SeatRepository;
import edu.icet.ecom.service.SeatService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;

    @Override
    @Transactional
    @AuditFailure // Hooks into AOP
    public void holdSeat(Long seatId, Long userId) {
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        if (seat.getStatus() == SeatStatus.AVAILABLE) {
            lockSeat(seat, userId);
        } else if (seat.getStatus() == SeatStatus.HELD) {
            // Check if expired [cite: 19]
            if (seat.getHoldExpiry().isBefore(LocalDateTime.now())) {
                lockSeat(seat, userId);
            } else {
                throw new RuntimeException("Seat is currently locked by another user.");
            }
        } else {
            throw new RuntimeException("Seat is already SOLD.");
        }
    }

    private void lockSeat(Seat seat, Long userId) {
        seat.setStatus(SeatStatus.HELD);
        seat.setHeldByUserId(userId);
        seat.setHoldExpiry(LocalDateTime.now().plusMinutes(10)); // [cite: 17]
        seatRepository.save(seat);
    }
}