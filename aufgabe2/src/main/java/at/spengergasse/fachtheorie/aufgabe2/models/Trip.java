package at.spengergasse.fachtheorie.aufgabe2.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trip")
@Getter
@ToString(callSuper = true)
public class Trip extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "scooter_id", nullable = false)
    @ToString.Exclude
    private Scooter scooter;

    @Column(name = "begin_time", nullable = false)
    private LocalDateTime begin;

    @Setter
    @Column(name = "end_time")
    private LocalDateTime end;

    @Setter
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "longitude", column = @Column(name = "parking_longitude")),
        @AttributeOverride(name = "latitude", column = @Column(name = "parking_latitude"))
    })
    private Location parkingLocation;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    private final List<TripLog> tripLogs = new ArrayList<>();

    protected Trip() {
    }

    public Trip(User user, Scooter scooter, LocalDateTime begin, LocalDateTime end, Location parkingLocation) {
        this.user = user;
        this.scooter = scooter;
        this.begin = begin;
        this.end = end;
        this.parkingLocation = parkingLocation;
    }
}
