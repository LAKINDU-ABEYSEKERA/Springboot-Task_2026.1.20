package edu.icet.ecom.model.entity;

import edu.icet.ecom.model.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long seatId;
    private BigDecimal amountPaid;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;
}