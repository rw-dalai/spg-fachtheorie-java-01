package at.spengergasse.fachtheorie.aufgabe2.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "trip_log")
@Getter
@ToString(callSuper = true)
public class TripLog extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "trip_id", nullable = false)
    @ToString.Exclude
    private Trip trip;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Embedded
    private Location location;

    @Column(nullable = false)
    private int mileageInMeters;

    protected TripLog() {
    }

    public TripLog(Trip trip, LocalDateTime timestamp, Location location, int mileageInMeters) {
        this.trip = trip;
        this.timestamp = timestamp;
        this.location = location;
        this.mileageInMeters = mileageInMeters;
    }
}
