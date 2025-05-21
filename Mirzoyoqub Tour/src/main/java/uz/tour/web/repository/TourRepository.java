package uz.tour.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.tour.web.model.Tour;

public interface TourRepository extends JpaRepository<Tour,Long> {
}
