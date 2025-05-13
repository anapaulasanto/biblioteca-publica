package br.edu.unichristus.controller.BookController;

import br.edu.unichristus.domain.dto.book.BookDTO;
import br.edu.unichristus.domain.dto.rental.RentalDTO;
import br.edu.unichristus.domain.dto.review.ReviewDTO;
import br.edu.unichristus.domain.model.Book;
import br.edu.unichristus.service.BookService.BookService;
import br.edu.unichristus.service.RentalService;
import br.edu.unichristus.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {
    @Autowired
    private BookService service;

    @Operation(summary = "Cadastra um novo livro | role: [ADMIN]", tags = "Book")
    @PostMapping
    public BookDTO save(@RequestBody BookDTO book){
        return service.save(book);
    }

    @Operation(summary = "Atualiza um livro existente | role: [ADMIN]", tags = "Book")
    @PutMapping("/{id}")
    public BookDTO update(@PathVariable Long id, @RequestBody BookDTO book){
        return service.save(book);
    }

    @Operation(summary = "Retorna todos os livros | role: [ADMIN, USER]", tags = "Book")
    @GetMapping("/all")
    public List<BookDTO> findAll(){
        return service.findAll();
    }

    @Operation(summary = "Retorna um livro por ID | role: [ADMIN, USER]", tags = "Book")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Livro encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/{id}")
    public Book findById(@PathVariable Long id){
        return service.findById(id);
    }

    @Operation(summary = "Exclui um livro por ID | role: [ADMIN]", tags = "Book")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Livro excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @Autowired
    private ReviewService reviewService;

    @Operation(summary = "Retorna avaliações de um livro | role: [ADMIN, USER]", tags = "Book")
    @GetMapping("/{id}/reviews")
    public List<ReviewDTO> getReviewByBook(@PathVariable Long id) {
        return reviewService.findReviewsByBookId(id);
    }

    @Autowired
    private RentalService rentalService;

    @Operation(summary = "Retorna locações de um livro | role: [ADMIN]", tags = "Book")
    @GetMapping("/{id}/rentals")
    public List<RentalDTO> getRentalByBook(@PathVariable Long id) {
        return rentalService.findRentalsByBookId(id);
    }
}
