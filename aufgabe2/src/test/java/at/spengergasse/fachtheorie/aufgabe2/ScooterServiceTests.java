package at.spengergasse.fachtheorie.aufgabe2;

import at.spengergasse.fachtheorie.aufgabe2.models.*;
import at.spengergasse.fachtheorie.aufgabe2.persistence.ScooterRepository;
import at.spengergasse.fachtheorie.aufgabe2.persistence.TripLogRepository;
import at.spengergasse.fachtheorie.aufgabe2.persistence.TripRepository;
import at.spengergasse.fachtheorie.aufgabe2.persistence.UserRepository;
import at.spengergasse.fachtheorie.aufgabe2.services.ScooterService;
import at.spengergasse.fachtheorie.aufgabe2.services.ScooterServiceException;
import at.spengergasse.fachtheorie.aufgabe2.services.TripDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static at.spengergasse.fachtheorie.aufgabe2.fixtures.ScooterFixtures.*;
import static org.assertj.core.api.Assertions.*;

// *************************************************************************************************
// VORGEGEBENE UNITTESTS FÜR AUFGABE 2
// DIESE KLASSE IST NUR FÜR DIE KORREKTUR UND ZUR SELBSTKONTROLLE. HIER MUSS NICHTS GEÄNDERT WERDEN.
// *************************************************************************************************
@DataJpaTest
public class ScooterServiceTests {

    @Autowired private UserRepository userRepository;
    @Autowired private ScooterRepository scooterRepository;
    @Autowired private TripRepository tripRepository;
    @Autowired private TripLogRepository tripLogRepository;

    private ScooterService scooterService;

    private User alice;
    private User bob;
    private Scooter xiaomi;
    private Scooter segway;
    private Trip trip1;

    @BeforeEach
    void setUp() {
        scooterService = new ScooterService(userRepository, scooterRepository, tripRepository);

        // Seed data
        alice = userRepository.save(alice());
        bob = userRepository.save(bob());

        xiaomi = scooterRepository.save(xiaomi());
        segway = scooterRepository.save(segway());

        // Trip 1: alice on xiaomi, still open (no end)
        trip1 = tripRepository.save(openTrip(alice, xiaomi,
            LocalDateTime.of(2025, 2, 7, 10, 46, 3)));

        // Trip logs for trip 1
        tripLogRepository.save(tripLog(trip1, LocalDateTime.of(2025, 2, 7, 10, 46, 3),
            new BigDecimal("16.48055"), new BigDecimal("48.16812"), 12397));
        tripLogRepository.save(tripLog(trip1, LocalDateTime.of(2025, 2, 7, 10, 48, 3),
            new BigDecimal("16.60784"), new BigDecimal("48.30346"), 12663));
        tripLogRepository.save(tripLog(trip1, LocalDateTime.of(2025, 2, 7, 10, 52, 3),
            new BigDecimal("15.85092"), new BigDecimal("48.21999"), 13510));
        tripLogRepository.save(tripLog(trip1, LocalDateTime.of(2025, 2, 7, 10, 54, 3),
            new BigDecimal("15.44802"), new BigDecimal("48.26622"), 14396));

        // Trip 2: bob on segway, ended
        var trip2 = tripRepository.save(endedTrip(bob, segway,
            LocalDateTime.of(2025, 2, 8, 9, 0, 0),
            LocalDateTime.of(2025, 2, 8, 9, 30, 0),
            new Location(new BigDecimal("16.37208"), new BigDecimal("48.20849"))));

        tripLogRepository.save(tripLog(trip2, LocalDateTime.of(2025, 2, 8, 9, 0, 0),
            new BigDecimal("16.37208"), new BigDecimal("48.20849"), 5000));
        tripLogRepository.save(tripLog(trip2, LocalDateTime.of(2025, 2, 8, 9, 10, 0),
            new BigDecimal("16.38000"), new BigDecimal("48.21000"), 6500));
        tripLogRepository.save(tripLog(trip2, LocalDateTime.of(2025, 2, 8, 9, 20, 0),
            new BigDecimal("16.38500"), new BigDecimal("48.21200"), 8200));
    }

    @Test
    void addTripTest() {
        // Invalid user should throw
        assertThatThrownBy(() -> scooterService.addTrip(9999L, segway.getId(),
            LocalDateTime.of(2025, 3, 1, 10, 0)))
            .isInstanceOf(ScooterServiceException.class)
            .hasMessage("Invalid user.");

        // Invalid scooter should throw
        assertThatThrownBy(() -> scooterService.addTrip(alice.getId(), 9999L,
            LocalDateTime.of(2025, 3, 1, 10, 0)))
            .isInstanceOf(ScooterServiceException.class)
            .hasMessage("Invalid scooter.");

        // Xiaomi has an open trip (trip1), should throw
        assertThatThrownBy(() -> scooterService.addTrip(bob.getId(), xiaomi.getId(),
            LocalDateTime.of(2025, 3, 1, 10, 0)))
            .isInstanceOf(ScooterServiceException.class)
            .hasMessage("Scooter is not parked.");

        // Segway has no open trips, should succeed
        var newTrip = scooterService.addTrip(bob.getId(), segway.getId(),
            LocalDateTime.of(2025, 3, 1, 10, 0));
        assertThat(newTrip).isNotNull();
        assertThat(newTrip.getId()).isNotNull();
        assertThat(newTrip.getUser().getEmail()).isEqualTo("bob@example.com");
        assertThat(newTrip.getScooter().getManufacturerId()).isEqualTo("Segway");
    }

    @Test
    void getTripInfosTest() {
        var from = LocalDateTime.of(2025, 2, 1, 0, 0);
        var to = LocalDateTime.of(2025, 2, 28, 23, 59);

        var trips = scooterService.getTripInfos(from, to);

        assertThat(trips).hasSize(2);

        TripDto aliceTrip = trips.stream()
            .filter(t -> t.userEmail().equals("alice@example.com"))
            .findFirst().orElseThrow();
        assertThat(aliceTrip.scooterModel()).isEqualTo("Mi Pro 2");
        assertThat(aliceTrip.isParked()).isFalse();

        TripDto bobTrip = trips.stream()
            .filter(t -> t.userEmail().equals("bob@example.com"))
            .findFirst().orElseThrow();
        assertThat(bobTrip.scooterModel()).isEqualTo("Max G30");
        assertThat(bobTrip.isParked()).isTrue();
    }

    @Test
    void calculateTripLengthTest() {
        // Trip 1: mileage range 12397..14396 = 1999 meters
        int length = scooterService.calculateTripLength(trip1.getId());
        assertThat(length).isEqualTo(1999);
    }

    @Test
    void calculatePriceTest() {
        // Trip 1: length = 1999m = 1.999km, alice has 2 free km
        // chargeable = max(0, 1.999 - 2) = 0
        // price = 0 * 0.25 = 0
        BigDecimal price1 = scooterService.calculatePrice(trip1.getId());
        assertThat(price1.compareTo(BigDecimal.ZERO)).isEqualTo(0);

        // Non-existent trip should return 0
        BigDecimal priceNone = scooterService.calculatePrice(9999L);
        assertThat(priceNone.compareTo(BigDecimal.ZERO)).isEqualTo(0);
    }
}
