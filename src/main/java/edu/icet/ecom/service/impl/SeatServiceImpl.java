package edu.icet.ecom.service.impl;

import edu.icet.ecom.aop.AuditFailure;
import edu.icet.ecom.exception.SeatLockedException;
import edu.icet.ecom.model.entity.Seat;
import edu.icet.ecom.model.enums.SeatStatus;
import edu.icet.ecom.repository.SeatRepository;
import edu.icet.ecom.service.SeatService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;

    @Override
    @Transactional
    @AuditFailure
    public void holdSeat(Long seatId, Long userId) {
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));


        if (seat.getStatus() == SeatStatus.AVAILABLE) {
            lockSeat(seat, userId);
        }

        else if (seat.getStatus() == SeatStatus.HELD) {
            if (seat.getHoldExpiry().isBefore(LocalDateTime.now())) {

                lockSeat(seat, userId);
            } else {

                long secondsLeft = ChronoUnit.SECONDS.between(
                        LocalDateTime.now(), seat.getHoldExpiry()
                );
                throw new SeatLockedException("Seat locked. Seconds remaining: " + secondsLeft);
            }
        }

        else {
            throw new RuntimeException("Seat is permanently SOLD");
        }
    }

    private void lockSeat(Seat seat, Long userId) {
        seat.setStatus(SeatStatus.HELD);
        seat.setHeldByUserId(userId);
        seat.setHoldExpiry(LocalDateTime.now().plusMinutes(10));
        seatRepository.save(seat);
    }
}