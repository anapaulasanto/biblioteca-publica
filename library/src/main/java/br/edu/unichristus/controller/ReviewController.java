package br.edu.unichristus.controller;

import br.edu.unichristus.domain.dto.review.ReviewDTO;
import br.edu.unichristus.domain.model.Review;
import br.edu.unichristus.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {
    @Autowired
    private ReviewService service;

    @Operation(summary = "Cadastra uma nova avaliação | role: [ADMIN, USER]", tags = "Review")
    @PostMapping
    public ReviewDTO save(@RequestBody ReviewDTO review){
        return service.save(review);
    }

    @Operation(summary = "Atualiza uma avaliação existente por ID | role: [ADMIN, USER]", tags = "Review")
    @PutMapping("/{id}")
    public ReviewDTO update(@PathVariable Long id, @RequestBody ReviewDTO dto) {
        return service.update(id, dto);
    }

    @Operation(summary = "Retorna todas as avaliações | role: [ADMIN]", tags = "Review")
    @GetMapping("/all")
    public List<ReviewDTO> findAll(){
        return service.findAll();
    }

    @Operation(summary = "Retorna uma avaliação por ID | role: [ADMIN, USER]", tags = "Review")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Avaliação retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Avaliação não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/{id}")
    public Review findById(@PathVariable Long id){
        return service.findById(id);
    }

    @Operation(summary = "Exclui uma avaliação por ID | role: [ADMIN]", tags = "Review")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @Operation(summary = "Retorna avaliações de um livro específico | role: [ADMIN, USER]", tags = "Review")
    @GetMapping("/book/{bookId}")
    public List<ReviewDTO> getByBook(@PathVariable Long bookId) {
        return service.findByBookId(bookId);
    }

    @Operation(summary = "Retorna avaliações feitas por um usuário | role: [ADMIN, USER]", tags = "Review")
    @GetMapping("/{id}/reviews")
    public List<ReviewDTO> getReviewsByUser(@PathVariable Long id) {
        return service.findReviewsByUserId(id);
    }
}
