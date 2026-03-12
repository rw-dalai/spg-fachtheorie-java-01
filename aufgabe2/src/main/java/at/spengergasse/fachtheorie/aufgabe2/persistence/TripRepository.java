package at.spengergasse.fachtheorie.aufgabe2.persistence;

import at.spengergasse.fachtheorie.aufgabe2.models.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

// TODO: Füge hier bei Bedarf eigene Abfragemethoden (Magic Finders) ein.
public interface TripRepository extends JpaRepository<Trip, Long> {
}
