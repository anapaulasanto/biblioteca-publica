package br.edu.unichristus.service.BookService;

import br.edu.unichristus.domain.dto.book.BookDTO;
import br.edu.unichristus.domain.model.Book;
import br.edu.unichristus.exception.CommonsException;
import br.edu.unichristus.repository.BookRepository;
import br.edu.unichristus.repository.CategoryRepository;
import br.edu.unichristus.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;
    @Autowired
    private CategoryRepository categoryRepository;

    public BookDTO save(BookDTO bookDTO) {
        if (bookDTO.getCategoryId() == null) {
            throw new CommonsException(HttpStatus.NOT_FOUND,
                    "unichristus.book.categoryid.save.notfound",
                    "Categoria do livro é um campo obrigatório!");
        }

        var bookEntity = MapperUtil.parseObject(bookDTO, Book.class);

        // salvando categoria na entidade livro
        var category = categoryRepository.findById(bookDTO.getCategoryId())
                .orElseThrow(() -> new CommonsException(HttpStatus.NOT_FOUND,
                        "unichristus.category.notfound",
                        "Categoria não encontrada!"));

        bookEntity.setCategory(category);

        var savedBook = repository.save(bookEntity);
        BookDTO dto = MapperUtil.parseObject(savedBook, BookDTO.class);
        dto.setCategoryId(savedBook.getCategory().getId());

        return dto;
    }

    public List<BookDTO> findAll() {
        var listBooks = repository.findAll();
        return MapperUtil.parseListObjects(listBooks, BookDTO.class);
    }

    public Book findById(Long id) {
        var bookEntity = repository.findById(id);

        if (bookEntity.isEmpty()) {
            throw new CommonsException(HttpStatus.NOT_FOUND,
                    "unichristus.book.findbyid.notfound",
                    "Livro não encontrado!");
        }
        return repository.findById(id).get();
    }

    public void delete(Long id) {
        var categoryEntity = repository.findById(id);

        if (categoryEntity.isEmpty()) { // trata exceção de não encontrar o id a ser deletado
            throw new CommonsException(HttpStatus.NOT_FOUND,
                    "unichristus.book.delete.notfound",
                    "Livro não encontrado!");
        }
        repository.deleteById(id);
    }

    //Listar livros de uma mesma categoria
    public List<BookDTO> findBooksByCategoryId(Long categoryId) {
        List<Book> books = repository.findByCategoryId(categoryId);

        if (books.isEmpty()) {
            throw new CommonsException(HttpStatus.NOT_FOUND,
                    "unichristus.book.findbooksbycategoryid.notfound",
                    "Categoria de livros não encontrada!");
        }
        return MapperUtil.parseListObjects(books, BookDTO.class);
    }
}