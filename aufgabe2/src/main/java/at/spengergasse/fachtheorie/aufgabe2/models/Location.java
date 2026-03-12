package at.spengergasse.fachtheorie.aufgabe2.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Embeddable
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Location {

    @Column(nullable = false, precision = 10, scale = 5)
    private BigDecimal longitude;

    @Column(nullable = false, precision = 10, scale = 5)
    private BigDecimal latitude;

    public Location(BigDecimal longitude, BigDecimal latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
