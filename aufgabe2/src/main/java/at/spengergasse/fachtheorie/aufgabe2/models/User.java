package at.spengergasse.fachtheorie.aufgabe2.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "scooter_user")
@Getter
@ToString(callSuper = true)
public class User extends BaseEntity {

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private int freeKilometers;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private final List<Trip> trips = new ArrayList<>();

    protected User() {
    }

    public User(String email, int freeKilometers) {
        this.email = email;
        this.freeKilometers = freeKilometers;
    }
}
