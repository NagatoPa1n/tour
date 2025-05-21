package uz.tour.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.tour.web.model.Tour;
import uz.tour.web.repository.TourRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/admin")
@Tag(name = "admin", description = "Admin operations")
public class TourController {
    TourRepository tourRepository;
    private final String UPLOADED_FOLDER = "C:/Users/Windows 11/OneDrive/Desktop/Mirzoyoqub Tour/src/main/resources/static/images/";

    public TourController(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadTour(@RequestParam String title,
                                             @RequestParam String description,
                                             @RequestParam String location,
                                             @RequestParam String price,
                                             @RequestParam MultipartFile image) throws IOException {
        String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
        Path path = Paths.get(UPLOADED_FOLDER + fileName);
        Files.createDirectories(path.getParent());
        Files.write(path, image.getBytes());

        Tour tour = new Tour(null, title, description, location, price, "/" + UPLOADED_FOLDER + fileName);
        tourRepository.save(tour);

        return ResponseEntity.ok("Tour uploaded");
    }
    @GetMapping("/tours")
    @Operation(summary = "List all tours")
    public List<Tour> getAllTours(){
        return tourRepository.findAll();
    }

    @PostMapping("/login")
    @Operation(summary = "Login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password){
        if("admin".equals(username) && "admin".equals(password)){
            return ResponseEntity.ok("Login successful");
        }else{
            return ResponseEntity.status(401).body("Login failed");
        }
    }

}
