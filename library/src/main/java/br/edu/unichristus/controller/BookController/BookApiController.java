package br.edu.unichristus.controller.BookController;

import br.edu.unichristus.domain.dto.book.BookLowDTO;
import br.edu.unichristus.service.BookService.BookApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class BookApiController {
    @Autowired
    private BookApiService service;

    // ROTAS PARA LIVROS DA API DO GOOGLE
    @Operation(summary = "Busca todos os livros da API do Google", tags = "Book (API externa)")
    @GetMapping("/external/all") //ex de busca: http://localhost:8081/api/v1/book/external/all
    public List<BookLowDTO> findAllApi() {
        return service.findAllApi();
    }

    @Operation(summary = "Busca livros da API do Google por título", tags = "Book (API externa)")
    @GetMapping("/external/title/{title}") //ex de busca: http://localhost:8081/api/v1/book/externa/title/world
    public List<BookLowDTO> findByTitle(@PathVariable String title) {
        return service.findByTitle(title);
    }

    @Operation(summary = "Busca livros da API do Google por autor", tags = "Book (API externa)")
    @GetMapping("/external/author/{author}") //ex de busca: http://localhost:8081/api/v1/book/external/author/cury
    public List<BookLowDTO> findByAuthor(@PathVariable String author) {
        return service.findByAuthor(author);
    }

    @Operation(summary = "Busca livros da API do Google por assunto", tags = "Book (API externa)")
    @GetMapping("/external/subject/{subject}") //ex de busca: http://localhost:8081/api/v1/book/external/subject/criminal
    public List<BookLowDTO> findBySubject(@PathVariable String subject) {
        return service.findBySubject(subject);
    }
}
