package br.edu.unichristus.service.BookService;

import br.edu.unichristus.api.googleBooks.AccessInfo;
import br.edu.unichristus.api.googleBooks.GoogleResponse;
import br.edu.unichristus.api.googleBooks.VolumeInfo;
import br.edu.unichristus.domain.dto.book.BookLowDTO;
import br.edu.unichristus.exception.CommonsException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookApiService {
    // pega a variavel endpoint.books que salvei no application.properties, que é o endpoint da API do Google
    @Value("${endpoint.books}")
    private String endpoint;
    // pega a minha chave da API que salvei também no properties
    @Value("${api.key}")
    private String apiKey;

    RestTemplate restTemplate = new RestTemplate(); // cria instancia de RestTemplate (classe do spring q faz chamadas à API externa, tipo o axios do React)

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
