package br.edu.unichristus.controller;

import br.edu.unichristus.domain.dto.book.BookDTO;
import br.edu.unichristus.domain.dto.book.BookLowDTO;
import br.edu.unichristus.domain.dto.rental.RentalDTO;
import br.edu.unichristus.domain.dto.review.ReviewDTO;
import br.edu.unichristus.domain.model.Book;
import br.edu.unichristus.service.BookService;
import br.edu.unichristus.service.RentalService;
import br.edu.unichristus.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {
    @Autowired
    private BookService service;

    // ROTAS PARA LIVROS SALVOS NO BANCO
    @PostMapping
    public BookDTO save(@RequestBody BookDTO book){
        return service.save(book);
    }

    @PutMapping("/{id}")
    public BookDTO update(@PathVariable Long id, @RequestBody BookDTO book){
        return service.save(book);
    }

    @GetMapping("/all")
    public List<BookDTO> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Book findById(@PathVariable Long id){
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/{id}/reviews")
    public List<ReviewDTO> getReviewByBook(@PathVariable Long id) {
        return reviewService.findReviewsByBookId(id);
    }

    @Autowired
    private RentalService rentalService;

    @GetMapping("/{id}/rentals")
    public List<RentalDTO> getRentalByBook(@PathVariable Long id) {
        return rentalService.findRentalsByBookId(id);
    }


    // ROTAS PARA LIVROS DA API DO GOOGLE
    @GetMapping("/external/all") //ex de busca: http://localhost:8081/api/v1/book/external/all
    public List<BookLowDTO> findAllApi() {
        return service.findAllApi();
    }

    @GetMapping("/external/search/title") //ex de busca: http://localhost:8081/api/v1/book/external/search/title?title=love
    public List<BookLowDTO> findByTitle(@RequestParam String title) {
        return service.findByTitle(title);
    }

    @GetMapping("/external/search/author") //ex de busca: http://localhost:8081/api/v1/book/external/search/author?author=cury
    public List<BookLowDTO> findByAuthor(@RequestParam String author) {
        return service.findByAuthor(author);
    }

    @GetMapping("/external/search/subject") //ex de busca: http://localhost:8081/api/v1/book/external/search/subject?subject=criminal
    public List<BookLowDTO> findBySubject(@RequestParam String subject) {
        return service.findBySubject(subject);
    }
}
