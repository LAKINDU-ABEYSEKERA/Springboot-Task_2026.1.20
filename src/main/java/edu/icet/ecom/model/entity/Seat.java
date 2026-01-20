package edu.icet.ecom.model.entity;

import edu.icet.ecom.model.enums.SeatStatus;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    private String seatNumber;

    @Enumerated(EnumType.STRING)
    private SeatStatus status;

    @Column(name = "held_by_user_id")
    private Long heldByUserId;

    private LocalDateTime holdExpiry;

    @Version // Optimistic Locking
    private Integer version;
}