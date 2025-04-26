package br.edu.unichristus.controller;

import br.edu.unichristus.domain.dto.rental.RentalDTO;
import br.edu.unichristus.domain.dto.review.ReviewDTO;
import br.edu.unichristus.domain.dto.user.UserDTO;
import br.edu.unichristus.domain.dto.user.UserLowDTO;
import br.edu.unichristus.domain.model.User;
import br.edu.unichristus.service.RentalService;
import br.edu.unichristus.service.ReviewService;
import br.edu.unichristus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public UserLowDTO save(@RequestBody UserDTO user){
        return service.save(user);
    }

    @PutMapping
    public UserLowDTO update(@RequestBody UserDTO user){
        return service.save(user);
    }

    @GetMapping("/all")
    public List<UserLowDTO> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id){
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @Autowired
    private ReviewService reviewService;
    @GetMapping("/{id}/reviews")
    public List<ReviewDTO> getReviewsByUser(@PathVariable Long id) {
        return reviewService.findReviewsByUserId(id);
    }

    @Autowired
    private RentalService rentalService;
    @GetMapping("/{userId}/rentals")
    public List<RentalDTO> getRentalsByUserId(@PathVariable Long userId) {
        return rentalService.findRentalsByUserId(userId);
    }

}
