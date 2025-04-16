package br.edu.unichristus.controller;

import br.edu.unichristus.domain.dto.UserDTO;
import br.edu.unichristus.domain.dto.UserLowDTO;
import br.edu.unichristus.domain.dto.UserRolesDTO;
import br.edu.unichristus.domain.model.User;
import br.edu.unichristus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/user")
public class UserControllerV2 {

    @Autowired
    private UserService service;

    @GetMapping("/{id}")
    public UserRolesDTO getRolesByUserId(@PathVariable Long id){
        return service.getRolesByUserId(id);
    }

}
