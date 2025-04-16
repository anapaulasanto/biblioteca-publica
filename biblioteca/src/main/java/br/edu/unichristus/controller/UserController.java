package br.edu.unichristus.controller;

import br.edu.unichristus.domain.dto.UserDTO;
import br.edu.unichristus.domain.dto.UserLowDTO;
import br.edu.unichristus.domain.model.User;
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




}
