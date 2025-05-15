package br.edu.unichristus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unichristus.domain.dto.rental.RentalDTO;
import br.edu.unichristus.domain.model.Rental;
import br.edu.unichristus.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/rental")
public class RentalController {
    @Autowired
    private RentalService service;

    @Operation(summary = "Cadastra um novo aluguel | role: [ADMIN, USER]", tags = "Rental")
    @PostMapping
    public RentalDTO save(@RequestBody RentalDTO rental) {
        return service.save(rental);
    }

    @Operation(summary = "Atualiza um aluguel existente por ID | role: [ADMIN, USER]", tags = "Rental")
    @PutMapping("/{id}")
    public RentalDTO update(@PathVariable Long id, @RequestBody RentalDTO rentalDTO) {
        return service.update(id, rentalDTO);
    }

    @Operation(summary = "Retorna todos os aluguéis | role: [ADMIN]", tags = "Rental")
    @GetMapping("/all")
    public List<RentalDTO> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Retorna um aluguel por ID | role: [ADMIN, USER]", tags = "Rental")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Aluguel encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aluguel não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/{id}")
    public Rental findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(summary = "Exclui um aluguel por ID | role: [ADMIN]", tags = "Rental")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Aluguel excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aluguel não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @Operation(summary = "Retorna todos os aluguéis de um usuário | role: [ADMIN, USER]", tags = "Rental")
    @GetMapping("/user/{userId}")
    public List<RentalDTO> findByUser(@PathVariable Long userId) {
        return service.findByUserId(userId);
    }
}
