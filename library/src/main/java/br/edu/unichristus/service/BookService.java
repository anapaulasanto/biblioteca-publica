package br.edu.unichristus.service;

import br.edu.unichristus.api.googleBooks.GoogleResponse;
import br.edu.unichristus.api.googleBooks.VolumeInfo;
import br.edu.unichristus.domain.dto.book.BookDTO;
import br.edu.unichristus.domain.dto.book.BookLowDTO;
import br.edu.unichristus.domain.model.Book;
import br.edu.unichristus.exception.CommonsException;
import br.edu.unichristus.repository.BookRepository;
import br.edu.unichristus.repository.CategoryRepository;
import br.edu.unichristus.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;
    @Autowired
    private CategoryRepository categoryRepository;

    // pega a variavel endpoint.books que salvei no application.properties, que é o endpoint da API do Google
    @Value("${endpoint.books}")
    private String endpoint;
    // pega a minha chave da API que salvei também no properties
    @Value("${api.key}")
    private String apiKey;

    public BookDTO save(BookDTO bookDTO){
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

    public List<BookLowDTO> findByTitle(String title){
        String url = endpoint + title + "&key=" + apiKey; // armazena em url o endpoint completo pra fazer a requisição à API
        RestTemplate restTemplate =new RestTemplate(); // cria instancia de RestTemplate (classe do spring q faz chamadas à API externa, tipo o axios do React)
        GoogleResponse response = restTemplate.getForObject(url, GoogleResponse.class);// faz uma requisição do tipo GET pra "url", esperando receber um dado do tipo GoogleResponse

        if (response.getItems() == null) { // tratamento pra quando a resposta da api for null
            throw new CommonsException(HttpStatus.NOT_FOUND,
                    "unichristus.book.findbytitle.apiresponse.null",
                    "Nenhum livro encontrado para o título informado.");
        }

        return response.getItems().stream().map(items -> { // se a resposta da api nao for null, faz um map na classe que armazena as respostas da API
            VolumeInfo volumeInfo = items.getVolumeInfo();

            return new BookLowDTO(
                    items.getId(),
                    volumeInfo.getTitle(),
                    volumeInfo.getAuthors(),
                    volumeInfo.getPublishedDate(),
                    volumeInfo.getDescription(),
                    volumeInfo.getCategories()
            );
        }).collect(Collectors.toList()); // transforma em uma lista
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    //Listar livros de uma mesma categoria
    public List<BookDTO> findBooksByCategoryId(Long categoryId) {
        List<Book> books = repository.findByCategoryId(categoryId);
        return MapperUtil.parseListObjects(books, BookDTO.class);
    }

}