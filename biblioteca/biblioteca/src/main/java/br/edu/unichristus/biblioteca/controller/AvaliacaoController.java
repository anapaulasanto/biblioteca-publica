package br.edu.unichristus.biblioteca.controller;


import br.edu.unichristus.biblioteca.domain.dto.AvaliacaoDTO;
import br.edu.unichristus.biblioteca.domain.dto.AvaliacaoLowDTO;
import br.edu.unichristus.biblioteca.domain.dto.TopAvaliadosDTO;
import br.edu.unichristus.biblioteca.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("biblioteca-publica/avaliacao")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService service;

    @PostMapping
    public void avaliar(@RequestBody AvaliacaoDTO avaliacao) {
        service.avaliar(avaliacao);
    }

    @GetMapping("/all")
    public List<AvaliacaoLowDTO> exibirAvaliacoes(){
        return service.exibirAvaliacoes();
    }

    @GetMapping("/top-avaliados")
    public List<TopAvaliadosDTO> mostrarTopAvaliados(@RequestParam int qtd) {
        return service.buscarTopAvaliados(qtd);
    }
}
