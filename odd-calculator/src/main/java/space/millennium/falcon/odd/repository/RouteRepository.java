package space.millennium.falcon.odd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import space.millennium.falcon.odd.entity.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Route.RouteId> {
}
