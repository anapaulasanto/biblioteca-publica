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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService service;

    @Operation(summary = "Cadastra os dados de um usuário | role: [ADMIN]", tags = "User")
    @PostMapping
    public UserLowDTO save(@RequestBody UserDTO user){
return service.save(user);
    }

    @Operation(summary = "Atualiza os dados de um usuário | role: [ADMIN]", tags = "User")
    @PutMapping
    public UserLowDTO update(@RequestBody UserDTO user){
        return service.save(user);
    }

    @Operation(summary = "Retorna a lista de todos os usuários | role: [ADMIN]", tags = "User")
    @GetMapping("/all")
    public List<UserLowDTO> findAll(){
        return service.findAll();
    }

    @Operation(summary = "Retorna os dados de um usuário pelo ID | role: [ADMIN]", tags = "User")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/{id}")
    public User findById(@PathVariable Long id){
        return service.findById(id);
    }

    @Operation(summary = "Exclui um usuário pelo ID | role: [ADMIN]", tags = "User")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @Autowired
    private ReviewService reviewService;
    @Operation(summary = "Retorna as avaliações feitas por um usuário | role: [ADMIN]", tags = "User")
    @GetMapping("/{id}/reviews")
    public List<ReviewDTO> getReviewsByUser(@PathVariable Long id) {
        return reviewService.findReviewsByUserId(id);
    }

    @Autowired
    private RentalService rentalService;
    @Operation(summary = "Retorna os aluguéis feitos por um usuário | role: [ADMIN]", tags = "User")
    @GetMapping("/{userId}/rentals")
    public List<RentalDTO> getRentalsByUserId(@PathVariable Long userId) {
        return rentalService.findRentalsByUserId(userId);
    }

}
