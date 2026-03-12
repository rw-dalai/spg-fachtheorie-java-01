package at.spengergasse.fachtheorie.aufgabe3.controllers;

import at.spengergasse.fachtheorie.aufgabe2.persistence.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trips")
@RequiredArgsConstructor
public class TripsController {

    private final TripRepository tripRepository;

    // TODO: Füge hier die Parameter der Controllermethode ein und implementiere den Endpunkt.
    @GetMapping("/{id}")
    public ResponseEntity<?> getTripById() {
        throw new UnsupportedOperationException("TODO: Implement this endpoint");
    }

    // TODO: Füge hier die Parameter der Controllermethode ein und implementiere den Endpunkt.
    @PatchMapping("/{id}")
    public ResponseEntity<?> patchTrip() {
        throw new UnsupportedOperationException("TODO: Implement this endpoint");
    }
}
