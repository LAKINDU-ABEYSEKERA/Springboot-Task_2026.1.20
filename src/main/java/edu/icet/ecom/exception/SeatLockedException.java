package edu.icet.ecom.exception;

public class SeatLockedException extends RuntimeException {
    public SeatLockedException(String message) {
        super(message);
    }
}