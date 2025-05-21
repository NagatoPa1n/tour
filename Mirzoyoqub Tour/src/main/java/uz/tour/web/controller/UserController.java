package uz.tour.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.tour.web.model.Tour;
import uz.tour.web.model.User;
import uz.tour.web.repository.TourRepository;
import uz.tour.web.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "user", description = "User operations")
public class UserController {
    private TourRepository tourRepository;
    private UserRepository userRepository;

    public UserController(TourRepository tourRepository, UserRepository userRepository) {
        this.tourRepository = tourRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/tours")
    @Operation(summary = "Get list of all tours")
    public List<Tour> getAllTours() {
        return tourRepository.findAll();
    }

    @PostMapping("/register")
    @Operation(summary = "Contact me")
    public ResponseEntity<String> registerUser(@RequestParam Long tourId,
                                               @RequestParam String fullname,
                                               @RequestParam String phoneNumber){
        Optional<Tour> tour = tourRepository.findById(tourId);
        if(!tour.isPresent()){
            return ResponseEntity.badRequest().body("Invalid tour ID");
        }
        User user = new User(null, fullname, phoneNumber, tour.get());
        userRepository.save(user);
        return ResponseEntity.ok("User registered for tour successfully");
    }
}
