package at.spengergasse.fachtheorie.aufgabe3.fixtures;

import at.spengergasse.fachtheorie.aufgabe2.models.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public final class ScooterFixtures {

    private ScooterFixtures() {
    }

    public static User alice() {
        return new User("alice@example.com", 2);
    }

    public static User bob() {
        return new User("bob@example.com", 0);
    }

    public static Scooter xiaomi() {
        return new Scooter("Xiaomi", "Mi Pro 2", new BigDecimal("0.25"));
    }

    public static Scooter segway() {
        return new Scooter("Segway", "Max G30", new BigDecimal("0.35"));
    }

    public static Trip openTrip(User user, Scooter scooter, LocalDateTime begin) {
        return new Trip(user, scooter, begin, null, null);
    }

    public static Trip endedTrip(User user, Scooter scooter, LocalDateTime begin,
                                 LocalDateTime end, Location parking) {
        return new Trip(user, scooter, begin, end, parking);
    }

    public static TripLog tripLog(Trip trip, LocalDateTime ts,
                                  BigDecimal lon, BigDecimal lat, int mileage) {
        return new TripLog(trip, ts, new Location(lon, lat), mileage);
    }
}
