package at.spengergasse.fachtheorie.aufgabe2.services;

import java.time.LocalDateTime;

public record TripDto(
    Long id,
    LocalDateTime begin,
    LocalDateTime end,
    Long scooterId,
    String scooterModel,
    Long userId,
    String userEmail,
    boolean isParked) {
}
