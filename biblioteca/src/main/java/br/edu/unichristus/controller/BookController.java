package br.edu.unichristus.controller;

import br.edu.unichristus.domain.dto.book.BookDTO;
import br.edu.unichristus.domain.model.Book;
import br.edu.unichristus.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {
    @Autowired
    private BookService service;

    @PostMapping
    public BookDTO save(@RequestBody BookDTO book){
        return service.save(book);
    }

    @PutMapping
    public BookDTO update(@RequestBody BookDTO book){
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
}
