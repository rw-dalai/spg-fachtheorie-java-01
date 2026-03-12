package at.spengergasse.fachtheorie.aufgabe2.services;

import at.spengergasse.fachtheorie.aufgabe2.models.Trip;
import at.spengergasse.fachtheorie.aufgabe2.persistence.ScooterRepository;
import at.spengergasse.fachtheorie.aufgabe2.persistence.TripRepository;
import at.spengergasse.fachtheorie.aufgabe2.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


/**
 * Hilfreiche Methoden und Konzepte für die Implementierung:
 *
 * Repository (Magic Finders):
 *   - findById(id)           -> Optional<T>
 *   - existsByXXX()          -> boolean
 *   - findByXXX(a, b)        -> List<T>
 *
 * Optional:
 *   - .orElseThrow(() -> new ScooterServiceException("..."))
 *   - .orElse(null)
 *
 * Streams:
 *   - list.stream().map(x -> ...).toList()
 *   - list.stream().mapToInt(X::getY).max().orElse(0)
 *   - list.stream().mapToInt(X::getY).min().orElse(0)
 *
 * Entity-Navigation:
 *   - trip.getScooter().getModel()      (über @ManyToOne)
 *   - trip.getTripLogs().stream()       (über @OneToMany)
 *
 * BigDecimal:
 *   - new BigDecimal(int), new BigDecimal("1000")
 *   - .divide(divisor, scale, RoundingMode.HALF_UP)
 *   - .subtract(other), .multiply(other), .max(BigDecimal.ZERO)
 */

// TODO: Verwende die Testklasse ScooterServiceTests, um deine Implementierung zu testen.

@Service
@RequiredArgsConstructor
@Transactional
public class ScooterService {

    private final UserRepository userRepository;
    private final ScooterRepository scooterRepository;
    private final TripRepository tripRepository;

    public Trip addTrip(Long userId, Long scooterId, LocalDateTime begin) {
        throw new UnsupportedOperationException("TODO: Implement this method");
    }

    @Transactional(readOnly = true)
    public List<TripDto> getTripInfos(LocalDateTime beginFrom, LocalDateTime beginTo) {
        throw new UnsupportedOperationException("TODO: Implement this method");
    }

    @Transactional(readOnly = true)
    public int calculateTripLength(Long tripId) {
        throw new UnsupportedOperationException("TODO: Implement this method");
    }

    @Transactional(readOnly = true)
    public BigDecimal calculatePrice(Long tripId) {
        throw new UnsupportedOperationException("TODO: Implement this method");
    }
}
