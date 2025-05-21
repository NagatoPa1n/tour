package uz.tour.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.tour.web.model.User;

public interface UserRepository  extends JpaRepository <User,Long>{
}
