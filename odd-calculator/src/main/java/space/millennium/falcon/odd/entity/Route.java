package space.millennium.falcon.odd.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ROUTES")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class Route implements Serializable {

    @EmbeddedId
    private RouteId routeId;

    @Column(name = "TRAVEL_TIME", nullable = false)
    private int travelTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Route route)) return false;

        return routeId.equals(route.routeId);
    }

    @Override
    public int hashCode() {
        return routeId.hashCode();
    }

    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Getter
    @Setter
    @Builder
    public static class RouteId implements Serializable {

        @Column(name = "ORIGIN", nullable = false)
        private String origin;

        @Column(name = "DESTINATION", nullable = false)
        private String destination;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof RouteId routeId)) return false;

            if (!origin.equals(routeId.origin)) return false;
            return destination.equals(routeId.destination);
        }

        @Override
        public int hashCode() {
            int result = origin.hashCode();
            result = 31 * result + destination.hashCode();
            return result;
        }
    }
}
