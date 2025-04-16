package br.edu.unichristus.service;

import br.edu.unichristus.domain.dto.book.BookDTO;
import br.edu.unichristus.domain.dto.user.UserDTO;
import br.edu.unichristus.domain.dto.user.UserLowDTO;
import br.edu.unichristus.domain.dto.user.UserRolesDTO;
import br.edu.unichristus.domain.model.Book;
import br.edu.unichristus.domain.model.User;
import br.edu.unichristus.exception.CommonsException;
import br.edu.unichristus.repository.BookRepository;
import br.edu.unichristus.repository.UserRepository;
import br.edu.unichristus.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public BookDTO save(BookDTO BookDTO){
        var bookEntity = MapperUtil.parseObject(BookDTO, Book.class);
        var savedBook = repository.save(bookEntity);
        return MapperUtil.parseObject(savedBook, BookDTO.class);
    }

    public List<BookDTO> findAll(){
        var listBooks = repository.findAll();
        return MapperUtil.parseListObjects(listBooks, BookDTO.class);
    }

    public Book findById(Long id){
        var bookEntity = repository.findById(id);
        if(bookEntity.isEmpty()){
            throw new CommonsException(HttpStatus.NOT_FOUND,
                    "unichristus.book.findbyid.notfound",
                    "Livro não encontrado!");
        }

        return repository.findById(id).get();
    }

    public void delete(Long id){
        repository.deleteById(id);
    }


}