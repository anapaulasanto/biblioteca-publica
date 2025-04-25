package br.edu.unichristus.controller;

import br.edu.unichristus.domain.dto.rental.RentalDTO;
import br.edu.unichristus.domain.model.Rental;
import br.edu.unichristus.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rental")
public class RentalController {
    @Autowired
    private RentalService service;

    @PostMapping
    public RentalDTO save(@RequestBody RentalDTO rental){
        return service.save(rental);
    }

    @PutMapping
    public RentalDTO update(@RequestBody RentalDTO rental){
        return service.save(rental);
    }

    @GetMapping("/all")
    public List<RentalDTO> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Rental findById(@PathVariable Long id){
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @GetMapping("/user/{userId}")
    public List<RentalDTO> findByUser(@PathVariable Long userId) {
        return service.findByUserId(userId);
    }

}
