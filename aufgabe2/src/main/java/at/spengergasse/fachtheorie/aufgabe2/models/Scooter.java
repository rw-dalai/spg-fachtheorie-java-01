package at.spengergasse.fachtheorie.aufgabe2.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "scooter")
@Getter
@ToString(callSuper = true)
public class Scooter extends BaseEntity {

    @Column(nullable = false)
    private String manufacturerId;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal pricePerKilometer;

    @OneToMany(mappedBy = "scooter")
    @ToString.Exclude
    private List<Trip> trips = new ArrayList<>();

    protected Scooter() {
    }

    public Scooter(String manufacturerId, String model, BigDecimal pricePerKilometer) {
        this.manufacturerId = manufacturerId;
        this.model = model;
        this.pricePerKilometer = pricePerKilometer;
    }
}
