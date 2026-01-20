package edu.icet.ecom.model.dto;

import lombok.Data;

@Data
public class BookingRequest {
    private Long userId;
    private Long seatId;
    private Long eventId; // For pricing calculation
}