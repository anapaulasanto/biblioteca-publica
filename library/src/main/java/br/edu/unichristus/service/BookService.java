package br.edu.unichristus.service;

import br.edu.unichristus.api.googleBooks.AccessInfo;
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
import org.springframework.dao.DataIntegrityViolationException;
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

    RestTemplate restTemplate = new RestTemplate(); // cria instancia de RestTemplate (classe do spring q faz chamadas à API externa, tipo o axios do React)

    public BookDTO save(BookDTO bookDTO) {
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


    //ENDPOINTS DA API DO GOOGLE
    public List<BookLowDTO> findAllApi() {
        String url = endpoint + "-harry" + "&key=" + apiKey; // armazena em url o endpoint completo pra fazer a requisição à API
        GoogleResponse response = restTemplate.getForObject(url, GoogleResponse.class);// faz uma requisição do tipo GET pra "url", esperando receber um dado do tipo GoogleResponse

        return response.getItems().stream().map(items -> { // se a resposta da api nao for null, faz um map na classe que armazena as respostas da API
            VolumeInfo volumeInfo = items.getVolumeInfo();
            AccessInfo accessInfo = items.getAccessInfo();
            String linkPdf = null;

            if (accessInfo != null && accessInfo.getPdf() != null && accessInfo.getPdf().isAvailable()) {
                linkPdf = accessInfo.getPdf().getAcsTokenLink();
            }

            return new BookLowDTO(
                    volumeInfo.getTitle(),
                    volumeInfo.getAuthors(),
                    volumeInfo.getPublishedDate(),
                    volumeInfo.getDescription(),
                    volumeInfo.getCategories(),
                    linkPdf
            );
        }).collect(Collectors.toList()); // transforma em uma lista
    }

    public List<BookLowDTO> findByTitle(String title) {
        String url = endpoint + title + "&key=" + apiKey;
        GoogleResponse response = restTemplate.getForObject(url, GoogleResponse.class);

        if (response.getItems() == null) { // tratamento pra quando não achar resposta pro titulo
            throw new CommonsException(HttpStatus.NOT_FOUND,
                    "unichristus.book.findbytitle.apiresponse.null",
                    "Nenhum livro encontrado para o título informado.");
        }

        return response.getItems().stream().map(items -> {
            VolumeInfo volumeInfo = items.getVolumeInfo();
            AccessInfo accessInfo = items.getAccessInfo();
            String linkPdf = null;

            if (accessInfo != null && accessInfo.getPdf() != null && accessInfo.getPdf().isAvailable()) {
                linkPdf = accessInfo.getPdf().getAcsTokenLink();
            }

            return new BookLowDTO(
                    volumeInfo.getTitle(),
                    volumeInfo.getAuthors(),
                    volumeInfo.getPublishedDate(),
                    volumeInfo.getDescription(),
                    volumeInfo.getCategories(),
                    linkPdf
            );
        }).collect(Collectors.toList());
    }

    public List<BookLowDTO> findByAuthor(String author) {
        String url = endpoint + "inauthor:" + author + "&key=" + apiKey;
        GoogleResponse response = restTemplate.getForObject(url, GoogleResponse.class);

        if (response.getItems() == null) {
            throw new CommonsException(HttpStatus.NOT_FOUND,
                    "unichristus.book.findbyauthor.apiresponse.null",
                    "Nenhum livro encontrado para o autor informado.");
        }

        return response.getItems().stream().map(items -> {
            VolumeInfo volumeInfo = items.getVolumeInfo();
            AccessInfo accessInfo = items.getAccessInfo();
            String linkPdf = null;

            if (accessInfo != null && accessInfo.getPdf() != null && accessInfo.getPdf().isAvailable()) {
                linkPdf = accessInfo.getPdf().getAcsTokenLink();
            }

            return new BookLowDTO(
                    volumeInfo.getTitle(),
                    volumeInfo.getAuthors(),
                    volumeInfo.getPublishedDate(),
                    volumeInfo.getDescription(),
                    volumeInfo.getCategories(),
                    linkPdf
            );
        }).collect(Collectors.toList());
    }

    public List<BookLowDTO> findBySubject(String subject) {
        String url = endpoint + "subject:" + subject + "&key=" + apiKey;
        GoogleResponse response = restTemplate.getForObject(url, GoogleResponse.class);

        if (response.getItems() == null) {
            throw new CommonsException(HttpStatus.NOT_FOUND,
                    "unichristus.book.findbysubject.apiresponse.null",
                    "Nenhum livro encontrado para o assunto informado.");
        }

        return response.getItems().stream().map(items -> {
            VolumeInfo volumeInfo = items.getVolumeInfo();
            AccessInfo accessInfo = items.getAccessInfo();
            String linkPdf = null;

            if (accessInfo != null && accessInfo.getPdf() != null && accessInfo.getPdf().isAvailable()) {
                linkPdf = accessInfo.getPdf().getAcsTokenLink();
            }

            return new BookLowDTO(
                    volumeInfo.getTitle(),
                    volumeInfo.getAuthors(),
                    volumeInfo.getPublishedDate(),
                    volumeInfo.getDescription(),
                    volumeInfo.getCategories(),
                    linkPdf
            );
        }).collect(Collectors.toList());
    }

}