package br.edu.unichristus.biblioteca.service;


import br.edu.unichristus.biblioteca.client.googlebooks.AcessInfo;
import br.edu.unichristus.biblioteca.client.googlebooks.GoogleResponse;
import br.edu.unichristus.biblioteca.client.googlebooks.VolumeInfo;
import br.edu.unichristus.biblioteca.domain.dto.LivroDTO;
import br.edu.unichristus.biblioteca.domain.dto.LivroLowDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {

    @Value("${endpoint.livros}")
    private String endpoint;

    @Value("${api.key}")
    private String apiKey;

    public List<LivroLowDTO> buscarLivros(String titulo){
        String url = endpoint + titulo + "&filter=full" + "&key=" + apiKey;

        RestTemplate restTemplate = new RestTemplate();

        GoogleResponse response = restTemplate.getForObject(url, GoogleResponse.class);

        return response.getItems().stream().map(item -> {

            AcessInfo acessInfo = item.getAcessInfo();
            boolean pdfDisponivel = acessInfo != null && acessInfo.getPdf().isAvaliable();
            String linkPdf = pdfDisponivel ? acessInfo.getPdf().getAcsTokenLink() : null;
            var volumeInfo = item.getVolumeInfo();

            return new LivroLowDTO(volumeInfo.getImageLinks().getThumbnail(),
                                    volumeInfo.getTitle(),
                                    volumeInfo.getAuthors(),
                                    volumeInfo.getPublishedDate(),
                                    linkPdf);
        }).collect(Collectors.toList());
    }
}

