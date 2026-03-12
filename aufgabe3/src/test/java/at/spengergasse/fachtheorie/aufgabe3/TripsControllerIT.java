package at.spengergasse.fachtheorie.aufgabe3;

import at.spengergasse.fachtheorie.aufgabe2.Aufgabe2Application;
import at.spengergasse.fachtheorie.aufgabe2.persistence.TripLogRepository;
import at.spengergasse.fachtheorie.aufgabe2.persistence.TripRepository;
import at.spengergasse.fachtheorie.aufgabe2.persistence.ScooterRepository;
import at.spengergasse.fachtheorie.aufgabe2.persistence.UserRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(classes = Aufgabe2Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TripsControllerIT {

    @LocalServerPort
    private int port;

    @Autowired private UserRepository userRepository;
    @Autowired private ScooterRepository scooterRepository;
    @Autowired private TripRepository tripRepository;
    @Autowired private TripLogRepository tripLogRepository;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        tripLogRepository.deleteAll();
        tripRepository.deleteAll();
        scooterRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void contextLoads() {
    }

    // TODO: Füge hier deine Integration Tests ein, um die Endpunkte zu testen.
}
