package br.edu.unichristus.controller.BookController;

import br.edu.unichristus.domain.dto.book.BookLowDTO;
import br.edu.unichristus.service.BookService.BookApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class BookApiController {
    @Autowired
    private BookApiService service;

    // ROTAS PARA LIVROS DA API DO GOOGLE
    @GetMapping("/external/all") //ex de busca: http://localhost:8081/api/v1/book/external/all
    public List<BookLowDTO> findAllApi() {
        return service.findAllApi();
    }

    @GetMapping("/external/title/{title}") //ex de busca: http://localhost:8081/api/v1/book/externa/title/world
    public List<BookLowDTO> findByTitle(@PathVariable String title) {
        return service.findByTitle(title);
    }

    @GetMapping("/external/author/{author}") //ex de busca: http://localhost:8081/api/v1/book/external/author/cury
    public List<BookLowDTO> findByAuthor(@PathVariable String author) {
        return service.findByAuthor(author);
    }

    @GetMapping("/external/subject/{subject}") //ex de busca: http://localhost:8081/api/v1/book/external/subject/criminal
    public List<BookLowDTO> findBySubject(@PathVariable String subject) {
        return service.findBySubject(subject);
    }
}
