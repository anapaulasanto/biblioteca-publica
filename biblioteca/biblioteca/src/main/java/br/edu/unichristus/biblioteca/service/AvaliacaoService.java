package br.edu.unichristus.biblioteca.service;


import br.edu.unichristus.biblioteca.domain.dto.AvaliacaoDTO;
import br.edu.unichristus.biblioteca.domain.dto.AvaliacaoLowDTO;
import br.edu.unichristus.biblioteca.domain.model.Avaliacao;
import br.edu.unichristus.biblioteca.repository.AvaliacaoRepository;
import br.edu.unichristus.biblioteca.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository repository;

    public void avaliar(AvaliacaoDTO dto) {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setIdVolume(dto.getIdVolume());
        avaliacao.setComentario(dto.getComentario());
        avaliacao.setNota(dto.getNota());

        repository.save(avaliacao);
    }

    public List<AvaliacaoLowDTO> exibirAvaliacoes() {
        var listaAvaliacoes = repository.findAll();
        return MapperUtil.parseListObjects(listaAvaliacoes, AvaliacaoLowDTO.class);
    }

    public List<TopAvaliadosDTO> buscarTopAvaliados(int limit) {
        // 🔹 Pega todas as avaliações do banco de dados
        List<Avaliacao> todas = repository.findAll();

        // 🔹 Agrupa as avaliações por idVolume (cada livro pode ter várias avaliações)
        Map<String, List<Avaliacao>> avaliacoesAgrupadas = todas.stream()
                .collect(Collectors.groupingBy(Avaliacao::getIdVolume));

        // 🔹 Cria um mapa para guardar a média de cada livro (idVolume -> média)
        Map<String, Double> mediasPorLivro = new HashMap<>();

        // 🔹 Para cada grupo (livro), calcula a média da nota
        for (Map.Entry<String, List<Avaliacao>> entry : avaliacoesAgrupadas.entrySet()) {
            String idVolume = entry.getKey(); // ID do livro
            List<Avaliacao> avaliacoes = entry.getValue(); // Lista de avaliações desse livro

            // 🔹 Calcula a média de notas do livro usando stream
            double media = avaliacoes.stream()
                    .mapToLong(Avaliacao::getNota) // transforma cada avaliação em um long (nota)
                    .average() // calcula a média
                    .orElse(0.0); // se não tiver nota, retorna 0.0

            // 🔹 Armazena a média no mapa (idVolume -> média)
            mediasPorLivro.put(idVolume, media);
        }

        // 🔹 Ordena os livros pela média (maior para menor), limita ao número pedido
        return mediasPorLivro.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed()) // ordena decrescente
                .limit(limit) // pega só os 'limit' primeiros
                .map(e -> new TopAvaliadosDTO(e.getKey(), e.getValue())) // transforma em DTO
                .collect(Collectors.toList()); // retorna como lista
    }
    Chat, voce pode me explicar detalhadamente cada metodo posto nesse codigo e o que faz? Não estou conseguindo compreender alguns metodos e nem o codigo

}
