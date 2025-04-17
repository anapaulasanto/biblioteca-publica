package br.edu.unichristus.controller;

import br.edu.unichristus.domain.dto.review.ReviewDTO;
import br.edu.unichristus.domain.dto.review.ReviewLowDTO;
import br.edu.unichristus.domain.model.Review;
import br.edu.unichristus.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {
    @Autowired
    private ReviewService service;

    @PostMapping
    public ReviewLowDTO save(@RequestBody ReviewDTO review){
        return service.save(review);
    }

    @PutMapping
    public ReviewLowDTO update(@RequestBody ReviewDTO review){
        return service.save(review);
    }

    @GetMapping("/all")
    public List<ReviewLowDTO> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Review findById(@PathVariable Long id){
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

}
